
import mcublocks.embeddecy.generator.Generator;


public class EmbeddecyRunner {
	
	public static void main(String[] args) {
		Generator mygenerator = new Generator();
		
		//Забираем параметры
		String embcFilePath = "";
		String compilatorExePath = "";
		for (int i = 0; i < args.length; i++) {
			//Путь к embeddecy-файлу
			if (args[i].equals("-file") && (i < (args.length - 1))) {
				embcFilePath = args[i+1];
			}
			//Путь к компилятору gcc
			if (args[i].equals("-pathtocompiler") && (i < (args.length - 1))) {
				compilatorExePath = args[i+1];
			}
		}
		if (embcFilePath.isEmpty()) {
			System.err.println("Необходимо указать путь к файлу при помощи параметра '-file': " + "\n\t-file <file path> (путь до embeddecy-файла)");
			//return;
		}
		if (compilatorExePath.isEmpty()) {
			System.err.println("Необходимо указать путь к exe-файлу компилятора gcc при помощи параметра '-pathtocompiler': " + "\n\t-pathtocompiler <exe path> (путь до exe-файла)");
			//return;
		}
		
		
		System.out.println(mygenerator.generate(embcFilePath, compilatorExePath));
		//System.out.println(mygenerator.generate("res/event-test/eventtest5.embc", "GNU Tools ARM Embedded/62017-q1-update/bin/arm-none-eabi-gcc.exe"));
	}
}