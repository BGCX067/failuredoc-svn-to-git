package failuredoc.analysis;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import randoop.ExecutableSequence;
import randoop.NormalExecution;
import randoop.Sequence;
import randoop.StatementKind;
import randoop.Variable;
import randoop.main.GenInputsAbstract;

import failure.FDLog;
import failure.FDUtils;
import failure.state.AbstractState;
import failure.state.IgnoreState;
import failure.state.ObjectProfileVector;
import failure.state.SingleTrace;
import failuredoc.analysis.inference.AbstractPropertyChecker;
import failuredoc.analysis.inference.ObjectPropertyInferrer;
import failuredoc.analysis.inference.SameTypeChecker;
import failuredoc.analysis.simplify.SequenceSimplifyUtils;

/**
 * Adapts Ben Liblit PLDI 2005 algorithm to the document inference problem
 * */
public class StatisticalDebugging {
	
	public final List<SingleTrace> traces;
	public final ExecutableSequence failed_seq;
	public final int line_size;
	public final ObjectProfileVector original_failed_trace;
	
	private List<Features> inferred_features = null;
	
	public StatisticalDebugging(ExecutableSequence failed_seq, List<SingleTrace> traces, /*int line_size,*/
			ObjectProfileVector original_failed_trace) {
		this.traces = traces;
		this.failed_seq = failed_seq;
		this.line_size = failed_seq.sequence.size();
		FDUtils.checkNull(original_failed_trace, "The failed trace should not be null.");
		this.original_failed_trace = original_failed_trace;
	}
	
	public void start_diagnose() {
		FDLog.log("Diagnosing errors in statistical debugging, trace num: "
				+ this.traces.size() + ", line size (num of statement): " + this.line_size);
		MetricsCalculator metricsCalculator = new MetricsCalculator(this.traces, this.line_size);
		metricsCalculator.compute_scores();
		//get the statistical counting
		 List<Map<AbstractState, Scores>> scores = metricsCalculator.getStatisticScores();
		 FDUtils.checkTrue(line_size == scores.size(), "the line_size: " + line_size + " should be equal to scores length: "
				 + scores.size());
		 
		 if(GenInputsAbstract.failuredoc_verbose) {
		     this.viewScores(scores); 
		 }
		 
		 //do the inference
		 this.inferred_features = this.summarize_ab_states(scores);
	}
	
	private List<Features> summarize_ab_states(List<Map<AbstractState, Scores>> scores) {
		List<Features> features = new LinkedList<Features>();
		for(int map_index = 0; map_index < scores.size(); map_index++) {
			Map<AbstractState, Scores> ab_map = scores.get(map_index);
			Features feat = new Features(ab_map);
			FDLog.log("Statistical anlaysis for the " + map_index + "-th line");
			feat.inferFeatures(); //the method name should be revised, misleading, due to historical reason
//			FDLog.log("@summarzie_ab_states, for the: " + map_index
//					+ "-th statement, has inferred: " + feat.hasInferred());
			if(!feat.hasInferred()) {
				features.add(null);
			} else {
				features.add(feat);
			}
			FDLog.log(" ----- ");
		}
		FDUtils.checkTrue(features.size() == this.line_size, "The line_size: " + line_size + " should be equal to features length: "
				+ features.size());
		return features;
	}
	
	private void viewScores(List<Map<AbstractState, Scores>> scores) {
		int i = 0;
		for(Map<AbstractState, Scores> score_map : scores) {
			FDUtils.stdln((i++) + "-th line ----------- ");
			for(AbstractState state : score_map.keySet()) {
				if(state.obj == IgnoreState.IGNORE) {
					continue;
				}
				Scores s = score_map.get(state);
                //only focus on those fail == 0 but pass > 0
				if(s.fail == 0 && s.pass > 0) {
					FDUtils.stdln("  " + state);
					FDUtils.stdln(s);
					FDUtils.stdln("");
				}
			}
		}
	}
	
