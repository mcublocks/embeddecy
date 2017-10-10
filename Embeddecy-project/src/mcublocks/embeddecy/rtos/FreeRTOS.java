package mcublocks.embeddecy.rtos;

import java.util.ArrayList;
import java.util.List;

import mcublocks.embeddecy.codeanalyzer.CodeAnalyzer;

public class FreeRTOS implements IRTOS {

	@Override
	public String getIncludes() {
		StringBuilder builder = new StringBuilder();
		builder.append("#include <FreeRTOSConfig.h>\n");
		builder.append("#include \"include/FreeRTOS.h\"\n");
		builder.append("#include \"include/task.h\"\n");
		return builder.toString();
	}
	
	@Override
	public String getDefines() {
		StringBuilder builder = new StringBuilder();
		builder.append("#define configMINIMAL_STACK_SIZE 128\n");
		return builder.toString();
	}

	@Override
	public String getGlobalDeclarations() {
		return "";
	}

	@Override
	public String getTaskCode(String taskName, String compoundStWithBrackets) {
		StringBuilder builder = new StringBuilder();
		builder.append("void _thread_" + taskName + "_(void *pvParameters)\n");
		builder.append(compoundStWithBrackets + "\n");
		return builder.toString();
	}

	@Override
	public String getTaskCreateCode(String taskName) {
		StringBuilder builder = new StringBuilder();
		builder.append("xTaskCreate(_thread_" + taskName + "_, (signed char *) \""+taskName+"\", configMINIMAL_STACK_SIZE, NULL, 2, NULL);");
		return builder.toString();
	}

	@Override
	public String getCodeInsertedAtBeginOfMain() {
		StringBuilder builder = new StringBuilder();
		for (String taskName: CodeAnalyzer.getArrayOfTaskNames()) {
			builder.append(getTaskCreateCode(taskName) + "\n");
		}
		return builder.toString();
	}

	@Override
	public String getDelayCode(String delayMs) {
		StringBuilder builder = new StringBuilder();
		builder.append("\nvTaskDelay(pdMS_TO_TICKS("+delayMs+"));\n");
		return builder.toString();
	}

