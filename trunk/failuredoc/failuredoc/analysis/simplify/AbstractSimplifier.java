package failuredoc.analysis.simplify;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;
import randoop.Check;
import randoop.ExecutableSequence;
import randoop.MultiVisitor;
import randoop.Sequence;

public abstract class AbstractSimplifier {
	
public final Sequence sequenceToSimplify;
	
	public final ExecutableSequence eSeq;
	
	public final MultiVisitor visitor;
	
	public final List<Integer> removed_indices;
	
	public AbstractSimplifier(Sequence sequence, MultiVisitor visitor) {
		FDUtils.checkNull(sequence, "The input sequence should not be null.");
		FDUtils.checkNull(visitor, "The visitor can not be null.");
		this.sequenceToSimplify = sequence;
		this.visitor = visitor;
		this.eSeq = this.execute_sequence(this.sequenceToSimplify, this.visitor);
		//the sequence should not have non-executed sequence
		FDUtils.checkTrue(this.eSeq.hasFailure(), "The input sequence should fail!");
		FDUtils.checkTrue(!this.eSeq.hasNonExecutedStatements(),
				"The sequence should not have non-executed statement.");
		this.removed_indices = new LinkedList<Integer>();
	}
	
	abstract public ExecutableSequence simplfy_sequence();
	
	protected List<Integer> getRemovedIndices() {
		return this.removed_indices;
	}
	
	protected ExecutableSequence execute_sequence(Sequence sequenceToSimplify, MultiVisitor visitor) {
		ExecutableSequence eseq = new ExecutableSequence(sequenceToSimplify);
		eseq.execute(visitor);
		return eseq;
	}
	
	protected List<Integer> compute_indices_in_original_sequence(Sequence simplifiedSequence,
			List<Integer> removed_indices, List<Integer> unused_indices) {
		List<Integer> indices_in_original_sequence = new LinkedList<Integer>();
		for(Integer unused_index : unused_indices) {
			Integer original_index = this.compute_index_in_original_sequence(simplifiedSequence,
					removed_indices, unused_index);
			indices_in_original_sequence.add(original_index);
		}
		return indices_in_original_sequence;
	}
	
	/**
	 * Returns the index (of original sequence) in the simplified sequence
	 * */
	protected int compute_index_in_simplified_sequence(Sequence simplifiedSequence,
			List<Integer> removed_indices, Integer indexInOrigSequence) {
		FDUtils.checkTrue(indexInOrigSequence >= 0 && indexInOrigSequence < this.eSeq.sequence.size(),
				"The index in orig sequence: " + indexInOrigSequence + " is not legal!");
		FDUtils.checkTrue(removed_indices.size() + simplifiedSequence.size() == this.eSeq.sequence.size(),
				"The sequence size is not correct, removed index num: " + removed_indices.size()
				+ ", simplifiedSequence size: " + simplifiedSequence.size() + ", orig sequence size: "
				+  this.eSeq.sequence.size());
		FDUtils.checkTrue(!removed_indices.contains(indexInOrigSequence), "The removed_indices can not contains queried" +
				" index: " + indexInOrigSequence);
		//the index for return in simplified sequence
		int index_in_simplified = -1;
		for(int i = 0; i < this.eSeq.sequence.size(); i++) {
			if(removed_indices.contains(i)) {
				continue;
			}
			index_in_simplified++;
			if( i == indexInOrigSequence) {
				break;
			}
		}
		
		FDUtils.checkTrue(index_in_simplified >= 0 && index_in_simplified < simplifiedSequence.size(),
				"The index is illegal: " + index_in_simplified + ", the size of sequence: " + simplifiedSequence.size()
				+ ", indexInOriginalSequence: " + indexInOrigSequence + ", the removed index: " + removed_indices);
		return index_in_simplified;
	}
	
	/**
	 * Returns the index in the original sequence before simplification.
	 * */
	protected int compute_index_in_original_sequence(Sequence simplifiedSequence,
	    List<Integer> removed_indices, Integer indexInSimplifiedSequence) {
		FDUtils.checkTrue(indexInSimplifiedSequence < simplifiedSequence.size(), "The given index: " + indexInSimplifiedSequence
				+ ", is not valid, the total length of simplified: " + simplifiedSequence.size());
		FDUtils.checkTrue(simplifiedSequence.size() + removed_indices.size() == this.sequenceToSimplify.size(),
				"Error in size, simpilified sequence size: " + simplifiedSequence.size() + ", removed index size: " + removed_indices.size()
				+ ", original sequence size: " + this.sequenceToSimplify.size());
		//sort it
		Collections.sort(removed_indices);
		//traverse the original un-simplified sequence
		int countInSimplified = -1;
		for(int i = 0; i < this.sequenceToSimplify.size(); i++) {
			if(removed_indices.contains(i)) {
				continue;
			} else {
				countInSimplified++;
				if(countInSimplified == indexInSimplifiedSequence) {
					return i;
				}
			}
		}
		System.out.println("total length of original: " + this.sequenceToSimplify.size());
		System.out.println("removed_indices: " + removed_indices);
		System.out.println("Count in simplified: " + countInSimplified);
		System.out.println("indexInSimplifiedSequence: " + indexInSimplifiedSequence);
		throw new Error("Should not be here."); 
	}
	
//	public boolean isFailed() {
//		return eSeq.hasFailure();
//	}
	
	/**
	 * XXX using failure index is not sound. It is possible (but
	 * not very likely different failures are thrown from the same
	 * index)
	 * */
	protected int getFailureIndex() {
		return eSeq.getFailureIndex();
	}
	
	protected List<Check> getFailureChecks() {
		List<Check> checklist = new LinkedList<Check>();
		int failure_index = this.getFailureIndex();
		if(failure_index != -1) {
			checklist.addAll(this.eSeq.getFailures(failure_index));
		}
		return checklist;
	}
}