	//convert the suspicious object and its states into comment
	public List<String> generate_comments() {
		List<String> object_comments = new LinkedList<String>();
		List<String> comments = new LinkedList<String>();
		/**
		 * The size of features should == the size of statements in a failed test
		 * */
		for(int stmt_index = 0; stmt_index < this.inferred_features.size(); stmt_index++) {
			Features feat = this.inferred_features.get(stmt_index);
			
			
			if(feat == null) {
				//two different lists
				object_comments.add("");
				comments.add("");
			} else {
				Object[] objs = feat.getOriginalObjects();
				FDLog.log("Inferring document for: " + stmt_index + "-th statement, object num: " + objs.length);
				for(Object obj : objs) {
					//FDLog.log(" obj: " + obj + ", type: " + (obj == null? "null, no type " : obj.getClass()));
				}
				//start to do property inference
				ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
				boolean hasProperty = inferrer.checkDefaultProperties();
				FDLog.log("has properties? " + hasProperty);
				if(hasProperty) {
					//use the following 2 statements to replace the following one
					//String comment = inferrer.checkedPropertiesToString();
					List<AbstractPropertyChecker> satisfied_checkers = inferrer.satisified_checkers;
					String comment = this.convertedCheckedPropertiesToString(satisfied_checkers, stmt_index);
					FDLog.log("The summarized comment: " + comment);
					if(GenInputsAbstract.append_example && inferrer.satisified_checkers.size() == 1
							&& inferrer.satisified_checkers.get(0).getClass().equals(SameTypeChecker.class)) {
						String objstr = chooseShortestObjectString(objs);
						comment = comment + ", an example value: " + objstr;
					}
					if(GenInputsAbstract.append_example) {
						comment = comment + ".\n        //Current value: " +
						this.original_failed_trace.failed_objects.get(stmt_index);
					}
					object_comments.add(comment);
				} else if(objs.length > 0 && GenInputsAbstract.select_closest_type) {
					//add all appearing types
					Set<Class<?>> all_types = new HashSet<Class<?>>();
					for(Object obj : this.original_failed_trace.failed_objects) {
						if(obj != null) {
							all_types.add(obj.getClass());
						}
					}
					//check obj
					for(int obj_index = objs.length - 1; obj_index > -1; obj_index --) {
						Object obj = objs[obj_index];
						if(obj == null) {
							continue;
						}
						Class<?> clz = obj.getClass();
						if(all_types.contains(clz)) {
							object_comments.add(" is type: " + clz + ", with value: " + obj
									+ ".\n        // Current type: " + this.original_failed_trace.failed_objects.get(stmt_index).getClass()
									+ ", with value: " + this.original_failed_trace.failed_objects.get(stmt_index));
							break;
						}
					}
					
				} else {
					object_comments.add("");
				}
				
//				boolean hasAdd = false;
//				String comment = "";
//				if(feat.getCommonClass() != null) {
//					comment +=  feat.getCommonClass().getName() + " type, ";
//					hasAdd = true;
//				}
//				if(feat.getSingleValue() != null) {
//					if(feat.getSingleValue().obj instanceof Collection) {
//						Collection c = (Collection)feat.getSingleValue().obj;
//						if(c.isEmpty()) {
//							comment += " an empty " + feat.getSingleValue().obj.getClass();
//						} else {
//							comment += " with value: " + feat.getSingleValue().obj
//							+ " is empty: " + c.isEmpty();
//						}
//					} else {
//					   comment += " with value: " + feat.getSingleValue().obj;
//					}
//				}
//				if(feat.isComparableObject()) {
//					if(hasAdd) {
//						comment += " and ";
//					}
//					comment += " is an comparable type ";
//					hasAdd = true;
//				}
//				if(feat.isEmptyCollection()) {
//					if(hasAdd) {
//						comment += " and ";
//					}
//					comment += " and is an empty collection ";
//				}
//				if(!comment.trim().equals("")) {
//					comment =  "Test passes when replacing with " + comment;
//				}
//				comments.add(comment);
			}
		}
//		return comments;
		return object_comments;
	}
	
	private String chooseShortestObjectString(Object[] objs) {
		if(objs.length == 0) {
			return "";
		}
		String shortestNonEmpty = objs[0] + "";
		for(int i = 1; i < objs.length; i++) {
			Object obj = objs[i];
			String tmp = obj +"";
			if(!tmp.isEmpty() && tmp.length() < shortestNonEmpty.length()) {
				shortestNonEmpty = tmp;
			}
		}
		return shortestNonEmpty;
	}
	
	private String convertedCheckedPropertiesToString(List<AbstractPropertyChecker> satisfied_checkers,
			 int stmt_index) {
		FDUtils.checkTrue(!satisfied_checkers.isEmpty(), "The satisfied checker list is not empty.");
		
		Class<?> outputType = this.failed_seq.sequence.getStatementKind(stmt_index).getOutputType();
		StatementKind statement = this.failed_seq.sequence.getStatementKind(stmt_index);
		boolean hasOutputObj = this.failed_seq.getResult(stmt_index) instanceof NormalExecution;
		Variable[] vars = this.failed_seq.sequence.getInputs(stmt_index).toArray(new Variable[0]);
		Variable outputVar = this.failed_seq.sequence.getVariable(stmt_index);
		
		StringBuilder sb = new StringBuilder();

		int count = 0;
		for(AbstractPropertyChecker checker : satisfied_checkers) {
//			System.out.println(checker);
			checker.setOutputType(outputType);
			checker.setStatement(statement);
			checker.setOutputVariable(outputVar);
			checker.setInputVariables(vars);
			if(hasOutputObj) {
				Object obj = this.executeToGetObjectAtIndex(stmt_index);
				checker.setFailedObject(obj);
			}
			
			if(count != 0) {
				sb.append(", or ");
			}
			sb.append(checker.propertyToString());
			count++;
		}
		
		return sb.toString();
	}
	
	private Object executeToGetObjectAtIndex(int stmt_index) {
		
		List<Integer> retained = new LinkedList<Integer>();
		for(int i = 0; i <= stmt_index; i++) {
			retained.add(i);
		}
		Sequence subSequence = SequenceSimplifyUtils.retainStatements(this.failed_seq.sequence, retained);
		ExecutableSequence esubseq = new ExecutableSequence(subSequence);
		esubseq.execute(null);
		FDUtils.checkTrue(!esubseq.hasFailure(), "should not has failure");
		Object obj = ((NormalExecution)esubseq.getResult(stmt_index)).getRuntimeValue();
		return obj;
	}
}