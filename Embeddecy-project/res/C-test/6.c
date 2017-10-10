#ifndef F_CPU
#define F_CPU 16000000UL // 16 MHz clock speed
#endif

#define RS 2
#define EN 3

#define HEAT_PWM			OCR0
#define FAN_PWM				OCR2

#define DOWN_BORDER 0
#define UP_BORDER 255

#define MAX_TEMP 75
#define MIN_TEMP 1

#define ADC_VREF_TYPE ((0<<REFS1) | (1<<REFS0) | (0<<ADLAR)) // VRef: AREF pin
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <stdio.h>
#include <avr/pgmspace.h>


volatile uint16_t adc_data;

// от 19x4 - 1 = 75 [0:74]
// от 0 до 74 градусов
// переделать               !!!!!
const int mass[] = {
	570, 570, 569, 569, 568, 568, 567, 567, 566, 566, 566, 565, 565, 565, 564, 564, 563, 562, 562,
	561, 560, 560, 559, 558, 556, 556, 555, 556, 556, 554, 553, 552, 551, 550, 549, 549, 548, 547,
	546, 545, 543, 542, 541, 539, 539, 537, 536, 534, 533, 532, 531, 529, 528, 526, 525, 523, 522,
	522, 520, 519, 517, 516, 514, 512, 510, 508, 506, 504, 503, 502, 500, 496, 494, 492, 490
};

int integrMemory[] = {0,0,0,0,0,0,0,0,0,0};

int Error = 0;
int Error_old = 0;
int Integr = 0;
double Kp = 40;
double Ki = 0.5;
double Kd = 0;
int P = 0;
int I = 0;
int D = 0;
double PID = 1;
int time = 1;

int index = 0;


int getTemperature(int value)
{
	int result = 100;
	
	for(int i = 0; i < sizeof ( mass ) / sizeof ( *mass ); i++)
	{
		if(value > mass[i])
		{
			result = i;
			break;
		}
		
	}
	return result + 1;
}

int getLength(int * mass)
{	
	return sizeof(mass)/sizeof(*mass);
}

void workWithTerm(int termGoal, int termNow){
	
	// несоответсвие температур
	Error = termGoal - termNow;
	// если мы не вышли за границу, то работаем
	if ( (DOWN_BORDER <= PID) && (PID <= UP_BORDER ))
	{
		index++;
		if(index > getLength(integrMemory))
		{
			index = 0;
		}
		
		Integr += Error;
		Integr -= integrMemory[index];
		integrMemory[index] = Error;
	}
	
	// формула PID регулирования
	P = Kp * Error;
	I = Ki * Integr * time;
	D = Kd * (Error - Error_old)/time;
	PID = P + I + D;
	
	// устанавливаем границы
	if (PID > UP_BORDER)
	{
		PID = UP_BORDER;
	} else if(PID < DOWN_BORDER)
	{
		PID = DOWN_BORDER;
	}
	
	Error_old = Error;
}

void initPWM(){
	
	/* сделать "1" бит 5 в DDRF
	это ножка PD5(OC1A)-PWM Timer1 OUT
	ножку PD5 назначили выходом */
	DDRF |= (1<<6);
	
	// Настройка TIMER1 ATmega16 для генерации ШИМ
	// stop Timer
	TCCR1B = 0x00;

	/* prescale:1  clock 7.37 MHz
	делитель 1 - т.е. таймер считает с частотой такта - частотой кварца.

	Режим 7:      PWM 10bit fast, TOP=0x03FF
	Частота ШИМ:      7200 Hz  */

	TCNT1H = 0xFC; // 11111100
	TCNT1L = 0x01; // 00000001

	/* значение в 16 битном регистре OCR1A определяет величину генерируемого ШИМ сигнала на ножке PD5 - если поделить ее  на 10.24 то получим величину ШИМ  PWM в процентах*/

	OCR1AH = 0x03; // PWM(PD5) = OCR1A / 10.24
	OCR1AL = 0xFF; // 0x03FF это число 1023

	/* регистр OCR1A состоит из двух 8-ми битных регистров OCR1AH и OCR1AL запись в них нужно проводить в указанной последовательности! */

	OCR1BH = 0x03;
	OCR1BL = 0xFF;

	ICR1H = 0x03;
	ICR1L = 0xFF;

	TCCR1A = 0x83; // 10000011

	TCCR1B = 0x09; // 00001001 - start Timer
}

