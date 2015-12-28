package failuredoc.analysis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import randoop.Globals;

import failure.FDUtils;
import failure.state.AbstractState;
import failure.state.ExecState;
import failure.state.IgnoreState;
import failure.state.SingleTrace;

/**
 * A class computing the metrics of pass, increase, and importance
 * */
public class MetricsCalculator {

	public final List<Map<AbstractState, Counting>> statistics = new ArrayList<Map<AbstractState, Counting>>();
	
	public final List<Map<AbstractState, Scores>> scores = new ArrayList<Map<AbstractState, Scores>>();
	
	public final List<Integer> failed_evaluations = new ArrayList<Integer>();
	public final List<Integer> passed_evaluations = new ArrayList<Integer>();
	
	public final List<SingleTrace> traces;
	public final int line_num;
	
	/**
	 * Constructor
	 * */
	public MetricsCalculator(List<SingleTrace> traces, int line_num) {
		this.traces = traces;
		this.line_num = line_num;
		this.construct_statistics_list(traces);
		//check the result
		FDUtils.checkTrue(statistics.size() == line_num, "The number of statistics should be the same as the given line num,"
				+ " in fact the list size: " + statistics.size() + ", line num is: " + line_num);
		FDUtils.checkTrue(failed_evaluations.size() == line_num, "The number of failed_evaluations should be the same as the given line num,"
				+ " in fact the failed_evaluations size: " + failed_evaluations.size() + ", line num is: " + line_num);
		FDUtils.checkTrue(passed_evaluations.size() == line_num, "The number of passed_evaluations should be the same as the given line num,"
				+ " in fact the passed_evaluations size: " + passed_evaluations.size() + ", line num is: " + line_num);
	}
	
	public void compute_scores() {
		FDUtils.checkTrue(!statistics.isEmpty(), "The statistics list could not be empty.");
		//first compute the total fails
		int total_fails = 0;
		int total_passes = 0;
		for(SingleTrace trace : traces) {
			if(trace.result == ExecState.FAIL) {
				total_fails++;
			}
			if(trace.result == ExecState.PASS) {
				total_passes++;
			}
		}
		
		//compute the scores
		for(int i = 0; i < statistics.size(); i++) {
			Map<AbstractState, Counting> ab_map_counting = statistics.get(i);
			Map<AbstractState, Scores> ab_map_score = new LinkedHashMap<AbstractState, Scores>();
			for(AbstractState ab_state : ab_map_counting.keySet()) {
				Counting counting = ab_map_counting.get(ab_state);
				int fail = counting.fail();
				int pass = counting.pass();
				int fail_evaluate = this.failed_evaluations.get(i);
				int pass_evaluate = this.passed_evaluations.get(i);
				Scores scores = new Scores(pass, fail, pass_evaluate, fail_evaluate, total_fails, total_passes);
				ab_map_score.put(ab_state, scores);
			}
			
			scores.add(ab_map_score);
		}
		
		FDUtils.checkTrue(statistics.size() == scores.size(), "The statistics size: " + this.statistics.size()
				+ ", the scores size: " + this.scores + " should be equal.");
	}
	
	public List<Map<AbstractState, Counting>> getStatisticCountings() {
		return this.statistics;
	}
	
	public List<Map<AbstractState, Scores>> getStatisticScores() {
		return this.scores;
	}
	
	
	/**
	 * Construct statistics list
	 * */
	private void construct_statistics_list(List<SingleTrace> traces) {
		//insert empty map first
		for(int i = 0; i < this.line_num; i++) {
			this.statistics.add( new LinkedHashMap<AbstractState, Counting>());
		}
		
		for(SingleTrace trace : traces) {
			ExecState exec_result = trace.result;
			List<AbstractState> state_list = trace.abStates;
			for(int i = 0; i < state_list.size(); i++) {
				AbstractState ab_state = state_list.get(i);
				Map<AbstractState, Counting> trace_map = this.statistics.get(i);
				if(!trace_map.containsKey(ab_state)) {
					trace_map.put(ab_state, new Counting());
				}
				Counting counting = trace_map.get(ab_state);
				//increase the executed, pass, fail, and exception count
				if(ab_state.obj == IgnoreState.IGNORE) {
					counting.incr_non_executed();
				} else {
				    if(exec_result == ExecState.PASS) {
					   counting.incr_pass();
				    } else if (exec_result == ExecState.FAIL) {
					   counting.incr_fail();
				    } else if (exec_result == ExecState.EXCEPTION) {
					   counting.incr_exception();
				    } else {
					  throw new RuntimeException("Unexpected execution result: " + exec_result + ", ab_state: " + ab_state);
				    }
				}
			}
		}
		
		//countinig for failed evaluation, and passed evaluations
		Map<Integer, Integer> failed_evaluate = new LinkedHashMap<Integer, Integer>();
		Map<Integer, Integer> passed_evaluate = new LinkedHashMap<Integer, Integer>();
		for(int i = 0; i < this.line_num; i++) {
			failed_evaluate.put(i, 0);
			passed_evaluate.put(i, 0);
		}
		for(SingleTrace trace : traces) {
			ExecState result = trace.result;
			List<AbstractState> ab_states = trace.abStates;
			FDUtils.checkTrue(ab_states.size() == this.line_num, "ab state size: " + ab_states.size()
					+ ", line num: " + this.line_num);
			for(int i = 0; i < ab_states.size(); i++) {
				AbstractState ab_state = ab_states.get(i);
				if(ab_state.obj != IgnoreState.IGNORE) {
					if(result == ExecState.FAIL) {
						failed_evaluate.put(i, failed_evaluate.get(i) + 1);
					} else if (result == ExecState.PASS) {
						passed_evaluate.put(i, passed_evaluate.get(i) + 1);
					}
				}
			}
		}
		
		Set<Integer> failed_key_set = failed_evaluate.keySet();
		Set<Integer> passed_key_set = passed_evaluate.keySet();
		FDUtils.checkTrue(failed_key_set.size() == passed_key_set.size(), "should have equal size");
		FDUtils.checkTrue(passed_key_set.size() == this.line_num, "should have equal size");
		
		for(int i = 0; i < this.line_num; i++) {
			FDUtils.checkTrue(failed_key_set.contains(i), "Should contain: " + i);
			FDUtils.checkTrue(passed_key_set.contains(i), "Should contain: " + i);
			this.passed_evaluations.add(passed_evaluate.get(i));
			this.failed_evaluations.add(failed_evaluate.get(i));
		}
	}
}

