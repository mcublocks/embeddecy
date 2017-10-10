package mcublocks.embeddecy.generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import mcublocks.embeddecy.codeanalyzer.CodeAnalyzer;
import mcublocks.embeddecy.sources.EmbeddecyParser;
import mcublocks.embeddecy.utilities.ASTUtilities;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.xpath.XPath;

public class Package {

	private EmbeddecyParser parser;
	private ParseTree tree;

	public Package(EmbeddecyParser parser, ParseTree tree) {
		this.parser = parser;
		this.tree = tree;
	}
	
	public void generatePackages() {
		
		if (CodeAnalyzer.getArrayOfPackageNames().isEmpty()) {
			return;
		}
		
		
		// √енераци€ h-файлов
		StringBuilder builderH = new StringBuilder();
		StringBuilder builderC = new StringBuilder();
		
		String xpath = "//*";
		for (ParseTree t : XPath.findAll(tree, xpath, parser)) {
			// ќбъ€вленные пакеты
			String xpath4 = "/packageDefinition";
			for (ParseTree t4 : XPath.findAll(t, xpath4, parser)) {
				builderH.setLength(0);
				builderC.setLength(0);
				String xpath5 = "//packageName";
				String packName = "";
				
				// GENERATION H-FILE---------------------------------------------------------------------
				// Include guard
				for (ParseTree t5 : XPath.findAll(t4, xpath5, parser)) {
					packName = t5.getText();
					builderH.append("#ifndef _" + packName.toUpperCase()
							+ "_H_\n");
					builderH.append("#define _" + packName.toUpperCase()
							+ "_H_\n\n");
				}
				
				// ќбъ€влени€ прототипов публичных функций и переменных в
				// .h-файле
				for (ParseTree t7 : XPath.findAll(t4, "//packageFuncAndVarDefinition", parser)) {
					if (ASTUtilities.isPublicDeclaration(t7)) {
						for (ParseTree t9 : XPath
								.findAll(
										t7,
										"//packageFuncAndVarDefinition/!publicitySpec/!compoundStatement//*",
										parser)) {
							if (ASTUtilities.getTypeOfToken(t9).equals("Identifier")) {
								builderH.append("_" + packName + "_");
							}
							builderH.append(ASTUtilities.getTokenText(t9));
						}
						builderH.append(";\n");
					}
				}
				builderH.append("\n#endif");
				
				// GENERATION C-FILE---------------------------------------------------------------------
				// ƒобавление includ'ов в .c-файл
				builderC.append("#include \"_" + packName + ".h\"\n\n");
				
				// –еализации публичных функций и объ€влени€ приватных
				// переменных в .c-файле
				for (ParseTree t6 : XPath.findAll(t4,
						"//packageFuncAndVarDefinition", parser)) {
					if (!((ASTUtilities.isPublicDeclaration(t6))
							&& (ASTUtilities.isVariableDeclaration(t6)))) {
						String xpath7 = "//packageFuncAndVarDefinition/!publicitySpec//*";
						for (ParseTree t7 : XPath.findAll(t6, xpath7, parser)) {
							if (ASTUtilities.getTypeOfToken(t7).equals("Identifier")) {
								builderC.append("_" + packName + "_");
							}
							builderC.append(ASTUtilities.getTokenText(t7));
						}
					}
				}
				
				// —генерировать .h - файл
				PrintWriter writerH = null;
				try {
					writerH = new PrintWriter("_" + packName + ".h", "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				writerH.println(builderH.toString());
				writerH.close();
				// —генерировать .c - файл
				PrintWriter writerC = null;
				try {
					writerC = new PrintWriter("_" + packName + ".c", "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				writerC.println(builderC.toString());
				writerC.close();
			}

		}


	}


	
}
