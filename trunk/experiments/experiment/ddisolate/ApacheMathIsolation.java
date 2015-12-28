package experiment.ddisolate;

import java.util.List;

import randoop.ExecutableSequence;
import randoop.ExecutionVisitor;
import failure.sequence.SequenceFactory;
import failure.sequence.VisitorFactory;

public class ApacheMathIsolation {
	
	// none
	// removable: 
//	remove 19 will lead to no failure
//	remove 44 will lead to no failure
	//none
	
	/**
	 * Buggy
	 * */
	public static void main(String[] args) {
		String fileName = "./experiments/experiment/ddisolate/apachemath.gz";
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