class Counting {
	private int pass = 0;
	private int fail = 0;
	private int exception = 0;
	//not executed
	private int not_executed = 0;
	
	//observed when fail
//	private int observe_when_fail = 0;
//	private int observe_when_pass = 0;
	
	public Counting() {}
	public void incr_pass() {
		pass++;
	}
	public void incr_fail() {
		fail++;
	}
	public void incr_exception() {
		exception++;
	}
	public void incr_non_executed() {
		not_executed++;
	}
//	public void incr_observe_when_fail() {
//		observe_when_fail++;
//	}
//	public void incr_observe_when_pass() {
//		observe_when_pass++;
//	}
	
	public int pass() {
		return pass;
	}
	public int fail() {
		return fail;
	}
	public int exception() {
		return exception;
	}
//	public int observe_when_fail() {
//		return observe_when_fail;
//	}
//	public int observe_when_pass() {
//		return observe_when_pass;
//	}
	public int non_executed() {
		return not_executed;
	}
	public int total_evaluated() {
		return pass + fail + exception;
	}
}

class Scores {
	
	public final int pass; //S(P)
	public final int fail; //F(p)
	public final int fail_executed; //F(P is executed)
	public final int pass_executed; //S(P is executed)
	public final int total_fail_num; //Num(F)
	public final int total_pass_num; //NUM(P)
	
	public double failure = Double.NaN;
	public double context = Double.NaN;
	public double increase = Double.NaN;
	public double importance = Double.NaN;
	
	public double pass_rate = Double.NaN;
	public double pass_context = Double.NaN;
	public double pass_increase = Double.NaN;
	public double pass_importance = Double.NaN;
	
	public Scores(int pass, int fail, int pass_exec, int fail_exec,  int total_fail, int total_passes) {
		FDUtils.checkTrue(pass >= 0 && fail >=0 && fail_exec >=0 && pass_exec >= 0 && total_fail >=0 && total_passes >= 0,
				"All parameters should >= 0, here: pass: " + pass + ", fail: " + fail + ", fail_exec: "
				+ fail_exec + ",  pass_exec: " + pass_exec + ", total fail: " + total_fail + ", total_passes: " + total_passes);
		this.pass = pass;
		this.fail = fail;
		this.fail_executed = fail_exec;
		this.pass_executed = pass_exec;
		this.total_fail_num = total_fail;
		this.total_pass_num = total_passes;
		//calculate scores
		this.calculate_scores();
	}
	
	private void calculate_scores() {
		if(fail + pass != 0) {
		  this.failure = ((double)fail / ((double)(fail + pass)));
		  this.context = ((double)fail_executed / ((double)(fail_executed + pass_executed)));
		  this.increase = this.failure - this.context;
		  //compute the importance
		  double log_fp = Math.log(this.fail);
		  double log_numF = Math.log(this.total_fail_num);
		  this.importance =  (2/((1/this.increase) + (1/(log_fp/log_numF))));
		  
		  //compute the passing value
		  this.pass_rate = (double)pass/(double)(fail + pass);
		  this.pass_context = (double)pass_executed / (double)(pass_executed + fail_executed);
		  this.pass_increase = this.pass_rate - this.pass_context;
		  //this.importance = 2/((1/this.pass_increase) + (1/(log_fp/log_numF)));
		  double log_pass = Math.log(this.pass);
		  double log_numP = Math.log(this.total_pass_num);
		  if(this.pass < 3) {
			  log_pass = this.pass;
		  }
//		  this.pass_importance = (2/((1/this.pass_increase) + (1/(log_pass/log_numP))));
		  this.pass_importance = (2/((1/this.pass_increase) + (1/(log_pass))));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("  pass: " + pass);
		sb.append(Globals.lineSep);
		sb.append("  fail: " + fail);
		sb.append(Globals.lineSep);
		sb.append("  fail_executed: " + fail_executed);
		sb.append(Globals.lineSep);
		sb.append("  pass_executed: " + pass_executed);
		sb.append(Globals.lineSep);
		sb.append("  total_fail_num: " + total_fail_num);
		sb.append(Globals.lineSep);
		sb.append("  failure rate: " + failure + "  " + " pass+fail is: " + (pass+fail) + "   "
				+ ((pass+fail) == 0 ? Double.NaN : (((fail)/(fail+pass)))));
		sb.append(Globals.lineSep);
		sb.append("  context rate: " + context);
		sb.append(Globals.lineSep);
		sb.append("  increase rate: " + increase);
		sb.append(Globals.lineSep);
		sb.append("  importance rate: " + importance);
		sb.append(Globals.lineSep);
		sb.append("  pass_rate: " + pass_rate);
		sb.append(Globals.lineSep);
		sb.append("  pass_context: " + pass_context);
		sb.append(Globals.lineSep);
		sb.append("  pass_increase: " + pass_increase);
		sb.append(Globals.lineSep);
		sb.append("  pass_importance: " + pass_importance);
		sb.append(Globals.lineSep);
		
		return sb.toString();
	}
	
}