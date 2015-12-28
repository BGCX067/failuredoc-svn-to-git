package failure.main;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import failure.sequence.SequenceExplainer;
import failure.sequence.SequenceOutputAfterCommenting;
import failure.sequence.SequenceParser;
import failure.sequence.prettyprint.SequencePrettyPrinter;
import plume.Option;
import plume.Options;
import plume.Unpublicized;
import randoop.Sequence;
import randoop.main.GenInputsAbstract;
import randoop.util.Reflection;

public class Main {
	
	/***
	 * Many more options should be added here to form a more complete
	 * tool. Please refer to the package experiment for all experimental
	 * setup.
	 * */
	@Option("The test options")
	public static boolean help = false;
	
	@Option("The file containing additional classes for FailureDoc")
	public static String additional_classes = null;
	
	@Option("The file containing failed tests that need to explain")
	public static String failed_tests = null;
	
	@Option("The name of documented test")
	public static String documented_test = "Documented_Failed_Test";
	
	@Option("The time for test generation")
	public static int testgen_time = 10;
	
	@Option("The forbidden exception")
	public static String forbidden_exception = null;//"java.lang.ArrayStoreException";
	
	@Option("The output path")
	public static String documented_test_output = "./failuredoc_output/";
	
	@Unpublicized
	@Option("Output sequence to a file")
	public static String failed_seq_output = null;
	
	/**
	 * The following options are not commonly used
	 * */
	@Option("Number of object profiles to select")
	public static int obj_select_num = 20;
	  
	@Option("Adaptively selecting replacing values")
	public static boolean adaptive_select = true;
	  
	@Option("The log file for failure doc")
	public static String failure_doc_log = "./failuredoc_log.txt";
	  
	@Option("Verbose output during failuredoc inferring document")
	public static boolean failuredoc_verbose = true;
	  
	@Option("Using aggressive pruning strategy")
	public static boolean aggressive_pruning = false;
	  
	@Option("Removing likely useless")
	public static boolean remove_likely_useless = false;
	  
	@Option("Select closest type")
	public static boolean select_closest_type = false;
	  
	@Option("Use abstract object profile over single value")
	public static boolean use_profile_over_value = false;
	  
	@Option("Append an example for better explaination")
	public static boolean append_example = false;
	  
	@Unpublicized
	@Option("Pretty print a statement")
	public static boolean pretty_print = false;
	  
	@Unpublicized
	@Option("Simplifying the failed sequence (buyer aware, experimental purpose.)")
	public static boolean simplifying = false;
	
	@Unpublicized
	@Option("Use delta debugging to simplify the failed sequence (buyer aware, experimental purpose)")
	public static boolean ddmin = false;
	
	@Unpublicized
	@Option("Simplifying the failed tests using typebased info")
	public static boolean typebased_simplified = false;
	
	@Unpublicized
	@Option("Only use simple metrics")
	public static boolean only_use_pass_metric = false;
	
	/**
	 * To explain any failed test, please refer to example <code>
	 * RunJDKRealFailedTest</code> to learn how to program.
	 * */
	public static void main(String[] args) {
		Main main = new Main();
		main.realMain(args);
	}
	
	private void realMain(String[] args) {
		this.parse_check_and_set_args(args);
		this.explain_and_output_tests();
	}
	
	private void parse_check_and_set_args(String[] args) {
		//process the arguments
		Options options = new Options("FailureDoc usage options: ", Main.class);
		String[] file_args = options.parse_or_usage(args);
		if(file_args.length != 0) {
			for(String errorMsg : file_args) {
				System.out.println(errorMsg);
				System.exit(1);
			}
		}
		
		//show the welcome page
		if(Main.help) {
			System.out.println("Welcome to use FailureDoc. It has been installed!");
			System.out.println();
			options.print_usage();
			System.exit(1);
		}
		
		//check the args
		if(Main.failed_tests == null) {
			System.out.println("Must specify failed tests file by : --failed_tests = file_name");
			System.exit(1);
		}
		//configure options
		GenInputsAbstract.long_format = true;
		GenInputsAbstract.documented_test = Main.documented_test;
		GenInputsAbstract.forbidden_exception = Main.forbidden_exception;
		GenInputsAbstract.obj_select_num = Main.obj_select_num;
		GenInputsAbstract.adaptive_select = Main.adaptive_select;
		GenInputsAbstract.failure_doc_log = Main.failure_doc_log;
		GenInputsAbstract.failuredoc_verbose = Main.failuredoc_verbose;
		GenInputsAbstract.aggressive_pruning = Main.aggressive_pruning;
		GenInputsAbstract.remove_likely_useless = Main.remove_likely_useless;
		GenInputsAbstract.select_closest_type = Main.select_closest_type;
		GenInputsAbstract.use_profile_over_value = Main.use_profile_over_value;
		GenInputsAbstract.append_example = Main.append_example;
		GenInputsAbstract.only_use_pass_metric = Main.only_use_pass_metric;
	}
	
	private void explain_and_output_tests() {
		//read if there are some additional class
		List<Class<?>> additional_classes = Main.additional_classes != null
		        ? this.loadClassNoException(Main.additional_classes)
				: new LinkedList<Class<?>>();
		//read all failed sequences
		SequenceParser parser = new SequenceParser(Main.failed_tests);
		List<Sequence> failed_sequences = parser.parseSequences();
		//start to explain the failure
		SequenceExplainer explainer = new SequenceExplainer(failed_sequences, additional_classes);
		List<SequenceOutputAfterCommenting> documented_seqs = explainer.explainFailedSequences();
		for(SequenceOutputAfterCommenting commented_seq : documented_seqs) {
	  	      String content = commented_seq.outputCommentedSequence();
	  	      //System.out.println("all comments: " + commented_seq.comments);
	  	      System.out.println(content);
	    }
		SequencePrettyPrinter printer = new SequencePrettyPrinter(documented_seqs, GenInputsAbstract.documented_test + "_Pretty");
  	    printer.create_file(GenInputsAbstract.documented_test_output);
	}
	
	private List<Class<?>> loadClassNoException(String file) {
		try {
			return Reflection.loadClassesFromFile(new File(file));
		} catch (IOException e) {
			throw new Error(e);
		}
	}
}