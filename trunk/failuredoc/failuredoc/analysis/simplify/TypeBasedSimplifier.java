package failuredoc.analysis.simplify;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;

import randoop.ExecutableSequence;
import randoop.MSequence;
import randoop.MultiVisitor;
import randoop.Sequence;
import randoop.Variable;
import randoop.util.Reflection;

public class TypeBasedSimplifier extends AbstractSimplifier {
	
	public TypeBasedSimplifier(Sequence sequence, MultiVisitor visitor) {
		super(sequence, visitor);
	}

	@Override
	public ExecutableSequence simplfy_sequence() {
		//The algorithm works from the last statement,  iteratively
		// change the input vars to a type-compatible one. It uses
		//heuristic to seek the earlies var definition. After each
		//replacement, try to remove unused vars.
		int orig_size = this.eSeq.sequence.size();
		//the index list in original list that has already been removed
		List<Integer> removed_indice = new LinkedList<Integer>();
		//the original sequence value to simplify
		Sequence simplified_sequence = this.eSeq.sequence;
		
		for(int i = orig_size - 1; i > -1; i--) {
			if(removed_indice.contains(i)) {
				continue;
			}
			//get all possible inputs
			int is = super.compute_index_in_simplified_sequence(simplified_sequence, removed_indice, i);
			FDUtils.checkTrue(is >= 0 && is < simplified_sequence.size(), "is is not legal: " + is + ", the simplified sequence length: "
					+ simplified_sequence.size());
			List<Variable> inputVars = simplified_sequence.getInputs(is);
			for(int input_index = 0;  input_index < inputVars.size(); input_index++) {
				Variable var = inputVars.get(input_index);
				int iInSimplified = super.compute_index_in_simplified_sequence(simplified_sequence, removed_indice, i);
				List<Integer> compatible_indices
				  = this.findCompatibleTypeIndicesInReverseOrder(simplified_sequence, iInSimplified, var.getType());
				if(compatible_indices.isEmpty()) {
					continue;
				} else {
					//1. replace var with variables produced by statement in compatible_indices,
					//and then get the smallest one
					int indexInSimplified = super.compute_index_in_simplified_sequence(simplified_sequence, removed_indice,
							i);
					Sequence s = this.replaceVarWithSmallestCompatibleIndex(compatible_indices, removed_indice, simplified_sequence,
							indexInSimplified, input_index);
					//2. remove all unused vars, then keep going
					if(s != null) {
					    simplified_sequence = s;
					    //then remove all unused vars
					    SequenceUnusedVarSimplifier unused_simplifier = new SequenceUnusedVarSimplifier(simplified_sequence, this.visitor);
					    Sequence tmp_sequence = unused_simplifier.simplfy_sequence().sequence;
					    List<Integer> remove_indices_in_orig = new LinkedList<Integer>();
					    for(int removed_index_in_simplified : unused_simplifier.getRemovedIndices()) {
					    	remove_indices_in_orig.add(super.compute_index_in_original_sequence(simplified_sequence, removed_indice,
					    			removed_index_in_simplified));
					    }
					    removed_indice.addAll(remove_indices_in_orig);
					    simplified_sequence = tmp_sequence;
//					    removed_indice.addAll(unused_simplifier.getRemovedIndices());
					}
				}
			}
		}
		
		//add to the list
		this.removed_indices.addAll(removed_indice);
		
		ExecutableSequence ret_eseq = new ExecutableSequence(simplified_sequence);
		ret_eseq.execute(this.visitor);
		return ret_eseq;
	}
	
	//private int compute_index_original_sequence_for
	
	protected Sequence replaceVarWithSmallestCompatibleIndex(List<Integer> replacable_var_index,
			List<Integer> removed_indices, Sequence sequence, int stmt_num, int input_index) {
		Collections.sort(replacable_var_index); //not necessary?
		for(int i : replacable_var_index) {
			Variable replace_var = sequence.getVariable(i);
			MSequence msequence = sequence.toModifiableSequence();
			msequence.getInputs(stmt_num).remove(input_index);
			msequence.getInputs(stmt_num).add(input_index, msequence.getVariable(replace_var.index));
			Sequence replaced_sequence = msequence.toImmutableSequence();
			ExecutableSequence eSeq = new ExecutableSequence(replaced_sequence);
			eSeq.execute(this.visitor);
			//check if we got the same error
			if(!eSeq.hasFailure()) {
				continue;
			} else {
				int failure_index = eSeq.getFailureIndex();
				int failure_index_in_orig = super.compute_index_in_original_sequence(sequence, removed_indices, failure_index);
				if(failure_index_in_orig == super.getFailureIndex()) {
					return replaced_sequence;
				}
			}
		}
		//nothing found
		return null;
	}
	
	/**Not including maxIndex*/
	protected List<Integer> findCompatibleTypeIndicesInReverseOrder(Sequence sequence, int maxIndex, Class<?> type) {
		FDUtils.checkTrue(maxIndex >= 0 && maxIndex < sequence.size(), "The max index: "
				+ maxIndex + " is not correct for sequence size: " + sequence.size());
		List<Integer> compatible_indices = new LinkedList<Integer>();
		for(int i = 0 ; i < maxIndex; i++) {
			Class<?> outputType = sequence.getStatementKind(i).getOutputType();
			if(Reflection.canBeUsedAs(outputType, type)) {
				compatible_indices.add(i);
			}
		}
		return compatible_indices;
	}
}
