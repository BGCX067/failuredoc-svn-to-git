package failuredoc.analysis;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import failure.state.AbstractState;
import failure.state.IgnoreState;

public class ObjectFeatures {
	
	public static Class<?> allSameClass(Collection<AbstractState> objs) {
		Class<?> type = null;
		for(AbstractState abstate : objs) {
			if(abstate.obj == null) {
				return null;
			}
			else {
				Class<?> clz = abstate.obj.getClass();
				if(type == null) {
					type = clz;
				} else {
					if(!type.equals(clz)) {
						return null;
					}
				}
			}
		}
		return type;
	}
	
	public static int numOfObjectTypes(Collection<AbstractState> states) {
		Set<Class<?>> class_set = new HashSet<Class<?>>();
		for(AbstractState ab_state : states) {
			if(ab_state.obj == null) {
				continue;
			} else if (ab_state.obj == IgnoreState.IGNORE) {
				continue;
			} else {
				class_set.add(ab_state.obj.getClass());
			}
		}
		return class_set.size();
	}

	public static boolean allComparableObjects(Collection<AbstractState> objs) {
		//no need to do this inference
		if(objs.size() == 1) {
			return false;
		}
		if(numOfObjectTypes(objs) == 1) {
			return false;
		}
		for(AbstractState ab_state : objs) {
			if(ab_state.obj == null) {
				return false;
			}
			if(!(ab_state.obj instanceof Comparable)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public static boolean allEmptyCollections(Collection<AbstractState> objs) {
		for(AbstractState ab_state : objs) {
			if(ab_state.obj == null) {
				return false;
			}
			if(!(ab_state.obj instanceof java.util.Collection)) {
				return false;
			} else if(ab_state.obj instanceof java.util.Collection) {
				try {
				  Collection collect =((java.util.Collection)ab_state.obj);
				} catch (ClassCastException e) {
//					return false;
					continue;
				}
				Collection collect = (java.util.Collection)ab_state.obj;
				if(!collect.isEmpty()) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	//if returns null if such object does not exist
	public static AbstractState inferSingleAbstractState(Collection<AbstractState> ab_states) {
		Set<AbstractState> set = new LinkedHashSet<AbstractState>();
		for(AbstractState ab_state : ab_states) {
			if(ab_state.obj == IgnoreState.IGNORE) {
			    set.add(ab_state);
			}
			set.add(ab_state);
		}
		if(set.size() == 1) {
			return new LinkedList<AbstractState>(set).get(0);
		}
		return null;
	}
	
	public static String findingTheSameFeature(Collection<AbstractState> states) {
		return null;
	}
	
}