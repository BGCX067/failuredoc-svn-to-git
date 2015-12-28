package failuredoc.analysis.inference;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import failure.FDUtils;
import failure.state.BooleanState;
import failure.state.BooleanVector;

public class BooleanVectorPropertyChecker extends AbstractPropertyChecker {

	private List<BooleanState> commonStates = new LinkedList<BooleanState>();
	
	public BooleanVectorPropertyChecker(Object...objs) {
		super(objs);
	}
	
	@Override
	public boolean checkProperty() {
		if(hasAnyNull()) {
			return false;
		}
		//if everyone is not null, we convert them to the boolean vector
		BooleanVector[] allBVectors = new BooleanVector[super.objs.length];
		for(int i = 0; i < objs.length; i++) {
			Object obj = objs[i];
			BooleanVector bVec = new BooleanVector(obj, obj.getClass());
			allBVectors[i] = bVec;
		}
		//check all the common states
 		Map<BooleanState, Integer> commonStateMap = new LinkedHashMap<BooleanState, Integer>();
 		for(BooleanVector vector : allBVectors) {
 			BooleanState[] bStates = vector.bStates;
 			for(BooleanState bState : bStates) {
 				if(!commonStateMap.containsKey(bState)) {
 					commonStateMap.put(bState, 1);
 				} else {
 					commonStateMap.put(bState, commonStateMap.get(bState) + 1);
 				}
 			}
 		}
 		
 		//check all the common ones
 		int intendedSize = allBVectors.length;
 		for(BooleanState bState : commonStateMap.keySet()) {
 			if(commonStateMap.get(bState) == intendedSize) {
 				this.commonStates.add(bState);
 			}
 		}
 		
 		//reclaim the memory
 		commonStateMap.clear();
 		
 		if(!this.commonStates.isEmpty()) {
 			return true;
 		} else {
		    return false;
 		}
	}
	
	private boolean hasAnyNull() {
		for(Object obj : super.objs) {
			if(obj == null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String propertyToString() {
		FDUtils.checkTrue(!this.commonStates.isEmpty(), "The common boolean state should not be null.");
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(BooleanState state : this.commonStates) {
			if(count != 0) {
				sb.append(", and ");
			}
			count++;
			sb.append(state.propertyToString());
		}
		
		return sb.toString();
	}

}
