package mcublocks.embeddecy.generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import mcublocks.embeddecy.codeanalyzer.CodeAnalyzer;
import mcublocks.embeddecy.rtos.IRTOS;
import mcublocks.embeddecy.sources.EmbeddecyParser;
import mcublocks.embeddecy.utilities.ASTUtilities;

import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.Triple;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.stringtemplate.v4.ST;

public class Task {
	
	private EmbeddecyParser parser;
	private ParseTree tree;	
	private IRTOS selectedRtos;
	
	public Task(EmbeddecyParser parser, ParseTree tree) {
		this.parser = parser;
		this.tree = tree;
	}
	
	String generateMessageDeclaration() {
		StringBuilder builder = new StringBuilder();
		
		//���������� struct'�� ���� ���������
		for (ParseTree t : XPath.findAll(tree, "//messageDeclaration", parser)) {
			String mesName = "";
			ArrayList<String> arrOfParams = new ArrayList<String>();
			//�������� ��� ���������
			for (ParseTree t2 : XPath.findAll(t, "//messageName", parser)) {
				mesName = t2.getText();
			}
			//�������� ��������� ���������
			for (ParseTree t2 : XPath.findAll(t, "//parameterDeclaration", parser)) {
				String parameter = ASTUtilities.getTokensWithSeparatorSpace(t2);
				arrOfParams.add(parameter);
			}
			builder.append(selectedRtos.getMessageDeclarationStruct(ASTUtilities.getParentTask(t), mesName, arrOfParams) + "\n");
		}
		
		//���������� �������� ��� ������������ ���������
		for (Triple<String, String, List<String>> p: CodeAnalyzer.getArrayOfSendMessages() ) {
				builder.append(selectedRtos.getMessageDeclarationQueue(p.a, p.b, CodeAnalyzer.getParentTaskForSendMessage(p.b, p.a)) + "\n");
		}
		return builder.toString();
	}
	
	String generateMessageQueueInit() {
		StringBuilder builder = new StringBuilder();
		// ���������� �������� ��� ������������ ���������
		for (Triple<String, String, List<String>> p : CodeAnalyzer.getArrayOfSendMessages()) {
			builder.append(selectedRtos.getMessageQueueInit(p.a, p.b)
					+ "\n");
		}
		return builder.toString();
	}
	
	String generateDelayMs(String codeStr) {
		// ������� ������ � �������, ��� ������ ����� delay_ms
		String xpath1 = "//*"; // get children of
		// taskCompoundStatement
		String treePattern = "delay_ms(<assignmentExpression>);";
		ParseTreePattern p = parser.compileParseTreePattern(treePattern,
				EmbeddecyParser.RULE_statement);
		List<ParseTreeMatch> matches = p.findAll(tree, xpath1);

		for (ParseTreeMatch itMatch : matches) {
			// TODO: �������� �������� �� ������� castExpression � ��������,
			// ����� �� ��������,
			// ���� ���� ����� �� delay_ms ��� �������� �� ���-�� ������

			ST hello = new ST(selectedRtos.getDelayCode("<delayMs>"));
			String str = "";
			// �������� �������� ������� delayMs
			for (ParseTree t9 : XPath.findAll(itMatch.getTree(),
					"//argumentExpressionList//*", parser)) {
				str += ASTUtilities.getTokenText(t9);
			}
			hello.add("delayMs", str);
			String output = hello.render();
			// ������
			codeStr = codeStr.replace(
					"delay_ms ( " + hello.getAttribute("delayMs") + ") ;",
					output);
		}

		return codeStr;
	}
	
	String generateStartSheduler(String codeStr) {
		
		// ������� ������, ��� ����������� �����������
		String output = selectedRtos.getStartShedulerCode();
		// ������
		codeStr = codeStr.replace(
				("startSheduler ( ) ;"), output);
		return codeStr;
	}
	