	@Override
	public String getStartShedulerCode() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nvTaskStartScheduler();\n");
		return builder.toString();
	}

	//TODO: УБРАТЬ ПОСКОЛЬКУ НЕ ЗАВИСИТ ОТ ПЛАТФОРМЫ
	@Override
	public String getMessageDeclarationStruct(String taskName,
			String messageName, ArrayList<String> arrayOfParams) {
		StringBuilder builder = new StringBuilder();
		//Объявление структуры для сообщения
		builder.append("typedef struct " + messageName + "_" + taskName + "_type{ ");
		for (String param: arrayOfParams) {
			builder.append(param + "; ");
		}
		builder.append("} " + messageName + "_" + taskName + "_struct;\n");
		builder.append(messageName+ "_" + taskName + "_struct " + messageName + "_" + taskName + ";\n");
		return builder.toString();
	}

	@Override
	public String getMessageDeclarationQueue(String messageName, String taskName, String parentTaskName) {
		StringBuilder builder = new StringBuilder();
		//Создание очереди сообщений
		builder.append("#define QUEUE_LENGTH_" + messageName + ((taskName==null)?"":("_" + taskName))+ " 5\n");
		builder.append("#define QUEUE_ITEM_SIZE_" + messageName + 
				((taskName==null)?"":("_" + taskName)) + 
				" sizeof( " + messageName + "_" + 
				((parentTaskName!=null)?parentTaskName:taskName) + "_struct )\n");
		builder.append("QueueHandle_t xQueue_" + messageName +((taskName==null)?"":("_" + taskName))+ ";");
		return builder.toString();
	}

	@Override
	public String getMessageIncludes() {
		StringBuilder builder = new StringBuilder();
		builder.append("#include \"queue.h\"\n");
		return builder.toString();
	}

	@Override
	public String getMessageQueueInit(String messageName, String taskName) {
		StringBuilder builder = new StringBuilder();
		builder.append("xQueue_" + messageName 
				+ ((taskName==null)?"":("_" + taskName)) + " = xQueueCreate( QUEUE_LENGTH_" + messageName +((taskName==null)?"":("_" + taskName)) 
				+ ", QUEUE_ITEM_SIZE_" + messageName + ((taskName==null)?"":("_" + taskName))+ " );\n");
		return builder.toString();
	}

	@Override
	public String getMessageSend(String messageName, String taskName,
			List<String> actualParameters, List<String> formalParameters, String parentTaskName) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; (i < actualParameters.size()) && (i < formalParameters.size()); i++) {
			builder.append(messageName + "_" + ((parentTaskName!=null)?parentTaskName:taskName)  + "." + formalParameters.get(i) + " = " + actualParameters.get(i) + ";\n");
		}
		builder.append("if( xQueueSend( xQueue_" + messageName 
				+ ((taskName==null)?"":("_" + taskName)) + ", &" + messageName + "_" + ((parentTaskName!=null)?parentTaskName:taskName)  + ", 1 ) != pdPASS )\n");
		//TODO: Дописать внутри скобок, что делать при ошибке,
		//когда невозможно положить сообщение в очередь
		builder.append("{ //TODO \n}\n");
		return builder.toString();
	}

	@Override
	public String getMessageAccept(String messageName, String parentTaskName, String fromTaskName) {
		StringBuilder builder = new StringBuilder();
		builder.append("if( xQueueReceive( xQueue_" + messageName 
				+((parentTaskName==null)?"":("_" + parentTaskName))+ ", &" + messageName + "_" + ((fromTaskName!=null)?fromTaskName:parentTaskName)  + ", portMAX_DELAY ) != pdPASS )\n");
		//TODO: Дописать внутри скобок, что делать при ошибке,
		//когда невозможно забрать сообщение из очереди
		builder.append("{ //TODO \n}\n");
		return builder.toString();
	}

	@Override
	public String getHasMessage(String messageName, String parentTaskName, String fromTaskName) {
		StringBuilder builder = new StringBuilder();
		builder.append(" xQueuePeek( xQueue_" + messageName 
				+((parentTaskName==null)?"":("_" + parentTaskName))+ ", &" + messageName + "_" + ((fromTaskName!=null)?fromTaskName:parentTaskName)  + ", 1 ) == pdPASS ");
		return builder.toString();
	}



	//TODO: УБРАТЬ структуры ПОСКОЛЬКУ НЕ ЗАВИСИТ ОТ ПЛАТФОРМЫ
	@Override
	public String getEventDeclaration(String taskName, String eventName, ArrayList<String> arrayOfParams) {
		StringBuilder builder = new StringBuilder();
		builder.append(getMessageDeclarationStruct(taskName, eventName, arrayOfParams));
		builder.append(getMessageDeclarationQueue(eventName, taskName, null));
		return builder.toString();
	}

	@Override
	public String getEventQueueInit(String taskName, String eventName) {
		StringBuilder builder = new StringBuilder();
		builder.append(getMessageQueueInit(eventName, taskName));
		return builder.toString();
	}

	@Override
	public String getEventFromQueue(String taskName, String evName) {
		StringBuilder builder = new StringBuilder();
		//забираем структуру события из очереди
		builder.append(getMessageAccept(evName, taskName, null));
		return builder.toString();
	}

	@Override
	public String setEventToQueue(String taskName, String evName,ArrayList<String> arrayOfFormParams, ArrayList<String> arrayOfFactParams) {
		// тройка имя события - формальные параметры - фактические параметры
		StringBuilder builder = new StringBuilder();
		builder.append(getMessageSend(evName, taskName, arrayOfFactParams, arrayOfFormParams, null));
		return builder.toString();
	}


}
