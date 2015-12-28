package tests;

import junit.framework.*;

public class RandoopTest0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(RandoopTest0.class);
  }

  public void test1() throws Throwable {

    java.util.List var1 = java.util.Collections.singletonList((java.lang.Object)(byte)1);
    java.util.TreeSet var2 = new java.util.TreeSet((java.util.Collection)var1);
    java.lang.Object[] var3 = var2.toArray();
    java.util.NavigableSet var6 = var2.headSet((java.lang.Object)10L, true);
    java.util.NavigableSet var7 = var2.descendingSet();
    java.util.List var10 = java.util.Collections.nCopies(10, (java.lang.Object)0);
    java.lang.Object var11 = java.util.Collections.max((java.util.Collection)var10);
    java.util.List var13 = java.util.Collections.singletonList((java.lang.Object)(byte)1);
    java.util.TreeSet var14 = new java.util.TreeSet((java.util.Collection)var13);
    java.lang.Object[] var15 = var14.toArray();
    java.util.NavigableSet var18 = var14.headSet((java.lang.Object)10L, true);
    java.util.NavigableSet var19 = var2.tailSet(var11, true);
    java.util.SortedSet var20 = java.util.Collections.synchronizedSortedSet((java.util.SortedSet)var19);
    
    // Checks the contract:  var20.equals(var20)
    assertTrue("Contract failed: var20.equals(var20)", var20.equals(var20));

  }

}
