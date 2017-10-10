package mcublocks.embeddecy.codeanalyzer;

import java.util.ArrayList;
import java.util.List;

import mcublocks.embeddecy.sources.EmbeddecyParser;
import mcublocks.embeddecy.utilities.ASTUtilities;

import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.Triple;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.antlr.v4.runtime.tree.xpath.XPath;

public class CodeAnalyzer {
	
	private static EmbeddecyParser parser;
	private static ParseTree tree;
	
	public CodeAnalyzer(EmbeddecyParser parser, ParseTree tree) {
		CodeAnalyzer.parser = parser;
		CodeAnalyzer.tree = tree;
	}
	
	public static ArrayList<String> getArrayOfTaskNames() {
		ArrayList<String> arrayOfTaskNames = new ArrayList<String>();
		//Объявленные Задачи
     	String xpath1 = "//taskDefinition";
     	for (ParseTree t1 : XPath.findAll(tree, xpath1, parser) ) {
     		String xpath2 = "//taskName";
     		for (ParseTree t2 : XPath.findAll(t1, xpath2, parser) ) {
     			arrayOfTaskNames.add(t2.getText());
     		}
     	}
		
		return arrayOfTaskNames;
	}
	
	public static ArrayList<String> getArrayOfPackageNames() {
		ArrayList<String> arrayOfPackageNames = new ArrayList<String>();
		//Объявленные Задачи
     	String xpath1 = "//packageDefinition";
     	for (ParseTree t1 : XPath.findAll(tree, xpath1, parser) ) {
     		String xpath2 = "//packageName";
     		for (ParseTree t2 : XPath.findAll(t1, xpath2, parser) ) {
     			arrayOfPackageNames.add(t2.getText());
     		}
     	}
		
		return arrayOfPackageNames;
	}
	
	public static ArrayList<Pair<String, List<String>>> getArrayOfMessageDeclarations() {
		//Массив пар ("имя сообщения", "формальные параметры")
		ArrayList<Pair<String, List<String>>> arrayOfMessageDeclarations = new ArrayList<Pair<String, List<String>>>();
		//Объявленные сообщения
     	for (ParseTree t1 : XPath.findAll(tree, "//messageDeclaration", parser) ) {
     		String mesName = "";
     		ArrayList<String> params = new ArrayList<String>();
     		for (ParseTree t2 : XPath.findAll(t1, "/messageDeclaration/messageName", parser) ) {
     			mesName = t2.getText();
     		}
     		for (ParseTree t2 : XPath.findAll(t1, "/messageDeclaration/parameterTypeList", parser) ) {
     			for (ParseTree t3 : XPath.findAll(t2, "//directDeclarator/Identifier", parser)) {
     				params.add(t3.getText());
     			}
     		}
     		arrayOfMessageDeclarations.add(new Pair<String, List<String>>(mesName, params));
     	}
		
		return arrayOfMessageDeclarations;
	}
	

	public static ArrayList<String> getArrayOfEventNames() {
		ArrayList<String> arrayOfEventNames = new ArrayList<String>();
		//Объявленные события
     	String xpath1 = "//eventDeclaration";
     	for (ParseTree t1 : XPath.findAll(tree, xpath1, parser) ) {
     		String xpath2 = "//eventName";
     		for (ParseTree t2 : XPath.findAll(t1, xpath2, parser) ) {
     			arrayOfEventNames.add(t2.getText());
     		}
     	}
		
		return arrayOfEventNames;
	}


	public static ArrayList<String> getArrayOfEventNamesForTask(String taskName) {
		ArrayList<String> arrayOfEventNames = new ArrayList<String>();
		// Объявленные события
		String xpath1 = "//eventDeclaration";
		for (ParseTree t1 : XPath.findAll(tree, xpath1, parser)) {
			String xpath2 = "//eventName";
			for (ParseTree t2 : XPath.findAll(t1, xpath2, parser)) {
				if (ASTUtilities.getParentTask(t2).equals(taskName)) {
					arrayOfEventNames.add(t2.getText());
				}
			}
		}

		return arrayOfEventNames;
	}
	
