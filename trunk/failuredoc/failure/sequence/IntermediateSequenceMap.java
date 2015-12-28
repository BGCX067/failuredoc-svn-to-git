package failure.sequence;

import java.util.LinkedHashMap;
import java.util.Map;

import failure.FDUtils;
import failure.doc.SequenceUtils;
import failure.state.AbstractState;
import randoop.ExecutableSequence;
import randoop.ExecutionOutcome;
import randoop.ExecutionVisitor;
import randoop.NormalExecution;
import randoop.main.GenInputsAbstract;

public class IntermediateSequenceMap {
	
	public final ExecutableSequence eSeq;
	
	public final ExecutionVisitor visitor;
	
	public final int map_size;
	
	public final Map<Integer, ExecutableSequence> intermediateMap
	  = new LinkedHashMap<Integer, ExecutableSequence>();
	
	public IntermediateSequenceMap(ExecutableSequence eSeq, ExecutionVisitor visitor) {
		FDUtils.checkNull(eSeq, "The eSeq should not be null.");
		this.eSeq = eSeq;
		map_size = this.eSeq.sequence.size();
		FDUtils.checkTrue(map_size > 0, "the map_size: " + map_size + " should > 0.");
		this.visitor = visitor;
	}
	
	public void init() {
		for(int index = 0; index < map_size; index++) {
			ExecutableSequence intermediate_seq =
				SequenceUtils.sequenceOfFirstNStmts(eSeq, index);
			intermediate_seq.execute(this.visitor);
			this.intermediateMap.put(index, intermediate_seq);
		}
	}
	
	public ExecutableSequence getESequence(int index) {
		FDUtils.checkTrue(index >=0 && index < map_size, "The index: " + index + "should >= 0 and < " + map_size);
		return intermediateMap.get(index);
	}
	
	public Object getRuntimeObject(int index) {
		FDUtils.checkTrue(index >=0 && index < map_size, "The index: " + index + " should >= 0 and < " + map_size);
		if(intermediateMap.isEmpty()) {
			this.init();
		}
		ExecutableSequence eSeq = this.intermediateMap.get(index);
		FDUtils.checkNull(eSeq, "The sequence should not be null.");
		
		if(GenInputsAbstract.forbidden_exception == null) {
		  FDUtils.checkTrue(!eSeq.throwsException(), "Should not be any exception in sequence: " + eSeq.sequence);
		}
		FDUtils.checkTrue(!eSeq.hasNonExecutedStatements(), "The eSeq should not have non executed statements.");// && !eSeq.hasFailure());
		
		ExecutionOutcome outcome = eSeq.getResult(eSeq.sequence.size() - 1);
		FDUtils.checkTrue(outcome instanceof NormalExecution, "The outcome: " + outcome.getClass()
				+ " should be a normal execution, when executing the: " + index + "-th statement. in eSeq: "
				+ eSeq.sequence + ", which has statement num: " + eSeq.sequence.size());
		
		NormalExecution ne = (NormalExecution)outcome;
		return ne.getRuntimeValue();
	}
	
	public AbstractState getAbstractState(int index) {
		Object rt = this.getRuntimeObject(index);
		Class<?> type = this.eSeq.sequence.getLastVariable().getType();
		if(rt != null) {
			type = rt.getClass();
		}
		return new AbstractState(rt, type, false);
	}
}