void MyPWM(int pwm_val)
{
	// pwm_val - это число от 0 до 1023
	// PWM на PD5 будет примерно  OCR1A / 10.24  (в %)
	OCR1AH = (char)(pwm_val>>8);
	OCR1AL = (char)pwm_val;
	
	_delay_ms(100);
}

void MyPWM2(int pwm_val)
{
	if(pwm_val)
	{
		const int k_pause = 500;
		
		sei();
		int saveReg = DDRF;
		int savePort = PORTF;
		
		//DDRF |= (1<<6);
		
		int pause = UP_BORDER - pwm_val;
		
		//включаем нагрев
		PORTF |= (1<<6);
		
		LCDGotoXY(11, 1);
		send_char_to_LCD('#');
		printInt((int)pwm_val);
		send_char_to_LCD(" ");
		
		for(int i = 0; i < k_pause * pwm_val / UP_BORDER ; i++)
		{
			_delay_ms(5);
		}
		
		// выключаем нагрев
		PORTF ^= (1<<6);
		for(int i = 0; i < k_pause * pause / UP_BORDER; i++)
		{
			_delay_ms(5);
		}
		
		DDRF = saveReg;
		PORTF = savePort;
		
		cli();
	}
}

uint8_t ch;
unsigned int adc_value; // Variable to hold ADC result
//////////// функции для работы с LCD ///////////////////////////////////////////

void send_cmd_to_LCD(uint8_t command)//command -кодкомандыдляотправкина LCD
{
	//устанавливаем ножки МК(PC7,PC6,PC5,PC4) согласно старшим 4 битам команды
	PORTC = (command& 0xF0);
	PORTC &= ~(1<<RS);//PC0(RS) установили 0, т.е. передаем команду(не данные)
	PORTC |= (1<<EN);//PC1(E) синхронизация
	_delay_us(2);
	PORTC &= ~(1<<EN);//PC1(E) синхронизация
	//устанавливаем ножки МК(PC7,PC6,PC5,PC4) согласно с младшими 4мя битами команды
	PORTC = ((command& 0x0F) << 4);
	PORTC&=~(1<<RS);//PC0(RS) установили 0, т.е. передаем команду
	PORTC|= (1<<EN);//PC1(E) синхронизация
	_delay_us(2);
	PORTC&=~(1<<EN);//PC1(E) синхронизация
	_delay_us(50);//операция длится больше 43 микросекунд согласно спецификации
}

void send_char_to_LCD(uint8_t data)//в data символдляотправкина LCD
{
	//устанавливаем ножки МК(PC7,PC6,PC5,PC4) согласно старшим 4 битам команды
	PORTC = (data& 0xF0);
	PORTC |= (1<<RS);//PC0(RS) установили 1, т.е. передаем команду
	PORTC |= (1<<EN);//PC1(E) синхронизация
	_delay_us(2);
	PORTC &= ~(1<<EN);//PC1(E) синхронизация
	
	//устанавливаем ножки МК(PC7,PC6,PC5,PC4) согласно с младшими 4мя битами команды
	PORTC = ((data& 0x0F) << 4);
	PORTC |= (1<<RS);//PC0(RS) установили 1, т.е. передаем данные
	PORTC |= (1<<EN);//PC1(E) синхронизация
	_delay_us(2);
	PORTC &=~ (1<<EN);//PC1(E) синхронизация(забирай)
	_delay_us(50);//операция длится больше 43 микросекунд согласно спецификации
	_delay_ms(50);//задержка для наглядного отображения
}

