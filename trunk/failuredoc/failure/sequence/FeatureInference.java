package failure.sequence;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import randoop.Globals;
import randoop.util.Randomness;

import failure.FDUtils;
import failure.state.AbstractState;

public class FeatureInference {
	
	public final List<ExecutionResult> all_exec_result;
	
	private final Map<Class<?>, List<ExecutionResult>> results_by_types;
	
	private final Set<Class<?>> typeOfMulResults;
	
	//only for one statement
	public FeatureInference(List<ExecutionResult> results) {
		FDUtils.checkNull(results, "The results should not be null.");
		this.all_exec_result = results;
		results_by_types = this.executionResultsOfTypes();
		this.typeOfMulResults = this.typesOfMulResults();
	}
	
	public Map<Class<?>, List<ExecutionResult>> getExecutionResultsOfTypes() {
		return this.results_by_types;
	}
	
	public Set<Class<?>> getTypeOfMultiResults() {
		return this.typeOfMulResults;
	}
	
	public String inferGeneralResults() {
        StringBuilder sb = new StringBuilder();
		
		Set<Class<?>> typesOfDiffResults = this.typesOfMulResults();
		for(Class<?> clz : typesOfDiffResults) {
			List<ExecutionResult> clz_results = this.results_by_types.get(clz);
			
			Map<ExecutionResult, List<AbstractState>> hasAnalyzed = new LinkedHashMap<ExecutionResult, List<AbstractState>>();
			
			for(ExecutionResult result : clz_results) {
				
				if(!hasAnalyzed.containsKey(result)) {
					hasAnalyzed.put(result, new LinkedList<AbstractState>());
				}
				hasAnalyzed.get(result).add(result.replacing_profile);
			}
			
			//analyze each result,
			for(ExecutionResult result : hasAnalyzed.keySet()) {
			   if(FDUtils.isPrimitiveOrStringType(result.replacing_profile.clz)) {
			       sb.append("General Result is: " + result.explain()
					   + this.analyzePrimitivesAndString(result.replacing_profile.clz, hasAnalyzed.get(result)) + Globals.lineSep);
			   } else {
				  sb.append(this.analyzeNonPrimitiveAndString(result /*not sure*/, hasAnalyzed.get(result)));
				  
				  
			   }
			}
			
		}
		
		return sb.toString();
	}
	
	
	//list is the a list of execution result which has the same result
	public String inferFeatures(List<ExecutionResult> list, Set<Class<?>> typeOfMulResult) {
		
		FDUtils.checkNull(typeOfMulResults, "The type of mul results should not be null.");
		
		StringBuilder sb = new StringBuilder();
		
		
		
		//for other statements
		
		Map<Class<?>, List<AbstractState>> classStateMap = this.extractExecutionResults(list);
		
		for(Class<?> clz : classStateMap.keySet()) {
			
			if(typeOfMulResult.contains(clz)) {
				//has already been analyzed
				continue;
			}
			
			List<AbstractState> states = classStateMap.get(clz);
			if(FDUtils.isPrimitive(clz) || clz.isPrimitive() || FDUtils.isStringType(clz)) {
				sb.append(this.analyzePrimitivesAndString(clz, states));
			} else {
				
				//sb.append(this.analyzeNonPrimitiveAndString(clz, states));
				
			}
		}
		
		//an alternative is to summary non-primitive type here
		List<ExecutionResult> nonprimitive = new ArrayList<ExecutionResult>();
		for(ExecutionResult exec_result : list) {
			if(typeOfMulResult.contains(exec_result.replacing_profile.clz)) {
				continue;
			}
			if(FDUtils.isPrimitiveOrStringType(exec_result.replacing_profile.clz)) {
				continue;
			}
			nonprimitive.add(exec_result);
		}
		
		//or pick up the shortest one
		if(nonprimitive.size() > 0) {
		    ExecutionResult randomResult = FDUtils.shortestSequence(nonprimitive);
		    if(randomResult.replacing_sequence.size() < 5) {
		        sb.append("   random pick up one: " + randomResult.replacing_sequence.toCodeString());
		    }
		}
		
		return sb.toString();
	}
	
	
	private Map<Class<?>, List<ExecutionResult>> executionResultsOfTypes() {
		Map<Class<?>, List<ExecutionResult>> retMap = new LinkedHashMap<Class<?>, List<ExecutionResult>>();
		
		for(ExecutionResult result : all_exec_result) {
			AbstractState state = result.replacing_profile;
			Class<?> clz = state.clz;
			if(!retMap.containsKey(clz)) {
				retMap.put(clz, new LinkedList<ExecutionResult>());
			}
			retMap.get(clz).add(result);
		}
		
		return retMap;
	}

