package failuredoc.analysis.ddmin;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;
import failuredoc.analysis.simplify.SequenceSimplifyUtils;

import randoop.Check;
import randoop.ExecutableSequence;
import randoop.MultiVisitor;
import randoop.Sequence;

public class StatementMinimizer extends AbstractMinimizer<Integer> {

	public final ExecutableSequence sequenceToSimplify;
	public final MultiVisitor visitor;
	public final int failed_stmt_index;
	//public final List<Check> failed_checks;
	
	public StatementMinimizer(List<Integer> data, ExecutableSequence sequence, MultiVisitor visitor) {
		super(data);
		FDUtils.checkNull(sequence, "the sequence could not be null.");
		FDUtils.checkNull(visitor, "the visitor could not be null.");
		//ugly code
		List<Integer> datalist = new LinkedList<Integer>();
		for(int i = 0; i < sequence.sequence.size(); i++) {
			datalist.add(i);
		}
		FDUtils.checkTrue(data.containsAll(datalist) && datalist.containsAll(data), "not equal");
		this.sequenceToSimplify = sequence;
		this.visitor = visitor;
		//execute the sequence and make sure there is an error, and record the index
		this.sequenceToSimplify.execute(this.visitor);
		FDUtils.checkTrue(this.sequenceToSimplify.hasFailure(), "The executable sequence should have failed!");
		this.failed_stmt_index = this.sequenceToSimplify.getFailureIndex();
		FDUtils.stdln("The failed index: " + this.failed_stmt_index);
		FDUtils.stdln("Length of sequenceToSimplified: " + this.sequenceToSimplify.sequence.size());
		//this.sequenceToSimplify.getFailures(this.failed_stmt_index);
		//this.failed_checks = this.sequenceToSimplify.getFailures(this.failed_stmt_index);
	}
	
	public ExecutableSequence minimize_sequence() {
		List<Integer> minimized_indices = super.minimize();
		FDUtils.stdln("The result after minimization: " + minimized_indices);
		Sequence min_sequence = SequenceSimplifyUtils.retainStatements(this.sequenceToSimplify.sequence, minimized_indices);
		ExecutableSequence eseq = new ExecutableSequence(min_sequence);
		eseq.execute(this.visitor);
//		FDUtils.checkTrue(eseq.hasFailure(), "The simplified sequence should also fail!");
		if(!eseq.hasFailure()) {
			FDUtils.stdln(eseq.toCodeString());
			throw new Error("The simplified sequence can not preserve the buggy behavior.");
		}
		return eseq;
	}

	@Override
	protected boolean is_still_fail(List<Integer> retainedStmtIndices) {
		if(retainedStmtIndices.isEmpty()) {
			return false;
		}
		//get all the removed indices
		List<Integer> remove_indices = new LinkedList<Integer>();
		for(int i = 0; i < this.sequenceToSimplify.sequence.size(); i++) {
			if(!retainedStmtIndices.contains(i)) {
				remove_indices.add(i);
			}
		}
		
		//in other words, remove the following statement
		Collections.sort(retainedStmtIndices);
		Sequence simplifiedSequence
		    = SequenceSimplifyUtils.retainStatements(this.sequenceToSimplify.sequence, retainedStmtIndices);
		if(simplifiedSequence == null) {
			//the failure never occurs
			return false;
		}
		boolean hasSameFailure = this.hasTheSameFailure(simplifiedSequence, remove_indices);
		return hasSameFailure;
	}

	private boolean hasTheSameFailure(Sequence simplifiedSequence, List<Integer> remove_indices) {
		ExecutableSequence eSeq = new ExecutableSequence(simplifiedSequence);
		eSeq.execute(this.visitor);
		if(!eSeq.hasFailure()) {
			return false;
		}
		int indexInSimplifiedSequence = eSeq.getFailureIndex();
		int failed_index_orig_seq = SequenceSimplifyUtils.computeIndexInOriginalSequence(this.sequenceToSimplify.sequence,
				simplifiedSequence, remove_indices, indexInSimplifiedSequence);
		if(failed_index_orig_seq != this.failed_stmt_index) {
			return false;
		}
		return true;
		//XXX need to compare failing checks
//		List<Check> checks = eSeq.getFailures(indexInSimplifiedSequence);
	}
}
