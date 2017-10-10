# 1 "D:\\eclipse-dsl-luna-SR2-win32-x86_64\\git\\mcublocks\\Embeddecy-project\\res\\event-test\\eventtest4.embc"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "D:\\eclipse-dsl-luna-SR2-win32-x86_64\\git\\mcublocks\\Embeddecy-project\\res\\event-test\\eventtest4.embc"



task task1 {

 message mes1(int x);

 body {
  int counter = 0;
  for (;;) {
   if (hasmessage(mes1)) {
    accept(mes1);
    counter = mes1.x;
   }
   if ((GPIO_ReadInputDataBit(GPIOA, GPIO_Pin_0))&&(counter == 0)) {
    counter = 1;
    send task2.mes1(1);
   }
   if ((GPIO_ReadInputDataBit(GPIOA, GPIO_Pin_0))&&(counter == 2)) {
    counter = 1;
    send task2.mes1(2);
   }
  }
 }
}

task task2 {

 message mes1(int x);

 body {
  for (;;) {
   accept(mes1);
   if (mes1.x == 1) {
    GPIO_SetBits(GPIOE, GPIO_Pin_8);
    delay_ms(2000);
    send task3.mes1(1);
   }
   if (mes1.x == 2) {
    GPIO_ResetBits(GPIOE, GPIO_Pin_9);
    delay_ms(2000);
    send task3.mes1(2);
   }
  }
 }
}


task task3 {

 message mes1(int x);

 body {
  for (;;) {
   accept(mes1);
   if (mes1.x == 1) {
    GPIO_SetBits(GPIOE, GPIO_Pin_9);
    delay_ms(2000);
    send task1.mes1(2);
   }
   if (mes1.x == 2) {
    GPIO_ResetBits(GPIOE, GPIO_Pin_8);
    delay_ms(2000);
    send task1.mes1(0);

   }
  }
 }
}

int main() {

 GPIO_InitTypeDef gpio;
  RCC_AHBPeriphClockCmd(RCC_AHBPeriph_GPIOE, ENABLE);
  RCC_AHBPeriphClockCmd(RCC_AHBPeriph_GPIOA, ENABLE);
  GPIO_StructInit(&gpio);
  gpio.GPIO_Mode = GPIO_Mode_OUT;
  gpio.GPIO_Pin = (GPIO_Pin_8 | GPIO_Pin_9 | GPIO_Pin_10);
  GPIO_Init(GPIOE, &gpio);
  gpio.GPIO_Pin = GPIO_Pin_0;
  gpio.GPIO_Mode = GPIO_Mode_IN;
  GPIO_Init(GPIOA, &gpio);

 startSheduler();


 return 0;

}