	String generateSendMessage(String codeStr) {
		for (ParseTree t : XPath.findAll(tree, "//unaryExpression", parser)) {
			String messageName = "", taskName = "";
			String strSend = "";
			ArrayList<String> actParams = new ArrayList<String>();
			ArrayList<String> formalParams = new ArrayList<String>();
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendTaskName", parser)) {
				taskName = t2.getText();
				strSend = ASTUtilities.getTokensWithSeparatorSpace(t);
				strSend += "; ";
			}
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/argumentExpressionList", parser)) {
				for (ParseTree t3 : XPath.findAll(t2, "//assignmentExpression", parser)) {
					if (ASTUtilities.getNameOfRule(t3.getParent()).equals("argumentExpressionList")) {
						actParams.add(ASTUtilities.getTokensWithSeparatorSpace(t3));
					}
				}
			}
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendMessageName", parser)) {
				messageName = t2.getText();
				for (Pair<String, List<String>> mesDecl: CodeAnalyzer.getArrayOfMessageDeclarations()) {
					if (mesDecl.a.equals(messageName)) {
						formalParams.addAll(mesDecl.b);
						break;
					}
				}
				String output = selectedRtos.getMessageSend(messageName, taskName, actParams, formalParams, ASTUtilities.getParentTask(t2));
				codeStr = codeStr.replace(strSend, output);
			}
		}
		return codeStr;
	}
	
	String generateEventQueueInit(ArrayList<String> taskList) {
		StringBuilder builder = new StringBuilder();
		for (String task : taskList) {
			for (String event : CodeAnalyzer.getArrayOfEventNamesForTask(task))
				builder.append(selectedRtos.getEventQueueInit(task, event));
		}
		return builder.toString();
	}

	String generateEventsDeclaration() {

		StringBuilder builder = new StringBuilder();
		for (ParseTree t : XPath.findAll(tree, "//eventDeclaration", parser)) {
			String evName = "";
			ArrayList<String> arrOfParams = new ArrayList<String>();
			// �������� ��� �������
			for (ParseTree t2 : XPath.findAll(t, "//eventName", parser)) {
				evName = t2.getText();
			}
			// �������� ��������� ���������
			for (ParseTree t2 : XPath.findAll(t, "//parameterDeclaration",
					parser)) {
				String parameter = "";
				for (ParseTree t3 : XPath.findAll(t2, "//*", parser)) {
					parameter += ASTUtilities.getTokenText(t3);

				}
				arrOfParams.add(parameter);
			}
			builder.append(selectedRtos.getEventDeclaration(ASTUtilities.getParentTask(t), evName, arrOfParams)
					+ "\n");
		}
		return builder.toString();
	}
	
	String replaceEventParamsInAnonymousFunc(String taskName, String eventName,ParseTree compStatement) {
		String builder = new String();
		ArrayList<String> params = CodeAnalyzer.getParamByEventInTask(eventName, taskName); 
		for (String par :params){
			builder = ASTUtilities.getTokensWithSeparatorSpace(compStatement).replace(" "+ par + " ",  eventName+"_" + taskName +"."+par+" ");
		}
		return builder;
	}

	String generateEventTask() {
		StringBuilder builder = new StringBuilder();
		if (!CodeAnalyzer.getArrayOfEventNames().isEmpty()) {
			StringBuilder buildevcompstatement = new StringBuilder();
			buildevcompstatement.append("{\n ");
			buildevcompstatement.append("for (;;){\n");
			for (String taskName : CodeAnalyzer.getArrayOfTaskNames()) {
				for (String evName : CodeAnalyzer
						.getArrayOfEventNamesForTask(taskName)) {
					if (!CodeAnalyzer.getCompoundStatementsForEvent(taskName,
							evName).isEmpty()) {
						buildevcompstatement.append("if (" + selectedRtos.getHasMessage(evName, taskName, null) + ") {\n");
						buildevcompstatement.append(selectedRtos.getEventFromQueue(taskName, evName));
						int indexSubscriber = 0;
						for (ParseTree compSt : CodeAnalyzer
								.getCompoundStatementsForEvent(taskName, evName)) {
							buildevcompstatement.append("if (" + evName + "_" + taskName + "_isSubscribe["
									+ (indexSubscriber++) + "] == 1) {\n");
							buildevcompstatement
									.append(replaceEventParamsInAnonymousFunc(
											taskName, evName, compSt));
							buildevcompstatement.append("\n}\n");
						}
						buildevcompstatement.append("\n}\n");

					}

				}
			}
			buildevcompstatement.append("}\n" + "}\n");
			builder.append(selectedRtos.getTaskCode("event_task",
					buildevcompstatement.toString()));
		}
		return builder.toString();
	}

	String generateArraysOfIsSubscribe() {
		StringBuilder builder = new StringBuilder();
		for (Pair<String, String> p: CodeAnalyzer.getArrayOfTaskPlusEvent()) {
			if (CodeAnalyzer.getCountOfSubscribersForTaskPlusEvent(p.a, p.b) > 0) {
				builder.append("\nint "+p.b+"_"+p.a+"_isSubscribe["+CodeAnalyzer.getCountOfSubscribersForTaskPlusEvent(p.a, p.b)+"];\n");
			}
		}
		return builder.toString();
	}
	
	String generateArraysOfIsSubscribeInit() {
		StringBuilder builder = new StringBuilder();
		for (Pair<String, String> p: CodeAnalyzer.getArrayOfTaskPlusEvent()) {
			if (CodeAnalyzer.getCountOfSubscribersForTaskPlusEvent(p.a, p.b) > 0) {
				builder.append("for (int i = 0; i < "+CodeAnalyzer.getCountOfSubscribersForTaskPlusEvent(p.a, p.b)+"; i++) \n{\n");
				builder.append("\n"+p.b+"_"+p.a+"_isSubscribe[i] = 0;\n");
				builder.append("\n}\n");
			}
		}
		return builder.toString();
	}	

	String generateUseElemFromPackage(String codeStr) {
		// ������� � �������� ������, ��� ������������ �������� ������
		for (String packName: CodeAnalyzer.getArrayOfPackageNames()) {
			codeStr = codeStr.replace(packName + " . ", "_" + packName + "_");
		}
		return codeStr;
	}
	
	String generateAnonFuncSubscribe(ParseTree assignmentExpr, ArrayList<Integer> countOfSubscribers) {
		StringBuilder builder = new StringBuilder();
		// �������� �������� �� ���������� 1 ���������������
		// �������� ������� isSubscribe
		String taskName = ASTUtilities.getTaskAndEventNamesByAssignmentExpr(assignmentExpr).a;
		String eventName = ASTUtilities.getTaskAndEventNamesByAssignmentExpr(assignmentExpr).b;
		Integer indexTaskPlusEvent = CodeAnalyzer
				.getArrayOfTaskPlusEvent().indexOf(
						new Pair<String, String>(taskName,
								eventName));
		builder.append(eventName+"_"+taskName
				+ "_isSubscribe["
				+ (countOfSubscribers.set(indexTaskPlusEvent,
						countOfSubscribers
								.get(indexTaskPlusEvent) + 1))
				+ "] = 1");
		return builder.toString();
	}

	String generateTaskForEvent() {
		StringBuilder builder = new StringBuilder();
		if (!CodeAnalyzer.getArrayOfEventNames().isEmpty()) {
			builder.append(selectedRtos.getTaskCreateCode("event_task") + "\n");
		}
		return builder.toString();
	}
	public String generateTasks() {
		
		// ����� RTOS, ��� ������� ������������ ���
		selectedRtos = IRTOS.createFreeRTOS();
		StringBuilder builder = new StringBuilder();
		
		// ������� ������� ��� ����������� �������
		for (String pack : CodeAnalyzer.getArrayOfPackageNames()) {
			builder.append("#include \"_" + pack + ".h\"\n");
		}
		if (!CodeAnalyzer.getArrayOfTaskNames().isEmpty()) {
			// ������� �������, ������� � ���������� ����������
			builder.append(selectedRtos.getIncludes());
			builder.append(selectedRtos.getDefines());
			builder.append(selectedRtos.getGlobalDeclarations());

			// ������� ������� ��� ���������
			if (!CodeAnalyzer.getArrayOfMessageDeclarations().isEmpty()
					|| (!CodeAnalyzer.getArrayOfEventNames().isEmpty())) {
				builder.append(selectedRtos.getMessageIncludes());
			}

			// ������� ���������� ���������
			builder.append(generateMessageDeclaration());

			// ������� ���������� �������
			builder.append(generateEventsDeclaration());

			// ������� ���������� �������� ������ ������������ �������� ��
			// ���������� �������
			builder.append(generateArraysOfIsSubscribe());
			
			// ������� ������ - �������
			builder.append(generateEventTask());
			
		}
		boolean isParamMes = false;
		int hashParamMesNode = 0;
		boolean isCallEvent = false;
		int hashCallEventNode = 0;
		boolean isAcceptMes = false;
		int hashAcceptMesNode = 0;
		boolean isHasMes = false;
		int hashHasMesNode = 0;
		boolean isAnonFunc = false;
		int hashAnonFuncNode = 0;
		ArrayList<Integer> countOfSubscribers = new ArrayList<Integer>();
		for (String ev : CodeAnalyzer.getArrayOfEventNames()) {
			countOfSubscribers.add(0);
		}
		for (ParseTree t : XPath.findAll(tree, "//*", parser)) {
			
			// ����������� ������
			String xpath4 = "/taskDefinition";
			for (ParseTree t4 : XPath.findAll(t, xpath4, parser)) {
				ST hello = new ST(selectedRtos.getTaskCode("<taskName>",
						"<compoundSt>"));
				String xpath5 = "//taskName";
				for (ParseTree t5 : XPath.findAll(t4, xpath5, parser)) {
					hello.add("taskName", t5.getText());
				}
				String xpath7 = "//taskFuncAndVarDefinition/*";
				String myCodeInTask = "{\n";
	

				for (ParseTree t7 : XPath.findAll(t4, xpath7, parser)) {
					if (ASTUtilities.getNameOfRule(t7).equals("messageDeclaration")) continue;
					if (ASTUtilities.getNameOfRule(t7).equals("eventDeclaration")) continue;
					for (ParseTree t8 : XPath.findAll(t7, "//*", parser)) {
						myCodeInTask += ASTUtilities.getTokenText(t8);
					}
				}
				String xpath6 = "//taskCompoundStatement//blockItem//*";
				String treePattern = "<primaryExpression>(<argumentExpressionList>);";
				ParseTreePattern p = parser.compileParseTreePattern(
						treePattern, EmbeddecyParser.RULE_statement);
				List<ParseTreeMatch> matches = p.findAll(tree, xpath6);
				for (ParseTree t6 : XPath.findAll(t4, xpath6, parser)) {
					
					// �������� �������� ������ �����
					if (ASTUtilities.isAnonymousFunctionByAssignmentExpression(t6)) {
						isAnonFunc = true;
						hashAnonFuncNode = t6.hashCode();
						myCodeInTask += generateAnonFuncSubscribe(t6, countOfSubscribers);
					}
					if (isAnonFunc
							&& ASTUtilities.hasParentByHashForNode(t6, hashAnonFuncNode)) {
						continue;
					} else {
						isAnonFunc = false;
					}
					
					
					// �������� hasmessage
					if (ASTUtilities.getNameOfRule(t6).equals("postfixExpression")
							&& (ASTUtilities.getNameOfRule(t6.getChild(0)).equals("hasMessageExpression"))) {
						isHasMes = true;
						hashHasMesNode = t6.hashCode();
						String fromTaskName = "";
						for (Triple<String, String, List<String>> sendMes: CodeAnalyzer.getArrayOfSendMessages()) {
							if ((t6.getChild(2).getText().equals(sendMes.a))
									&& (ASTUtilities.getParentTask(t6)
											.equals(sendMes.b))) {
								fromTaskName = CodeAnalyzer.getParentTaskForSendMessage(sendMes.b, sendMes.a);
							}
						}
						myCodeInTask += selectedRtos.getHasMessage(t6.getChild(2).getText(), ASTUtilities.getParentTask(t6), fromTaskName);
					}
					if (isHasMes
							&& ASTUtilities.hasParentByHashForNode(t6, hashHasMesNode)) {
						continue;
					} else {
						isHasMes = false;
					}
					
					// �������� accept
					if (ASTUtilities.getNameOfRule(t6).equals("unaryExpression")
							&& (t6.getChild(0).getText().equals("accept"))) {
						isAcceptMes = true;
						hashAcceptMesNode = t6.hashCode();
						String fromTaskName = "";
						for (Triple<String, String, List<String>> sendMes: CodeAnalyzer.getArrayOfSendMessages()) {
							if ((t6.getChild(2).getText().equals(sendMes.a))
									&& (ASTUtilities.getParentTask(t6)
											.equals(sendMes.b))) {
								fromTaskName = CodeAnalyzer.getParentTaskForSendMessage(sendMes.b, sendMes.a);
							}
						}
						myCodeInTask += selectedRtos.getMessageAccept(t6.getChild(2).getText(), ASTUtilities.getParentTask(t6), fromTaskName);
					}
					if (isAcceptMes
							&& ASTUtilities.hasParentByHashForNode(t6, hashAcceptMesNode)) {
						continue;
					} else {
						isAcceptMes = false;
					}
					
					// �������� ��������� � ���������� ��������� ���������
					for (Pair<String, String> sm: CodeAnalyzer.getArrayOfSendMessagesWithoutParams()) {
						if (ASTUtilities.getNameOfRule(t6).equals("postfixExpression")
								&& (t6.getChild(0).getText().equals(sm.a))
								&& (ASTUtilities.getParentTask(t6).equals(sm.b))
								&& (t6.getChildCount() == 3)) {
							isParamMes = true;
							hashParamMesNode = t6.hashCode();
							myCodeInTask += t6.getChild(0).getText() + "_" + CodeAnalyzer.getParentTaskForSendMessage(sm.b, sm.a) + "." + t6.getChild(2) + " ";
						}
					}
					if (isParamMes
							&& ASTUtilities.hasParentByHashForNode(t6, hashParamMesNode)) {
						continue;
					} else {
						isParamMes = false;
					}
					
					// ���� ������ ������� ������ ����� � ��������
					for (ParseTreeMatch match : matches) {
						if (match.getTree().hashCode() == t6.hashCode()) {
							hashCallEventNode = t6.hashCode();
							String xpath8 = "//primaryExpression";

							for (ParseTree t8 : XPath.findAll(t6, xpath8,
									parser)) {
								if (CodeAnalyzer.getArrayOfEventNamesForTask(
										ASTUtilities.getParentTask(t8))
										.contains(t8.getText())) {
									ST mySt = new ST(
											selectedRtos.setEventToQueue(
													ASTUtilities
															.getParentTask(t8),
													"<evName>",
													CodeAnalyzer
															.getEventCallsWithParamsByEventName(
																	ASTUtilities
																			.getParentTask(t8),
																	t8.getText()).b,
													CodeAnalyzer
															.getEventCallsWithParamsByEventName(
																	ASTUtilities
																			.getParentTask(t8),
																	t8.getText()).c));
									String str = t8.getText();
									mySt.add("evName", str);

									// �������� �������� �������
									str = "";
									for (ParseTree t9 : XPath.findAll(t6,
											"//argumentExpressionList//*",
											parser)) {
										str += ASTUtilities.getTokenText(t9);
									}
									String output = mySt.render();

									myCodeInTask += output;

									isCallEvent = true;
								}
							}
						}

					}
					if (isCallEvent
							&& ASTUtilities.hasParentByHashForNode(t6,
									hashCallEventNode)) {
						continue;
					} else {
						isCallEvent = false;
					}
					
					
					
					
					myCodeInTask += ASTUtilities.getTokenText(t6);
				}

				
				myCodeInTask += "}";
				hello.add("compoundSt", myCodeInTask);
				String output = hello.render();
				builder.append(output);
			}

			// ����������� ���������� ������� � ����������
			String xpath2 = "/externalDeclaration/*";
			for (ParseTree t2 : XPath.findAll(t, xpath2, parser)) {
				if (ASTUtilities.getNameOfRule(t2).equals("taskDefinition")) continue;
				if (ASTUtilities.getNameOfRule(t2).equals("packageDefinition")) continue;
				for (ParseTree t8 : XPath.findAll(t2, "//*", parser)) {

					//�������� �������� ������ ���������� �������
					if (ASTUtilities.isAnonymousFunctionByAssignmentExpression(t8)){
						isAnonFunc = true;
						hashAnonFuncNode =t8.hashCode();
						builder.append(generateAnonFuncSubscribe(t8, countOfSubscribers));
					}
					if (isAnonFunc && ASTUtilities.hasParentByHashForNode(t8, hashAnonFuncNode) ){
						continue;
					} else {
						isAnonFunc = false;
					}

					if (t8 instanceof TerminalNode) {
						TerminalNode token = (TerminalNode) t8;
						builder.append(ASTUtilities.getTokenText(t8));
						// ������� ������������������ ���� � ������ main
						// �������
						if (token.getText().equals("{")) {
							if ((token.getParent().getParent().getText()
									.matches("intmain\\(void\\)\\{(.*?)\\}"))
									|| (token.getParent().getParent().getText()
											.matches("voidmain\\(\\)\\{(.*?)\\}"))
									|| (token.getParent().getParent().getText()
											.matches("intmain\\(\\)\\{(.*?)\\}"))
									|| (token.getParent().getParent().getText()
											.matches("voidmain\\(void\\)\\{(.*?)\\}"))) {

								builder.append(selectedRtos
										.getCodeInsertedAtBeginOfMain());
								//��������� task ��� �������
								builder.append(generateTaskForEvent());
								// �������������� ������� �������
								builder.append(generateEventQueueInit(CodeAnalyzer
										.getArrayOfTaskNames()));
								// �������������� ������� isSubscribe ������
								builder.append(generateArraysOfIsSubscribeInit());
								// ������� �������� ������� ��� ������� ���������
								builder.append(generateMessageQueueInit());
							}
						}
					}
				}
			}

		}

		//������ delay_ms
		builder = new StringBuilder(generateDelayMs(builder.toString()));
		
		//������ startSheduler()
		builder = new StringBuilder(generateStartSheduler(builder.toString()));
		
		//������ send message
		builder = new StringBuilder(generateSendMessage(builder.toString()));
		
		//������ ��������� � �������� ������
		builder = new StringBuilder(generateUseElemFromPackage(builder.toString()));
		
		
		// ������������� .c - ����
		PrintWriter writerC = null;
		try {
			writerC = new PrintWriter("_gen_c_file.c", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writerC.println(builder.toString());
		writerC.close();
		
		return builder.toString();
	}

}
