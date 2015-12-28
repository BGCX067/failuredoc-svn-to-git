
import junit.framework.*;

public class ApacheCommonListOrderedSet_simplified_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(ApacheCommonListOrderedSet_simplified_simplified0.class);
  }

  public void test1() throws Throwable {

    org.apache.commons.collections.set.ListOrderedSet var0 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var1 = new org.apache.commons.collections.set.ListOrderedSet();
    java.util.List var2 = var1.asList();
    org.apache.commons.collections.set.ListOrderedSet var3 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var0, var2);
    org.apache.commons.collections.set.ListOrderedSet var4 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var5 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var6 = new org.apache.commons.collections.set.ListOrderedSet();
    int var7 = var0.size();
    boolean var8 = var1.add((java.lang.Object)var7);
    boolean var9 = var0.removeAll((java.util.Collection)var1);
    
    // Checks the contract:  equals-symmetric on var3 and var0.
    assertTrue("Contract failed: equals-symmetric on var3 and var0.", var3.equals(var0) ? var0.equals(var3) : true);
    
    // Checks the contract:  equals-symmetric on var3 and var4.
    assertTrue("Contract failed: equals-symmetric on var3 and var4.", var3.equals(var4) ? var4.equals(var3) : true);
    
    // Checks the contract:  equals-symmetric on var3 and var5.
    assertTrue("Contract failed: equals-symmetric on var3 and var5.", var3.equals(var5) ? var5.equals(var3) : true);
    
    // Checks the contract:  equals-symmetric on var3 and var6.
    assertTrue("Contract failed: equals-symmetric on var3 and var6.", var3.equals(var6) ? var6.equals(var3) : true);

  }

  public void test2() throws Throwable {

    org.apache.commons.collections.set.ListOrderedSet var0 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var1 = new org.apache.commons.collections.set.ListOrderedSet();
    java.util.List var2 = var1.asList();
    org.apache.commons.collections.set.ListOrderedSet var3 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var0, var2);
    org.apache.commons.collections.set.ListOrderedSet var4 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var5 = new org.apache.commons.collections.set.ListOrderedSet();
    org.apache.commons.collections.set.ListOrderedSet var6 = new org.apache.commons.collections.set.ListOrderedSet();
    int var7 = var0.size();
    boolean var8 = var1.add((java.lang.Object)var7);
    boolean var9 = var0.removeAll((java.util.Collection)var1);
    
    // Checks the contract:  equals-symmetric on var3 and var0.
    assertTrue("Contract failed: equals-symmetric on var3 and var0.", var3.equals(var0) ? var0.equals(var3) : true);
    
    // Checks the contract:  equals-symmetric on var3 and var4.
    assertTrue("Contract failed: equals-symmetric on var3 and var4.", var3.equals(var4) ? var4.equals(var3) : true);
    
    // Checks the contract:  equals-symmetric on var3 and var5.
    assertTrue("Contract failed: equals-symmetric on var3 and var5.", var3.equals(var5) ? var5.equals(var3) : true);
    
    // Checks the contract:  equals-symmetric on var3 and var6.
    assertTrue("Contract failed: equals-symmetric on var3 and var6.", var3.equals(var6) ? var6.equals(var3) : true);

  }

}
