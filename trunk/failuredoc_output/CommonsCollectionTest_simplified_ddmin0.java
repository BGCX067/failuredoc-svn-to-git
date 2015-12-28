
import junit.framework.*;

public class CommonsCollectionTest_simplified_ddmin0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(CommonsCollectionTest_simplified_ddmin0.class);
  }

  public void test1() throws Throwable {

    java.lang.String var0 = "";
    java.lang.Class var1 = null;
    java.lang.Class[] var2 = new java.lang.Class[] { var1};
    java.lang.Byte var3 = new java.lang.Byte((byte)0);
    org.apache.commons.collections.BeanMap var4 = new org.apache.commons.collections.BeanMap((java.lang.Object)var0);
    
    // Checks the contract:  equals-hashcode on var4 and var4
    assertTrue("Contract failed: equals-hashcode on var4 and var4", var4.equals(var4) ? var4.hashCode() == var4.hashCode() : true);

  }

  public void test2() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(100);
    org.apache.commons.collections.buffer.BoundedFifoBuffer var1 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var0);
    java.lang.Integer var2 = new java.lang.Integer(100);
    org.apache.commons.collections.buffer.BoundedFifoBuffer var3 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var2);
    org.apache.commons.collections.Transformer var4 = org.apache.commons.collections.TransformerUtils.exceptionTransformer();
    org.apache.commons.collections.Buffer var5 = org.apache.commons.collections.BufferUtils.transformedBuffer((org.apache.commons.collections.Buffer)var3, var4);
    java.lang.Integer var6 = new java.lang.Integer(100);
    org.apache.commons.collections.buffer.BoundedFifoBuffer var7 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var6);
    java.lang.Integer var8 = new java.lang.Integer(100);
    org.apache.commons.collections.buffer.BoundedFifoBuffer var9 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var8);
    org.apache.commons.collections.Predicate var10 = org.apache.commons.collections.PredicateUtils.uniquePredicate();
    org.apache.commons.collections.Predicate[] var11 = new org.apache.commons.collections.Predicate[] { var10};
    org.apache.commons.collections.functors.AllPredicate var12 = new org.apache.commons.collections.functors.AllPredicate(var11);
    org.apache.commons.collections.Buffer var13 = org.apache.commons.collections.BufferUtils.unmodifiableBuffer(var5);
    
    // Checks the contract:  equals-symmetric on var13 and var5.
    assertTrue("Contract failed: equals-symmetric on var13 and var5.", var13.equals(var5) ? var5.equals(var13) : true);

  }

}
