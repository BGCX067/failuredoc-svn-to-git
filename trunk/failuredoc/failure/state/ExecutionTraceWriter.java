package failure.state;

import java.io.File;
import java.util.List;

public class ExecutionTraceWriter {

	public static void saveTraceToFile(List<List<AbstractState>> traces, File f) {
		
	}
	
    public static void saveTraceToFile(List<List<AbstractState>> traces, String filePath) {
		saveTraceToFile(traces, new File(filePath));
	}
    
    public static List<List<AbstractState>> readTraceFromFile(File f) {
    	return null;
    }
    
    public static List<List<AbstractState>> readTraceFromFile(String filePath) {
    	return readTraceFromFile(new File(filePath));
    }
	
}