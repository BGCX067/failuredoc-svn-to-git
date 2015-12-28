package failure.doc;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import randoop.MSequence;
import randoop.MStatement;
import randoop.MVariable;

public class VarUtils {

	public static Set<Class<?>> findUsedAsType(MSequence msequence, MVariable mv/*, int stmt_index*/) {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
//		System.out.println();
//		System.out.println("check var: " + mv+ ":    (" + mv.getType() + ")");
		List<MStatement> mstatements = msequence.statements;
		for (MStatement mstatement : mstatements) {
			List<MVariable> inputVars = mstatement.inputs;
			int i = 0;
			for(MVariable inputVar : inputVars) {
				if(inputVar.equals(mv)) {
					//System.out.println(" " + inputVar + "  used as: " + mstatement.statementKind.getInputTypes().get(i));
					classes.add(mstatement.statementKind.getInputTypes().get(i));
				}
				i++;
			}
		}
		//System.out.println();
		return classes;
	}
	
	public static List<MStatement> usedStatement(MSequence msequence, MVariable mv) {
		List<MStatement> usedStmts = new LinkedList<MStatement>();
		
		List<MStatement> mstatements = msequence.statements;
		for (MStatement mstatement : mstatements) {
			List<MVariable> inputVars = mstatement.inputs;
//			int i = 0;
			for(MVariable inputVar : inputVars) {
				if(inputVar.equals(mv)) {
					usedStmts.add(mstatement);
					break;
				}
			}
		}
		
		return usedStmts;
	}
	
	public static Class<?> computeLowerType(Set<Class<?>> typeSet) {
		Class<?> currentBound = null;
		
		for(Class<?> type : typeSet) {
			if(currentBound == null) {
				currentBound = type;
			} else {
				if(!type.isAssignableFrom(currentBound)) {
					currentBound = type;
				}
			}
		}
		
		return currentBound;
		
	}
	
	public static Class<?> computeUpboundType(Set<Class<?>> typeSet) {
		Class<?> currentBound = null;
		
		for(Class<?> type : typeSet) {
			if(currentBound == null) {
				currentBound = type;
			} else {
				//check which is the upbound
				if(type.isAssignableFrom(currentBound)) {
					currentBound = type;
				}
			}
		}
		
		//null means this variable is never used afterwards
		return currentBound;
	}
}
