package experiment;

import failure.main.Main;
import randoop.main.GenInputsAbstract;

public class RunJDKExample {
	public static void main(String[] args) {
		GenInputsAbstract.failure_doc = true;
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.documented_test = "JDK_Documented_Test";
		GenInputsAbstract.obj_select_num = 50;
		//GenInputsAbstract.aggressive_pruning = true;
		GenInputsAbstract.remove_likely_useless = true;
		GenInputsAbstract.pretty_print = true;
		//Main.failed_seq_output = "./experiments/experiment/ddisolate/jdkbug.gz";
		//GenInputsAbstract.adaptive_select = false;
		args = new String[]{"gentests", "--testclass=java.util.TreeSet",
				"--testclass=java.util.Collections",
				"--timelimit=8", "--output-tests=fail",
				"--junit-classname=JDKTest",
				"--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}