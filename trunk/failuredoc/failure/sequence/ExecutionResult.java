package failure.sequence;

import java.util.LinkedList;
import java.util.List;

import failure.FDLog;
import failure.FDUtils;
import failure.state.AbstractState;
import randoop.ExceptionalExecution;
import randoop.ExecutableSequence;
import randoop.ExecutionOutcome;
import randoop.NotExecuted;
import randoop.Sequence;
import randoop.Check;

public class ExecutionResult {
	
	public final Sequence original_sequence;
	public final int replacing_index;
	public final Sequence replacing_sequence;
	public final AbstractState replacing_profile;
	public final ExecutableSequence replaced_eSeq;
	
	//the variable for cache
	private int cached_executed_index = -1;
	private Throwable cached_exception_thrown = null;
	
	public ExecutionResult(Sequence original_seq, int replacing_index,
			Sequence replacing_sequence, AbstractState replacing_profile, ExecutableSequence eseq_replaced) {
		FDUtils.checkNull(original_seq, "The original seq could not be null.");
		FDUtils.checkTrue( replacing_index >= 0 && replacing_index < original_seq.size(),
				"The replacing_index: " + replacing_index + " is not valid, the seq size: " + original_seq.size());
		FDUtils.checkNull(replacing_sequence, "the replacing_sequence could not be null.");
		FDUtils.checkNull(eseq_replaced, "The eseq_replaced could not be null.");
		
		this.original_sequence = original_seq;
		this.replacing_index = replacing_index;
		this.replacing_sequence = replacing_sequence;
		this.replacing_profile = replacing_profile;
		this.replaced_eSeq = eseq_replaced;
		
		//get the result,  about exceptional, failure, or not executed?
		
		//get the binary-profile
	}
	
	public boolean hasNonExecuted() {
		return replaced_eSeq.hasNonExecutedStatements();
	}
	
	/**
	 * Return the max index from 0 - size() -1, which executes normally
	 * */
	public int indexOfExecuted() {
		if(this.cached_executed_index != -1) {
			return this.cached_executed_index;
		}
		//recompute it
		if(!hasNonExecuted()) {
			this.cached_executed_index = this.original_sequence.size() - 1;
			return this.cached_executed_index;
		}
		
		int exec_index_in_replaced = -1;
		ExecutionOutcome[] outcomes = this.replaced_eSeq.getAllResults();
		for(int i = 0; i < outcomes.length; i++) {
			if(outcomes[i] instanceof NotExecuted) {
				exec_index_in_replaced = i - 1;
				break;
			}
		}
		
		FDUtils.checkTrue(exec_index_in_replaced != -1, "The exec_index_in_replaced: " + exec_index_in_replaced
				+ " should not be -1.");
		//that is the replacing sequence should not fail!
		FDUtils.checkTrue(exec_index_in_replaced > this.replacing_index, "The exec_index_in_replaced: "
				+ exec_index_in_replaced + " should > this.replacing_index: " + this.replacing_index);
		//we could even claim
//		PalusUtil.checkTrue(exec_index_in_replaced > this.replacing_index
//				+ this.replacing_sequence.size() - 1);
		//XXX fixme
		
		//compute the corresponding index in the original statement
		int exec_index_in_orig = exec_index_in_replaced - this.replacing_sequence.size() + 1;
		
		this.cached_executed_index = exec_index_in_orig;
		
		return this.cached_executed_index;
	}
	
	public boolean hasExceptionThrown() {
		//return this.replaced_eSeq.hasUnexpectedException();
		
		for (int i = 0 ; i < (this.replaced_eSeq.sequence.size()-1) ; i++) {
		      if(replaced_eSeq.getResult(i) instanceof ExceptionalExecution) {
		    	  return true;
		      }
		}
		return false;
		
	}
	
