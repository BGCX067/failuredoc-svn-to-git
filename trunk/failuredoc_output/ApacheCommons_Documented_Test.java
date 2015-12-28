import junit.framework.TestCase;
public class ApacheCommons_Documented_Test extends TestCase {
  public void test0() throws Throwable {
        java.lang.String var0 = "";
        java.lang.Class var1 = null;
        java.lang.Class[] var2 = new java.lang.Class[] { var1};
        java.lang.Byte var3 = new java.lang.Byte((byte)0);
        java.lang.Object[] var4 = new java.lang.Object[] { var3};
        org.apache.commons.collections.Transformer var5 = org.apache.commons.collections.functors.InvokerTransformer.getInstance(var0, var2, var4);
        org.apache.commons.collections.Transformer var6 = org.apache.commons.collections.functors.InvokerTransformer.getInstance(var0);
        org.apache.commons.collections.BeanMap var7 = new org.apache.commons.collections.BeanMap((java.lang.Object)var0);
        //Test passes if var7 is: (org.apache.commons.collections.BeanMap)BeanMap<null>
        
        // Checks the contract:  equals-hashcode on var7 and var7
        assertTrue("Contract failed: equals-hashcode on var7 and var7", var7.equals(var7) ? var7.hashCode() == var7.hashCode() : true);
  }
  public void test1() throws Throwable {
        java.lang.Integer var0 = new java.lang.Integer(100);
        org.apache.commons.collections.buffer.BoundedFifoBuffer var1 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var0);
        java.lang.Integer var2 = new java.lang.Integer(100);
        org.apache.commons.collections.buffer.BoundedFifoBuffer var3 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var2);
        boolean var4 = var1.addAll((java.util.Collection)var3);
        int var5 = var3.size();
        org.apache.commons.collections.Transformer var6 = org.apache.commons.collections.TransformerUtils.exceptionTransformer();
        org.apache.commons.collections.Predicate var7 = org.apache.commons.collections.PredicateUtils.asPredicate(var6);
        org.apache.commons.collections.Buffer var8 = org.apache.commons.collections.BufferUtils.transformedBuffer((org.apache.commons.collections.Buffer)var3, var6);
        //Test passes if var8  is type: class org.apache.commons.collections.buffer.UnmodifiableBuffer, with value: [].
        // Current type: class org.apache.commons.collections.buffer.TransformedBuffer, with value: []
        java.lang.Integer var9 = new java.lang.Integer(100);
        org.apache.commons.collections.buffer.BoundedFifoBuffer var10 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var9);
        java.lang.Integer var11 = new java.lang.Integer(100);
        org.apache.commons.collections.buffer.BoundedFifoBuffer var12 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var11);
        boolean var13 = var10.addAll((java.util.Collection)var12);
        org.apache.commons.collections.Predicate var14 = org.apache.commons.collections.PredicateUtils.uniquePredicate();
        org.apache.commons.collections.CollectionUtils.filter((java.util.Collection)var10, var14);
        org.apache.commons.collections.Predicate[] var16 = new org.apache.commons.collections.Predicate[] { var14};
        org.apache.commons.collections.functors.AllPredicate var17 = new org.apache.commons.collections.functors.AllPredicate(var16);
        java.lang.Object var18 = org.apache.commons.collections.CollectionUtils.find((java.util.Collection)var8, (org.apache.commons.collections.Predicate)var17);
        org.apache.commons.collections.Buffer var19 = org.apache.commons.collections.BufferUtils.unmodifiableBuffer(var8);
        //Test passes if var19  is type: class org.apache.commons.collections.buffer.TransformedBuffer, with value: [].
        // Current type: class org.apache.commons.collections.buffer.UnmodifiableBuffer, with value: []
        
        // Checks the contract:  equals-symmetric on var19 and var8.
        assertTrue("Contract failed: equals-symmetric on var19 and var8.", var19.equals(var8) ? var8.equals(var19) : true);
  }
}
