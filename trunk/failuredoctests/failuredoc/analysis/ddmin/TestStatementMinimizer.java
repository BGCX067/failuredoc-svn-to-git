package failuredoc.analysis.ddmin;

import randoop.main.GenInputsAbstract;

public class TestStatementMinimizer {
	public static void main(String[] args) {
		int choice = 2;
		//invoke the statement minimizer
		GenInputsAbstract.ddmin = true;
		switch(choice) {
		case 0:
			args = new String[]{"gentests", "--testclass=java.util.TreeSet",
					"--testclass=java.util.Collections",
					"--timelimit=8", "--output-tests=fail",
					"--junit-classname=JDKTest",
					"--junit-output-dir=./experiments"};
			break;
		case 1:
			args = new String[]{"gentests",
					"--classlist=./subjects/apachecommons-collections-faillist.txt",
					"--timelimit=20", "--output-tests=fail",
					"--junit-classname=CommonsCollectionTest",
					"--junit-output-dir=./experiments"};
			break;
		case 2:
			//XXX error
			args = new String[]{"gentests",
					"--testclass=org.apache.commons.collections.set.ListOrderedSet",
					"--timelimit=15", "--output-tests=fail",
			        "--junit-classname=ApacheCommonListOrderedSet",
			        "--junit-output-dir=./experiments"};
			break;
		case 3:
			args = new String[]{"gentests","--classlist=./subjects/moneyandtimeclass.txt",
					"--timelimit=13", "--output-tests=fail",
					"--junit-classname=TimeAndMoneyTest"
					, "--junit-output-dir=./experiments"};
			break;
		case 4:
			args = new String[]{"gentests",
					"--classlist=./subjects/apache-commons-maths-failed.txt",
					"--timelimit=20",
					"--output-tests=fail",
				    "--junit-classname=ApacheMathTest",
				    "--junit-output-dir=./experiments"};
			break;
		case 5:
			args = new String[]{"gentests",
					"--classlist=./subjects/apache-primitives-failed.txt",
					"--timelimit=200",
					"--output-tests=fail",
					"--junit-classname=PrimitiveTest", "--junit-output-dir=./experiments"};
			break;
		default:
		    throw new Error("The choice is wrong: " + choice);		
		}
		
		randoop.main.Main.main(args);
	}
}