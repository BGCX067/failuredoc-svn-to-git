package failure.sequence;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import randoop.ExecutableSequence;
import randoop.Sequence;
import randoop.util.Reflection;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSequenceParser extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestSequenceParser.class);
	}
	
	public void testParseOneSequence() {
		String txtfile = "./failuredoctests/failure/sequence/one_seq.txt";
		SequenceParser parser = new SequenceParser(txtfile);
		Collection<Sequence> seqs = parser.parseSequences();
		assertTrue(seqs.size() == 1);
		Iterator<Sequence> it = seqs.iterator();
		while(it.hasNext()) {
			System.out.println("The parsed sequence: ");
			Sequence seq = it.next();
			ExecutableSequence eseq = new ExecutableSequence(seq);
			eseq.execute(null);
			System.out.println(eseq.toCodeString());
		}
	}
	
	public void testReflectiveClassReader() throws IOException {
		String clazzFile = "./failuredoctests/failure/sequence/class_file.txt";
		List<Class<?>> classes = Reflection.loadClassesFromFile(new File(clazzFile));
		System.out.println(classes);
	}
}
