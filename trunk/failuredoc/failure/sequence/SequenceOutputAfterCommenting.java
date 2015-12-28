package failure.sequence;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import failure.FDLog;
import failure.FDUtils;
import failure.sequence.prettyprint.VariableRenamer;
import failuredoc.analysis.inference.SingleValuePropertyChecker;
import randoop.Check;
import randoop.ExecutableSequence;
import randoop.ExpectedExceptionChecker;
import randoop.Globals;
import randoop.Variable;
import randoop.main.GenInputsAbstract;


public class SequenceOutputAfterCommenting {
	
	public static final String template = "Test passes if ";
	
    public final ExecutableSequence failed_sequence;
    
    public final List<String> comments;
	
	public SequenceOutputAfterCommenting(ExecutableSequence sequence, List<String> comments) {
		FDUtils.checkNull(sequence, "The sequence should not be null.");
		FDUtils.checkNull(comments, "The comments list should not be null.");
		
		FDUtils.checkTrue(sequence.sequence.size() == comments.size(), "The size of sequence: " + sequence.sequence.size()
				+ " is not equal to comments.size(): " + comments.size());
		
		this.failed_sequence = sequence;
		this.comments = comments;
		//processCommentsToRemove(this.comments);
	}
	
	private static void processCommentsToRemove(List<String> comments) {
		if(GenInputsAbstract.remove_likely_useless) {
			String pattern = "\\[.java";
			Pattern r = Pattern.compile(pattern);
			List<Integer> removed_indices = new LinkedList<Integer>();
			for(int i = 0; i < comments.size(); i++) {
				String comment = comments.get(i);
				Matcher m = r.matcher(comment);
				if(comment.indexOf("[Ljava") != -1) {
					removed_indices.add(i);
				}
			}
			for(int i = removed_indices.size() - 1; i >=0; i--) {
				comments.remove(i);
				comments.add(i, "");
			}
		}
	}
	
	public List<String> generateProcessedComment(VariableRenamer renamer) {
		List<String> commentAfterProcessing = new LinkedList<String>();
		
		for(int i = 0; i < this.failed_sequence.sequence.size(); i++) {
			String comment = comments.get(i);
			//prune out redundancy
			if(GenInputsAbstract.remove_likely_useless) {
			  Class<?> outputType = this.failed_sequence.sequence.getVariable(i).getType();
			  //FDLog.log("output type: " + outputType.getName() + " for: " + i);
			  if(outputType.isPrimitive()) {
				  outputType = FDUtils.getBoxedClassForPrimitiveType(outputType.getName());
			  }
			  if(comment.endsWith(outputType.getName() + ")") || comment.trim().endsWith(outputType.getName())) {
				FDLog.log("Delete comment by heuristic: " + comment);
				FDLog.log("For: "  + this.failed_sequence.sequence.getStatementKind(i).toParseableString());
				comment = "";
			  }
			  String pattern = "\\[.java";
			  Pattern r = Pattern.compile(pattern);
			  Matcher m = r.matcher(comment);
			  if(m.find()) {
				  FDLog.log("remove comments of array property: " + comment);
				  comment = "";
			  }
			}
			//if there is no comment
			if(!comment.trim().equals("")) {
				String commentToAdd = comment( template + renamer.getRenamedVar(i) +" " + comment);
				if(comment.indexOf(SingleValuePropertyChecker.NOT_ADD) != -1) {
					FDLog.log("Change comments for pretty print for add is operations.");
					String elem = renamer.getRenamedVar(this.failed_sequence.sequence.getInputs(i).get(1).getDeclIndex());
					String container = renamer.getRenamedVar(this.failed_sequence.sequence.getInputs(i).get(0).getDeclIndex());
					commentToAdd = comment( template + elem + SingleValuePropertyChecker.NOT_ADD + container);
				} else if (comment.indexOf(SingleValuePropertyChecker.LINE_IS) != -1) {
					FDLog.log("Change comments for pretty print for line is is operations.");
//					String elem = renamer.getRenamedVar(this.failed_sequence.sequence.getInputs(i).get(1).getDeclIndex());
//					String container = renamer.getRenamedVar(this.failed_sequence.sequence.getInputs(i).get(0).getDeclIndex());
					String varName = randoop.Variable.getName(i);
					String renamed = renamer.getRenamedVar(i);
					comment = comment.replaceFirst(varName, renamed);
					commentToAdd = comment( template + comment);
				}
				commentAfterProcessing.add(commentToAdd);
			    
			} else {
				commentAfterProcessing.add("");
			}
		}
		
		return commentAfterProcessing;
	}
	
