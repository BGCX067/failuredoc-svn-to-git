package failure;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public final class FDLog {

	public static FileWriter log = null;

	public static final String lineSep = System.getProperty("line.separator");

	private FDLog() {
		throw new IllegalStateException("no instance");
	}

	public static final ByteArrayOutputStream bos;
	public static final PrintStream systemOutErrStream;
	public static final PrintStream err;
	public static final PrintStream out;

	static {
		bos = new ByteArrayOutputStream();
		systemOutErrStream = new PrintStream(bos);
		err = System.err;
		out = System.out;
	}

	public static void logConfig(String logFile) {
		if (logFile == null) {
			return;
		}
		try {
			FDLog.log = new FileWriter((logFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void log(String s) {
		if (!isLoggingOn())
			return;

		try {
			log.write(s);
			log.write(FDLog.lineSep);
			log.flush();
		} catch (Throwable e) {
			e.printStackTrace();
			//System.exit(1);
		}
	}

	public static boolean isLoggingOn() {
		return log != null;
	}
}