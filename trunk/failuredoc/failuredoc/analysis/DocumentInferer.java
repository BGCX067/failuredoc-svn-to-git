package failuredoc.analysis;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import failure.FDLog;
import failure.FDUtils;
import failure.sequence.SequenceOutputAfterCommenting;
import failure.state.AbstractState;
import failure.state.IgnoreState;
import failure.state.ObjectProfileVectorCollection;
import failure.state.SingleTrace;

import randoop.ExecutableSequence;
import randoop.Globals;
import randoop.util.Files;

public class DocumentInferer {

	public final Map<ExecutableSequence, ObjectProfileVectorCollection> sequence_trace_map;
	
	/**
	 * The ExecutableSequences here are the original failed ones
	 * */
	public DocumentInferer(Map<ExecutableSequence, ObjectProfileVectorCollection> trace_map) {
		FDLog.log("Initialize document inferrer, failed sequence size: " + trace_map.size());
		this.sequence_trace_map = trace_map;
	}
	
	public List<SequenceOutputAfterCommenting> inferCommentsForSequence() {
		List<SequenceOutputAfterCommenting> commented_sequence = new LinkedList<SequenceOutputAfterCommenting>();
		for(ExecutableSequence seq : this.sequence_trace_map.keySet()) {
			FDLog.log("Inferring comments for seq: " + Globals.lineSep + seq.sequence);
			List<String> comments = this.inferComment(seq, this.sequence_trace_map.get(seq));
			FDLog.log("See the inferred results: " + comments);
			SequenceOutputAfterCommenting seqComment = new SequenceOutputAfterCommenting(seq, comments);
			commented_sequence.add(seqComment);
			//System.out.println(seqComment.outputCommentedSequence());
		}
		return commented_sequence;
	}
	
	List<String> inferComment(ExecutableSequence eSeq, ObjectProfileVectorCollection traces) {
		//eliminate all the redundant observations
		//List<List<AbstractState>> pruned_obj = traces.getCollectedAbstractTraces();
		List<SingleTrace> trace_collection = traces.prune_redundancy_for_singletrace();
		
		StatisticalDebugging debugger = new StatisticalDebugging(eSeq, trace_collection, /*eSeq.sequence.size(),*/
				traces.getOriginalFailedVector());
		//start to infer document
		debugger.start_diagnose();
		//get the generated document
		List<String> comments = debugger.generate_comments();
		
		this.haveARoughView(trace_collection);
		
		return comments;
	}
	
	void haveARoughView(List<SingleTrace> traces) {
		StringBuilder sb = new StringBuilder();
		for(SingleTrace list : traces) {
			for(AbstractState obj : list.abStates) {
				if(obj.obj instanceof IgnoreState) {
					FDUtils.std(" N ");
					sb.append(" N ");
				} else {
					FDUtils.std(" O ");
					sb.append(" O ");
				}
			}
			FDUtils.std("   :  " + list.result);
			FDUtils.stdln("");
			sb.append(Globals.lineSep);
		}
		try {
			Files.writeToFile(sb.toString(), new File("./pruned_output.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}