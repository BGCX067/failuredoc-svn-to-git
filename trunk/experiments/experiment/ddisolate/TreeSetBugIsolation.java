package experiment.ddisolate;

import randoop.ExecutionVisitor;
import randoop.Sequence;
import failure.sequence.SequenceFactory;
import failure.sequence.VisitorFactory;

public class TreeSetBugIsolation {

//	remove 3 will lead to no failure
//	skip the failure index: 5

	public static void main(String[] args) {
		Sequence treeSetSequence = SequenceFactory.createTreeSetFailedSequence();
		ExecutionVisitor visitor = VisitorFactory.createDefaultVisitor();
		FaultyStatementIsolator isolator = new FaultyStatementIsolator(treeSetSequence, visitor);
		Integer[] ints = isolator.isolateFaultyStatementIndices();
		for(Integer i : ints) {
			System.out.println(" - " + i);
		}
	}
	
}