	public String outputCommentedSequence() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < this.failed_sequence.sequence.size(); i++) {
			String comment = comments.get(i);
			
//			System.out.println("comment: " + comment);
//			System.out.println("stmt: " + failed_sequence.sequence.getStatementKind(i));
			
			//prune out redundancy
			if(GenInputsAbstract.remove_likely_useless) {
			  Class<?> outputType = this.failed_sequence.sequence.getVariable(i).getType();
			  //FDLog.log("output type: " + outputType.getName() + " for: " + i);
			  if(outputType.isPrimitive()) {
				  outputType = FDUtils.getBoxedClassForPrimitiveType(outputType.getName());
			  }
			  if(comment.endsWith(outputType.getName() + ")") || comment.trim().endsWith(outputType.getName())) {
				FDLog.log("Delete comment by heuristic: " + comment);
				FDLog.log("For: "  + this.failed_sequence.sequence.getStatementKind(i).toParseableString());
				comment = "";
			  }
			}
			//heuristic for rewritting
			//FIXME ugly code, should rewrite 
			//System.out.println(comment.trim());
			if(comment.trim().endsWith(")[]") && comment.trim().startsWith("is: (java.util.")) {
				comment = "is an empty " + comment.substring(comment.indexOf("(") + 1, comment.indexOf(")")) + " object";
			}
			//other more array types
			if(comment.trim().indexOf("[Lj") != -1) {
				System.out.println("Remove useless: " + comment);
				comment = "";
			}
			
			StringBuilder oneStatement = new StringBuilder();
			//if there is no comment
			if(!comment.trim().equals("")) {
				if(comment.indexOf(SingleValuePropertyChecker.NOT_ADD) != -1
						|| comment.indexOf(SingleValuePropertyChecker.LINE_IS) != -1) {
					oneStatement.append(comment( template +" " + comment));
				} else {
				   oneStatement.append(comment( template + "var" + i +" " + comment));
				}
			}
			this.failed_sequence.sequence.printStatement(oneStatement, i);
			//sb.append(Globals.lineSep);
			
			// Print exception check first, if present.
		    List<Check> exObs = failed_sequence.getChecks(i, ExpectedExceptionChecker.class);
		    if (!exObs.isEmpty()) {
		      assert exObs.size() == 1 : toString();
		      Check o = exObs.get(0);
		      oneStatement.insert(0, o.toCodeStringPreStatement());
		      oneStatement.append(o.toCodeStringPostStatement());
		      oneStatement.append(Globals.lineSep);
		    }
			
			// Print the rest of the checks.
		    for (Check d : failed_sequence.getChecks(i)) {
		      if (d instanceof ExpectedExceptionChecker)
		         continue;
		      oneStatement.insert(0, d.toCodeStringPreStatement());
		      oneStatement.append(d.toCodeStringPostStatement());
		      oneStatement.append(Globals.lineSep);
		    }
		    sb.append(oneStatement);
		}
		
		return indent(sb.toString());
	}
	
	private String indent(String codeString) {
	    StringBuilder indented = new StringBuilder();
	    String[] lines = codeString.split(Globals.lineSep);
	    for (String line : lines) {
	      indented.append("    " + line + Globals.lineSep);
	    }
	    return indented.toString();
	  }
	
	private String comment(String commentString) {
		StringBuilder commented = new StringBuilder();
	    String[] lines = commentString.split(Globals.lineSep);
	    for (String line : lines) {
	    	commented.append("//" + line + Globals.lineSep);
	    }
	    return commented.toString();
	}
}