void lcd_clear()
{
	send_cmd_to_LCD(0x01);   //отправляем код команды очистки экрана на LCD
	_delay_ms(3);   //ждем больше 1.53ms согласно спецификации
}

void secondline()
{
	//при режиме двух строк
	//Первая строка (DDRAM) начинается с адреса 0x00 и оканчивается адресом 0x27
	//Вторая строка (DDRAM) начинается с адреса 0x40 и оканчивается адресом 0x67
	//устанавливаем курсор(AC) по адресу 0x40(hex), т.е. на начало второй строки
	send_cmd_to_LCD(0xC0);//1100 0000(bin)
	_delay_ms(3);
}

void LCDGotoXY(uint8_t x, uint8_t y)
{
	if (x >= 20)
	return;
	
	switch(y)
	{
		case 0:
		break;
		case 1:
		x|=0b01000000;
		break;
		case 2:
		x+=0x14;
		break;
		case 3:
		x+=0x54;
		break;
	}
	x |= 0b10000000;
	send_cmd_to_LCD(x);
}

void send_str_from_data_to_lcd(char *str )
{
	while(*str)//пока есть что отправлять
	{
		send_char_to_LCD(*str++);//отправляемсимвол
		_delay_ms(1);
	}
}

void lcd_init()
{
	DDRC = 0xFF;//порт С подключен к дисплею
	PORTC = 0;
	//Power on
	//send_cmd_to_LCD(0x02);//длядисплеямарки LM016L
	//ждем больше 30ms согласно спецификации
	_delay_ms(60);

	//6)FUNCTION SET //Определение параметров развертки, ширины шины данных
	//lcdcmd(0x20);  //0010 0000(bin) 4 bit, 1 строка, 5*7 шрифт
	//lcdcmd(0x2C);  //0010 1100(bin) 4 bit, 2 строка, 5*11 шрифт
	send_cmd_to_LCD(0x28);  //0010 1000(bin) 4 bit, 2 строка, 5*7 шрифт

	_delay_us(50);//по спецификации больше 39микросекунд

	//4)DISPLAY ON/OFF CONTROL//Выборрежимаотображения
	//0000 1100 включение экрана, выключение отображения курсора, курсор не мигает
	send_cmd_to_LCD(0x0C);
	//0000 1101 включение экрана, выключение отображения курсора, курсор мигает
	//lcdcmd(0x0D);
	//0000 1111 включение экрана, включение отображения курсора, курсор мигает
	//send_cmd_to_LCD(0x0F);
	
	_delay_us(50);//по спецификации больше 39микросекунд

	lcd_clear();//очистка экрана

	//3)EntryModeSet//Выбор направления сдвига курсора или экрана
	//0000 0111 -> инкрементирование, сдвиг всего экрана включен(влево)
	//lcdcmd(0x07);
	//0000 0101(bin) -> декрементирование, сдвиг всего экрана включен(вправо)
	//lcdcmd(0x05);
	//0000 0110(bin) -> инкрементирование, сдвиг всего экрана отключен
	send_cmd_to_LCD(0x06);

	_delay_us(50);//по спецификации больше 39микросекунд
	//initialization end
}


void init()
{
	
	TCCR1A = 0x00;
	TCCR1B = 0xC2;	//2MHz «измерительныйгенератор» + noise canceller + rising_edge
	TCNT1 = 0x00;
	ICR1 = 0x00;

	TCCR3A = 0x00;		//for delay
	TCCR3B = 0x05;
	TCNT3 = 0x00;
	OCR3A = 0xFF;
	
	EICRA = 0xFF;	//rising edge on int 0-3
	EICRB = 0xFF; //rising edge on int 4-7
	EIMSK = (1<<INT0)|(1<<INT1)|(1<<INT2)|(1<<INT4)|(1<<INT5);
	EIFR = (1<<INT0)|(1<<INT1)|(1<<INT2)|(1<<INT4)|(1<<INT5);

	TIMSK = (1<<TICIE1) | (1<<TOIE1) | (1<<TOIE3);
	
}

