package failure.sequence;

import java.util.LinkedHashMap;
import java.util.Map;

import randoop.Sequence;
import failure.FDUtils;
import failure.state.AbstractState;

public class HeuristicFilters {

	public static Map<AbstractState, Sequence> filterByHeuristic(Map<AbstractState, Sequence> inputMap, Class<?> type) {
		FDUtils.checkNull(inputMap, "The inputMap should not be null.");
		
		Map<AbstractState, Sequence> retMap = new LinkedHashMap<AbstractState, Sequence>();
		
		for(AbstractState state : inputMap.keySet()) {
			
		}
		
		
		return inputMap;
	}
	
}
