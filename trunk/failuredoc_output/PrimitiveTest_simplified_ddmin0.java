
import junit.framework.*;

public class PrimitiveTest_simplified_ddmin0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(PrimitiveTest_simplified_ddmin0.class);
  }

  public void test1() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
    org.apache.commons.collections.primitives.ArrayCharList var2 = new org.apache.commons.collections.primitives.ArrayCharList();
    java.lang.Integer var3 = new java.lang.Integer(10);
    java.lang.Character var4 = new java.lang.Character('#');
    boolean var5 = var1.add((char)var4);
    java.lang.Integer var6 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var7 = new org.apache.commons.collections.primitives.ArrayCharList(var6);
    java.lang.Integer var8 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var9 = new org.apache.commons.collections.primitives.ArrayCharList(var8);
    java.lang.Integer var10 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var11 = new org.apache.commons.collections.primitives.ArrayCharList(var10);
    java.lang.Integer var12 = new java.lang.Integer(10);
    java.lang.Integer var13 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var14 = new org.apache.commons.collections.primitives.ArrayCharList(var13);
    java.lang.Integer var15 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var16 = new org.apache.commons.collections.primitives.ArrayCharList(var15);
    java.lang.Integer var17 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var18 = new org.apache.commons.collections.primitives.ArrayCharList(var17);
    org.apache.commons.collections.primitives.ArrayCharList var19 = new org.apache.commons.collections.primitives.ArrayCharList();
    java.lang.Integer var20 = new java.lang.Integer(10);
    java.lang.Character var21 = new java.lang.Character('#');
    org.apache.commons.collections.primitives.adapters.CharListList var22 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var1);
    org.apache.commons.collections.primitives.adapters.ListShortList var23 = new org.apache.commons.collections.primitives.adapters.ListShortList((java.util.List)var22);
    org.apache.commons.collections.primitives.ShortList var24 = org.apache.commons.collections.primitives.ShortCollections.unmodifiableShortList((org.apache.commons.collections.primitives.ShortList)var23);
    
    // Checks the contract:  var24.equals(var24)
    assertTrue("Contract failed: var24.equals(var24)", var24.equals(var24));

  }

  public void test2() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
    org.apache.commons.collections.primitives.ArrayCharList var2 = new org.apache.commons.collections.primitives.ArrayCharList();
    java.lang.Integer var3 = new java.lang.Integer(10);
    java.lang.Character var4 = new java.lang.Character('#');
    boolean var5 = var1.add((char)var4);
    java.lang.Integer var6 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var7 = new org.apache.commons.collections.primitives.ArrayCharList(var6);
    java.lang.Integer var8 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var9 = new org.apache.commons.collections.primitives.ArrayCharList(var8);
    java.lang.Integer var10 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var11 = new org.apache.commons.collections.primitives.ArrayCharList(var10);
    java.lang.Character var12 = new java.lang.Character('#');
    java.lang.Integer var13 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var14 = new org.apache.commons.collections.primitives.ArrayCharList(var13);
    java.lang.Integer var15 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var16 = new org.apache.commons.collections.primitives.ArrayCharList(var15);
    java.lang.Character var17 = new java.lang.Character('#');
    java.lang.Character var18 = new java.lang.Character('4');
    int var19 = var16.size();
    java.lang.Integer var20 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var21 = new org.apache.commons.collections.primitives.ArrayCharList(var20);
    java.lang.Integer var22 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var23 = new org.apache.commons.collections.primitives.ArrayCharList(var22);
    org.apache.commons.collections.primitives.ArrayCharList var24 = new org.apache.commons.collections.primitives.ArrayCharList();
    java.lang.Integer var25 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var26 = new org.apache.commons.collections.primitives.ArrayCharList(var25);
    java.lang.Character var27 = new java.lang.Character('#');
    org.apache.commons.collections.primitives.adapters.CharListList var28 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var1);
    org.apache.commons.collections.primitives.DoubleList var29 = org.apache.commons.collections.primitives.adapters.ListDoubleList.wrap((java.util.List)var28);
    org.apache.commons.collections.primitives.DoubleList var30 = org.apache.commons.collections.primitives.DoubleCollections.unmodifiableDoubleList(var29);
    
    // Checks the contract:  var30.equals(var30)
    assertTrue("Contract failed: var30.equals(var30)", var30.equals(var30));

  }

}