	private Set<Class<?>> typesOfMulResults() {
		Set<Class<?>> types = new LinkedHashSet<Class<?>>();
		for(Class<?> clz : this.results_by_types.keySet()) {
			List<ExecutionResult> resultList = this.results_by_types.get(clz);
			if(resultList.size() < 2) {
				continue;
			}
			for(int i = 0; i < resultList.size(); i++) {
				ExecutionResult currResult = resultList.get(i);
				if( i != resultList.size() - 1) {
					ExecutionResult nextResult = resultList.get(i+1);
					if((currResult.hasFailure() != nextResult.hasFailure())
						|| (currResult.hasExceptionThrown() != nextResult.hasExceptionThrown())
						|| (currResult.hasNonExecuted() != nextResult.hasNonExecuted())) {
						types.add(clz);
						break;
					}
				}
			}
		}
		return types;
	}
	
	//a list of states has the same result
	private String analyzeNonPrimitiveAndString(ExecutionResult result, List<AbstractState> states) {
		StringBuilder sb = new StringBuilder();
		sb.append("General result for nonprimitive: " + result.explain()+  Globals.lineSep);
		for(AbstractState state : states) {
			 sb.append(  "    " + state.toString());
		 }
		sb.append(Globals.lineSep);
		
		return sb.toString();
	}
	
	private String analyzeNonPrimitiveAndString(Class<?> type, List<AbstractState> states) {
		
		StringBuilder sb = new StringBuilder();
		
		//XXX summary here
		sb.append("Did not implemented for class: " + type + " has: " + states.size() + " states " + Globals.lineSep);
		for(AbstractState state : states) {
			sb.append("  " + state + Globals.lineSep);
		}
		
		return sb.toString();
		
	}
	
