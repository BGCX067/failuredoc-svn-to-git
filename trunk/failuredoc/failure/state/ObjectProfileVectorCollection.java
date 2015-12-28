package failure.state;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;

import randoop.Sequence;

public class ObjectProfileVectorCollection {

	public final Sequence sequence;
	public final List<Class<?>> used_types;
	private final List<ObjectProfileVector> vectors = new LinkedList<ObjectProfileVector>();
	
	private ObjectProfileVector failed_vector = null;
	
	public ObjectProfileVectorCollection(Sequence sequence, List<Class<?>> usedAsTypes) {
		FDUtils.checkNull(sequence, "The sequence could not be null.");
		this.sequence = sequence;
		this.used_types = usedAsTypes;
	}
	
	public void setOriginalFailedTrace(ObjectProfileVector vector) {
		FDUtils.checkNull(vector, "The failed trace can not be null.");
		FDUtils.checkTrue(vector.exec_state == ExecState.FAIL, "The state must be failed");
		this.failed_vector = vector;
	}
	
	public ObjectProfileVector getOriginalFailedVector() {
		return this.failed_vector;
	}
	
	public void addToVectors(ObjectProfileVector vector) {
		this.vectors.add(vector);
	}
	
	public List<SingleTrace> prune_redundancy_for_singletrace() {
		List<SingleTrace> traces = new LinkedList<SingleTrace>();
		for(ObjectProfileVector vector : vectors) {
			List<Object> trace = vector.eliminate_redundant_observation();
			ExecState result = vector.exec_state;
			SingleTrace singleTrace = new SingleTrace(trace, this.used_types, result);
			traces.add(singleTrace);
		}
		return traces;
	}
	
//	public List<List<AbstractState>> getCollectedAbstractTraces() {
//	List<List<AbstractState>> collected_traces = new LinkedList<List<AbstractState>>();
//	List<List<Object>> object_list = this.prune_redundancy();
//	for(List<Object> objsPerTraces : object_list) {
//		FDUtils.checkTrue(objsPerTraces.size() == this.used_types.size(), "The size of objects per traces: "
//				+ objsPerTraces.size() + " is not equal to the size of types: " + this.used_types.size());
//		List<AbstractState> ab_states = new LinkedList<AbstractState>();
//		for(int i = 0; i < objsPerTraces.size(); i++) {
//			Object o = objsPerTraces.get(i);
//			Class<?> clz = this.used_types.get(i);
//			//create an abstract state
//			AbstractState abstate = null;
//			if(o == IgnoreState.IGNORE) {
//				abstate = new AbstractState(o, IgnoreState.class);
//			} else {
//				abstate = new AbstractState(o, clz);
//			}
//			FDUtils.checkNull(abstate, "");
//			//add the create state
//			ab_states.add(abstate);
//		}
//		collected_traces.add(ab_states);
//	}
//	return collected_traces;
//}

//public List<List<Object>> prune_redundancy() {
//	List<List<Object>> traces = new LinkedList<List<Object>>();
//	for(ObjectProfileVector vector : vectors) {
//		List<Object> trace = vector.eliminate_redundant_observation();
//		ExecState result = vector.exec_state;
//		
//		traces.add(trace);
//	}
//	return traces;
//}
	
}