int min(int x1, int x2)
{
	if(x1 > x2)
	return x2;
	else
	return x1;
}

int max(int x1, int x2)
{
	if(x1 < x2)
	return x2;
	else
	return x1;
}

void printInt(int printV)
{
	if(printV){
		printInt(printV / 10);
		send_char_to_LCD(printV % 10 + '0');
	}
}

/*
ISR(ADC_vect)
{
volatile uint16_t adc_value;
//volatile uint16_t test = 9999;
adc_value = ADC;
}
*/


int changeGoalTerm(int valuePort, int variable, int valueEkran)
{
	int multy = 1;
	
	for(int i = 0 ; i < 3; i++)
	{
		valueEkran += ((valuePort >> i) & 1 ) * variable * multy;
		multy*=10;
	}
	
	return max(min(MAX_TEMP, valueEkran),MIN_TEMP);
}
/*
int getTempare(int value)
{
return mass[value];
}
*/


void initPWM2()
{
	TCCR1A=(1<<COM1A1)|(1<<WGM10); //На выводе OC1A единица, когда OCR1A==TCNT1, восьмибитный ШИМ
	TCCR1B=(1<<CS10);		 //Делитель= /1
	OCR1A=0x00;			//Начальная яркость нулевая
}

void MyPWM3(int val)
{
	OCR1A = val;
	_delay_ms(200);
}
//interpolating polynomial{(556,28),(554,30),(553,31),(552,32),(551,33),(547,38),(545,40),(539,44),(534,48),(532,50),(526,54),(522,58),(519,60),(504,65),(500,68)(494,70)}
int main(void)
{
	//	init();

	ADCSRA = (1<<ADEN) | (1<<ADPS2) | (1<<ADPS0);
	// ADEN: Set to turn on ADC , by default it is turned off
	//ADPS2: ADPS2 and ADPS0 set to make division factor 32
	ADMUX = (0x01) | (ADC_VREF_TYPE); // ADC input channel set to PF1
	
	lcd_init();
	lcd_clear();
	
	initPWM2();
	
	//DDRA = 0x00;
	
	// ожидаемое значение температуры
	int termGoal = 1;
	
	//DDRB = 0x00;//input
	
	
	DDRE = 0;
	PORTE = 0;

	DDRD = 0;
	PORTD = 0;
	
	DDRB = 0x00;//B0 - input(button)
	PORTB = 0;
	
	//	uint8_t t = 0;
	//uint8_t tNow = 0;
	//	DDRF = 0b00100000;
	//PORTF= 0b00100000;
	//PORTA = 0;
	sei();
	
	send_str_from_data_to_lcd("T GOAL = ");
	
	secondline();
	
	send_str_from_data_to_lcd("T CUR  = ");
	
	
	cli();
	
	
	int adc_value;
	int termNow;
	
	
	while(1) //infinite loop
	{
		ADCSRA |= (1<<ADSC); // Start conversion
		while (ADCSRA & (1<<ADSC)); // wait for conversion to complete
		
		// получаем текущее значение датчика температуры
		adc_value = ADC;
		
		// переводим значение в градусы Цельсия
		termNow = getTemperature(adc_value);
		
		// нагреваем, если требуется
		workWithTerm(termGoal, termNow);
		
		
		//MyPWM(PID);
		MyPWM2(PID);
		
		// изменение значения целевой температуры
		termGoal = changeGoalTerm(PIND, 1, termGoal);
		termGoal = changeGoalTerm(PINE, -1, termGoal);
		
		sei();
		
		// выводим в первую строку значение целевой температуры !! не трогать !!
		LCDGotoXY(9, 0);
		//printInt(termGoal);
		printInt(termGoal);
		send_char_to_LCD(' ');
		
		// выводим в первую строку текущее значение температуры !! не трогать !!
		LCDGotoXY(9, 1);
		printInt(termNow);
		send_char_to_LCD(' ');
		
		cli();
		
		//_delay_ms(500);
		
	}
}