package experiment.command;

import java.util.List;

import junit.framework.TestCase;

import randoop.Sequence;
import failure.sequence.SequenceParser;

public class ExplainFailedArrayListToArray extends TestCase {
	
	public static void test(String[] args) {
		String fileName = "./experiments/experiment/command/jdktoarray_fail.txt";
		SequenceParser parser = new SequenceParser(fileName);
		List<Sequence> seqs = parser.parseSequences();
		assertTrue(seqs.size() == 1);
		System.out.println(seqs.get(0));
	}
	
	public static void main(String[] args) {
		args = new String[]{"--failed_tests=./experiments/experiment/command/jdktoarray_fail.txt",
				"--forbidden_exception=java.lang.ArrayStoreException",
		        "--documented_test=ToArrayDocumented_Command"};
        failure.main.Main.main(args);
	}
}
