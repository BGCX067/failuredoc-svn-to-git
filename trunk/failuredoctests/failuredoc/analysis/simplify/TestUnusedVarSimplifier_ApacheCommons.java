package failuredoc.analysis.simplify;

import randoop.main.GenInputsAbstract;

public class TestUnusedVarSimplifier_ApacheCommons {
	
	public static void main(String[] args) {
		GenInputsAbstract.simplifying = true;
		args = new String[]{"gentests",
				"--classlist=./subjects/apachecommons-collections-faillist.txt",
				"--timelimit=20", "--output-tests=fail",
				"--junit-classname=CommonsCollectionTest",
				"--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
	
}