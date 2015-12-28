package experiment;

import failure.main.Main;
import randoop.main.GenInputsAbstract;

public class RunTimeAndMoney {
	
	public static void main(String[] args) {
		GenInputsAbstract.failure_doc = true;
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.documented_test = "TimeAndMoney_Documented_Test";
		//GenInputsAbstract.aggressive_pruning = true;
		GenInputsAbstract.remove_likely_useless = true;
		GenInputsAbstract.pretty_print = true;
		Main.failed_seq_output = "./experiments/experiment/ddisolate/timeandmoney.gz";
		args = new String[]{"gentests","--classlist=./subjects/moneyandtimeclass.txt",
				"--timelimit=13", "--output-tests=fail",
				"--junit-classname=TimeAndMoneyTest"
				, "--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}

}