	public Throwable getThrownException() {
		if(this.cached_exception_thrown != null) {
			return this.cached_exception_thrown;
		}
		
		if(!hasExceptionThrown()) {
			this.cached_exception_thrown = null;
			return this.cached_exception_thrown;
		}
		
		int index_of_exception = -1;
		ExecutionOutcome[] outcomes = this.replaced_eSeq.getAllResults();
		for(int i = 0; i < outcomes.length; i++) {
			if(outcomes[i] instanceof ExceptionalExecution) {
				index_of_exception = i;
				ExceptionalExecution e = (ExceptionalExecution)outcomes[i];
				this.cached_exception_thrown = e.getException();
				break;
			}
		}
		
		int index_of_exception_in_original = index_of_exception - this.replacing_sequence.size()
		    + 1;
		
		//check there must be exception
		FDUtils.checkTrue(this.replaced_eSeq.hasUnexpectedException(), "The replace_eSeq should have unexpected" +
				" exception.");
		FDUtils.checkNull(this.cached_exception_thrown, "The cached_exception could not be null.");
		//the statement must has exceptions, the last statement executed
		FDUtils.checkTrue(index_of_exception_in_original == this.indexOfExecuted(), "The index_of_exception: " +
				index_of_exception_in_original + " !=  this.indexOfExecuted(): " + this.indexOfExecuted());
		
		return this.cached_exception_thrown;
	}
	
	public boolean hasFailure() {
		return this.replaced_eSeq.hasFailure();
	}
	
	//in original sequence
	public int getFailureIndex() {
		if(!hasFailure()) {
			return -1;
		}
		int index = this.replaced_eSeq.getFailureIndex() - this.replacing_sequence.size() + 1;
		FDUtils.checkTrue(index > 0 && index < this.original_sequence.size(), "The index: " + index
				+ " is not valid, the original sequence size: " + this.original_sequence.size());
		
		return index;
	}
	
	public List<Check> getFailures() {
		if(!hasFailure()) {
			return new LinkedList<Check>();
		}
		int failure_index = this.replaced_eSeq.getFailureIndex();
		return this.replaced_eSeq.getFailures(failure_index);
	}
	
	@Override
	public int hashCode() {
		return this.replacing_profile.clz.hashCode() + (this.hasFailure() ? 13 : 88)  + (this.hasExceptionThrown() ? 91 : 127)
				+ (this.getThrownException() == null ? 999 : this.getThrownException().hashCode())
				+ (this.hasNonExecuted() ? 10999 : 444 ) + 45 * this.indexOfExecuted();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ExecutionResult)) {
			return false;
		}
		ExecutionResult other = (ExecutionResult)obj;
		
		if(!this.replacing_profile.clz.equals(other.replacing_profile.clz)) {
			return false;
		}
		
		if(this.hasFailure() != other.hasFailure()) {
			return false;
		}
		
		if(this.hasExceptionThrown() != other.hasExceptionThrown() ) {
			return false;
		}
		
		if(this.hasNonExecuted() != other.hasNonExecuted()) {
			return false;
		}
		
		if(this.hasExceptionThrown() && other.hasExceptionThrown() && !this.getThrownException().equals(other.getThrownException())) {
			return false;
		}
		
		if(this.indexOfExecuted() != other.indexOfExecuted()) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Type: " + this.replacing_profile.clz.getName() + ",  replace the "
				+ this.replacing_index + "th statement" + " with: " + this.replacing_profile + "\n");
		sb.append("     Has failure? " + this.hasFailure() + ",  has exceptional statement? " + this.hasExceptionThrown()
				+ ",  if so, the index of exception thrown: " + (this.indexOfExecuted()) + ", exception type: "
				+ this.getThrownException() + "\n");

		
		return sb.toString();
	}
	
	public String explain() {
		StringBuilder sb = new StringBuilder();
		
		if(this.hasFailure()) {sb.append(" still has failure. ");}
		else if (this.hasExceptionThrown()) {sb.append(" not failed, but has exception: " + this.getThrownException()
				+ " at line: " + this.indexOfExecuted());}
		else if (!this.hasNonExecuted()) {sb.append(" executes successfully. ");}
		else {sb.append(" the assertion fails (this input is illegal).");}
		
		return sb.toString();
	}
}