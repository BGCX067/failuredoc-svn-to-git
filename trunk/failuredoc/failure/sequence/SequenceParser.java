package failure.sequence;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import randoop.Sequence;

public class SequenceParser {
	
	public final String fileToRead;
	
	public SequenceParser(String fileToRead) {
		assert fileToRead != null : "The input file can not be null";
		this.fileToRead = fileToRead;
	}
	
	/**
	 * Reads the file, and parses the content to get sequences.
	 * */
	public List<Sequence> parseSequences() {
		List<Sequence> sequences = new LinkedList<Sequence>();
		Sequence.readTextSequences(this.fileToRead, sequences);
		return sequences;
	}
}