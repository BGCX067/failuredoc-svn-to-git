import junit.framework.TestCase;
public class ApacheListOrderSet_Documented_Test extends TestCase {
  public void test0() throws Throwable {
        org.apache.commons.collections.set.ListOrderedSet var0 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var1 = var0.asList();
        java.util.List var2 = var0.asList();
        var0.clear();
        java.lang.Integer var4 = new java.lang.Integer((-1));
        org.apache.commons.collections.set.ListOrderedSet var5 = new org.apache.commons.collections.set.ListOrderedSet();
        java.lang.Integer var6 = new java.lang.Integer((-1));
        org.apache.commons.collections.set.ListOrderedSet var7 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var8 = var7.asList();
        org.apache.commons.collections.set.ListOrderedSet var9 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var7);
        boolean var10 = var5.addAll((int)var6, (java.util.Collection)var7);
        boolean var11 = var0.addAll((int)var4, (java.util.Collection)var7);
        org.apache.commons.collections.set.ListOrderedSet var12 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var13 = var12.asList();
        java.util.List var14 = var12.asList();
        org.apache.commons.collections.set.ListOrderedSet var15 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var16 = var15.asList();
        org.apache.commons.collections.set.ListOrderedSet var17 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var15);
        org.apache.commons.collections.set.ListOrderedSet var18 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var19 = var18.asList();
        org.apache.commons.collections.set.ListOrderedSet var20 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var15, var19);
        boolean var21 = var12.containsAll((java.util.Collection)var19);
        org.apache.commons.collections.set.ListOrderedSet var22 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var0, var19);
        org.apache.commons.collections.set.ListOrderedSet var23 = new org.apache.commons.collections.set.ListOrderedSet();
        int var24 = var23.size();
        boolean var25 = var0.add((java.lang.Object)var24);
        java.lang.Short var26 = new java.lang.Short((short)1);
        //Test passes if var26 is: (java.lang.Integer)0
        boolean var27 = var0.add((java.lang.Object)var26);
        //Test passes if var27 is: (java.lang.Boolean)false
        org.apache.commons.collections.set.ListOrderedSet var28 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var29 = var28.asList();
        java.util.List var30 = var28.asList();
        var28.clear();
        java.lang.Integer var32 = new java.lang.Integer((-1));
        org.apache.commons.collections.set.ListOrderedSet var33 = new org.apache.commons.collections.set.ListOrderedSet();
        java.lang.Integer var34 = new java.lang.Integer((-1));
        org.apache.commons.collections.set.ListOrderedSet var35 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var36 = var35.asList();
        org.apache.commons.collections.set.ListOrderedSet var37 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var35);
        boolean var38 = var33.addAll((int)var34, (java.util.Collection)var35);
        boolean var39 = var28.addAll((int)var32, (java.util.Collection)var35);
        org.apache.commons.collections.set.ListOrderedSet var40 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var41 = var40.asList();
        java.util.List var42 = var40.asList();
        org.apache.commons.collections.set.ListOrderedSet var43 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var44 = var43.asList();
        org.apache.commons.collections.set.ListOrderedSet var45 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var43);
        org.apache.commons.collections.set.ListOrderedSet var46 = new org.apache.commons.collections.set.ListOrderedSet();
        java.util.List var47 = var46.asList();
        org.apache.commons.collections.set.ListOrderedSet var48 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var43, var47);
        boolean var49 = var40.containsAll((java.util.Collection)var47);
        org.apache.commons.collections.set.ListOrderedSet var50 = org.apache.commons.collections.set.ListOrderedSet.decorate((java.util.Set)var28, var47);
        org.apache.commons.collections.set.ListOrderedSet var51 = new org.apache.commons.collections.set.ListOrderedSet();
        int var52 = var51.size();
        boolean var53 = var28.add((java.lang.Object)var52);
        //Test passes if var53 is: (java.lang.Boolean)false
        boolean var54 = var0.removeAll((java.util.Collection)var28);
        
        // Checks the contract:  equals-hashcode on var0 and var50
        assertTrue("Contract failed: equals-hashcode on var0 and var50", var0.equals(var50) ? var0.hashCode() == var50.hashCode() : true);
        
        // Checks the contract:  equals-hashcode on var22 and var50
        assertTrue("Contract failed: equals-hashcode on var22 and var50", var22.equals(var50) ? var22.hashCode() == var50.hashCode() : true);
        
        // Checks the contract:  equals-hashcode on var28 and var22
        assertTrue("Contract failed: equals-hashcode on var28 and var22", var28.equals(var22) ? var28.hashCode() == var22.hashCode() : true);
        
        // Checks the contract:  equals-hashcode on var50 and var22
        assertTrue("Contract failed: equals-hashcode on var50 and var22", var50.equals(var22) ? var50.hashCode() == var22.hashCode() : true);
        
        // Checks the contract:  equals-symmetric on var0 and var50.
        assertTrue("Contract failed: equals-symmetric on var0 and var50.", var0.equals(var50) ? var50.equals(var0) : true);
        
        // Checks the contract:  equals-symmetric on var28 and var22.
        assertTrue("Contract failed: equals-symmetric on var28 and var22.", var28.equals(var22) ? var22.equals(var28) : true);
  }
}
