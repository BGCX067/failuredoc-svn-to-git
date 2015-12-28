import junit.framework.*;
import junit.textui.*;

public class TimeAndMoneyTest_simplified_ddmin extends TestCase {

  public static void main(String[] args) {
    TestRunner runner = new TestRunner();
    TestResult result = runner.doRun(suite(), false);
    if (! result.wasSuccessful()) {
      System.exit(1);
    }
  }

  public TimeAndMoneyTest_simplified_ddmin(String name) {
    super(name);
  }

  public static Test suite() {
    TestSuite result = new TestSuite();
    result.addTest(new TestSuite(TimeAndMoneyTest_simplified_ddmin0.class));
    return result;
  }

}
