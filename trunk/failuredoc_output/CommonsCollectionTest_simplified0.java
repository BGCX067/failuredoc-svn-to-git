
import junit.framework.*;

public class CommonsCollectionTest_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(CommonsCollectionTest_simplified0.class);
  }

  public void test1() throws Throwable {

    java.lang.String var0 = "";
    org.apache.commons.collections.BeanMap var1 = new org.apache.commons.collections.BeanMap((java.lang.Object)var0);
    
    // Checks the contract:  equals-hashcode on var1 and var1
    assertTrue("Contract failed: equals-hashcode on var1 and var1", var1.equals(var1) ? var1.hashCode() == var1.hashCode() : true);

  }

  public void test2() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(100);
    org.apache.commons.collections.buffer.BoundedFifoBuffer var1 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var0);
    org.apache.commons.collections.Transformer var2 = org.apache.commons.collections.TransformerUtils.exceptionTransformer();
    org.apache.commons.collections.Buffer var3 = org.apache.commons.collections.BufferUtils.transformedBuffer((org.apache.commons.collections.Buffer)var1, var2);
    org.apache.commons.collections.Buffer var4 = org.apache.commons.collections.BufferUtils.unmodifiableBuffer(var3);
    
    // Checks the contract:  equals-symmetric on var4 and var3.
    assertTrue("Contract failed: equals-symmetric on var4 and var3.", var4.equals(var3) ? var3.equals(var4) : true);

  }

}
