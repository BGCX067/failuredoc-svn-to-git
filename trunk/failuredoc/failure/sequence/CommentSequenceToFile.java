package failure.sequence;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import randoop.ExecutableSequence;
import randoop.main.GenInputsAbstract;
import randoop.util.Log;

public class CommentSequenceToFile {
	
	public static String output_dir = "./failuredoc/tests";
	
	public static String output_package = "tests";
	
	public static String junit_class_name = "CommentedTest";
	
	public static void outputCommentedSequence(List<SequenceOutputAfterCommenting> seqComment) {
		String className = junit_class_name;
	    File file = new File(output_dir, className + ".java");
	    PrintStream out = createTextOutputStream(file);

	    try{
	      outputPackageName(out);
	      out.println();
	      out.println("import junit.framework.*;");
	      out.println();
	      out.println("public class " + className + " extends TestCase {");
	      out.println();
	      writeMain(out, className);
	      out.println();
	      int testCounter = 1;
	      for (SequenceOutputAfterCommenting fault : seqComment) {
	        out.println("  public void test" + testCounter++ + "() throws Throwable {");
	        out.println();
	        out.println(fault.outputCommentedSequence());
	        out.println("  }");
	        out.println();
	      }
	      out.println("}");
	    } finally {
	      if (out != null)
	        out.close();
	    }
	}
	
	private static void writeMain(PrintStream out, String className) {
	    out.println("  // Runs all the tests in this file.");
	    out.println("  public static void main(String[] args) {");
	    if (GenInputsAbstract.init_routine != null)
	      out.println ("    " + GenInputsAbstract.init_routine + "();");
	    out.println("    junit.textui.TestRunner.run(" + className + ".class);");
	    out.println("  }");
	  }
	
	private static PrintStream createTextOutputStream(File file) {
	    try {
	      return new PrintStream(file);
	    } catch (IOException e) {
	      Log.out.println("Exception thrown while creating text print stream:" + file.getName());
	      e.printStackTrace();
	      System.exit(1);
	      return null;//make compiler happy
	    }
	  }

	private static void outputPackageName(PrintStream out) {
	    boolean isDefaultPackage= output_package.length() == 0;
	    if (!isDefaultPackage)
	      out.println("package " + output_package + ";");
	  }
}
