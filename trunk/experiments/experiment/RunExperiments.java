package experiment;

public class RunExperiments {
	
	public static void main(String[] args) {
		if(args.length < 1) {
			usage();
			System.exit(1);
		}
		int i = -1;
		try {
		  i = Integer.valueOf(args[0]);
		} catch (NumberFormatException nfe) {
			System.out.println("The args you provide is not valid.");
			usage();
			System.exit(1);
		}
		switch(i) {
		case 0:
			RunApacheCommons.main(null);
			break;
		case 1:
			RunApacheCommonsListOrderSet.main(null);
			break;
		case 2: 
			RunApacheMath.main(null);
			break;
		case 3:
			RunApachePrimitives.main(null);
			break;
		case 4:
			RunJDKExample.main(null);
			break;
		case 5:
			RunTimeAndMoney.main(null);
			break;
		default:
			System.out.println("The args you provide is not valid.");
			usage();
			System.exit(1);
		}
	}
	
	static void usage() {
		System.out.println("");
		System.out.println("Please make your choice in running the experiment. ");
		System.out.println("Command: java -cp CLASSPATH experiment.RunAll  args");
		System.out.println("The \"args\" above could be one of them: ");
		System.out.println(" 0 - Run Apache Commons Example (first part) ");
		System.out.println(" 1 - Run Apache Commons Example (second part) ");
		System.out.println(" 2 - Run Apache Commons Math ");
		System.out.println(" 3 - Run Apache Commons Primitives ");
		System.out.println(" 4 - Run Apache JDK Util Package");
		System.out.println(" 5 - Run Time and Money Example");
	}
}