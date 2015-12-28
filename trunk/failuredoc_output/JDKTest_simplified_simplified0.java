
import junit.framework.*;

public class JDKTest_simplified_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(JDKTest_simplified_simplified0.class);
  }

  public void test1() throws Throwable {

    java.lang.Byte var0 = new java.lang.Byte((byte)1);
    java.util.List var1 = java.util.Collections.singletonList((java.lang.Object)var0);
    java.util.TreeSet var2 = new java.util.TreeSet((java.util.Collection)var1);
    java.lang.Integer var3 = new java.lang.Integer(10);
    java.lang.Boolean var4 = new java.lang.Boolean(true);
    java.util.NavigableSet var5 = var2.tailSet((java.lang.Object)var3, (boolean)var4);
    java.util.SortedSet var6 = java.util.Collections.synchronizedSortedSet((java.util.SortedSet)var5);
    
    // Checks the contract:  var6.equals(var6)
    assertTrue("Contract failed: var6.equals(var6)", var6.equals(var6));

  }

}
