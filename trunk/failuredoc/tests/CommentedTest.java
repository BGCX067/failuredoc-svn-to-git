package tests;

import junit.framework.*;

public class CommentedTest extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(CommentedTest.class);
  }

  public void test1() throws Throwable {

    //empty comment here!
    java.lang.Byte var0 = new java.lang.Byte((byte)1);
    
    //empty comment here!
    java.util.List var1 = java.util.Collections.singletonList((java.lang.Object)(byte)1);
    
    //empty comment here!
    java.util.TreeSet var2 = new java.util.TreeSet((java.util.Collection)var1);
    
    //empty comment here!
    java.lang.Object[] var3 = var2.toArray();
    
    //empty comment here!
    java.lang.Long var4 = new java.lang.Long(10L);
    
    //empty comment here!
    java.lang.Boolean var5 = new java.lang.Boolean(true);
    
    //empty comment here!
    java.util.NavigableSet var6 = var2.headSet((java.lang.Object)10L, true);
    
    //empty comment here!
    java.util.NavigableSet var7 = var2.descendingSet();
    
    //empty comment here!
    java.lang.Integer var8 = new java.lang.Integer(10);
    
    //empty comment here!
    java.lang.Integer var9 = new java.lang.Integer(0);
    
    //empty comment here!
    java.util.List var10 = java.util.Collections.nCopies(10, (java.lang.Object)0);
    
    //empty comment here!
    java.lang.Object var11 = java.util.Collections.max((java.util.Collection)var10);
    
    //empty comment here!
    java.lang.Byte var12 = new java.lang.Byte((byte)1);
    
    //empty comment here!
    java.util.List var13 = java.util.Collections.singletonList((java.lang.Object)(byte)1);
    
    //empty comment here!
    java.util.TreeSet var14 = new java.util.TreeSet((java.util.Collection)var13);
    
    //empty comment here!
    java.lang.Object[] var15 = var14.toArray();
    
    //empty comment here!
    java.lang.Long var16 = new java.lang.Long(10L);
    
    //empty comment here!
    java.lang.Boolean var17 = new java.lang.Boolean(true);
    
    //empty comment here!
    java.util.NavigableSet var18 = var14.headSet((java.lang.Object)10L, true);
    
    //empty comment here!
    java.util.NavigableSet var19 = var2.tailSet(var11, true);
    
    //empty comment here!
    java.util.SortedSet var20 = java.util.Collections.synchronizedSortedSet((java.util.SortedSet)var19);
    
    
    // Checks the contract:  var20.equals(var20)
    assertTrue("Contract failed: var20.equals(var20)", var20.equals(var20));

  }

  public void test2() throws Throwable {

    //empty comment here!
    java.util.TreeSet var0 = new java.util.TreeSet();
    
    //empty comment here!
    java.lang.Byte var1 = new java.lang.Byte((byte)1);
    
    //empty comment here!
    java.util.List var2 = java.util.Collections.singletonList((java.lang.Object)(byte)1);
    
    //empty comment here!
    java.util.TreeSet var3 = new java.util.TreeSet((java.util.Collection)var2);
    
    //empty comment here!
    java.lang.Object var4 = var3.clone();
    
    //empty comment here!
    boolean var5 = var0.add((java.lang.Object)var3);
    
    //empty comment here!
    java.util.SortedSet var6 = java.util.Collections.synchronizedSortedSet((java.util.SortedSet)var0);
    
    
    // Checks the contract:  var6.equals(var6)
    assertTrue("Contract failed: var6.equals(var6)", var6.equals(var6));

  }

}
