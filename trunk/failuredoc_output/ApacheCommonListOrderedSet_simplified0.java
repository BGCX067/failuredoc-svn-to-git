
import junit.framework.*;

public class ApacheCommonListOrderedSet_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(ApacheCommonListOrderedSet_simplified0.class);
  }

  public void test1() throws Throwable {

    org.apache.commons.collections.set.ListOrderedSet var0 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var1 = new org.apache.commons.collections.set.ListOrderedSet();
    java.util.List var2 = var1.asList();
    org.apache.commons.collections.set.ListOrderedSet var3 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var0, var2);
    java.lang.Short var4 = new java.lang.Short((short)1);
    boolean var5 = var0.add((java.lang.Object)var4);
    org.apache.commons.collections.set.ListOrderedSet var6 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var7 = new org.apache.commons.collections.set.ListOrderedSet();
    java.util.List var8 = var7.asList();
    org.apache.commons.collections.set.ListOrderedSet var9 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var6, var8);
    org.apache.commons.collections.set.ListOrderedSet var10 = new org.apache.commons.collections.set.ListOrderedSet();
    int var11 = var10.size();
    boolean var12 = var6.add((java.lang.Object)var11);
    boolean var13 = var0.removeAll((java.util.Collection)var6);
    
    // Checks the contract:  equals-hashcode on var0 and var9
    assertTrue("Contract failed: equals-hashcode on var0 and var9", var0.equals(var9) ? var0.hashCode() == var9.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var3 and var9
    assertTrue("Contract failed: equals-hashcode on var3 and var9", var3.equals(var9) ? var3.hashCode() == var9.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var3
    assertTrue("Contract failed: equals-hashcode on var6 and var3", var6.equals(var3) ? var6.hashCode() == var3.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var9 and var3
    assertTrue("Contract failed: equals-hashcode on var9 and var3", var9.equals(var3) ? var9.hashCode() == var3.hashCode() : true);
    
    // Checks the contract:  equals-symmetric on var0 and var9.
    assertTrue("Contract failed: equals-symmetric on var0 and var9.", var0.equals(var9) ? var9.equals(var0) : true);
    
    // Checks the contract:  equals-symmetric on var6 and var3.
    assertTrue("Contract failed: equals-symmetric on var6 and var3.", var6.equals(var3) ? var3.equals(var6) : true);

  }

  public void test2() throws Throwable {

    org.apache.commons.collections.set.ListOrderedSet var0 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var1 = new org.apache.commons.collections.set.ListOrderedSet();
    java.util.List var2 = var1.asList();
    org.apache.commons.collections.set.ListOrderedSet var3 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var0, var2);
    java.lang.Short var4 = new java.lang.Short((short)1);
    boolean var5 = var0.add((java.lang.Object)var4);
    org.apache.commons.collections.set.ListOrderedSet var6 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var7 = new org.apache.commons.collections.set.ListOrderedSet();
    java.util.List var8 = var7.asList();
    org.apache.commons.collections.set.ListOrderedSet var9 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var6, var8);
    org.apache.commons.collections.set.ListOrderedSet var10 = new org.apache.commons.collections.set.ListOrderedSet();
    int var11 = var10.size();
    boolean var12 = var6.add((java.lang.Object)var11);
    boolean var13 = var0.removeAll((java.util.Collection)var6);
    
    // Checks the contract:  equals-hashcode on var0 and var9
    assertTrue("Contract failed: equals-hashcode on var0 and var9", var0.equals(var9) ? var0.hashCode() == var9.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var3 and var9
    assertTrue("Contract failed: equals-hashcode on var3 and var9", var3.equals(var9) ? var3.hashCode() == var9.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var3
    assertTrue("Contract failed: equals-hashcode on var6 and var3", var6.equals(var3) ? var6.hashCode() == var3.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var9 and var3
    assertTrue("Contract failed: equals-hashcode on var9 and var3", var9.equals(var3) ? var9.hashCode() == var3.hashCode() : true);
    
    // Checks the contract:  equals-symmetric on var0 and var9.
    assertTrue("Contract failed: equals-symmetric on var0 and var9.", var0.equals(var9) ? var9.equals(var0) : true);
    
    // Checks the contract:  equals-symmetric on var6 and var3.
    assertTrue("Contract failed: equals-symmetric on var6 and var3.", var6.equals(var3) ? var3.equals(var6) : true);

  }

}
