
import junit.framework.*;

public class JDKTest_simplified_ddmin0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(JDKTest_simplified_ddmin0.class);
  }

  public void test1() throws Throwable {

    java.lang.Byte var0 = new java.lang.Byte((byte)1);
    java.util.List var1 = java.util.Collections.singletonList((java.lang.Object)var0);
    java.util.TreeSet var2 = new java.util.TreeSet((java.util.Collection)var1);
    java.lang.Long var3 = new java.lang.Long(10L);
    java.lang.Boolean var4 = new java.lang.Boolean(true);
    java.lang.Integer var5 = new java.lang.Integer(10);
    java.lang.Integer var6 = new java.lang.Integer(0);
    java.util.List var7 = java.util.Collections.nCopies((int)var5, (java.lang.Object)var6);
    java.lang.Object var8 = java.util.Collections.max((java.util.Collection)var7);
    java.lang.Byte var9 = new java.lang.Byte((byte)1);
    java.util.List var10 = java.util.Collections.singletonList((java.lang.Object)var9);
    java.util.TreeSet var11 = new java.util.TreeSet((java.util.Collection)var10);
    java.lang.Boolean var12 = new java.lang.Boolean(true);
    java.util.NavigableSet var13 = var2.tailSet(var8, (boolean)var12);
    java.util.SortedSet var14 = java.util.Collections.synchronizedSortedSet((java.util.SortedSet)var13);
    
    // Checks the contract:  var14.equals(var14)
    assertTrue("Contract failed: var14.equals(var14)", var14.equals(var14));

  }

}
