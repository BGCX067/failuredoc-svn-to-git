package failure.state;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import plume.Pair;
import failure.FDUtils;

public class ObjectStateComparator {
	
	public final AbstractState state1;
	
	public final AbstractState state2;
	
	public ObjectStateComparator(AbstractState s1, AbstractState s2) {
		FDUtils.checkNull(s1, "The abstract state s1 could not be null.");
		FDUtils.checkNull(s2, "The abstract state s2 could not be null.");
		FDUtils.checkTrue(s1.clz.equals(s2.clz), "The s1.clz: " + s1.clz + " != the s2.clz "
				+ s2.clz);
		FDUtils.checkTrue(!s1.isPrimtiveOrString, "s1 should not be a primitive type.");
		
		state1 = s1;
		state2 = s2;
	}
	
	
	public Map<Field, Pair<State, State>> getDifferentFieldStates() {
		
		Map<Field, Pair<State, State>> retMap = new LinkedHashMap<Field, Pair<State, State>>();
		
		Field[] field1 = state1.getStateFields();
		
		State[] abstate1 = state1.getStates();
		State[] abstate2 = state2.getStates();
		
		for(int i = 0; i < field1.length; i++) {
			if(!abstate1[i].equals(abstate2[i])) {
				retMap.put(field1[i], new Pair<State, State>(abstate1[i], abstate2[i]));
			}
		}
		
		return retMap;
		
	}
	

}