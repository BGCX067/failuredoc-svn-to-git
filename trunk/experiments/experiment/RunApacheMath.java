package experiment;

import randoop.main.GenInputsAbstract;

public class RunApacheMath {
	public static void main(String[] args) {
		GenInputsAbstract.failure_doc = true;
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.documented_test = "ApacheMath_Documented_Test";
		GenInputsAbstract.remove_likely_useless = true;
		GenInputsAbstract.use_profile_over_value = true;
		GenInputsAbstract.pretty_print = true;
//		GenInputsAbstract.remove_likely_useless = true;
//		GenInputsAbstract.adaptive_select = true;
//		GenInputsAbstract.obj_select_num = 50;
//		GenInputsAbstract.
		//GenInputsAbstract.adaptive_select = false;
//		failure.main.Main.failed_seq_output
//		    = "./experiments/experiment/ddisolate/apachemath.gz";
		args = new String[]{"gentests",
			"--classlist=./subjects/apache-commons-maths-failed.txt",
			"--timelimit=20",
			"--output-tests=fail",
		    "--junit-classname=ApacheMathTest",
		    "--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}