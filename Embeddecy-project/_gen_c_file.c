#include <FreeRTOSConfig.h>
#include "include/FreeRTOS.h"
#include "include/task.h"
#define configMINIMAL_STACK_SIZE 128
#include "queue.h"
typedef struct mes1_task1_type{ int x ; } mes1_task1_struct;
mes1_task1_struct mes1_task1;

#define QUEUE_LENGTH_mes1_task2 5
#define QUEUE_ITEM_SIZE_mes1_task2 sizeof( mes1_task1_struct )
QueueHandle_t xQueue_mes1_task2;
void _thread_task1_(void *pvParameters)
{
for ( ; 
; 
) { 
mes1_task1.x = 87 ;
if( xQueueSend( xQueue_mes1_task2, &mes1_task1, 1 ) != pdPASS )
{ //TODO 
}

GPIO_SetBits ( GPIOE , GPIO_Pin_8 ) ; 
vTaskDelay(pdMS_TO_TICKS(200 ));
 
GPIO_ResetBits ( GPIOE , GPIO_Pin_8 ) ; 
vTaskDelay(pdMS_TO_TICKS(200 ));
 
} 
}
void _thread_task2_(void *pvParameters)
{
for ( ; 
; 
) { 
if( xQueueReceive( xQueue_mes1_task2, &mes1_task1, portMAX_DELAY ) != pdPASS )
{ //TODO 
}
; 
if ( mes1_task1.x == 87 ) { 
GPIO_SetBits ( GPIOE , GPIO_Pin_9 ) ; 
vTaskDelay(pdMS_TO_TICKS(200 ));
 
GPIO_ResetBits ( GPIOE , GPIO_Pin_9 ) ; 
vTaskDelay(pdMS_TO_TICKS(200 ));
 
} 
} 
}
int main ( ) { 
xTaskCreate(_thread_task1_, (signed char *) "task1", configMINIMAL_STACK_SIZE, NULL, 2, NULL);
xTaskCreate(_thread_task2_, (signed char *) "task2", configMINIMAL_STACK_SIZE, NULL, 2, NULL);
xQueue_mes1_task2 = xQueueCreate( QUEUE_LENGTH_mes1_task2, QUEUE_ITEM_SIZE_mes1_task2 );

GPIO_InitTypeDef gpio ; 
RCC_AHBPeriphClockCmd ( RCC_AHBPeriph_GPIOE , ENABLE ) ; 
RCC_AHBPeriphClockCmd ( RCC_AHBPeriph_GPIOA , ENABLE ) ; 
GPIO_StructInit ( & gpio ) ; 
gpio . GPIO_Mode = GPIO_Mode_OUT ; 
gpio . GPIO_Pin = ( GPIO_Pin_8 | GPIO_Pin_9 | GPIO_Pin_10 ) ; 
GPIO_Init ( GPIOE , & gpio ) ; 
gpio . GPIO_Pin = GPIO_Pin_0 ; 
gpio . GPIO_Mode = GPIO_Mode_IN ; 
GPIO_Init ( GPIOA , & gpio ) ; 

vTaskStartScheduler();
 
return 0 ; 
} 

