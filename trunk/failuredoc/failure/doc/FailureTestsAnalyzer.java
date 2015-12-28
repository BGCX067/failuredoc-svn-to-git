package failure.doc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import failure.FDLog;
import failure.FDUtils;
import failure.sequence.CommentSequenceToFile;
import failure.sequence.ExecutionResult;
import failure.sequence.HeuristicFilters;
import failure.sequence.IntermediateSequenceMap;
import failure.sequence.SequenceExecutionHistory;
import failure.sequence.SequenceOutputAfterCommenting;
import failure.sequence.SequenceReplacement;
import failure.state.AbstractState;
import failure.state.ExecState;
import failure.state.IgnoreState;
import failure.state.ObjectProfileVector;
import failure.state.ObjectProfileVectorCollection;
import failuredoc.analysis.DocumentInferer;

import randoop.ExceptionalExecution;
import randoop.ExecutableSequence;
import randoop.ExecutionOutcome;
import randoop.ExecutionVisitor;
import randoop.NormalExecution;
import randoop.NotExecuted;
import randoop.PrimitiveOrStringOrNullDecl;
import randoop.Sequence;
import randoop.SequenceCollection;
import randoop.StatementKind;
import randoop.Variable;
import randoop.main.GenInputsAbstract;
import randoop.util.Reflection;

public class FailureTestsAnalyzer {
	/**
	 * A list of failed tests
	 * */
	public final List<ExecutableSequence> failedTests;
	/**
	 * The component storing all replacement candidates
	 * */
	public final SequenceCollection components;
	/**
	 * The execution visitor to re-execute the constructed sequence
	 * */
	public final ExecutionVisitor visitor;
	/**
	 * A helper class for output the documented sequence
	 * */
	private SequenceOutputHelper sequenceHelper = null;
	
	/**
	 * Establish the mapping between an executable sequence with the collected traces
	 * */
	Map<ExecutableSequence, ObjectProfileVectorCollection> collected_trace_for_eseq =
		new LinkedHashMap<ExecutableSequence, ObjectProfileVectorCollection>();

	private Class<?> forbiddenException = null;
	
	/**
	 * Default constructor
	 * */
	public FailureTestsAnalyzer(List<ExecutableSequence> failedTests,
			SequenceCollection components, ExecutionVisitor visitor) {
		//configurate the log file
		FDLog.logConfig(GenInputsAbstract.failure_doc_log);
		//check the validity of inputs
		FDUtils.checkNull(failedTests, "The failedTests should not be null.");
		FDUtils.checkNull(components, "The components should not be null.");
		//assign value
		this.failedTests = failedTests;
		this.components = components;
		this.visitor = visitor;
		//add string s = null; to the component pool
		this.addStringNullValue();
	}
	/**
	 * Add null value of string type.
	 * */
	private void addStringNullValue() {
		StatementKind st = new PrimitiveOrStringOrNullDecl(java.lang.String.class, null);
        Sequence seq = new Sequence().extend(st, new ArrayList<Variable>());
		this.components.add(seq);
	}

	/**
	 * The main entry, which performs failure analysis to infer document.
	 * */
	public List<SequenceOutputAfterCommenting> doFailureAnalysis() {
		FDUtils.stdln("----Start performing failure analysis-----");
		FDUtils.stdln("Failed tests: " + failedTests.size());
		FDUtils.stdln("Component size: " + components.size());
		FDUtils.stdln("---- start building state in the component ----");
		
		this.sequenceHelper = new SequenceOutputHelper(this.components, this.visitor);
		FDUtils.stdln(sequenceHelper.showSequenceMap());
		FDUtils.stdln("---- finish building state in the component ----");
		
		//do value replacement, and collecting sample traces
		for (ExecutableSequence eSeq : this.failedTests) {
			FDUtils.checkTrue(!eSeq.hasNonExecutedStatements(), "Should not have any non-executed statements. ");
			if(GenInputsAbstract.forbidden_exception != null) {
			  FDUtils.checkTrue(eSeq.hasFailure(), "the failed sequence must be failed");
			} else {
			  FDUtils.checkTrue(!eSeq.throwsException(), "Should not have any thrown exceptions." +
					"for sequence: " + eSeq.sequence);
			}
			ObjectProfileVectorCollection collection = this.individualAnalysis(eSeq);
			this.collected_trace_for_eseq.put(eSeq, collection);
		}
		
		//start to infer document
		DocumentInferer inferer = new DocumentInferer(this.collected_trace_for_eseq);
		List<SequenceOutputAfterCommenting> commented_seq = inferer.inferCommentsForSequence();
		return commented_seq;
	}
	
