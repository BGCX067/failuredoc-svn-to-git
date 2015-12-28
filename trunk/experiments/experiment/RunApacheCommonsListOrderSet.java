package experiment;

import randoop.main.GenInputsAbstract;

public class RunApacheCommonsListOrderSet {
	
	public static void main(String[] args) {
		GenInputsAbstract.failure_doc = true;
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.documented_test = "ApacheListOrderSet_Documented_Test";
		GenInputsAbstract.pretty_print = true;
//		failure.main.Main.failed_seq_output
//	    = "./experiments/experiment/ddisolate/apachecommonlistorderset.gz";
		args = new String[]{"gentests",
				"--testclass=org.apache.commons.collections.set.ListOrderedSet",
				"--timelimit=15", "--output-tests=fail",
		        "--junit-classname=ApacheCommonListOrderedSet",
		        "--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
	
}
