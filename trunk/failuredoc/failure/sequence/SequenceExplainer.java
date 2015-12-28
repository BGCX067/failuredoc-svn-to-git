package failure.sequence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import cov.Coverage;
import failure.doc.FailureTestsAnalyzer;
import failure.doc.JUnitTestsWriter;
import failure.main.Main;
import failure.sequence.prettyprint.SequencePrettyPrinter;

import randoop.AbstractGenerator;
import randoop.ArrayDeclaration;
import randoop.BugInRandoopException;
import randoop.ContractCheckingVisitor;
import randoop.EqualsHashcode;
import randoop.EqualsReflexive;
import randoop.EqualsSymmetric;
import randoop.EqualsToNullRetFalse;
import randoop.ExecutableSequence;
import randoop.ExecutionVisitor;
import randoop.ForwardGenerator;
import randoop.MultiVisitor;
import randoop.ObjectContract;
import randoop.PrimitiveOrStringOrNullDecl;
import randoop.RConstructor;
import randoop.RMethod;
import randoop.RegressionCaptureVisitor;
import randoop.SeedSequences;
import randoop.Sequence;
import randoop.SequenceCollection;
import randoop.StatementKind;
import randoop.main.GenInputsAbstract;
import randoop.util.DefaultReflectionFilter;
import randoop.util.Log;
import randoop.util.PrimitiveTypes;
import randoop.util.Reflection;

/**
 * Given a sequence in Randoop's format, this class first constructs
 * an object component, then use the component to perform documentation
 * inference.
 * */
public class SequenceExplainer {
	
	/**
	 * A list of sequences that need to explian, and a collection of classes.
	 * */
	public final List<Sequence> sequencesToExplain;
	public final Collection<Class<?>> testedClasses;
	
	/**
	 * User can set a customized visitor
	 * */
	private MultiVisitor visitor;
	/**
	 * The default explain time is 10 seconds
	 * */
	//private int time = 10;
	
	public SequenceExplainer(List<Sequence> sequences) {
		assert sequences != null : "The given sequence can not be null.";
		assert sequences.size() > 0 : "The given sequence list can not be empty.";
		this.sequencesToExplain = sequences;
		Collection<Class<?>> classes = this.getTestedClassesFromSequences(this.sequencesToExplain);
		this.testedClasses = classes;
		this.visitor = createDefaultVisitor();
	}
	
	public SequenceExplainer(List<Sequence> sequences, Collection<Class<?>> additionalClasses) {
		assert sequences != null : "The given sequence can not be null.";
		assert sequences.size() > 0 : "The given sequence list can not be empty.";
		assert additionalClasses != null : "The class list can not be null.";
		this.sequencesToExplain = sequences;
		Collection<Class<?>> classes = this.getTestedClassesFromSequences(this.sequencesToExplain);
		classes.addAll(additionalClasses);
		this.testedClasses = classes;
		this.visitor = createDefaultVisitor();
	}
	
	public void setMutliVisitor(MultiVisitor  visitor) {
		assert visitor != null : "The visitor should not be null";
		this.visitor = visitor;
	}
	
//	public void setExplainingTime(int time) {
//		assert time > 0 : "The time your set: " + time + " is illegal, it should > 0";
//		this.time = time;
//	}
	
