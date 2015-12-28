package experiment;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import randoop.Sequence;
import randoop.main.GenInputsAbstract;
import failure.sequence.SequenceExplainer;
import failure.sequence.SequenceFactory;
import failure.sequence.SequenceOutputAfterCommenting;
import failure.sequence.prettyprint.SequencePrettyPrinter;

public class RunJDKRealFailedTest {
	
	public static void main(String[] args) {
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.documented_test = "JDKRealRport";
		GenInputsAbstract.forbidden_exception = "java.lang.ArrayStoreException";
		GenInputsAbstract.remove_likely_useless = true;
		List<Sequence> sequences = new LinkedList<Sequence>();
		Sequence toArrayFailed = SequenceFactory.createJDKReportedFailedSequence();
		sequences.add(toArrayFailed);
		Collection<Class<?>> additionals = new LinkedList<Class<?>>();
		//additionals.add(Integer.class);
		SequenceExplainer explainer = new SequenceExplainer(sequences, additionals);
		List<SequenceOutputAfterCommenting> documented_seqs = explainer.explainFailedSequences();
		for(SequenceOutputAfterCommenting commented_seq : documented_seqs) {
  	      String content = commented_seq.outputCommentedSequence();
  	      //System.out.println("all comments: " + commented_seq.comments);
  	      System.out.println(content);
        }
		
		SequencePrettyPrinter printer = new SequencePrettyPrinter(documented_seqs, GenInputsAbstract.documented_test + "_Pretty");
  	    printer.create_file(GenInputsAbstract.documented_test_output);
	}
	
}
