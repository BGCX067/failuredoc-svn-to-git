package failuredoc.analysis;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import randoop.Globals;

import failure.FDLog;
import failure.FDUtils;
import failure.state.AbstractState;
import failure.state.IgnoreState;

public class Features {
	
	public final Map<AbstractState, Scores> ab_state_map;
	
	private double highest_score = Double.MIN_VALUE;
	
//	private boolean isComparableObject;
//	private Class<?> commonClass = null;
//	private boolean isEmptyCollection;
	
	private AbstractState single_value = null;
	
//	private Object simpleValue
	
	private boolean hasInferred = false;
	
	public static double threshold_pass_importance = 0.81d;
	
	public static double threshold_pass_increase = 0.57d;
	
	public Features(Map<AbstractState, Scores> ab_state_map) {
		this.ab_state_map = ab_state_map;
		//infer features
		//this.inferFeatures();
	}
	
	public void inferFeatures() {
		FDLog.log("ab state map size before prunning: " + this.ab_state_map.size());
		FDLog.log(" the abstract state map: " + this.ab_state_map.keySet());
		this.removeIgnoreStateAndStateWithNoPass();
		//if(failure.main.Main.only_use_pass_metric) 
		{
		  this.removeStateBelowPassImportanceThreshod();
		}
		this.computeHighestScore();
		FDLog.log(" ab state map size after prunning: " + this.ab_state_map.size());
		FDLog.log("");
		if(!this.ab_state_map.isEmpty()) {
		  //set the flag here
		  this.hasInferred = true;
//		  this.commonClass = ObjectFeatures.allSameClass(ab_state_map.keySet());
//		  this.isComparableObject = ObjectFeatures.allComparableObjects(ab_state_map.keySet());
//		  this.isEmptyCollection = ObjectFeatures.allEmptyCollections(ab_state_map.keySet());
//		  if(ObjectFeatures.numOfObjectTypes(ab_state_map.keySet()) == 1) {
//		      this.single_value = ObjectFeatures.inferSingleAbstractState(ab_state_map.keySet());
//		  }
		}
	}
	
	public Object[] getOriginalObjects() {
		int size = this.ab_state_map.size();
		Object[] retObjs = new Object[size];
		int index = 0;
		for(AbstractState state : this.ab_state_map.keySet()) {
			retObjs[index] = state.obj;
			index++;
		}
		return retObjs;
	}
	
	private void removeStateBelowPassImportanceThreshod() {
		//first we need to remove those abstract state with zero importance scores
		List<AbstractState> needToRemove = new LinkedList<AbstractState>();
		for(AbstractState state : this.ab_state_map.keySet()) {
			Scores scores = this.ab_state_map.get(state);
			if(scores.pass_increase < threshold_pass_increase) {
				needToRemove.add(state);
				FDLog.log("remove below increase: " + threshold_pass_increase + ", the processed score: " + scores.pass_increase
						+ "   " + state + ", and scores: " + scores + Globals.lineSep);
			} else if(scores.pass_importance < threshold_pass_importance) {
				needToRemove.add(state);
				FDLog.log("remove below importance: " + threshold_pass_importance + ", the processed score: " + scores.pass_importance
						+ "   " + state + ", and scores: " + scores + Globals.lineSep);
			} else {
				FDLog.log("retain the processed score: " + scores.pass_importance
						+ "   " + state + ", and scores: " + scores + Globals.lineSep);
			}
		}
		for(AbstractState s : needToRemove) {
		    ab_state_map.remove(s);
		}
	}
	
	private void removeIgnoreStateAndStateWithNoPass() {
		List<AbstractState> needToRemove = new LinkedList<AbstractState>();
		for(AbstractState state : this.ab_state_map.keySet()) {
			if (state.obj == IgnoreState.IGNORE) {
				needToRemove.add(state);
				continue;
			}
			Scores s = this.ab_state_map.get(state);
//			//s.fail == 0, and s.pass > 0 
			if(!(s.fail == 0 && s.pass > 0)) {
				needToRemove.add(state);
			}
		}
		for(AbstractState s : needToRemove) {
		    ab_state_map.remove(s);
		}
	}
	
	private void computeHighestScore() {
		//compute highest score
		for(Scores scores : this.ab_state_map.values()) {
			if(scores.pass_importance > this.highest_score) {
				this.highest_score = scores.pass_importance;
			}
		}
	}
	
	public boolean hasInferred() {
		return this.hasInferred;
	}
	
//	public double highestImportanceScore() {
//		return this.highest_score;
//	}
//	
//	public boolean isComparableObject() {
//		return this.isComparableObject;
//	}
//	
//	public boolean isEmptyCollection() {
//		return this.isEmptyCollection;
//	}
	
//	public Class<?> getCommonClass() {
//		return this.commonClass;
//	}
	
	public AbstractState getSingleValue() {
		return this.single_value;
	}
}