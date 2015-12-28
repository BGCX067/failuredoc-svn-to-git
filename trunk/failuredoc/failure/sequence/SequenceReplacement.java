package failure.sequence;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import failure.FDUtils;
import failure.doc.VarUtils;
import randoop.ExecutableSequence;
import randoop.MSequence;
import randoop.MStatement;
import randoop.MVariable;
import randoop.Sequence;
import randoop.util.Reflection;

public class SequenceReplacement {
	
	/**
	 * For experiments, the total number of execution traces
	 * */
	public static int replacement_traces = 0;
	
	public static Sequence replaceToNewSequence(ExecutableSequence eSeq,
			int replace_index, Sequence replacing_sequence) {
		FDUtils.checkNull(eSeq, "The eSeq object could not be null.");
		FDUtils.checkTrue(replace_index >= 0 && replace_index < eSeq.sequence.size(),
				"replace_index: " + replace_index + " is not valid, eSeq.sequences.size is : "
				+ eSeq.sequence.size());
		FDUtils.checkNull(replacing_sequence, "The replacing_sequence could not be null.");
		
		//get a mutable sequence
		MSequence msequence = eSeq.sequence.toModifiableSequence();
		List<MStatement> mstatements = msequence.statements;

		
		MStatement mstatement = mstatements.get(replace_index);

		//mstatement.result. the type of the result
		MVariable mv = mstatement.result;			
		Class<?> varType = mv.getType();
		
		//how this variable is used 
		Set<Class<?>> typeSet = VarUtils.findUsedAsType(msequence, mv/*, stmt_index*/);
		Class<?> bound = VarUtils.computeLowerType(typeSet);//computeUpboundType(typeSet);
		//which statement used this variable
		List<MStatement> usedStmts = VarUtils.usedStatement(msequence, mv);

		Class<?> type = (bound == null)? varType : bound;
		Sequence candidate = replacing_sequence;
		
//		System.out.println("\n\ntype: " + type.getName());
//		System.out.println("last var: " + candidate.getLastVariable().getType().getName());
		
//		PalusUtil.checkTrue(type.isAssignableFrom(candidate.getLastVariable().getType()));
		
		//the candidate to replace
		MSequence mcandidate = candidate.toModifiableSequence();
		MVariable outputVar = mcandidate.getVariable(mcandidate.size() - 1);
		
		//go through each statement to decide which one to replace
		//for (MStatement mstatement : mstatements) {
		for(int stmt_index = 0; stmt_index < mstatements.size(); stmt_index++) {
			if (stmt_index == replace_index) {
				if(candidate.getLastVariable().getType().isPrimitive()) {
					if(!candidate.getLastVariable().getType().equals(type)) {
					    String className = candidate.getLastVariable().getType().getName();
					    FDUtils.checkTrue(type.isAssignableFrom(FDUtils.getBoxedClassForPrimitiveType(className)),
					    		"Type: " + type + " is not an assignable form of: " + className);
					}
				} else {
					if(!type.isAssignableFrom(candidate.getLastVariable().getType())) {
						System.err.println(type.getName() + ",   " + candidate.getLastVariable().getType().getName());
					}
//					FDUtils.checkTrue(type.isAssignableFrom(candidate.getLastVariable().getType()),
//							"Type: " + type + " is not an assignable form of: " + candidate.getLastVariable().getType());
					FDUtils.checkTrue(Reflection.canBeUsedAs(candidate.getLastVariable().getType(), type),
							"Type: " + type + " is not an assignable form of: " + candidate.getLastVariable().getType());
				}
				//System.err.println(type.getName() + ",   " + candidate.getLastVariable().getType().getName());
				int index = mstatements.indexOf(mstatement);
				mstatements.remove(index);
				
				List<MStatement> newMStatements = new LinkedList<MStatement>();
				Map<MVariable, MVariable> varMap = new LinkedHashMap<MVariable, MVariable>();
				for(MStatement mstmt : mcandidate.statements) {
					List<MVariable> oldInputs = mstmt.inputs;
					List<MVariable> newInputs = new LinkedList<MVariable>();
					for(MVariable oldInput : oldInputs) {
						
						MVariable var = null;
						if(varMap.containsKey(oldInput)) {
							var = varMap.get(oldInput);
						} else {
							var = new MVariable(msequence, oldInput.getName());
							varMap.put(oldInput, var);
						}
						
						newInputs.add(var);//become this!
					}
					MVariable result = null;
					if(varMap.containsKey(mstmt.result)) {
						result = varMap.get(mstmt.result);
					} else {
						result = new MVariable(msequence, mstmt.result.getName());
						varMap.put(mstmt.result, result);
					}
					//MVariable result = new MVariable(msequence, mstmt.result.getName());
					MStatement newMStatement = new MStatement(mstmt.statementKind, newInputs, result);
					newMStatements.add(newMStatement);
				}
				mstatements.addAll(index, newMStatements);
				
				
				for(MStatement usedStmt : usedStmts) {
					int mvIndex = usedStmt.inputs.indexOf(mv); //could be multiple XXXX be aware
					while(mvIndex != -1) {
					    usedStmt.inputs.remove(mvIndex);
					    if(varMap.get(outputVar) == null) {
					    	throw new Error();
					    }
					    usedStmt.inputs.add(mvIndex, /*outputVar*/ varMap.get(outputVar));
					    mvIndex = usedStmt.inputs.indexOf(mv);
					}
				}
				break;
			}
		}
		Sequence newSequence = msequence.toImmutableSequence();
		
		return newSequence;
	}
}
