package failure.sequence;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import failure.FDUtils;
import failure.state.AbstractState;
import randoop.ExecutableSequence;
import randoop.Globals;

public class SequenceExecutionHistory {

	public final ExecutableSequence origianl_failing_seq;
	
	public final IntermediateSequenceMap original_tests_results;
	
	public final List<ExecutionResult> replacement_result = new ArrayList<ExecutionResult>();
	
	public final Map<Integer, Class<?>> usedAsTypes = new LinkedHashMap<Integer, Class<?>>();
	
	public SequenceExecutionHistory(ExecutableSequence failing_seq, IntermediateSequenceMap sequence_map) {
		FDUtils.checkNull(failing_seq, "the failing sequence should not be null.");
		FDUtils.checkNull(sequence_map, "The sequence_map should not be null.");
		this.origianl_failing_seq = failing_seq;
		this.original_tests_results = sequence_map;
	}
	
	public void addExecutionResult(ExecutionResult result) {
		FDUtils.checkNull(result, "the execution result should not be null.");
		this.replacement_result.add(result);
	}
	
	
	public void addUsedTypeForStatement(int i, Class<?> type) {
		FDUtils.checkNull(type, "The class type should not be null.");
		FDUtils.checkTrue(!usedAsTypes.containsKey(i), "The usedAsTypes does not contain index: " + i);
		usedAsTypes.put(i, type);
	}
	
	/**
	 * Which feature to extract?
	 * Type?  Value? 
	 * */
	public List<String> diagnose() {
		List<String> comments = new ArrayList<String>();
		Map<Integer, List<ExecutionResult>> resultsOfStatements = this.resultsOfStatements();
		for(int i = 0; i < this.origianl_failing_seq.sequence.size(); i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("The output of this statement is used as type: " + this.usedAsTypes.get(i).getName());
			sb.append(Globals.lineSep);
			if(resultsOfStatements.containsKey(i)) {
				sb.append(this.diagnoseStatement(i, resultsOfStatements.get(i)));
				sb.append(Globals.lineSep);
			}
			comments.add(sb.toString());
		}
		
		return comments;
	}
	
	private Map<Integer, List<ExecutionResult>> resultsOfStatements() {
		Map<Integer, List<ExecutionResult>> retMap  = new LinkedHashMap<Integer, List<ExecutionResult>>();
		
		for(ExecutionResult result : this.replacement_result) {
			Integer index = result.replacing_index;
			if(!retMap.containsKey(index)) {
				retMap.put(index, new LinkedList<ExecutionResult>());
			}
			retMap.get(index).add(result);
		}
		
		return retMap;
	}
	
	private String diagnoseStatement(Integer index, List<ExecutionResult> results) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("About statement: " + index + Globals.lineSep);
		
		List<ExecutionResult> success = this.selectSuccessExecutions(results);
		List<ExecutionResult> exceptionals = this.selectExceptionalExecutions(results);
		List<ExecutionResult> fails = this.selectFailedExecutions(results);
		
		FeatureInference featureInfer = new FeatureInference(results);
		
		//that is for the same type of object, but have different results.
		//the object profile could be very long
		sb.append(featureInfer.inferGeneralResults());
		
		Set<Class<?>> typeOfMulResult = featureInfer.getTypeOfMultiResults();
		
		if(success.isEmpty() && exceptionals.isEmpty()) {
			sb.append("Statement " + index + " seems to be irrelevant to correct the result." + Globals.lineSep);
//			String reason = featureInfer.inferFeatures(fails);
//			if(reason.trim().length() != 0) {
//			    sb.append("The test will still fail after we try: \n");
//			    sb.append(reason);
//			}
		} else {
			if(!success.isEmpty()) {
		      String reason = featureInfer.inferFeatures(success, typeOfMulResult);
		      if(reason.trim().length() != 0) {
			      sb.append("The test will success when trying:" + Globals.lineSep);
			      sb.append(reason + Globals.lineSep);
		      }
			}
			if (success.isEmpty()) {
				if (!fails.isEmpty()) {
					String reason = featureInfer.inferFeatures(fails,
							typeOfMulResult);
					if (reason.trim().length() != 0) {
						sb.append("The test still fails when trying:"
								+ Globals.lineSep);
						sb.append(reason + Globals.lineSep);
					}
				}
				if (!exceptionals.isEmpty()) {
					String reason = featureInfer.inferFeatures(exceptionals,
							typeOfMulResult);
					if (reason.trim().length() != 0) {
						sb
								.append("The test will throw an exception when trying:"
										+ Globals.lineSep);
						sb.append(reason + Globals.lineSep);
					}
				}
			}
		}
		
		
		return sb.toString();
	}
	
	private List<ExecutionResult> selectSuccessExecutions(List<ExecutionResult> list) {
		List<ExecutionResult> retList = new LinkedList<ExecutionResult>();
		for(ExecutionResult result : list) {
			if(!result.hasFailure() && !result.hasNonExecuted()) {
				retList.add(result);
			}
		}
		return retList;
	}
	
	private List<ExecutionResult> selectExceptionalExecutions(List<ExecutionResult> list) {
		List<ExecutionResult> retList = new LinkedList<ExecutionResult>();
		for(ExecutionResult result : list) {
			if(result.hasNonExecuted()) {
				retList.add(result);
			}
		}
		return retList;
	}
	
	private List<ExecutionResult> selectFailedExecutions(List<ExecutionResult> list) {
		List<ExecutionResult> retList = new LinkedList<ExecutionResult>();
		for(ExecutionResult result : list) {
			if(result.hasFailure()) {
				retList.add(result);
			}
		}
		return retList;
	}
}