	/**
	 * It outputs a list of sequences with inferred documentation
	 * */
	public List<SequenceOutputAfterCommenting> explainFailedSequences() {
		//create a list of failed executable sequences
		List<ExecutableSequence> failed_sequences = new LinkedList<ExecutableSequence>();
		for(Sequence sequence : this.sequencesToExplain) {
			ExecutableSequence eseq = new ExecutableSequence(sequence);
			eseq.execute(this.visitor);
			if(eseq.hasFailure()) {
				failed_sequences.add(eseq);
//				for(int i = 0; i < eseq.getFailureIndex(); i++) {
//					Object obj = ((randoop.NormalExecution)eseq.getResult(i)).getRuntimeValue();
//					System.out.println("exec result of : " + i + "-th: " + obj + ", type: " + obj.getClass());
//					if(obj instanceof java.util.ArrayList) {
//						System.out.println("  size: " + ((ArrayList)obj).size());
//					}
//				}
			} else {
				throw new Error("The sequence to explain: " + sequence + " has not error!");
			}
		}
		
		//first, use random testing to create a component
		SequenceCollection components = this.createSequenceCollections(this.testedClasses, this.visitor.visitors, Main.testgen_time);
		
		//start to explain
		long start_time = System.currentTimeMillis();
        FailureTestsAnalyzer analyzer =
  	      new FailureTestsAnalyzer(failed_sequences, components, this.visitor);
        //generate a list of tests after augmenting it with comments
        List<SequenceOutputAfterCommenting> documented = analyzer.doFailureAnalysis();
    
        List<String> contents = new LinkedList<String>();
        for(SequenceOutputAfterCommenting commented_seq : documented) {
  	      String content = commented_seq.outputCommentedSequence();
  	      contents.add(content);
        }
    
        long end_time = System.currentTimeMillis();
        JUnitTestsWriter.writeJUnits(contents, GenInputsAbstract.documented_test, "", GenInputsAbstract.documented_test_output);
        System.out.println("Documenting: " + failed_sequences.size() + " failed tests.");
        System.out.println("Number of constructed execution traces: " + SequenceReplacement.replacement_traces);
        System.out.println("Total time: " + (end_time - start_time)/1000 + " seconds.");
        
        if(GenInputsAbstract.pretty_print) {
      	  SequencePrettyPrinter printer = new SequencePrettyPrinter(documented, GenInputsAbstract.documented_test + "_Pretty");
      	  printer.create_file(GenInputsAbstract.documented_test_output);
      	  
        }
		
		return documented;
	}
	
	static MultiVisitor createDefaultVisitor() {
		MultiVisitor visitor = new MultiVisitor();
		List<ExecutionVisitor> visitors = new ArrayList<ExecutionVisitor>();
	    //add all contracts
	    List<ObjectContract> contracts = new ArrayList<ObjectContract>();
	    // Now add all of Randoop's default contracts.
	    contracts.add(new EqualsReflexive());
	    contracts.add(new EqualsToNullRetFalse());
	    contracts.add(new EqualsHashcode());
	    contracts.add(new EqualsSymmetric());
	    ContractCheckingVisitor contractVisitor = new ContractCheckingVisitor(contracts,
	    GenInputsAbstract.offline ? false : true);
	    visitors.add(contractVisitor);
	    visitors.add(new RegressionCaptureVisitor());
	    //add to the mutli visitor
	    visitor.visitors.addAll(visitors);
	    return visitor;
	}
	
	private Collection<Class<?>> getTestedClassesFromSequences(List<Sequence> sequences) {
		Collection<Class<?>> clzCollection = new LinkedHashSet<Class<?>>();
		for(Sequence sequence : sequences) {
			for(int i = 0; i < sequence.size(); i++) {
				StatementKind stmt = sequence.getStatementKind(i);
				if(stmt instanceof RMethod) {
					clzCollection.add(((RMethod)stmt).getMethod().getDeclaringClass());
				} else if (stmt instanceof RConstructor) {
					clzCollection.add(((RConstructor)stmt).getConstructor().getDeclaringClass());
				} else {
					//ignore others
					continue;
				}
			}
		}
		//also add all default primitive types
		Collection<Class<?>> primTypes = this.getAppearedPrimitiveTypes(sequences);
		clzCollection.addAll(primTypes);
		
		return clzCollection;
	}
	
	private Collection<Class<?>> getAppearedPrimitiveTypes(List<Sequence> sequences) {
		Collection<Class<?>> primitiveTypes = new LinkedHashSet<Class<?>>();
		for(Sequence sequence : sequences) {
		    for(int i = 0; i < sequence.size(); i++) {
		    	StatementKind stmt = sequence.getStatementKind(i);
		    	if(stmt instanceof PrimitiveOrStringOrNullDecl) {
		    		PrimitiveOrStringOrNullDecl primDecl = (PrimitiveOrStringOrNullDecl)stmt;
		    		if(primDecl.getType().isPrimitive()) {
		    			primitiveTypes.add(PrimitiveTypes.boxedType(primDecl.getType()));
		    		}
		    	}
		    	if(stmt instanceof ArrayDeclaration) {
		    		ArrayDeclaration arrayDecl = (ArrayDeclaration)stmt;
		    		Class<?> type = arrayDecl.getElementType();
		    		if(type.isPrimitive() ) {
		    			primitiveTypes.add(type);
		    		}
		    	}
		    }
		}
		return primitiveTypes;
	}
	
