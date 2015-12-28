import org.apache.commons.collections.buffer.BoundedFifoBuffer;
import org.apache.commons.collections.BeanMap;
import org.apache.commons.collections.functors.AllPredicate;
import java.lang.Object;
import java.util.AbstractCollection;
import java.lang.Class;
import java.lang.String;
import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.Predicate;
import java.util.Collection;
import org.apache.commons.collections.Transformer;

import junit.framework.TestCase;
public class ApacheCommons_Documented_Test_Pretty extends TestCase { 
  public void test0() {
    String str0 = "";
    Class clazz0 = null;
    Class[] clazz_array0 = new Class[] { clazz0};
    Byte byte0 = new Byte((byte)0);
    Object[] obj_array0 = new Object[] { byte0};
    Transformer transformer0 = org.apache.commons.collections.functors.InvokerTransformer.getInstance(str0, clazz_array0, obj_array0);
    Transformer transformer1 = org.apache.commons.collections.functors.InvokerTransformer.getInstance(str0);
    //Test passes if beanMap0 is: (org.apache.commons.collections.BeanMap)BeanMap<null>
    BeanMap beanMap0 = new BeanMap((Object)str0);
    
    // Checks the contract:  equals-hashcode on beanMap0 and beanMap0
    assertTrue("Contract failed: equals-hashcode on beanMap0 and beanMap0", beanMap0.equals(beanMap0) ? beanMap0.hashCode() == beanMap0.hashCode() : true);
  }
  public void test1() {
    Integer i0 = new Integer(100);
    BoundedFifoBuffer boundedFifoBuffer0 = new BoundedFifoBuffer(i0);
    Integer i1 = new Integer(100);
    BoundedFifoBuffer boundedFifoBuffer1 = new BoundedFifoBuffer(i1);
    boolean b0 = boundedFifoBuffer0.addAll((Collection)boundedFifoBuffer1);
    int i2 = boundedFifoBuffer1.size();
    Transformer transformer0 = org.apache.commons.collections.TransformerUtils.exceptionTransformer();
    Predicate predicate0 = org.apache.commons.collections.PredicateUtils.asPredicate(transformer0);
    //Test passes if buffer0  is type: class org.apache.commons.collections.buffer.UnmodifiableBuffer, with value: [].
        // Current type: class org.apache.commons.collections.buffer.TransformedBuffer, with value: []
    Buffer buffer0 = org.apache.commons.collections.BufferUtils.transformedBuffer((Buffer)boundedFifoBuffer1, transformer0);
    Integer i3 = new Integer(100);
    BoundedFifoBuffer boundedFifoBuffer2 = new BoundedFifoBuffer(i3);
    Integer i4 = new Integer(100);
    BoundedFifoBuffer boundedFifoBuffer3 = new BoundedFifoBuffer(i4);
    boolean b1 = boundedFifoBuffer2.addAll((Collection)boundedFifoBuffer3);
    Predicate predicate1 = org.apache.commons.collections.PredicateUtils.uniquePredicate();
    org.apache.commons.collections.CollectionUtils.filter((Collection)boundedFifoBuffer2, predicate1);
    Predicate[] predicate_array0 = new Predicate[] { predicate1};
    AllPredicate allPredicate0 = new AllPredicate(predicate_array0);
    Object obj0 = org.apache.commons.collections.CollectionUtils.find((Collection)buffer0, (Predicate)allPredicate0);
    //Test passes if buffer1  is type: class org.apache.commons.collections.buffer.TransformedBuffer, with value: [].
        // Current type: class org.apache.commons.collections.buffer.UnmodifiableBuffer, with value: []
    Buffer buffer1 = org.apache.commons.collections.BufferUtils.unmodifiableBuffer(buffer0);
    
    // Checks the contract:  equals-symmetric on buffer1 and buffer0.
    assertTrue("Contract failed: equals-symmetric on buffer1 and buffer0.", buffer1.equals(buffer0) ? buffer0.equals(buffer1) : true);
  }

}