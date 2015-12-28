package failuredoc.analysis.simplify;

import randoop.main.GenInputsAbstract;

public class TestTypeBasedSimplifier_ApachePrimitives {
	public static void main(String[] args) {
		GenInputsAbstract.simplifying = true;
		GenInputsAbstract.typebased_simplified = true;
		args = new String[]{"gentests",
				"--classlist=./subjects/apache-primitives-failed.txt",
				"--timelimit=200",
				"--output-tests=fail",
				"--junit-classname=PrimitiveTest", "--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}
