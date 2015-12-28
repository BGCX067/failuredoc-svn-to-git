package experiment.ddisolate;

import java.util.List;

import randoop.ExecutableSequence;
import randoop.ExecutionVisitor;

import failure.sequence.SequenceFactory;
import failure.sequence.VisitorFactory;

public class JDKBugIsolation {

	//None of the statemnts can be removed
	public static void main(String[] args) {
		String fileName = "./experiments/experiment/ddisolate/jdkbug.gz";
		List<ExecutableSequence> eseqs = SequenceFactory.readSequencesFromFile(fileName);
//		System.out.println("size: " + eseqs.size());
//		System.out.println(eseqs.get(0));
		
		for(ExecutableSequence eseq : eseqs) {
			ExecutionVisitor visitor = VisitorFactory.createDefaultVisitor();
			FaultyStatementIsolator isolator = new FaultyStatementIsolator(eseq.sequence, visitor);
			Integer[] ints = isolator.isolateFaultyStatementIndices();
			for(int i : ints) {
				System.out.println(" - " + i);
			}
		}
	}
	
}