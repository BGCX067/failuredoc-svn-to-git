
import junit.framework.*;

public class PrimitiveTest_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(PrimitiveTest_simplified0.class);
  }

  public void test1() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
    java.lang.Character var2 = new java.lang.Character('#');
    boolean var3 = var1.add((char)var2);
    org.apache.commons.collections.primitives.adapters.CharListList var4 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var1);
    org.apache.commons.collections.primitives.adapters.ListShortList var5 = new org.apache.commons.collections.primitives.adapters.ListShortList((java.util.List)var4);
    org.apache.commons.collections.primitives.ShortList var6 = org.apache.commons.collections.primitives.ShortCollections.unmodifiableShortList((org.apache.commons.collections.primitives.ShortList)var5);
    
    // Checks the contract:  var6.equals(var6)
    assertTrue("Contract failed: var6.equals(var6)", var6.equals(var6));

  }

  public void test2() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(10);
    org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
    java.lang.Character var2 = new java.lang.Character('#');
    boolean var3 = var1.add((char)var2);
    org.apache.commons.collections.primitives.adapters.CharListList var4 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var1);
    org.apache.commons.collections.primitives.DoubleList var5 = org.apache.commons.collections.primitives.adapters.ListDoubleList.wrap((java.util.List)var4);
    org.apache.commons.collections.primitives.DoubleList var6 = org.apache.commons.collections.primitives.DoubleCollections.unmodifiableDoubleList(var5);
    
    // Checks the contract:  var6.equals(var6)
    assertTrue("Contract failed: var6.equals(var6)", var6.equals(var6));

  }

}
