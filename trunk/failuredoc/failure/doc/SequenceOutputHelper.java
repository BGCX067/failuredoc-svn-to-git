package failure.doc;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import randoop.ExecutableSequence;
import randoop.ExecutionOutcome;
import randoop.ExecutionVisitor;
import randoop.Globals;
import randoop.NormalExecution;
import randoop.Sequence;
import randoop.SubTypeSet;
import randoop.Variable;

import failure.state.AbstractState;

import failure.FDUtils;
import randoop.SequenceCollection;
import randoop.main.GenInputsAbstract;
import randoop.util.Randomness;
import randoop.util.Reflection.Match;
import failure.FDLog;

/**
 * A helper class 
 * */
public class SequenceOutputHelper {
	/**
	 * The components containing all sequences
	 * */
	private final SequenceCollection components;
	/**
	 * The visitor for executing the newly constructed sequence
	 * */
	private final ExecutionVisitor visitor;
	/**
	 * The classification map for sequence
	 * */
	private Map<Class<?>, Map<AbstractState, List<Sequence>>> stateSequences = null;
	/**
	 * A type map for fast lookup
	 * */
	private SubTypeSet typesWithSequencesMap = null;
	
	/**
	 * Default constructor
	 * */
	public SequenceOutputHelper(SequenceCollection components, ExecutionVisitor visitor) {
		FDUtils.checkNull(components, "The components could not be null.");
		FDUtils.checkNull(visitor, "The visitor could not be null.");
		this.components = components;
		this.visitor = visitor;
	}
	
	/**
	 * Select all available types
	 * */
	public Collection<Sequence> getAllCompatibleSequence(Class<?> type) {
		this.initSequenceMap();
		this.initTypeMap();
		
		Collection<Sequence> retseqs = new LinkedHashSet<Sequence>();
		Set<Class<?>> queryClasses = new LinkedHashSet<Class<?>>();
		queryClasses.addAll(this.typesWithSequencesMap.getMatches(type));
		for(Class<?> c : queryClasses) {
			Map<AbstractState, List<Sequence>> map = this.stateSequences.get(c);
			for(List<Sequence> seqlist : map.values()) {
				retseqs.addAll(seqlist);
			}
		}
		return retseqs;
	}
	
	/**
	 * Select all sequence producing compatible types up to a total number k
	 * */
	public Collection<Sequence> getCompatibleSequenceWithBound(Class<?> type) {
		int k = GenInputsAbstract.obj_select_num;
		FDUtils.checkTrue(k > 0, "The value of k is not valid: " + k);
		
		this.initSequenceMap();
		this.initTypeMap();
		
		//do the selection
		Collection<Sequence> retseqs = new LinkedHashSet<Sequence>();
		Set<Class<?>> queryClasses = new LinkedHashSet<Class<?>>();
		queryClasses.addAll(this.typesWithSequencesMap.getMatches(type));
		
		int total_count = 0;
		for(Class<?> c : queryClasses) {
			Map<AbstractState, List<Sequence>> map = this.stateSequences.get(c);
			for(List<Sequence> seqlist : map.values()) {
				total_count = total_count + seqlist.size();
			}
		}
		//if the total number <= k
		if(total_count <= k) {
			for(Class<?> c : queryClasses) {
				Map<AbstractState, List<Sequence>> map = this.stateSequences.get(c);
				for(List<Sequence> seqlist : map.values()) {
					retseqs.addAll(seqlist);
				}
			}
		} else {
		  //select adaptively random
		  int selected = 0;
		  while(selected < k) {
			Class<?> clz = Randomness.randomSetMember(queryClasses);
			Map<AbstractState, List<Sequence>> map = this.stateSequences.get(clz);
			AbstractState state = Randomness.randomSetMember(map.keySet());
			Sequence seq = Randomness.randomSetMember(map.get(state));
			retseqs.add(seq);
			selected++;
		  }
		}
		return retseqs;
	}
	