	/**
	 * Performs value replacement, collecting multiple traces.
	 * */
	private ObjectProfileVectorCollection individualAnalysis(ExecutableSequence eSeq) {
		int total_size = eSeq.sequence.size();
		FDUtils.stdln("------------ analyze sequence with size: " + total_size + "------------");
		List<Class<?>> usedAsTypes = new LinkedList<Class<?>>();
		for(int index = 0; index < total_size; index++) {
			Class<?> type = SequenceUtils.getUsedTypeForOutput(eSeq, index);
			usedAsTypes.add(type);
		}
		//create a vector collection
		//it is an empty collection to collect all result values after value replacement
		ObjectProfileVectorCollection collection = new ObjectProfileVectorCollection(eSeq.sequence, usedAsTypes);
		
		//add the original failed observation
		//if(GenInputsAbstract.forbidden_exception == null) {
		    ObjectProfileVector failed_vector = new ObjectProfileVector(eSeq.sequence, -1, usedAsTypes,
				this.getOutcomes(eSeq), this.getOutcomes(eSeq), ExecState.FAIL);
	    	collection.addToVectors(failed_vector);
		    collection.setOriginalFailedTrace(failed_vector);
		//}
		
		//start replacement
		for(int index = 0; index < total_size; index++) {
			FDUtils.stdln("analyze the: " + index + "-th statement:");
			Class<?> type = usedAsTypes.get(index);
			Collection<Sequence> sequences = null;
			if(GenInputsAbstract.adaptive_select) {
			  sequences = this.sequenceHelper.getCompatibleSequenceWithBound(type);
			} else {
			  sequences = this.sequenceHelper.getAllCompatibleSequence(type);
			}
			FDUtils.stdln("replacing canddiates num: " + sequences.size()
					+ ", use adaptive selectioin: " + GenInputsAbstract.adaptive_select);
			FDLog.log("for " + index + "-th statement, there are: " + sequences.size() + " candidates.");
			//for experiment
			SequenceReplacement.replacement_traces = SequenceReplacement.replacement_traces + sequences.size();
			for(Sequence replace_candidate : sequences) {
				FDUtils.std(".");
				Variable replaced_var = replace_candidate.getLastVariable();
				if(!Reflection.canBeUsedAs(replaced_var.getType(), type)) {
					//System.err.println(replaced_var.getType() + " can not be used as: " + type );
					continue;
				}
				
				Sequence newSequence = SequenceReplacement.replaceToNewSequence(eSeq, index, replace_candidate);
				ExecutableSequence exec_seq = new ExecutableSequence(newSequence);
				exec_seq.execute(this.visitor);
				
				//check the result
				ExecState execState = getResult(exec_seq);
				List<Object> failed_objects = this.getOutcomes(eSeq);
				List<Object> replaced_objects = this.getOutcomes(exec_seq);
				
				if(replace_candidate.size() > 1) {
					FDUtils.checkTrue(failed_objects.size() < replaced_objects.size(), "Their size must be different!");
					//here we need to remove some of the result
					for(int remove_index = index + replace_candidate.size() - 2; remove_index >= index; remove_index--) {
						replaced_objects.remove(remove_index);
					}
				}
				
				ObjectProfileVector vector = new ObjectProfileVector(eSeq.sequence, index, usedAsTypes, failed_objects, replaced_objects, execState);
				collection.addToVectors(vector);
			}
			FDUtils.stdln("");
		}
		
		return collection;
//		//empty comment
//		List<String> commentforStmt = new LinkedList<String>();
//		for(int i = 0; i < eSeq.sequence.size(); i++) {
//			commentforStmt.add("empty comment here!");
//		}
//        SequenceOutputAfterCommenting seqComment = new SequenceOutputAfterCommenting(eSeq, commentforStmt);
//		System.out.println(seqComment.outputCommentedSequence());
//		
//		return seqComment;
	}
	
