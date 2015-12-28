package experiment.ddisolate;

import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;
import failuredoc.analysis.simplify.SequenceSimplifyUtils;
import randoop.ExecutableSequence;
import randoop.ExecutionVisitor;
import randoop.Sequence;

/**
 * It uses a minimzation algorithm to isolate a small
 * set of faulty statements
 * */
public class FaultyStatementIsolator {

	public final ExecutableSequence failed_sequence;
	
	public final ExecutionVisitor visitor;
	
	public final int failed_index;
	
	public FaultyStatementIsolator(Sequence seq, ExecutionVisitor visitor) {
		this.failed_sequence = new ExecutableSequence(seq);
		this.visitor = visitor;
		this.failed_sequence.execute(this.visitor);
		FDUtils.checkTrue(this.failed_sequence.hasFailure(), "The given executable sequence should fail.");
		this.failed_index = this.failed_sequence.getFailureIndex();
	}
	
	public Integer[] isolateFaultyStatementIndices() {
		//repeatedly removes a statement to see if
		//it can be removed, and the test passes
		int length = this.failed_sequence.sequence.size();
		List<Integer> removable_index = new LinkedList<Integer>();
		for(int i = 0; i < length; i++) {
			if(i == this.failed_index) {
				System.out.println("skip the failure index: " + i);
				continue;
			}
			boolean canRemove = SequenceSimplifyUtils.isStatementRemovable(this.failed_sequence.sequence, i);
			//System.out.println("can remove index: " + i + "? " + canRemove);
			if(!canRemove) {
				continue;
			} else {
				Sequence simplifiedSequence = SequenceSimplifyUtils.removeStatement(this.failed_sequence.sequence, i);
				ExecutableSequence simplifiedESeq = new ExecutableSequence(simplifiedSequence);
				simplifiedESeq.execute(this.visitor);
				if(simplifiedESeq.hasFailure()) {
					continue;
				} else {
					System.out.println("remove " + i + " will lead to no failure");
					int failedIndexInSimplified = simplifiedESeq.getFailureIndex();
					boolean sameFailure = (i > this.failed_index) ? failedIndexInSimplified == this.failed_index
							: failedIndexInSimplified + 1 == this.failed_index;
					if(sameFailure) {
						removable_index.add(i);
					}
				}
			}
		}
		return removable_index.toArray(new Integer[0]);
	}
}