package mcublocks.embeddecy.rtos;

import java.util.ArrayList;
import java.util.List;

import mcublocks.embeddecy.codeanalyzer.CodeAnalyzer;

public class AtomThreads implements IRTOS {

	@Override
	public String getIncludes() {
		StringBuilder builder = new StringBuilder();
		builder.append("#include <avr/io.h>\n");
		builder.append("#include \"kernel/atom.h\"\n");
		builder.append("#include \"kernel/atomtimer.h\"\n");
		return builder.toString();
	}

	@Override
	public String getDefines() {
		StringBuilder builder = new StringBuilder();
		builder.append("#define STACK_SIZE 128\n");
		return builder.toString();
	}
	
	@Override
	public String getGlobalDeclarations() {
		StringBuilder builder = new StringBuilder();
		builder.append("uint8_t IdleStack[STACK_SIZE];\n");
		return builder.toString();
	}
	
	@Override
	public String getTaskCode(String taskName, String compoundStWithBrackets) {
		StringBuilder builder = new StringBuilder();
		builder.append("uint8_t $" + taskName + "_Stack$[STACK_SIZE];\n");
		builder.append("ATOM_TCB $tcb_" + taskName + "$;\n");
		builder.append("void $thread_" + taskName + "$()\n");
		builder.append(compoundStWithBrackets + "\n");
		return builder.toString();
	}
	
	@Override
	public String getTaskCreateCode(String taskName) {
		StringBuilder builder = new StringBuilder();
		builder.append("atomThreadCreate(&$tcb_" + taskName + "$, 0, $thread_" + taskName + "$, 0, &$" + taskName + "_Stack$[STACK_SIZE - 1], STACK_SIZE);");
		return builder.toString();
	}
	
	@Override
	public String getCodeInsertedAtBeginOfMain() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tatomOSInit(&IdleStack[STACK_SIZE - 1], STACK_SIZE);	\n\tavrInitSystemTickTimer();\n");
		//Создание всех задач (atomThreadCreate) 
		for (String taskName: CodeAnalyzer.getArrayOfTaskNames()) {
			builder.append(getTaskCreateCode(taskName) + "\n");
		}
		return builder.toString();
	}

	@Override
	public String getDelayCode(String delayMs) {
		StringBuilder builder = new StringBuilder();
		//TODO: Пока поделить на 10, после добавления конфиг файлов
		//будет по заданному количеству тиков пользователем
		builder.append("\natomTimerDelay( (" + delayMs+") / 10" + ");\n");
		return builder.toString();
	}

	@Override
	public String getStartShedulerCode() {
		StringBuilder builder = new StringBuilder();
		builder.append("\natomOSStart();\n");
		return builder.toString();
	}


	@Override
	public String getMessageIncludes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessageQueueInit(String messageName, String taskName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessageDeclarationQueue(String messageName, String taskName, String parentTaskName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessageSend(String messageName, String taskName,
			List<String> actualParameters, List<String> formalParameters, String parentTaskName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessageAccept(String messageName, String taskName, String parentTaskName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHasMessage(String messageName, String parentTaskName, String fromTaskName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEventDeclaration(String taskName, String eventName,
			ArrayList<String> arrayOfParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEventQueueInit(String taskName, String eventName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEventFromQueue(String taskName, String evName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setEventToQueue(String taskName, String evName,
			ArrayList<String> arrayOfFormParams,
			ArrayList<String> arrayOfFactParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessageDeclarationStruct(String taskName,
			String messageName, ArrayList<String> arrayOfParams) {
		// TODO Auto-generated method stub
		return null;
	}


}