	private ExecState getResult(ExecutableSequence eSeq) {
		if(GenInputsAbstract.forbidden_exception != null) {
			if(this.forbiddenException == null) {
				try {
					this.forbiddenException = Class.forName(GenInputsAbstract.forbidden_exception);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			if(eSeq.throwsException() && eSeq.getExceptionIndex(this.forbiddenException) == eSeq.sequence.size() - 1) {
				return ExecState.FAIL;
			}
		}
		if(eSeq.hasFailure()) {
			return ExecState.FAIL;
		} else if (eSeq.throwsException() || eSeq.hasUnexpectedException()) {
			return ExecState.EXCEPTION;
		} else {
			FDUtils.checkTrue(!eSeq.hasNonExecutedStatements(),
					"Number of statements executed: " + eSeq.executedSize()
					+ ", has exception: " + eSeq.hasUnexpectedException()
					+ ", has throw exception: " + eSeq.throwsException());
					//"Every statement should be executed, but eSeq is: "	+ eSeq);
			return ExecState.PASS;
		}
	}
	
	private List<Object> getOutcomes(ExecutableSequence eSeq) {
		List<Object> outcomes = new LinkedList<Object>();
//		ExecutionOutcome[] results = eSeq.getAllResults();
		for(int i = 0; i < eSeq.sequence.size(); i++) {
			ExecutionOutcome outcome = eSeq.getResult(i);
//			if(outcome instanceof ExceptionalExecution && ((ExceptionalExecution)outcome).getException().getClass().equals(this.forbiddenException)) {
//				outcomes.add()
//			}
			if(outcome instanceof NotExecuted || outcome instanceof ExceptionalExecution) {
				//if(GenInputsAbstract.forbidden_exception != null )
				outcomes.add(IgnoreState.IGNORE);
			} else {
				FDUtils.checkTrue(outcome instanceof NormalExecution, "The outcome type: " + outcome.getClass() + " is not valid");
				outcomes.add(((NormalExecution)outcome).getRuntimeValue());
			}
		}
		return outcomes;
	}

	/***
	 * analysis step: 1. prune out irrelevant variable 2. for each method call,
	 * change its parameter values 1. int float, double, short-> 0, >0, < 0 2.
	 * byte -> true ,false 3. string -> "", null, "non-empty, different length"
	 * 4. char -> different char
	 * 
	 * object 1. same type: object binary profile 2. different type: compatible,
	 * exhaustive? have cast statement
	 * 
	 * array 1. clear all 2. put a null 3. null
	 * 
	 * byte could be used as Object, so could change to another type
	 * 
	 * using delta debugging
	 * 
	 * analyze the stack trace
	 * 
	 * statistic analysis, if try exceed a threshold, that indicate that
	 * value is irrelevant with the failure
	 * 
	 * 3. summarize and document sythesis
	 * 
	 * if you replace a late sequence, need to prune out all un-used variables
	 * 
	 * current component does not have null value
	 * 
	 * multiple 
	 * 
	 * use heuristic ranking to decide which satement to comemnt
	 * for some statement, many inputs lead to exception, no need to comment
	 * for statement input to success / total input  is very low, we
	 * output the success input
	 * 
	 * for statement input to exceptional / total input is low, we output exceptional one
	 * 
	 * */
//	private SequenceOutputAfterCommenting Old_individualAnalysis(ExecutableSequence eSeq) {
//		int length = eSeq.sequence.size();
//		//the sequence map keep track of the intermediate result
//		IntermediateSequenceMap sequenceMap = new IntermediateSequenceMap(eSeq, this.visitor);
//		sequenceMap.init();
//		
//		//construct a sequence execution history for fault diagnose
//		SequenceExecutionHistory history = new SequenceExecutionHistory(eSeq, sequenceMap);
//		
//		//diagnosing each statement one by one
//		for(int index = 0; index < length; index++) {
//			System.out.println("start diagnosing the " + index + "th statement");
//			//the type
//			
//			FDLog.log("Start replacing values for the " + index + "th statement");
//			
//			Class<?> type = SequenceUtils.getUsedTypeForOutput(eSeq, index);
//			
//			history.addUsedTypeForStatement(index, type);
//			
//			AbstractState stateOfIndex = sequenceMap.getAbstractState(index);
//			
//			Map<AbstractState, Sequence> stateSeqs =
//				this.sequenceHelper.chooseShortestSequenceWithDiffStates(type, stateOfIndex);
//			
//			stateSeqs = HeuristicFilters.filterByHeuristic(stateSeqs, type);
//			
//			System.out.println("   number of potential sequence for type: " + type.getName() + " is " + stateSeqs.size());
//			
//			for(AbstractState replace_state : stateSeqs.keySet()) {
//				
//				FDLog.log("   for type: " + type.getName() + " try: " + replace_state );
//				
//				System.out.print(".");
//				Sequence replace_candidate = stateSeqs.get(replace_state);
//				//Sequence replace_candidate = SequenceUtils.chooseShortestWithOutputType(candidates, type);
//				
//				Sequence newSequence = SequenceReplacement.replaceToNewSequence(eSeq, index, replace_candidate);
//				
//				ExecutableSequence exec_seq = new ExecutableSequence(newSequence);
//				exec_seq.execute(this.visitor);
//				
//	            ExecutionResult execResult = new ExecutionResult(eSeq.sequence, index, replace_candidate, replace_state, exec_seq);			
//				history.addExecutionResult(execResult);
//				
//				FDLog.log("     Has failure? " + execResult.hasFailure() + ",  has exceptional statement? " + execResult.hasExceptionThrown()
//						+ ",  if so, the index of exception thrown: " + (execResult.indexOfExecuted()) + ", exception type: " + execResult.getThrownException());
//				
//				
//				FDLog.log("--------------");
//				FDLog.log(execResult.toString());
//			}
//			System.out.println();
//			
//		}
//		System.out.println("Sample comments:");
//		
//		List<String> commentforStmt = history.diagnose();
//		
//		SequenceOutputAfterCommenting seqComment = new SequenceOutputAfterCommenting(eSeq, commentforStmt);
//		
//		System.out.println(seqComment.outputCommentedSequence());
//		
//		return seqComment;
//	}
}
