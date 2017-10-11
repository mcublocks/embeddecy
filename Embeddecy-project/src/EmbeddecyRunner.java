
import mcublocks.embeddecy.generator.Generator;


public class EmbeddecyRunner {
	
	public static void main(String[] args) {
		Generator mygenerator = new Generator();
		
		//�������� ���������
		String embcFilePath = "";
		String compilatorExePath = "";
		for (int i = 0; i < args.length; i++) {
			//���� � embeddecy-�����
			if (args[i].equals("-file") && (i < (args.length - 1))) {
				embcFilePath = args[i+1];
			}
			//���� � ����������� gcc
			if (args[i].equals("-pathtocompiler") && (i < (args.length - 1))) {
				compilatorExePath = args[i+1];
			}
		}
		if (embcFilePath.isEmpty()) {
			System.err.println("���������� ������� ���� � ����� ��� ������ ��������� '-file': " + "\n\t-file <file path> (���� �� embeddecy-�����)");
			//return;
		}
		if (compilatorExePath.isEmpty()) {
			System.err.println("���������� ������� ���� � exe-����� ����������� gcc ��� ������ ��������� '-pathtocompiler': " + "\n\t-pathtocompiler <exe path> (���� �� exe-�����)");
			//return;
		}
		
		
		System.out.println(mygenerator.generate(embcFilePath, compilatorExePath));
		//System.out.println(mygenerator.generate("res/event-test/eventtest5.embc", "GNU Tools ARM Embedded/62017-q1-update/bin/arm-none-eabi-gcc.exe"));
	}
}