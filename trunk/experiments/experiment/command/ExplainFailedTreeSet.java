package experiment.command;

public class ExplainFailedTreeSet {
	public static void main(String[] args) {
		args = new String[]{"--failed_tests=./failuredoctests/failure/sequence/treeset_fail.txt",
				"--documented_test=TreeSetDocumented_Command"};
		failure.main.Main.main(args);
	}
}