package mcublocks.embeddecy.rtos;

import java.util.ArrayList;
import java.util.List;

public interface IRTOS {
	static IRTOS createOSA() {
		return new OSA();
	}
	static IRTOS createFreeRTOS() {
		return new FreeRTOS();
	}
	static IRTOS createAtomThreads() {
		return new AtomThreads();
	}
	
	public String getIncludes();
	public String getDefines();
	public String getGlobalDeclarations();
	public String getTaskCode(String taskName, String compoundStWithBrackets);
	
	public String getCodeInsertedAtBeginOfMain();
	
	public String getDelayCode(String delayMs);
	public String getTaskCreateCode(String taskName);
	public String getStartShedulerCode();
	
	public String getMessageDeclarationQueue(String messageName, String taskName, String parentTaskName);
	public String getMessageIncludes();
	public String getMessageQueueInit(String messageName, String taskName);
	public String getMessageSend(String messageName, String taskName, List<String> actualParameters, List<String> formalParameters, String parentTaskName);
	public String getMessageAccept(String messageName, String parentTaskName, String fromTaskName);
	public String getHasMessage(String messageName, String parenttaskName, String fromTaskName);
	
	public String getEventDeclaration(String taskName, String eventName,
			ArrayList<String> arrayOfParams);
	public String getEventQueueInit(String taskName, String eventName);
	public String getEventFromQueue(String taskName, String evName);
	public String setEventToQueue(String taskName, String evName,
			ArrayList<String> arrayOfFormParams,
			ArrayList<String> arrayOfFactParams);
	String getMessageDeclarationStruct(String taskName, String messageName,
			ArrayList<String> arrayOfParams);
	
}
