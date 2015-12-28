package failure.state;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import failure.FDUtils;

import randoop.ExceptionalExecution;
import randoop.NotExecuted;
import randoop.Sequence;
import randoop.Variable;
import randoop.main.GenInputsAbstract;
import randoop.util.Reflection;

public class ObjectProfileVector {

	public final Sequence sequence;
	public final int total_size;
	public final int replaced_index;
	public final List<Class<?>> used_types;
	public final List<Object> failed_objects;
	public final List<Object> object_after_replaced;
	public final ExecState exec_state;
	
	public ObjectProfileVector(Sequence sequence, int replaced_index, List<Class<?>> used_types,
			List<Object> failed_objects, List<Object> object_after_replaced, ExecState execState) {
		FDUtils.checkNull(sequence, "The sequence could not be null.");
		this.sequence = sequence;
		this.total_size = sequence.size();
		
		if(replaced_index == -1) {
		  //skip
		} else {
		  FDUtils.checkTrue(replaced_index >= 0 && replaced_index < sequence.size(), "Invalid replaced_index: "
				+ replaced_index + ", the length of sequence is: " + sequence.size());
		}
		this.replaced_index = replaced_index;
		this.used_types = used_types;
		this.failed_objects = failed_objects;
		this.object_after_replaced = object_after_replaced;
		this.exec_state = execState;
		
		checkValidity(used_types, failed_objects, object_after_replaced);
	}
	
	private void checkValidity(List<Class<?>> types, List<Object> failed_objs, List<Object> obj_after_replace) {
		FDUtils.checkTrue(used_types.size() == sequence.size(), "The sizes are not the same.");
		FDUtils.checkTrue(failed_objects.size() == object_after_replaced.size(), "The size are not equal! failed test is: " +
				failed_objects.size() + ", the tests after replacing is: " + object_after_replaced.size());
		for(int i = 0; i < types.size(); i++) {
			if(GenInputsAbstract.forbidden_exception != null && i == types.size() - 1) {
				continue;
			}
			Class<?> type = types.get(i);
			Object failed_object = failed_objs.get(i);
			Object replaced_object = obj_after_replace.get(i);
			if(failed_object != null) {
				FDUtils.checkTrue(Reflection.canBeUsedAs(failed_object.getClass(), type), failed_object.getClass()
						+ " can not be used as: " + type + ", for the: " + i + "-th statement.");
			}
			if(replaced_object != null && replaced_object != IgnoreState.IGNORE) {//!(replaced_object instanceof NotExecuted)
					//&& !(replaced_object instanceof ExceptionalExecution)) {
				FDUtils.checkTrue(Reflection.canBeUsedAs(replaced_object.getClass(), type), replaced_object.getClass()
						+ " can not be used as: " + type + ", for the: " + i + "-th statement.");
			}
		}
	}
	
	public List<Object> eliminate_redundant_observation() {
		//when replacing the i-th place, we only retain those observation which is forward reachable from i.
		List<Object> observations_after_elimination = new LinkedList<Object>();
		
		Set<Integer> remainedindex = this.remainingIndices();
		for(Integer i = 0; i < used_types.size(); i++) {
			if(!remainedindex.contains(i)) {
				observations_after_elimination.add(IgnoreState.IGNORE);
			} else {
				observations_after_elimination.add(object_after_replaced.get(i));
			}
		}
		
		FDUtils.checkTrue(observations_after_elimination.size() == used_types.size(), "The size are not equal: "
				+ "The observations_after_eliminate size: " + observations_after_elimination.size()
				+ ", used type size: " + used_types.size());
		return observations_after_elimination;
	}
	
	private Set<Integer> remainingIndices() {
		SimpleVarFlowGraph graph = new SimpleVarFlowGraph(this.sequence);
		Set<Integer> set = new LinkedHashSet<Integer>();
		//compute which index need to be remained
		for(int i = 0; i < this.used_types.size(); i++) {
			if(i >= this.replaced_index) {
				set.add(i);
			} else if(graph.hasMaxAffectedIndexBeyond(i, replaced_index)) {
				set.add(i);
			}
		}
		return set;
	}
}

/**
 * Constructs the simple data flow diagram.
 * Thanks to the existing infrastructure in Randoop, which significantly
 * ease the whole process.
 * */
class SimpleVarFlowGraph {
	public final Sequence sequence;
	
	public SimpleVarFlowGraph(Sequence sequence) {
		this.sequence = sequence;
	}
	
	public boolean hasMaxAffectedIndexBeyond(int index, int replace_index) {
		FDUtils.checkTrue(index < replace_index, "should not be equal.");
		if(replace_index == this.sequence.size() - 1) {
			return false;
		}
		for(int i = replace_index + 1; i < sequence.size() - 1; i++) {
			Set<Integer> usedVarIndexSet = this.usedVarSet(i, replace_index);
			if(usedVarIndexSet.contains(index)) {
				return true;
			}
		}
		return false;
	}
	
	private Set<Integer> usedVarSet(int index, int removed_index) {
		List<Variable> vars = this.sequence.getInputs(index);
		removeVarDefinedIn(vars, removed_index);
		Set<Variable> uniqueVas = new LinkedHashSet<Variable>(vars);
		boolean shouldContinue = true;
		while(shouldContinue) {
		  List<Variable> expanded = new LinkedList<Variable>();
		  for(Variable v : uniqueVas) {
			  int declared_index = v.getDeclIndex();
			  FDUtils.checkTrue(declared_index != removed_index, "");
			  List<Variable> more = this.sequence.getInputs(declared_index);
			  removeVarDefinedIn(more, removed_index);
			  for(Variable morev : more) {
				  if(!uniqueVas.contains(morev)) {
					  expanded.add(morev);
				  }
			  }
		  }
		  if(expanded.isEmpty()) {
			  shouldContinue = false;
		  } else {
			  uniqueVas.addAll(expanded);
			  expanded.clear();
		  }
		}
		//convert to ints
		Set<Integer> ints = new LinkedHashSet<Integer>();
		for(Variable var : uniqueVas) {
			ints.add(var.getDeclIndex());
		}
		return ints;
	}
	
	private void removeVarDefinedIn(List<Variable> inputs, int index) {
		List<Variable> toremove = new LinkedList<Variable>();
		for(Variable input : inputs) {
			if(input.getDeclIndex() == index) {
				toremove.add(input);
			}
		}
		//remove it
		inputs.removeAll(toremove);
	}
}