package experiment.ddisolate;

import randoop.ExecutionVisitor;
import randoop.Sequence;
import randoop.main.GenInputsAbstract;
import failure.sequence.SequenceFactory;
import failure.sequence.VisitorFactory;

//remove 4 will lead to no failure
//skip the failure index: 6

public class ToArrayBugIsolation {
	public static void main(String[] args) {
		Sequence treeSetSequence = SequenceFactory.createJDKReportedFailedSequence();
		ExecutionVisitor visitor = VisitorFactory.createDefaultVisitor();
		GenInputsAbstract.forbidden_exception = "java.lang.ArrayStoreException";
		FaultyStatementIsolator isolator = new FaultyStatementIsolator(treeSetSequence, visitor);
		Integer[] ints = isolator.isolateFaultyStatementIndices();
		for(Integer i : ints) {
			System.out.println(" - " + i);
		}
	}
}
