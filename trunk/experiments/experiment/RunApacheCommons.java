package experiment;

import randoop.main.GenInputsAbstract;


public class RunApacheCommons {
	public static void main(String[] args) {
		GenInputsAbstract.failure_doc = true;
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.obj_select_num = 50;
		GenInputsAbstract.select_closest_type = true;
		GenInputsAbstract.documented_test = "ApacheCommons_Documented_Test";
		GenInputsAbstract.pretty_print = true;
//		failure.main.Main.failed_seq_output
//	       = "./experiments/experiment/ddisolate/apachecommons.gz";
		args = new String[]{"gentests",
				"--classlist=./subjects/apachecommons-collections-faillist.txt",
				"--timelimit=20", "--output-tests=fail",
				"--junit-classname=CommonsCollectionTest",
				"--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}
