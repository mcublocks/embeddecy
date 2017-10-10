#include <FreeRTOSConfig.h>
#include "include/FreeRTOS.h"
#include "include/task.h"
#define configMINIMAL_STACK_SIZE 128
#include "queue.h"
typedef struct mes1_task1_type{ int x ; } mes1_task1_struct;
mes1_task1_struct mes1_task1;

typedef struct mes1_task2_type{ int x ; } mes1_task2_struct;
mes1_task2_struct mes1_task2;

typedef struct mes1_task3_type{ int x ; } mes1_task3_struct;
mes1_task3_struct mes1_task3;

#define QUEUE_LENGTH_mes1_task2 5
#define QUEUE_ITEM_SIZE_mes1_task2 sizeof( mes1_task1_struct )
QueueHandle_t xQueue_mes1_task2;
#define QUEUE_LENGTH_mes1_task2 5
#define QUEUE_ITEM_SIZE_mes1_task2 sizeof( mes1_task1_struct )
QueueHandle_t xQueue_mes1_task2;
#define QUEUE_LENGTH_mes1_task3 5
#define QUEUE_ITEM_SIZE_mes1_task3 sizeof( mes1_task2_struct )
QueueHandle_t xQueue_mes1_task3;
#define QUEUE_LENGTH_mes1_task3 5
#define QUEUE_ITEM_SIZE_mes1_task3 sizeof( mes1_task2_struct )
QueueHandle_t xQueue_mes1_task3;
#define QUEUE_LENGTH_mes1_task1 5
#define QUEUE_ITEM_SIZE_mes1_task1 sizeof( mes1_task3_struct )
QueueHandle_t xQueue_mes1_task1;
#define QUEUE_LENGTH_mes1_task1 5
#define QUEUE_ITEM_SIZE_mes1_task1 sizeof( mes1_task3_struct )
QueueHandle_t xQueue_mes1_task1;
void _thread_task1_(void *pvParameters)
{
int counter = 0 ; 
for ( ; 
; 
) { 
if (  xQueuePeek( xQueue_mes1_task1, &mes1_task3, 1 ) == pdPASS ) { 
if( xQueueReceive( xQueue_mes1_task1, &mes1_task3, portMAX_DELAY ) != pdPASS )
{ //TODO 
}
; 
counter = mes1_task3.x ; 
} 
if ( ( GPIO_ReadInputDataBit ( GPIOA , GPIO_Pin_0 ) ) && ( counter == 0 ) ) { 
counter = 1 ; 
mes1_task1.x = 1 ;
if( xQueueSend( xQueue_mes1_task2, &mes1_task1, 1 ) != pdPASS )
{ //TODO 
}

} 
if ( ( GPIO_ReadInputDataBit ( GPIOA , GPIO_Pin_0 ) ) && ( counter == 2 ) ) { 
counter = 1 ; 
mes1_task1.x = 2 ;
if( xQueueSend( xQueue_mes1_task2, &mes1_task1, 1 ) != pdPASS )
{ //TODO 
}

} 
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
if ( mes1_task1.x == 1 ) { 
GPIO_SetBits ( GPIOE , GPIO_Pin_8 ) ; 
vTaskDelay(pdMS_TO_TICKS(2000 ));
 
mes1_task2.x = 1 ;
if( xQueueSend( xQueue_mes1_task3, &mes1_task2, 1 ) != pdPASS )
{ //TODO 
}

} 
if ( mes1_task1.x == 2 ) { 
GPIO_ResetBits ( GPIOE , GPIO_Pin_9 ) ; 
vTaskDelay(pdMS_TO_TICKS(2000 ));
 
mes1_task2.x = 2 ;
if( xQueueSend( xQueue_mes1_task3, &mes1_task2, 1 ) != pdPASS )
{ //TODO 
}

} 
} 
}
void _thread_task3_(void *pvParameters)
{
for ( ; 
; 
) { 
if( xQueueReceive( xQueue_mes1_task3, &mes1_task2, portMAX_DELAY ) != pdPASS )
{ //TODO 
}
; 
if ( mes1_task2.x == 1 ) { 
GPIO_SetBits ( GPIOE , GPIO_Pin_9 ) ; 
vTaskDelay(pdMS_TO_TICKS(2000 ));
 
mes1_task3.x = 2 ;
if( xQueueSend( xQueue_mes1_task1, &mes1_task3, 1 ) != pdPASS )
{ //TODO 
}

} 
if ( mes1_task2.x == 2 ) { 
GPIO_ResetBits ( GPIOE , GPIO_Pin_8 ) ; 
vTaskDelay(pdMS_TO_TICKS(2000 ));
 
mes1_task3.x = 0 ;
if( xQueueSend( xQueue_mes1_task1, &mes1_task3, 1 ) != pdPASS )
{ //TODO 
}

} 
} 
}
int main ( ) { 
xTaskCreate(_thread_task1_, (signed char *) "task1", configMINIMAL_STACK_SIZE, NULL, 2, NULL);
xTaskCreate(_thread_task2_, (signed char *) "task2", configMINIMAL_STACK_SIZE, NULL, 2, NULL);
xTaskCreate(_thread_task3_, (signed char *) "task3", configMINIMAL_STACK_SIZE, NULL, 2, NULL);
xQueue_mes1_task2 = xQueueCreate( QUEUE_LENGTH_mes1_task2, QUEUE_ITEM_SIZE_mes1_task2 );

xQueue_mes1_task2 = xQueueCreate( QUEUE_LENGTH_mes1_task2, QUEUE_ITEM_SIZE_mes1_task2 );

xQueue_mes1_task3 = xQueueCreate( QUEUE_LENGTH_mes1_task3, QUEUE_ITEM_SIZE_mes1_task3 );

xQueue_mes1_task3 = xQueueCreate( QUEUE_LENGTH_mes1_task3, QUEUE_ITEM_SIZE_mes1_task3 );

xQueue_mes1_task1 = xQueueCreate( QUEUE_LENGTH_mes1_task1, QUEUE_ITEM_SIZE_mes1_task1 );

xQueue_mes1_task1 = xQueueCreate( QUEUE_LENGTH_mes1_task1, QUEUE_ITEM_SIZE_mes1_task1 );

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

