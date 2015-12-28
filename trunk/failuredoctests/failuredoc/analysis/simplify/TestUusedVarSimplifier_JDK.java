package failuredoc.analysis.simplify;

import randoop.main.GenInputsAbstract;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestUusedVarSimplifier_JDK extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestUusedVarSimplifier_JDK.class);
	}
	
	public static void main(String[] args) {
		GenInputsAbstract.simplifying = true;
		GenInputsAbstract.long_format = true;
		args = new String[]{"gentests", "--testclass=java.util.TreeSet",
				"--testclass=java.util.Collections",
				"--timelimit=8", "--output-tests=fail",
				"--junit-classname=JDKTest",
				"--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
	
}