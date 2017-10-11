# 1 "D:\\embcrepo\\embeddecy\\Embeddecy-project\\res\\message-test\\messagetest.embc"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "D:\\embcrepo\\embeddecy\\Embeddecy-project\\res\\message-test\\messagetest.embc"



task task1 {

 message mes1(int x);

 body {
  for (;;) {
   send task2.mes1(87);
   GPIO_SetBits(GPIOE, GPIO_Pin_8);
   delay_ms(200);
   GPIO_ResetBits(GPIOE, GPIO_Pin_8);
   delay_ms(200);
  }

 }
}

task task2 {

 body {
  for (;;) {

   accept(mes1);

   if (mes1.x == 87) {
    GPIO_SetBits(GPIOE, GPIO_Pin_9);
    delay_ms(200);
    GPIO_ResetBits(GPIOE, GPIO_Pin_9);
    delay_ms(200);
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