	public Map<AbstractState, Sequence> chooseShortestSequenceWithDiffStates(Class<?> type, AbstractState currState) {
		FDUtils.checkNull(currState, "The current abstract state could not be null.");
		Map<AbstractState, Sequence> retMap = new LinkedHashMap<AbstractState, Sequence>();
		//should consider its subtype also
		Map<AbstractState, List<Sequence>> stateAndSequenceList =  this.getStateSequences(type);
		for(AbstractState state : stateAndSequenceList.keySet()) {
			if(state.equals(currState)) {
				continue;
			}
			Sequence shortest = shortestSequence(stateAndSequenceList.get(state));
			FDUtils.checkNull(shortest, "The shortest sequence could not be null.");
			retMap.put(state, shortest);
		}
		
		return retMap;
	}
	
	public Sequence getShortestSequenceWithOutputType(Class<?> type, AbstractState state) {
		return getShortestSequenceWithOutputType(type, state, Match.COMPATIBLE_TYPE);
	}
	
	private Sequence getShortestSequenceWithOutputType(Class<?> type, AbstractState state, Match m) {
		Map<AbstractState, List<Sequence>> retMap = this.getStateSequences(type, m);
		if(retMap.isEmpty()) {
			return null;
		}
		if(!retMap.containsKey(state)) {
			return null;
		}
		List<Sequence> seqs = retMap.get(state);
		Sequence s = shortestSequence(seqs);
		FDUtils.checkNull(s, "The shortest sequence could not be null");
		return s;
	}
	
	/**
	 * Gets the shortest sequence instance from a list
	 * */
	public static Sequence shortestSequence(List<Sequence> seqs) {
		Sequence s = null;
		for(Sequence seq : seqs) {
			if(s == null) {
				s = seq;
			} else {
				if(s.size() > seq.size()) {
					s = seq;
				}
			}
		}
		return s;
	}
	
	private Map<AbstractState, List<Sequence>> getStateSequences(Class<?> type) {
		return getStateSequences(type, Match.COMPATIBLE_TYPE);
	}
	
	private Map<AbstractState, List<Sequence>> getStateSequences(Class<?> type, Match m) {
		FDUtils.checkNull(type, "The given type could not be null.");
		this.initSequenceMap();
		this.initTypeMap();
		//the return map from abstract state to a list of sequences
		Map<AbstractState, List<Sequence>> retMap = new LinkedHashMap<AbstractState, List<Sequence>>();
		//get a set of compatible types
		Set<Class<?>> queryClasses = new LinkedHashSet<Class<?>>();
		if(m == Match.EXACT_TYPE) {
			queryClasses.add(type);
		} else {
			queryClasses.addAll(this.typesWithSequencesMap.getMatches(type));
		}
		//get all abstract states of the given class
		for(Class<?> clz : queryClasses) {
			Map<AbstractState, List<Sequence>> map = this.stateSequences.get(clz);
			for(AbstractState stateAdd : map.keySet()) {
				if(retMap.containsKey(stateAdd)) {
				  retMap.get(stateAdd).addAll(map.get(stateAdd));
				} else {
				  retMap.put(stateAdd, map.get(stateAdd));
				}
			}
		}
		
		return retMap;
	}
	
	/**
	 * No side-effects just show the map content
	 * */
	public String showSequenceMap() {
		StringBuilder sb = new StringBuilder();
		//if the map has not been inited, initialize the maps first
		if(this.stateSequences == null) {
			this.initSequenceMap();
		}
		if(this.typesWithSequencesMap == null) {
			this.initTypeMap();
		}
		sb.append("--- below is the sequence map ----");
		//append the sequence map
		Map<Class<?>, Map<AbstractState, List<Sequence>>> sequenceMap = this.stateSequences;
		for(Class<?> clazz : sequenceMap.keySet()) {
			Map<AbstractState, List<Sequence>> stateMap = sequenceMap.get(clazz);
			sb.append("For class: " + clazz + Globals.lineSep);
			for(AbstractState state : stateMap.keySet()) {
				sb.append("   " + state + ", has sequence: " + stateMap.get(state).size() + Globals.lineSep);
			}
			sb.append(Globals.lineSep);
		}
		//append the type map
		sb.append("--- below is the type map ----");
		SubTypeSet set = this.typesWithSequencesMap;
		Set<Class<?>> allClasses = set.getElements();
		for(Class<?> clazz : allClasses) {
			Set<Class<?>> matched = set.getMatches(clazz);
			sb.append("For class: " + clazz.getName() + "\n");
			sb.append("    its compatible classes: " + matched.toString() + "\n");
		}
		//return the string
		return sb.toString();
	}
	
