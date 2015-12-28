package failure.doc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import randoop.Globals;
import randoop.util.Files;

public class JUnitTestsWriter {
	
	public static void writeJUnits(List<String> contents, String fileName, String packageName, String path) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("import junit.framework.TestCase;");
		sb.append(Globals.lineSep);
		
		sb.append("public class " + fileName + " extends TestCase {");
		sb.append(Globals.lineSep);
		
		int count = 0;
		for(String content : contents) {
			sb.append("  public void test" + (count++) + "() throws Throwable {");
			sb.append(Globals.lineSep);
			sb.append(indent(content));
			sb.append("  }");
			sb.append(Globals.lineSep);
		}
		
		sb.append("}");
		sb.append(Globals.lineSep);
		
		try {
			Files.writeToFile(sb.toString(), new File(path + "/" + fileName + ".java" ));
			System.out.println("Create docummented file: " + path + "/" + fileName + ".java");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String indent(String codeString) {
	    StringBuilder indented = new StringBuilder();
	    String[] lines = codeString.split(Globals.lineSep);
	    for (String line : lines) {
	      indented.append("    " + line + Globals.lineSep);
	    }
	    return indented.toString();
	  }
	
}