import junit.framework.*;
import junit.textui.*;

public class CommonsCollectionTest_simplified_simplified extends TestCase {

  public static void main(String[] args) {
    TestRunner runner = new TestRunner();
    TestResult result = runner.doRun(suite(), false);
    if (! result.wasSuccessful()) {
      System.exit(1);
    }
  }

  public CommonsCollectionTest_simplified_simplified(String name) {
    super(name);
  }

  public static Test suite() {
    TestSuite result = new TestSuite();
    result.addTest(new TestSuite(CommonsCollectionTest_simplified_simplified0.class));
    return result;
  }

}
