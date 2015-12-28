import junit.framework.*;
import junit.textui.*;

public class ApacheCommonListOrderedSet_simplified_ddmin extends TestCase {

  public static void main(String[] args) {
    TestRunner runner = new TestRunner();
    TestResult result = runner.doRun(suite(), false);
    if (! result.wasSuccessful()) {
      System.exit(1);
    }
  }

  public ApacheCommonListOrderedSet_simplified_ddmin(String name) {
    super(name);
  }

  public static Test suite() {
    TestSuite result = new TestSuite();
    result.addTest(new TestSuite(ApacheCommonListOrderedSet_simplified_ddmin0.class));
    return result;
  }

}
