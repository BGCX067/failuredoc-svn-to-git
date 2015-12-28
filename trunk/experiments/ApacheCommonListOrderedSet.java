import junit.framework.*;
import junit.textui.*;

public class ApacheCommonListOrderedSet extends TestCase {

  public static void main(String[] args) {
    TestRunner runner = new TestRunner();
    TestResult result = runner.doRun(suite(), false);
    if (! result.wasSuccessful()) {
      System.exit(1);
    }
  }

  public ApacheCommonListOrderedSet(String name) {
    super(name);
  }

  public static Test suite() {
    TestSuite result = new TestSuite();
    result.addTest(new TestSuite(ApacheCommonListOrderedSet0.class));
    return result;
  }

}
