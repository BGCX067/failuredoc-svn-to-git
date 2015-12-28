package failuredoc.analysis.simplify;

import randoop.main.GenInputsAbstract;

public class TestTypeBasedSimplifier_ApacheCommonsListOrderedSet {
	public static void main(String[] args) {
		GenInputsAbstract.simplifying = true;
		GenInputsAbstract.typebased_simplified = true;
		args = new String[]{"gentests",
				"--testclass=org.apache.commons.collections.set.ListOrderedSet",
				"--timelimit=15", "--output-tests=fail",
		        "--junit-classname=ApacheCommonListOrderedSet",
		        "--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}
