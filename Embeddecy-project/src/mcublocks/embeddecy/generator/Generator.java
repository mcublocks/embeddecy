package mcublocks.embeddecy.generator;

import mcublocks.embeddecy.sources.EmbeddecyLexer;
import mcublocks.embeddecy.sources.EmbeddecyParser;
import mcublocks.embeddecy.utilities.ASTUtilities;
import mcublocks.embeddecy.validator.Validation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import mcublocks.embeddecy.codeanalyzer.CodeAnalyzer;
import mcublocks.embeddecy.generator.Generator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Generator {

	public String generate(String embcFilePath, String compilatorExePath) {
		
//		CharStream input = null;
//		try {
//			input = CharStreams.fromFileName(embcFilePath);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		
//		EmbeddecyLexer embcLexer = new EmbeddecyLexer(input);
//        CommonTokenStream embcTokens = new CommonTokenStream(embcLexer);
//        EmbeddecyParser embcParser = new EmbeddecyParser(embcTokens);
//        
//        //embcParser.removeErrorListeners();
//        ParseTree embcTree = embcParser.compilationUnit();
//        if (embcParser.getNumberOfSyntaxErrors() > 0) {
//        	return "";
//        }
        
//        //Создание объекта анализатора кода и передача ему AST и парсера
//        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(embcParser, embcTree);
//        //Создание анализатора AST и передача ему дерева и парсера
//        ASTUtilities astUtils = new ASTUtilities(embcParser, embcTree);
        
        
        //Вывод AST-дерева
        //System.out.println("\n"+embcTree.toStringTree(embcParser));

		
		
		//C-preprocessor
		File pathToExecutable = new File( compilatorExePath );
		File pathToEmbcFile = new File( embcFilePath );
		ProcessBuilder pBuilder = new ProcessBuilder( pathToExecutable.getAbsolutePath(), "-xc", "-E", pathToEmbcFile.getAbsolutePath(), "-o", "pfile.i");
		File workingDirectory = new File(embcFilePath).getParentFile();
		if (workingDirectory != null) {
			pBuilder.directory( workingDirectory.getAbsoluteFile() ); // this is where you set the root folder for the executable to run with
		}
		pBuilder.redirectErrorStream(true);
		Process process = null;
		try {
			process = pBuilder.start();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		Scanner s = new Scanner(process.getInputStream());
		StringBuilder text = new StringBuilder();
		while (s.hasNextLine()) {
		  text.append(s.nextLine());
		  text.append("\n");
		}
		s.close();

		int result = 0;
		try {
			result = process.waitFor();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		if ((result == 0) && (text.toString().isEmpty())) {
			System.out.printf(
					"Preprocess exited with result %d. Done!\n %s%n",
					result, text);
		} else {
			if (result == 0) {
				System.out.printf(
						"Preprocess exited with result %d and output:\n %s%n",
						result, text);
			} else {
				System.err.printf(
						"Preprocess exited with result %d and output:\n %s%n",
						result, text);
				return "";
			}
		}

        //Вывод AST-дерева
        //System.out.println("\n"+cprepTree.toStringTree(cprepParser));
		
		
		
		
		//Embeddecy
		CharStream input = null;
		try {
			if (workingDirectory != null) {
				input = CharStreams.fromFileName(workingDirectory+"/pfile.i");
			} else {
				input = CharStreams.fromFileName("pfile.i");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		EmbeddecyLexer embcLexer = new EmbeddecyLexer(input);
        
		CommonTokenStream embcTokens = new CommonTokenStream(embcLexer);
        
		EmbeddecyParser embcParser = new EmbeddecyParser(embcTokens);
		ParseTree embcTree = embcParser.compilationUnit();

        //Вывод AST-дерева
        //System.out.println("\n"+embcTree.toStringTree(embcParser));
        
        
		if (embcParser.getNumberOfSyntaxErrors() > 0) {
			return "";
		} 
		
        //Создание объекта анализатора кода и передача ему AST и парсера
		CodeAnalyzer codeAnalyzer = new CodeAnalyzer(embcParser, embcTree);
        //Создание анализатора AST и передача ему дерева и парсера
		ASTUtilities astUtils = new ASTUtilities(embcParser, embcTree);
        
        
        
        //Валидация
		Validation valid = new Validation(embcParser, embcTree);
		if (valid.validate() != 0) {
			return "";
		}
        
        
        
		Package pkg = new Package(embcParser, embcTree);
        Task tsk = new Task(embcParser, embcTree);
        
        
        
        StringBuilder builder = new StringBuilder();
        pkg.generatePackages();
        builder.append(tsk.generateTasks());
        
        
        
	    return builder.toString();
		
	}

}
