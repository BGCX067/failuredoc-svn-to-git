package failure.state;

import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;

public class SingleTrace {
	
	public final List<AbstractState> abStates;
	public final List<Object> objects;
	public final List<Class<?>> used_types;
	public final ExecState result;
	
	public SingleTrace(List<Object> objects, List<Class<?>> used_types, ExecState result) {
		this.used_types = used_types;
		this.abStates = this.getAbStates(objects);
		this.result = result;
		this.objects = this.getOriginalObjects(this.abStates);
	}
	
	private List<AbstractState> getAbStates(List<Object> objsPerTraces) {
		FDUtils.checkTrue(objsPerTraces.size() == this.used_types.size(),
				"The size of objects per traces: " + objsPerTraces.size()
						+ " is not equal to the size of types: "
						+ this.used_types.size());
		List<AbstractState> ab_states = new LinkedList<AbstractState>();
		for (int i = 0; i < objsPerTraces.size(); i++) {
			Object o = objsPerTraces.get(i);
			Class<?> clz = this.used_types.get(i);
			// create an abstract state
			AbstractState abstate = null;
			if (o == IgnoreState.IGNORE) {
				abstate = new AbstractState(o, IgnoreState.class);
			} else {
				abstate = new AbstractState(o, clz);
			}
			FDUtils.checkNull(abstate, "The abstract state should not be null.");
			// add the create state
			ab_states.add(abstate);
		}
		return ab_states;
	}
	
	private List<Object> getOriginalObjects(List<AbstractState> states) {
		List<Object> obj_list = new LinkedList<Object>();
		for(AbstractState state : states) {
			if(state.obj == IgnoreState.IGNORE) {
				continue;
			}
			obj_list.add(state.obj);
		}
		return obj_list;
	}
}