package failure.sequence;

import java.util.LinkedList;
import java.util.List;

import failure.sequence.prettyprint.SequencePrettyPrinter;

import randoop.Sequence;
import randoop.main.GenInputsAbstract;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestTreeSetDocumentation extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestTreeSetDocumentation.class);
	}

	public void testTreeSet() {
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.documented_test = "TreeSetDocumented";
		List<Sequence> sequences = new LinkedList<Sequence>();
		Sequence treeSetFailed = SequenceFactory.createTreeSetFailedSequence();
		sequences.add(treeSetFailed);
		SequenceExplainer explainer = new SequenceExplainer(sequences);
		List<SequenceOutputAfterCommenting> documented_seqs = explainer.explainFailedSequences();
		for(SequenceOutputAfterCommenting commented_seq : documented_seqs) {
  	      String content = commented_seq.outputCommentedSequence();
  	      System.out.println("all comments: " + commented_seq.comments);
  	      System.out.println(content);
        }
		
		SequencePrettyPrinter printer = new SequencePrettyPrinter(documented_seqs, GenInputsAbstract.documented_test + "_Pretty");
  	    printer.create_file(GenInputsAbstract.documented_test_output);
	}
}
