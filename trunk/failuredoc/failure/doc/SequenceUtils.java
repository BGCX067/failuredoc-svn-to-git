package failure.doc;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import failure.FDUtils;

import randoop.ExecutableSequence;
import randoop.MSequence;
import randoop.MStatement;
import randoop.MVariable;
import randoop.Sequence;
import randoop.Variable;
import randoop.util.SimpleList;

public class SequenceUtils {
	public static Sequence chooseShortestWithOutputType(SimpleList<Sequence> candidates, Class<?> output) {
		Sequence retSeq = null;
		for(Sequence s : candidates.toJDKList()) {
			Variable last = s.getLastVariable();
			Class<?> outputType = last.getType();
			if(output.isAssignableFrom(outputType)) {
				if(retSeq == null) {
					retSeq = s;
				} else {
					if(s.size() < retSeq.size()) {
						retSeq = s;
					}
				}
			}
		}
		return retSeq;
	}
	
	public static Sequence chooseOutputWithType(SimpleList<Sequence> candidates, Class<?> output) {
		for(Sequence s : candidates.toJDKList()) {
			Variable last = s.getLastVariable();
			Class<?> outputType = last.getType();
			if(output.isAssignableFrom(outputType)) {
				return s;
			}
		}
		return null;
	}
	
	public static Class<?> getUsedTypeForOutput(ExecutableSequence eSeq, int index) {
		
		FDUtils.checkTrue(index >= 0 && index < eSeq.sequence.size(), "The index: " + index
				+ " is not valid: the sequenc esize: " + eSeq.sequence.size());
		
		Sequence sequence = eSeq.sequence;
		MSequence msequence = sequence.toModifiableSequence();

		//int stmt_index = 0;
		List<MStatement> mstatements = msequence.statements;
		MStatement mstatement = mstatements.get(index);
		
		//mstatement.result. the type of the result
		MVariable mv = mstatement.result;			
		Class<?> varType = mv.getType();
			
		//how this variable is used 
		Set<Class<?>> typeSet = VarUtils.findUsedAsType(msequence, mv);
		Class<?> bound = VarUtils.computeLowerType(typeSet);//VarUtils.computeUpboundType(typeSet);
			
		//if the output of index-th statement is not used
		Class<?> type = (bound == null)? varType : bound;
		
		return type;
		
	}
	
	public static ExecutableSequence sequenceOfFirstNStmts(ExecutableSequence eSeq, int index) {
		
		FDUtils.checkNull(eSeq, "The executable sequence should not be null.");
		FDUtils.checkTrue(index >= 0 && index < eSeq.sequence.size(), "The index: " + index + " is not valid." +
				", given the fact the sequence size; " + eSeq.sequence.size());
		
		MSequence msequence = eSeq.sequence.toModifiableSequence();
		
		List<MStatement> toBeRemoved = new LinkedList<MStatement>();
		for(int i = index + 1 ; i < msequence.statements.size(); i++) {
			toBeRemoved.add(msequence.statements.get(i));
		}
		
		msequence.statements.removeAll(toBeRemoved);
		
		Sequence topIndexSequence = msequence.toImmutableSequence();
		
		return new ExecutableSequence(topIndexSequence);
	}
}
