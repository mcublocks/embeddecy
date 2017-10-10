package mcublocks.embeddecy.validator;

import java.util.ArrayList;

import mcublocks.embeddecy.sources.EmbeddecyParser;
import mcublocks.embeddecy.utilities.ASTUtilities;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.xpath.XPath;

public class Validation {

	private static EmbeddecyParser parser;
	private static ParseTree tree;

	public Validation(EmbeddecyParser parser, ParseTree tree) {
		Validation.parser = parser;
		Validation.tree = tree;
	}
	
	private int duplicate(ArrayList<String> arr) {
		int lastDuplElement = -1;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i+1; j < arr.size(); j++) {
				if (arr.get(i).equals(arr.get(j))) {
					lastDuplElement = j;
				}
			}
		}
		return lastDuplElement;
	}
	
	public int validate() {
		
		//Повторное объявление переменных в compoundStatement'ах
		boolean hasErrors = false;
		ArrayList<String> listOfDeclarationNames = new ArrayList<String>();
		ArrayList<Token> listOfDeclNamesPosition = new ArrayList<Token>();
		for (ParseTree t1 : XPath.findAll(tree, "//compoundStatement", parser)) {
			listOfDeclarationNames.clear();
			listOfDeclNamesPosition.clear();
			for (ParseTree t2 : XPath.findAll(t1, "//blockItem", parser)) {
				ParseTree tempT = t2;
				while (!ASTUtilities.getNameOfRule(tempT).equals("compoundStatement")) {
					tempT = tempT.getParent();
				}
				if (!tempT.equals(t1)) continue;
				for (ParseTree t3 : XPath.findAll(t2, "/blockItem/declaration//directDeclarator", parser)) {
					
					ParseTree node = t3.getChild(0);
					
					if (node instanceof TerminalNode) {
						listOfDeclarationNames.add(node.getText());
						TerminalNode token = (TerminalNode) node;
						listOfDeclNamesPosition.add(token.getSymbol());
					}
				}
			}
			int duplicateNameIndex = duplicate(listOfDeclarationNames);
			if (duplicateNameIndex != -1) {
				System.err.println("\nПовторное объявление переменной с именем '"
								+ listOfDeclarationNames.get(duplicateNameIndex)
								+ "':"
								+ listOfDeclNamesPosition.get(duplicateNameIndex).getLine()
								+ ":"
								+ listOfDeclNamesPosition.get(duplicateNameIndex).getCharPositionInLine());
				hasErrors = true;
			}
		}
		
		//Повторное объявление задач
		ArrayList<String> listOfTaskNames = new ArrayList<String>();
		ArrayList<Token> listOfTaskNamesPosition = new ArrayList<Token>();
     	String xpath1 = "//taskDefinition";
     	for (ParseTree t1 : XPath.findAll(tree, xpath1, parser) ) {
     		String xpath2 = "//taskName";
     		for (ParseTree t2 : XPath.findAll(t1, xpath2, parser) ) {
     			listOfTaskNames.add(t2.getText());
     			ParseTree node = t2.getChild(0);
				if (node instanceof TerminalNode) {
					TerminalNode token = (TerminalNode) node;
					listOfTaskNamesPosition.add(token.getSymbol());
				}
     		}
     	}
     	int duplicateNameIndex = duplicate(listOfTaskNames);
		if (duplicateNameIndex != -1) {
			System.err.println("\nПовторное объявление задачи с именем '"
							+ listOfTaskNames.get(duplicateNameIndex)
							+ "':"
							+ listOfTaskNamesPosition.get(duplicateNameIndex).getLine()
							+ ":"
							+ listOfTaskNamesPosition.get(duplicateNameIndex).getCharPositionInLine());
			hasErrors = true;
		}

		// Повторное объявление пакетов
		ArrayList<String> listOfPackageNames = new ArrayList<String>();
		ArrayList<Token> listOfPackageNamesPosition = new ArrayList<Token>();
		xpath1 = "//packageDefinition";
		for (ParseTree t1 : XPath.findAll(tree, xpath1, parser)) {
			String xpath2 = "//packageName";
			for (ParseTree t2 : XPath.findAll(t1, xpath2, parser)) {
				listOfPackageNames.add(t2.getText());
				ParseTree node = t2.getChild(0);
				if (node instanceof TerminalNode) {
					TerminalNode token = (TerminalNode) node;
					listOfPackageNamesPosition.add(token.getSymbol());
				}
			}
		}
		duplicateNameIndex = duplicate(listOfPackageNames);
		if (duplicateNameIndex != -1) {
			System.err.println("\nПовторное объявление пакета с именем '"
					+ listOfPackageNames.get(duplicateNameIndex)
					+ "':"
					+ listOfPackageNamesPosition.get(duplicateNameIndex).getLine()
					+ ":"
					+ listOfPackageNamesPosition.get(duplicateNameIndex)
							.getCharPositionInLine());
			hasErrors = true;
		}
		
		// Повторное объявление глобальных функций
		ArrayList<String> listOfFunctionNames = new ArrayList<String>();
		ArrayList<Token> listOfFunctionNamesPosition = new ArrayList<Token>();
		xpath1 = "//externalDeclaration/functionDefinition/declarator/directDeclarator";
		for (ParseTree t1 : XPath.findAll(tree, xpath1, parser)) {
			String xpath2 = "/!parameterTypeList/!identifierList/Identifier";
			for (ParseTree t2 : XPath.findAll(t1, xpath2, parser)) {
				listOfFunctionNames.add(t2.getText());
				ParseTree node = t2;
				if (node instanceof TerminalNode) {
					TerminalNode token = (TerminalNode) node;
					listOfFunctionNamesPosition.add(token.getSymbol());
				}
			}
		}
		duplicateNameIndex = duplicate(listOfFunctionNames);
		if (duplicateNameIndex != -1) {
			System.err.println("\nПовторное объявление функции с именем '"
					+ listOfFunctionNames.get(duplicateNameIndex)
					+ "':"
					+ listOfFunctionNamesPosition.get(duplicateNameIndex).getLine()
					+ ":"
					+ listOfFunctionNamesPosition.get(duplicateNameIndex)
							.getCharPositionInLine());
			hasErrors = true;
		}
		
		// Повторное объявление глобальных переменных
		ArrayList<String> listOfVariableNames = new ArrayList<String>();
		ArrayList<Token> listOfVariableNamesPosition = new ArrayList<Token>();
		xpath1 = "//externalDeclaration/declaration//initDeclarator";
		for (ParseTree t1 : XPath.findAll(tree, xpath1, parser)) {
			String xpath2 ="initDeclarator/declarator/directDeclarator/Identifier";
			for (ParseTree t2 : XPath.findAll(t1, xpath2, parser)) {
				ParseTree node = t2;
//				while (!(node instanceof TerminalNode)) {
//					node = node.getChild(0);
//				}
				
				if (node instanceof TerminalNode) {
					listOfVariableNames.add(node.getText());
					TerminalNode token = (TerminalNode) node;
					listOfVariableNamesPosition.add(token.getSymbol());
				}
			}
		}
		duplicateNameIndex = duplicate(listOfVariableNames);
		if (duplicateNameIndex != -1) {
			System.err.println("\nПовторное объявление переменной с именем '"
					+ listOfVariableNames.get(duplicateNameIndex)
					+ "':"
					+ listOfVariableNamesPosition.get(duplicateNameIndex)
							.getLine()
					+ ":"
					+ listOfVariableNamesPosition.get(duplicateNameIndex)
							.getCharPositionInLine());
			hasErrors = true;
		}
	
		// Повторное объявление прототипов функций и массивов
		ArrayList<String> listOfFuncPrototypeNames = new ArrayList<String>();
		ArrayList<Token> listOfFuncProtNamesPosition = new ArrayList<Token>();
		xpath1 = "//externalDeclaration//*/initDeclarator/declarator/directDeclarator";
		for (ParseTree t1 : XPath.findAll(tree, xpath1, parser)) {
			String xpath2 = "/!identifierList/!parameterTypeList//directDeclarator";
			for (ParseTree t2 : XPath.findAll(t1, xpath2, parser)) {
				ParseTree node = t2.getChild(0);
				ParseTree tempT = t2;
				boolean isCompound = false;
				while (tempT != null) {
					tempT = tempT.getParent();
					if (ASTUtilities.getNameOfRule(tempT).equals("compoundStatement")) {
						isCompound = true;
					}
				}
				if (isCompound) continue;
				
				if (node instanceof TerminalNode) {
					listOfFuncPrototypeNames.add(node.getText());
					TerminalNode token = (TerminalNode) node;
					listOfFuncProtNamesPosition.add(token.getSymbol());
				}
			}
		}
		duplicateNameIndex = duplicate(listOfFuncPrototypeNames);
		if (duplicateNameIndex != -1) {
			System.err.println("\nПовторное объявление имени '"
					+ listOfFuncPrototypeNames.get(duplicateNameIndex)
					+ "':"
					+ listOfFuncProtNamesPosition.get(duplicateNameIndex)
							.getLine()
					+ ":"
					+ listOfFuncProtNamesPosition.get(duplicateNameIndex)
							.getCharPositionInLine());
			hasErrors = true;
		}
		
		//Дублирование имен
		if (!hasErrors) {
			ArrayList<String> listOfAllNames = new ArrayList<String>();
			ArrayList<Token> listOfAllNamesPosition = new ArrayList<Token>();
			listOfAllNames.addAll(listOfTaskNames);
			listOfAllNames.addAll(listOfPackageNames);
			listOfAllNames.addAll(listOfFunctionNames);
			listOfAllNames.addAll(listOfVariableNames);
			listOfAllNamesPosition.addAll(listOfTaskNamesPosition);
			listOfAllNamesPosition.addAll(listOfPackageNamesPosition);
			listOfAllNamesPosition.addAll(listOfFunctionNamesPosition);
			listOfAllNamesPosition.addAll(listOfVariableNamesPosition);
			duplicateNameIndex = duplicate(listOfAllNames);
			if (duplicateNameIndex != -1) {
				System.err.println("\nДублирование имени '"
						+ listOfAllNames.get(duplicateNameIndex)
						+ "':"
						+ listOfAllNamesPosition.get(duplicateNameIndex)
								.getLine()
						+ ":"
						+ listOfAllNamesPosition.get(duplicateNameIndex)
								.getCharPositionInLine());
				hasErrors = true;
			}
		}
		
		
		if (hasErrors) {
			return 1;
		} else {
			return 0;
		}
	}

}
