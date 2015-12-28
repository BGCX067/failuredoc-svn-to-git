package experiment.ddisolate;

import java.util.List;

import randoop.ExecutableSequence;
import randoop.ExecutionVisitor;
import randoop.Sequence;
import failure.sequence.SequenceFactory;
import failure.sequence.VisitorFactory;

public class TimeAndMoneyIsolation {
	
	/**
	 * None of the
	 * */
	
	public static void main(String[] args) {
	String fileName = "./experiments/experiment/ddisolate/timeandmoney.gz";
	List<ExecutableSequence> eseqs = SequenceFactory.readSequencesFromFile(fileName);
	System.out.println("size: " + eseqs.size());
	
	for(ExecutableSequence eseq : eseqs) {
		ExecutionVisitor visitor = VisitorFactory.createDefaultVisitor();
		FaultyStatementIsolator isolator = new FaultyStatementIsolator(eseq.sequence, visitor);
		Integer[] ints = isolator.isolateFaultyStatementIndices();
		System.out.println(eseq.toCodeString());
		for(int i : ints) {
			System.out.println(" - " + i);
		}
		System.out.println("   --------------------- ");
	}
	}
}
