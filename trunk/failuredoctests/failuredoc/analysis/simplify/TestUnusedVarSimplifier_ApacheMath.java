package failuredoc.analysis.simplify;

import randoop.main.GenInputsAbstract;

public class TestUnusedVarSimplifier_ApacheMath {
	public static void main(String[] args) {
		GenInputsAbstract.simplifying = true;
		args = new String[]{"gentests",
			"--classlist=./subjects/apache-commons-maths-failed.txt",
			"--timelimit=20",
			"--output-tests=fail",
		    "--junit-classname=ApacheMathTest",
		    "--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}