	public static ArrayList<Triple<String, String, List<String>>> getArrayOfSendMessages() {
		//Массив триад ("имя сообщения", "задача, которой отправлено", "параметры")
		ArrayList<Triple<String, String, List<String>>> arrayOfSendMessages = new ArrayList<Triple<String, String, List<String>>>();
		//Отправленные сообщения
		String messageName = "", taskName = "";
		for (ParseTree t : XPath.findAll(tree, "//unaryExpression", parser)) {
			ArrayList<String> params = new ArrayList<String>();
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendTaskName", parser)) {
				taskName = t2.getText();
			}
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/argumentExpressionList", parser)) {
				
				for (ParseTree t3 : XPath.findAll(t2, "//assignmentExpression", parser)) {
					if (ASTUtilities.getNameOfRule(t3.getParent()).equals("argumentExpressionList")) {
						params.add(ASTUtilities.getTokensWithSeparatorSpace(t3));
					}
				}
			}
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendMessageName", parser)) {
				messageName = t2.getText();
				Triple<String, String, List<String>> tripleSend = new Triple<String, String, List<String>>(messageName, taskName, params);
				if (!arrayOfSendMessages.contains(tripleSend)) {
					arrayOfSendMessages.add(tripleSend);
				}
			}
		}
		return arrayOfSendMessages;
	}

	public static ArrayList<Pair<String, String>> getArrayOfSendMessagesWithoutParams() {
		//Массив пар ("имя сообщения", "задача, которой отправлено")
		ArrayList<Pair<String, String>> arrayOfSendMessages = new ArrayList<Pair<String, String>>();
		//Отправленные сообщения
		String messageName = "", taskName = "";
		for (ParseTree t : XPath.findAll(tree, "//unaryExpression", parser)) {
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendTaskName", parser)) {
				taskName = t2.getText();
			}
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendMessageName", parser)) {
				messageName = t2.getText();
				Pair<String, String> pairSend = new Pair<String, String>(messageName, taskName);
				if (!arrayOfSendMessages.contains(pairSend)) {
					arrayOfSendMessages.add(pairSend);
				}
			}
		}
		return arrayOfSendMessages;
	}
	
	public static String getParentTaskForSendMessage(String taskName, String messageName) {
		//Отправленные сообщения
		String findMessageName = "", findTaskName = "", parentTask = "";
		for (ParseTree t : XPath.findAll(tree, "//unaryExpression", parser)) {
			ArrayList<String> params = new ArrayList<String>();
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendTaskName", parser)) {
				findTaskName = t2.getText();
			}
			for (ParseTree t2 : XPath.findAll(t, "/unaryExpression/sendMessageName", parser)) {
				findMessageName = t2.getText();
				if ((taskName.equals(findTaskName)) && (messageName.equals(findMessageName))) {
					parentTask = ASTUtilities.getParentTask(t2);
				}
				
			}
		}
		return parentTask;
	}

	public static ArrayList<String> getParamByEventDeclarationName(String taskName, String evName) {
		ArrayList<String> arrayOfParams = new ArrayList<String>();
		String xpath1 = "//eventDeclaration";
		for (ParseTree t1 : XPath.findAll(tree, xpath1, parser)) {
			for (ParseTree t2 : XPath.findAll(t1,
					"/eventDeclaration/eventName", parser)) {
				if (t2.getText().equals(evName) && (ASTUtilities.getParentTask(t2).equals(taskName))) {
					for (ParseTree t3 : XPath.findAll(t1, "//directDeclarator",
							parser)) {
						arrayOfParams.add(t3.getText());
					}
				}
			}

		}
		return arrayOfParams;
	}
	
	public static ArrayList<String> getParamByEventInTask(String evName, String taskName) {
		ArrayList<String> arrayOfParams = new ArrayList<String>();
		String xpath1 = "//eventDeclaration";
		for (ParseTree t1 : XPath.findAll(tree, xpath1, parser)) {
			if (ASTUtilities.getParentTask(t1).equals(taskName)){
			for (ParseTree t2 : XPath.findAll(t1,
					"/eventDeclaration/eventName", parser)) {
				if (t2.getText().equals(evName)) {
					for (ParseTree t3 : XPath.findAll(t1, "//directDeclarator",
							parser)) {
						arrayOfParams.add(t3.getText());
					}
				}
			}
			}
		}
		return arrayOfParams;
	}


	public static Triple<String, ArrayList<String>, ArrayList<String>> getEventCallsWithParamsByEventName(String taskName,
			String evName) {
		// тройка имя события - формальные параметры - фактические параметры
		ArrayList<String> formParams = new ArrayList<String>();
		ArrayList<String> actParams = new ArrayList<String>();
		String xpath = "//taskCompoundStatement//blockItem//*";
		String treePattern = "<primaryExpression>(<argumentExpressionList>);";
		ParseTreePattern p = parser.compileParseTreePattern(treePattern,
				EmbeddecyParser.RULE_statement);
		List<ParseTreeMatch> matches = p.findAll(tree, xpath);
		for (ParseTreeMatch itMatch : matches) {
			for (ParseTree t : XPath.findAll(itMatch.getTree(),
					"//primaryExpression", parser)) {
				if (((t.getText()).equals(evName)) && (ASTUtilities.getParentTask(t).equals(taskName))) {
					for (ParseTree t2 : XPath.findAll(itMatch.getTree(),
							"//unaryExpression", parser)) {
						for (ParseTree t3 : XPath.findAll(t2,
								"//assignmentExpression", parser)) {
							if (ASTUtilities.getNameOfRule(t3.getParent())
									.equals("argumentExpressionList")) {
								actParams.add(ASTUtilities
										.getTokensWithSeparatorSpace(t3));
							}
						}
					}
					formParams = CodeAnalyzer
							.getParamByEventDeclarationName(taskName, evName);
				}
			}
		}
		return new Triple<String, ArrayList<String>, ArrayList<String>>(evName,
				formParams, actParams);

	}

	public static List<ParseTree> getCompoundStatementsForEvent(String taskName,String eventName)
	{
		ArrayList<ParseTree> result = new  ArrayList<ParseTree>(); 
		for (ParseTree t : XPath.findAll(tree, "//anonymousFunction", parser))
		{
			for (ParseTree t2 : XPath.findAll(t.getParent(), "/assignmentExpression/unaryExpression//primaryExpression", parser)){
				if ((t2.getChild(0).getText().equals(taskName))){
					if (t2.getParent().getParent().getChild(2).getText().equals(eventName)){
					for (ParseTree t3 : XPath.findAll(t, "/anonymousFunction/compoundStatement/blockItemList", parser)){
						result.add(t3);
					}
					}
				}
			}
		}
		return result;
	}
	
	public static ArrayList<Pair<String,String>> getArrayOfTaskPlusEvent() {
		ArrayList<Pair<String,String>> arrayOfTaskPlusEvent = new ArrayList<Pair<String,String>>();
		for (String taskName: getArrayOfTaskNames()) {
			for (String eventName: getArrayOfEventNamesForTask(taskName)) {
				arrayOfTaskPlusEvent.add(new Pair<String, String>(taskName, eventName));
			}
		}
		
		return arrayOfTaskPlusEvent;
		
	}
	
	public static Integer getCountOfSubscribersForTaskPlusEvent(String taskName, String eventName) {
		Integer count = 0;
		for (ParseTree t : XPath.findAll(tree, "//anonymousFunction", parser))
		{
			for (ParseTree t2 : XPath.findAll(t.getParent(), "/assignmentExpression/unaryExpression//primaryExpression", parser)){
				if ((t2.getChild(0).getText().equals(taskName))){
					if (t2.getParent().getParent().getChild(2).getText().equals(eventName)){
						count++;
					}
				}
			}
		}
		return count;
		
	}
	
	
}
