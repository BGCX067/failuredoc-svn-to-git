import junit.framework.*;
import junit.textui.*;

public class JDKTest_simplified extends TestCase {

  public static void main(String[] args) {
    TestRunner runner = new TestRunner();
    TestResult result = runner.doRun(suite(), false);
    if (! result.wasSuccessful()) {
      System.exit(1);
    }
  }

  public JDKTest_simplified(String name) {
    super(name);
  }

  public static Test suite() {
    TestSuite result = new TestSuite();
    result.addTest(new TestSuite(JDKTest_simplified0.class));
    return result;
  }

}