	private SequenceCollection createSequenceCollections(Collection<Class<?>> classes,
			List<ExecutionVisitor> visitors, int time) {
		List<StatementKind> model = this.getModel(classes);
		List<Class<?>> covClasses = this.getCovClasses();
		int timelimit = time * 1000;
		int inputlimit = GenInputsAbstract.inputlimit;
		SequenceCollection components = this.initSequenceCollection(classes);
		
		AbstractGenerator explorer = new ForwardGenerator(model, covClasses, timelimit, inputlimit, components);
		System.out.printf ("Explorer = %s\n", explorer);
		
		explorer.executionVisitor.visitors.addAll(visitors);
		//create the component by test generation
	    explorer.explore();
	    SequenceCollection retComponents = ((ForwardGenerator)explorer).components;
	    return retComponents;
	}
	
	private List<StatementKind> getModel(Collection<Class<?>> classes) {
		List<StatementKind> model = Reflection.getStatements(classes,
				new DefaultReflectionFilter(GenInputsAbstract.omitmethods));

		// Always add Object constructor (it's often useful).
		RConstructor objectConstructor = null;
		try {
			objectConstructor = RConstructor.getRConstructor(Object.class
					.getConstructor());
			if (!model.contains(objectConstructor))
				model.add(objectConstructor);
		} catch (Exception e) {
			throw new BugInRandoopException(e); // Should never reach here!
		}

		if (GenInputsAbstract.methodlist != null) {
			Set<StatementKind> statements = new LinkedHashSet<StatementKind>();
			try {
				for (Member m : Reflection
						.loadMethodsAndCtorsFromFile(new File(
								GenInputsAbstract.methodlist))) {
					if (m instanceof Method) {
						statements.add(RMethod.getRMethod((Method) m));
					} else {
						assert m instanceof Constructor<?>;
						statements.add(RConstructor
								.getRConstructor((Constructor<?>) m));
					}
				}
			} catch (IOException e) {
				System.out.println("Error while reading method list file "
						+ GenInputsAbstract.methodlist);
				System.exit(1);
			}
			for (StatementKind st : statements) {
				if (!model.contains(st))
					model.add(st);
			}
		}

		if (model.size() == 0) {
			Log.out.println("There are no methods to test. Exiting.");
			System.exit(1);
		}
		System.out.println("PUBLIC MEMBERS=" + model.size());
		return model;
	}
	
	private List<Class<?>> getCovClasses() {
		List<Class<?>> covClasses = new ArrayList<Class<?>>();
		if (GenInputsAbstract.coverage_instrumented_classes != null) {
			File covClassesFile = new File(
					GenInputsAbstract.coverage_instrumented_classes);
			try {
				covClasses = Reflection.loadClassesFromFile(covClassesFile);
			} catch (IOException e) {
				throw new Error(e);
			}
			for (Class<?> cls : covClasses) {
				assert Coverage.isInstrumented(cls) : cls.toString();
				// System.out.println("Will track branch coverage for " + cls);
			}
		}
		return covClasses;
	}
	
	private SequenceCollection initSequenceCollection(Collection<Class<?>> classes) {
		SequenceCollection components = new SequenceCollection();
		if (!GenInputsAbstract.componentfile_ser.isEmpty()) {
			for (String onefile : GenInputsAbstract.componentfile_ser) {
				try {
					FileInputStream fileos = new FileInputStream(onefile);
					ObjectInputStream objectos = new ObjectInputStream(
							new GZIPInputStream(fileos));
					Set<Sequence> seqset = (Set<Sequence>) objectos.readObject();
					System.out.println("Adding " + seqset.size()
							+ " component sequences from file " + onefile);
					components.addAll(seqset);
				} catch (Exception e) {
					throw new Error(e);
				}
			}
		}
		if (!GenInputsAbstract.componentfile_txt.isEmpty()) {
			for (String onefile : GenInputsAbstract.componentfile_txt) {
				Set<Sequence> seqset = new LinkedHashSet<Sequence>();
				Sequence.readTextSequences(onefile, seqset);
				System.out.println("Adding " + seqset.size()
						+ " component sequences from file " + onefile);
				components.addAll(seqset);
			}
		}

		// Add default seeds.
		components.addAll(SeedSequences
				.objectsToSeeds(SeedSequences.primitiveSeeds));

		// Add user-specified seeds.
		components.addAll(SeedSequences.getSeedsFromAnnotatedFields(classes
				.toArray(new Class<?>[0])));
		return components;
	}
}
