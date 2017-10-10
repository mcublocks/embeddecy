package mcublocks.embeddecy.rtos;

import java.util.ArrayList;
import java.util.List;

public class OSA implements IRTOS {

	@Override
	public String getIncludes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGlobalDeclarations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskCode(String taskName, String compoundStWithBrackets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodeInsertedAtBeginOfMain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDelayCode(String delayMs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskCreateCode(String taskName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStartShedulerCode() {
		// TODO Auto-generated method stub
		return null;
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