	//a list of states has the same result
	private String analyzePrimitivesAndString(Class<?> type, List<AbstractState> states) {
		
		StringBuilder sb = new StringBuilder();
		
		if(type == java.lang.String.class) {
			
			boolean includeNull = false;
			boolean includeZeroLength = false;
			boolean includeNonZeroLength = false;
			
			for(AbstractState state : states) {
				if(includeNull && includeZeroLength && includeNonZeroLength) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				if(obj == null) {
					includeNull = true;
				} else {
				    String s = (String)obj;
				    if(s.length() == 0) {
					    includeZeroLength = true;
				    } else {
					    includeNonZeroLength = true;
				    }
				}
			}
			
			if(includeNull && includeZeroLength && includeNonZeroLength) {
				sb.append(" string type, ");
			} else  {
				sb.append("String values except for: " + (!includeNull? "null string " : "")
						+ (!includeZeroLength? "empty string " : "") + (!includeNonZeroLength? "non-empty string" : ""));
			}
			
		} else if (type == java.lang.Integer.class) {
			
			boolean equalZero = false;
			boolean gtZero = false;
			boolean ltZero = false;
			
			for(AbstractState state : states) {
				if(equalZero && gtZero && ltZero) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Integer value = (Integer)obj;
				if(value == 0) {
					equalZero = true;
				} else if (value > 0) {
					gtZero = true;
				} else {
					ltZero = true;
				}
			}
 			
			if(equalZero && gtZero && ltZero) {
				sb.append(" integer type, ");
			} else {
				sb.append("Int values of " + (equalZero ? "0 " : "") + (gtZero ? " >0 " : "") + (ltZero ? " <0 " : ""));
			}
			
		} else if (type == java.lang.Byte.class) {
			boolean equalZero = false;
			boolean gtZero = false;
			boolean ltZero = false;
			
			for(AbstractState state : states) {
				if(equalZero && gtZero && ltZero) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Byte value = (Byte)obj;
				if(value == 0) {
					equalZero = true;
				} else if (value > 0) {
					gtZero = true;
				} else {
					ltZero = true;
				}
			}
 			
			if(equalZero && gtZero && ltZero) {
				sb.append(" byte types, ");
			} else {
				sb.append("Byte values of " + (equalZero ? "0 " : "") + (gtZero ? " >0 " : "") + (ltZero ? " <0 " : ""));
			}
		} else if (type == java.lang.Short.class) {
			boolean equalZero = false;
			boolean gtZero = false;
			boolean ltZero = false;
			
			for(AbstractState state : states) {
				if(equalZero && gtZero && ltZero) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Short value = (Short)obj;
				if(value == 0) {
					equalZero = true;
				} else if (value > 0) {
					gtZero = true;
				} else {
					ltZero = true;
				}
			}
 			
			if(equalZero && gtZero && ltZero) {
				sb.append(" short type, ");
			} else {
				sb.append("Short values of " + (equalZero ? "0 " : "") + (gtZero ? " >0 " : "") + (ltZero ? " <0 " : ""));
			}
		} else if (type == java.lang.Float.class) {
			boolean equalZero = false;
			boolean gtZero = false;
			boolean ltZero = false;
			
			for(AbstractState state : states) {
				if(equalZero && gtZero && ltZero) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Float value = (Float)obj;
				if(value == 0.0f) {
					equalZero = true;
				} else if (value > 0.0f) {
					gtZero = true;
				} else {
					ltZero = true;
				}
			}
 			
			if(equalZero && gtZero && ltZero) {
				sb.append(" float type ");
			} else {
				sb.append("Float values of " + (equalZero ? "0 " : "") + (gtZero ? " >0 " : "") + (ltZero ? " <0 " : ""));
			}
		} else if (type == java.lang.Boolean.class) {
			boolean equalTrue = false;
			boolean equalFalse = false;
			
			for(AbstractState state : states) {
				if(equalTrue && equalFalse) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Boolean value = (Boolean)obj;
				if(value) {
					equalTrue = true;
				} else {
					equalFalse = true;
				}
			}
 			
			if(equalTrue && equalFalse) {
				sb.append(" boolean type, ");
			} else {
				sb.append("Boolean values of " + (equalTrue ? "true " : "") + (equalFalse ? " false " : ""));
			}
		} else if (type == java.lang.Short.class) {
			boolean equalZero = false;
			boolean gtZero = false;
			boolean ltZero = false;
			
			for(AbstractState state : states) {
				if(equalZero && gtZero && ltZero) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Short value = (Short)obj;
				if(value == 0) {
					equalZero = true;
				} else if (value > 0) {
					gtZero = true;
				} else {
					ltZero = true;
				}
			}
 			
			if(equalZero && gtZero && ltZero) {
				sb.append("short type, ");
			} else {
				sb.append("Short values of " + (equalZero ? "0 " : "") + (gtZero ? " >0 " : "") + (ltZero ? " <0 " : ""));
			}
		} else if (type == java.lang.Long.class) {
			boolean equalZero = false;
			boolean gtZero = false;
			boolean ltZero = false;
			
			for(AbstractState state : states) {
				if(equalZero && gtZero && ltZero) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Long value = (Long)obj;
				if(value == 0l) {
					equalZero = true;
				} else if (value > 0l) {
					gtZero = true;
				} else {
					ltZero = true;
				}
			}
 			
			if(equalZero && gtZero && ltZero) {
				sb.append("long type, ");
			} else {
				sb.append("Long values of " + (equalZero ? "0 " : "") + (gtZero ? " >0 " : "") + (ltZero ? " <0 " : ""));
			}
		} else if (type == java.lang.Double.class) {
			boolean equalZero = false;
			boolean gtZero = false;
			boolean ltZero = false;
			
			for(AbstractState state : states) {
				if(equalZero && gtZero && ltZero) {
					break;
				}
				Object obj = state.valueOfPrimitiveOrString;
				Double value = (Double)obj;
				if(value == 0.0d) {
					equalZero = true;
				} else if (value > 0.0d) {
					gtZero = true;
				} else {
					ltZero = true;
				}
			}
 			
			if(equalZero && gtZero && ltZero) {
				sb.append("double type, ");
			} else {
				sb.append("Double values of " + (equalZero ? "0 " : "") + (gtZero ? " >0 " : "") + (ltZero ? " <0 " : ""));
			}
		} else if (type == java.lang.Character.class) {
			
		} else {
			throw new RuntimeException(type.getName() + " is not primitive!");
		}
		
		return sb.toString();
	}
	
	private Map<Class<?>, List<AbstractState> > extractExecutionResults(List<ExecutionResult> executions) {
		
		Map<Class<?>, List<AbstractState>> retMap = new LinkedHashMap<Class<?>, List<AbstractState>>();
		
		for(ExecutionResult result : executions) {
			
			//PalusUtil.checkTrue(!result.hasNonExecuted());
			
			AbstractState state = result.replacing_profile;
			Class<?> clz = state.clz;
			if(!retMap.containsKey(clz)) {
				retMap.put(clz, new LinkedList<AbstractState>());
			}
			retMap.get(clz).add(state);
		}
		
		return retMap;
		
	}
}