	/**
	 * Init the type map for fast look up
	 * */
	private void initTypeMap() {
		if(this.typesWithSequencesMap != null) {
			return;
		}
		if(this.stateSequences == null) {
			this.initSequenceMap();
		}
		this.typesWithSequencesMap = new SubTypeSet(false);
		for(Class<?> clz : this.stateSequences.keySet()) {
			this.typesWithSequencesMap.add(clz);
		}
	}
	/**
	 * Iterate through the sequences in the component, execute it. If it is a normal execution,
	 * then keeps record of the object states in the last statement
	 * */
	private void initSequenceMap() {
		if(stateSequences != null) {
			return;
		}
		this.stateSequences = 
			new LinkedHashMap<Class<?>, Map<AbstractState, List<Sequence>>>();
		//get all sequences
		Set<Sequence> allSequences  = this.components.getAllSequences();
		for(Sequence s : allSequences) {
			ExecutableSequence eSeq = new ExecutableSequence(s);
			//re-execute it
			eSeq.execute(this.visitor);
			if(!eSeq.isNormalExecution()) {
				//FDLog.log("Not normal execution: " + eSeq);
				continue;
			}
			//get the variable produced by the last statements
			List<Variable> lastVars = new LinkedList<Variable>();//eSeq.sequence.getLastStatementVariables();
			lastVars.add(eSeq.sequence.getLastVariable());
			for(Variable var : lastVars) {
				int index = var.getDeclIndex();
				ExecutionOutcome outcome = eSeq.getResult(index);
				FDUtils.checkTrue(outcome instanceof NormalExecution, "The outcome type: " + outcome.getClass()
						+ " is not normal execution.");
				//it must be a normal execution
				NormalExecution ne = (NormalExecution)outcome;
				Object rtObj = ne.getRuntimeValue();
				Class<?> type = var.getType();
				if(rtObj != null) {
					type = rtObj.getClass();
				}
				//get the statically-declared type
				Class<?> staticType = type;
//				FDUtils.checkTrue(staticType != void.class, "The staticType: " + var + " is not valid, should not be void. varname: "
//						+ staticType + ", in: " + eSeq.sequence);
				if(staticType == void.class) {
					continue;
				}
				if(staticType.isPrimitive()) {
					staticType = FDUtils.getBoxedClassForPrimitiveType(staticType.getName());
				}
				FDUtils.checkTrue(staticType.isAssignableFrom(type), "The type: " + type + " is not an assigable form" +
						" of type: " + staticType);
				AbstractState state = new AbstractState(rtObj, type, false);
				this.updateMap(stateSequences, type, state, s);
			}
		}
	}
	/**
	 * Update a given map
	 * */
	private void updateMap(Map<Class<?>, Map<AbstractState, List<Sequence>>> stateSequencesMap,
			Class<?> type, AbstractState state, Sequence sequence) {
		if(!stateSequencesMap.containsKey(type)) {
			stateSequencesMap.put(type, new LinkedHashMap<AbstractState, List<Sequence>>());
		}
		Map<AbstractState, List<Sequence>> stateMap = stateSequencesMap.get(type);
		if(!stateMap.containsKey(state)) {
			stateMap.put(state, new LinkedList<Sequence>());
		}
		stateMap.get(state).add(sequence);
	}
	
}