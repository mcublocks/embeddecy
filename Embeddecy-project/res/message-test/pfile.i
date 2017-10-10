# 1 "D:\\eclipse-dsl-luna-SR2-win32-x86_64\\git\\mcublocks\\Embeddecy-project\\res\\message-test\\messagetest6.embc"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "D:\\eclipse-dsl-luna-SR2-win32-x86_64\\git\\mcublocks\\Embeddecy-project\\res\\message-test\\messagetest6.embc"


task task1 {

 message mes1(int x);

 body {
  for (;;) {
   send task2.mes1(87);
   GPIO_SetBits(GPIOE, GPIO_Pin_8);
   delay_ms(400);
   GPIO_ResetBits(GPIOE, GPIO_Pin_8);
   delay_ms(400);
   delay_ms(1000);
  }

 }
}

task task2 {

 message mes2(int x);

 body {
  for (;;) {

     if (hasmessage(mes1)) {
     GPIO_SetBits(GPIOE, GPIO_Pin_9);
     delay_ms(200);

     accept(mes1);
     if (mes1.x == 87) {
      GPIO_ResetBits(GPIOE, GPIO_Pin_9);
      delay_ms(200);
     }
   }
   if (hasmessage(mes3)) {
     GPIO_SetBits(GPIOE, GPIO_Pin_9);
     delay_ms(800);
     accept(mes3);
     if (mes3.x == 88) {
      GPIO_ResetBits(GPIOE, GPIO_Pin_9);
      delay_ms(800);
     }
     }

  }
 }
}

task task3 {

 message mes3(int x);

 body {
  for (;;) {
   send task2.mes3(88);
   GPIO_SetBits(GPIOE, GPIO_Pin_10);
   delay_ms(400);
   GPIO_ResetBits(GPIOE, GPIO_Pin_10);
   delay_ms(400);
   delay_ms(2000);
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
