package failuredoc.analysis.simplify;

import randoop.main.GenInputsAbstract;

public class TestTypeBasedSimplifier_JDK {
	public static void main(String[] args) {
		GenInputsAbstract.simplifying = true;
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.typebased_simplified = true;
		args = new String[]{"gentests", "--testclass=java.util.TreeSet",
				"--testclass=java.util.Collections",
				"--timelimit=8", "--output-tests=fail",
				"--junit-classname=JDKTest",
				"--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}
