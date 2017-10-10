package mcublocks.embeddecy.utilities;

import mcublocks.embeddecy.codeanalyzer.CodeAnalyzer;
import mcublocks.embeddecy.sources.EmbeddecyParser;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.xpath.XPath;

public class ASTUtilities {
	private static EmbeddecyParser parser;
	private static ParseTree tree;

	public ASTUtilities(EmbeddecyParser parser, ParseTree tree) {
		ASTUtilities.parser = parser;
		ASTUtilities.tree = tree;
	}

	public static boolean isPublicDeclaration(ParseTree rootDecl) {
		for (ParseTree t : XPath
				.findAll(rootDecl, "//publicitySpec//*", parser)) {
			if ((t instanceof TerminalNode)) {
				TerminalNode token = (TerminalNode) t;
				if (token.getText().equals("public")) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isPrivateDeclaration(ParseTree rootDecl) {
		if (isPublicDeclaration(rootDecl)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isVariableDeclaration(ParseTree rootDecl) {
		for (ParseTree t : XPath.findAll(rootDecl,
				"//packageFuncAndVarDefinition/*", parser)) {
			if (ASTUtilities.getNameOfRule(t).equals("declaration")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFunctionDeclaration(ParseTree rootDecl) {
		if (isVariableDeclaration(rootDecl)) {
			return false;
		} else {
			return true;
		}
	}

	public static String getNameOfRule(ParseTree node) {
		if ((node instanceof RuleContext)
				&& (((RuleContext) node).getRuleIndex() >= 0)) {
			RuleContext r = (RuleContext) node;
			return parser.getRuleNames()[r.getRuleIndex()];
		}
		return "";
	}

	public static String getTypeOfToken(ParseTree node) {
		if (node instanceof TerminalNode) {
			TerminalNode token = (TerminalNode) node;
			return parser.getVocabulary().getSymbolicName(
					token.getSymbol().getType());
		}
		return "";
	}

	public static String getTokenText(ParseTree node) {
		StringBuilder builder = new StringBuilder("");
		if ((node instanceof TerminalNode)) {
			TerminalNode token2 = (TerminalNode) node;
			builder.append(token2.getText() + " ");
		}
		// Перенос на новую строку для удобства чтения кода
		if ((node instanceof TerminalNode)
				&& (node.getText().equals(";"))
				|| ((node instanceof TerminalNode) && (node.getText()
						.equals("{")))
				|| ((node instanceof TerminalNode) && (node.getText()
						.equals("}")))) {
			builder.append("\n");
		}

		return builder.toString();
	}

	public static boolean isArgumentExpressionList(ParseTree node) {

		while (node.getParent() != null) {
			// System.out.println(node.toStringTree(parser));
			if (ASTUtilities.getNameOfRule(node).equals(
					"argumentExpressionList"))
				return true;
			else
				node = node.getParent();
		}
		return false;
	}

	public static String getTokensWithSeparatorSpace(ParseTree node) {
		StringBuilder builder = new StringBuilder("");
		for (ParseTree t : XPath.findAll(node, "//*", parser)) {
			if ((t instanceof TerminalNode)) {
				TerminalNode token2 = (TerminalNode) t;
				builder.append(token2.getText() + " ");
			}
			// Перенос на новую строку для удобства чтения кода
			if ((t instanceof TerminalNode)
					&& (t.getText().equals(";"))
					|| ((t instanceof TerminalNode) && (t.getText().equals("{")))
					|| ((t instanceof TerminalNode) && (t.getText().equals("}")))) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}

	public static String getParentTask(ParseTree node) {
		while (node.getParent() != null) {
			if (getNameOfRule(node).equals("taskDefinition")) {
				for (int i = 0; i < node.getChildCount(); i++) {
					if (getNameOfRule(node.getChild(i)).equals("taskName")) {
						return node.getChild(i).getText();
					}
				}
			}
			node = node.getParent();
		}
		return "";
	}

	public static boolean hasParentByHashForNode(ParseTree node, int hash) {
		while (node.getParent() != null) {
			if (node.hashCode() == hash) {
				return true;
			}
			node = node.getParent();
		}
		return false;
	}

	public static Pair<String, String> getTaskAndEventNamesByAssignmentExpr(ParseTree pt) {
		// пара <имя задачи, имя события>
		String taskName = "", eventName = "";
		for (ParseTree t2 : XPath.findAll(pt,
				"/assignmentExpression/unaryExpression//primaryExpression",
				parser)) {
			taskName = t2.getChild(0).getText();
			eventName = t2.getParent().getParent().getChild(2).getText();
		}
		return new Pair<String, String>(taskName, eventName);
	}
	
	public static Boolean isAnonymousFunctionByAssignmentExpression(ParseTree pt) {
		if ((ASTUtilities.getNameOfRule(pt).equals("assignmentExpression"))
				&& ((pt.getChild(2) != null) && (ASTUtilities.getNameOfRule(pt
						.getChild(2)).equals("anonymousFunction")))) {
			return true;
		} else {
			return false;
		}
	}
	
}
