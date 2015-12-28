package failure.sequence.prettyprint;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import randoop.Globals;
import randoop.RMethod;
import randoop.Sequence;
import randoop.StatementKind;

import failure.FDUtils;
import failure.sequence.SequenceOutputAfterCommenting;

public class SequencePrettyPrinter {
	
	public final List<SequenceOutputAfterCommenting> output_sequences;
	    //= new LinkedList<SequenceOutputAfterCommenting>();
	
	public final String output_classname;
	
	//public final String output_dir;
	
	public SequencePrettyPrinter(List<SequenceOutputAfterCommenting> seqs,
			/*String output_dir,*/ String clzName) {
		FDUtils.checkNull(seqs, "The sequences can not be null.");
		FDUtils.checkNull(clzName, "The clazzName can not be null.");
		//FDUtils.checkNull(output_dir, "The output_dir should not be null.");
		this.output_sequences = seqs;
		this.output_classname = clzName;
		//this.output_dir = output_dir;
	}
	
	public String pretty_print_sequences() {
		String[] all_import_classes = this.create_import_classes();
		StringBuilder sb = new StringBuilder();
		
		//default package
		//add import
		for(String import_clz : all_import_classes) {
			sb.append("import ");
			sb.append(import_clz);
			sb.append(";");
			sb.append(Globals.lineSep);
		}
		sb.append(Globals.lineSep);
		sb.append("import junit.framework.TestCase;");
		sb.append(Globals.lineSep);
		
		//add class header
		sb.append("public class " + this.output_classname + " extends TestCase { ");
		sb.append(Globals.lineSep);
		
		int count = 0;
		for(SequenceOutputAfterCommenting sequenceWithComment : this.output_sequences) {
			Sequence sequence = sequenceWithComment.failed_sequence.sequence;
			VariableRenamer renamer = new VariableRenamer(sequence);
			List<String> comments = sequenceWithComment.generateProcessedComment(renamer);
			
			FDUtils.checkTrue(sequence.size() == comments.size(), "The size of sequence: " + sequence.size()
					+ " and comments: " + comments.size() + " is not equal.");
			
			
			//print the method
			sb.append(indent("public void test" + (count++) + "() {", 2));
			sb.append(Globals.lineSep);
			
			//makes 4 indentation here
			SequencePrinter printer = new SequencePrinter(sequenceWithComment.failed_sequence, renamer);
			String codelines = printer.printToString();
			
			//XXX note that SIZE of code lines >= SIZE of comments
			//due to extra assertions
			String[] all_code_lines = codelines.split(Globals.lineSep);
			for(int lineNum = 0; lineNum < all_code_lines.length; lineNum++) {
			    String codeline = all_code_lines[lineNum];
			    String comment = "";
			    if(lineNum < comments.size()) {
			    	comment = comments.get(lineNum);
			    }
			    
			    if(!comment.trim().equals("")) {
			    	sb.append(indent(comment, 4));
			    }
			    sb.append(indent(codeline, 4));
			    sb.append(Globals.lineSep);
			}
			
			sb.append(indent("}", 2));
			sb.append(Globals.lineSep);
		}
		
		sb.append(Globals.lineSep);
		sb.append("}");
		
		return sb.toString();
	}
	
	public void create_file(String output_dir) {
		FDUtils.checkNull(output_dir, "The output_dir can not be null.");
		File f = new File(output_dir);
		if(f.exists()) {
			FDUtils.checkTrue(f.isDirectory(), "The output_dir: "
					+ output_dir + " should be a dir.");
		} else {
			f.mkdirs();
		}
		File output_file = new File(output_dir + Globals.fileSep + this.output_classname + ".java");
		String content = this.pretty_print_sequences();
		FDUtils.writeToFile(content, output_file);
		FDUtils.stdln("Pretty print output: " + output_file.getAbsolutePath());
	}
	
	private String[] create_import_classes() {
		//keep all needed import
		Set<String> import_clz_set = new HashSet<String>();
		for(SequenceOutputAfterCommenting commented_seq : this.output_sequences) {
			Sequence sequence = commented_seq.failed_sequence.sequence;
			int length = sequence.size();
			for(int i = 0; i < length; i++) {
				StatementKind statement = sequence.getStatementKind(i);
				Class<?> outputType = statement.getOutputType();
				outputType = this.getComponentType(outputType);
				
				//add return type
				if(needImport(outputType)) {
					import_clz_set.add(outputType.getName());
				}
				//add input types
				for(Class<?> inputType : statement.getInputTypes()) {
					inputType = this.getComponentType(inputType);
					if(needImport(inputType)) {
						import_clz_set.add(inputType.getName());
					}
				}
				
				//if it is a RMethod, consider the case it may be
				//static method
				if(statement instanceof RMethod) {
					RMethod rmethod = (RMethod)statement;
					if(rmethod.isStatic()) {
						Class<?> declaring_class = rmethod.getMethod().getDeclaringClass();
						if(needImport(declaring_class)) {
							import_clz_set.add(declaring_class.getName());
						}
					}
				}
			}
		}
		//return the import class array
		return (String[])import_clz_set.toArray(new String[0]);
	}
	
	/**
	 * Needs import a class?
	 * */
	private boolean needImport(Class<?> clazz) {
		return !clazz.equals(void.class) && !clazz.isPrimitive();
	}
	
	private String indent(String str, int num) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < num; i++) {
			sb.append(" ");
		}
		sb.append(str);
		return sb.toString();
	}
	
	private Class<?> getComponentType(Class<?> type) {
		if(type.isArray()) {
			while(type.isArray()) {
				type = type.getComponentType();
			}
		}
		return type;
	}
}