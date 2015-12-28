
import junit.framework.*;

public class JDKTest_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(JDKTest_simplified0.class);
  }

  public void test1() throws Throwable {

    java.lang.Byte var0 = new java.lang.Byte((byte)1);
    java.util.List var1 = java.util.Collections.singletonList((java.lang.Object)var0);
    java.util.TreeSet var2 = new java.util.TreeSet((java.util.Collection)var1);
    java.lang.Integer var3 = new java.lang.Integer(10);
    java.lang.Integer var4 = new java.lang.Integer(0);
    java.util.List var5 = java.util.Collections.nCopies((int)var3, (java.lang.Object)var4);
    java.lang.Object var6 = java.util.Collections.max((java.util.Collection)var5);
    java.lang.Boolean var7 = new java.lang.Boolean(true);
    java.util.NavigableSet var8 = var2.tailSet(var6, (boolean)var7);
    java.util.SortedSet var9 = java.util.Collections.synchronizedSortedSet((java.util.SortedSet)var8);
    
    // Checks the contract:  var9.equals(var9)
    assertTrue("Contract failed: var9.equals(var9)", var9.equals(var9));

  }

}
