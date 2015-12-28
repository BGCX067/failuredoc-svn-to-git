package failuredoc.analysis.simplify;

import randoop.main.GenInputsAbstract;

public class TestTypeBasedSimplifier_TimeAndMoney {
	public static void main(String[] args) {
		GenInputsAbstract.simplifying = true;
		GenInputsAbstract.typebased_simplified = true;
		args = new String[]{"gentests","--classlist=./subjects/moneyandtimeclass.txt",
				"--timelimit=13", "--output-tests=fail",
				"--junit-classname=TimeAndMoneyTest"
				, "--junit-output-dir=./experiments"};
		randoop.main.Main.main(args);
	}
}