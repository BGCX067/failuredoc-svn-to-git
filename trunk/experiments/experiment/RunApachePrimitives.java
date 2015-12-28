package experiment;

import randoop.main.GenInputsAbstract;

public class RunApachePrimitives {
	
	public static void main(String[] args) {
		GenInputsAbstract.failure_doc = true;
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.obj_select_num = 60;
		GenInputsAbstract.documented_test = "ApachePrimitive_Documented_Test";
		GenInputsAbstract.remove_likely_useless = true;
		GenInputsAbstract.append_example = true;
		GenInputsAbstract.pretty_print = true;
	    failure.main.Main.failed_seq_output = "./experiments/experiment/ddisolate/apacheprimitive.gz";
		args = new String[]{"gentests",
				"--classlist=./subjects/apache-primitives-failed.txt",
				"--timelimit=200",
				"--output-tests=fail",
				"--junit-classname=PrimitiveTest", "--junit-output-dir=./experiments"};
		GenInputsAbstract.failure_doc = true;
		randoop.main.Main.main(args);
	}
}
