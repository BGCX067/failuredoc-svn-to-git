package experiment.ddisolate;

public class ApacheCommonsIsolation {

}


//skip the failure index: 7
//for sequence: 
//java.lang.String var0 = "";
//// <NormalExecution object=object-of-type-java.lang.String>;
//java.lang.Class var1 = null;
//// <NormalExecution object=null>;
//java.lang.Class[] var2 = new java.lang.Class[] { var1};
//// <NormalExecution object=object-of-type-[Ljava.lang.Class;>;
//java.lang.Byte var3 = new java.lang.Byte((byte)0);
//// <NormalExecution object=object-of-type-java.lang.Byte>;
//java.lang.Object[] var4 = new java.lang.Object[] { var3};
//// <NormalExecution object=object-of-type-[Ljava.lang.Object;>;
//org.apache.commons.collections.Transformer var5 = org.apache.commons.collections.functors.InvokerTransformer.getInstance(var0, var2, var4);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.functors.InvokerTransformer>;
//org.apache.commons.collections.Transformer var6 = org.apache.commons.collections.functors.InvokerTransformer.getInstance(var0);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.functors.InvokerTransformer>;
//org.apache.commons.collections.BeanMap var7 = new org.apache.commons.collections.BeanMap((java.lang.Object)var0);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.BeanMap>;
//<randoop.EqualsHashcode [Lrandoop.Variable;@198439 
//
//removable: 
//skip the failure index: 19
//for sequence: 
//java.lang.Integer var0 = new java.lang.Integer(100);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.buffer.BoundedFifoBuffer var1 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var0);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.buffer.BoundedFifoBuffer>;
//java.lang.Integer var2 = new java.lang.Integer(100);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.buffer.BoundedFifoBuffer var3 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var2);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.buffer.BoundedFifoBuffer>;
//boolean var4 = var1.addAll((java.util.Collection)var3);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//int var5 = var3.size();
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.Transformer var6 = org.apache.commons.collections.TransformerUtils.exceptionTransformer();
//// <NormalExecution object=object-of-type-org.apache.commons.collections.functors.ExceptionTransformer>;
//org.apache.commons.collections.Predicate var7 = org.apache.commons.collections.PredicateUtils.asPredicate(var6);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.functors.TransformerPredicate>;
//org.apache.commons.collections.Buffer var8 = org.apache.commons.collections.BufferUtils.transformedBuffer((org.apache.commons.collections.Buffer)var3, var6);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.buffer.TransformedBuffer>;
//java.lang.Integer var9 = new java.lang.Integer(100);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.buffer.BoundedFifoBuffer var10 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var9);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.buffer.BoundedFifoBuffer>;
//java.lang.Integer var11 = new java.lang.Integer(100);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.buffer.BoundedFifoBuffer var12 = new org.apache.commons.collections.buffer.BoundedFifoBuffer(var11);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.buffer.BoundedFifoBuffer>;
//boolean var13 = var10.addAll((java.util.Collection)var12);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.Predicate var14 = org.apache.commons.collections.PredicateUtils.uniquePredicate();
//// <NormalExecution object=object-of-type-org.apache.commons.collections.functors.UniquePredicate>;
//org.apache.commons.collections.CollectionUtils.filter((java.util.Collection)var10, var14);
//// <NormalExecution object=null>;
//org.apache.commons.collections.Predicate[] var16 = new org.apache.commons.collections.Predicate[] { var14};
//// <NormalExecution object=object-of-type-[Lorg.apache.commons.collections.Predicate;>;
//org.apache.commons.collections.functors.AllPredicate var17 = new org.apache.commons.collections.functors.AllPredicate(var16);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.functors.AllPredicate>;
//java.lang.Object var18 = org.apache.commons.collections.CollectionUtils.find((java.util.Collection)var8, (org.apache.commons.collections.Predicate)var17);
//// <NormalExecution object=null>;
//org.apache.commons.collections.Buffer var19 = org.apache.commons.collections.BufferUtils.unmodifiableBuffer(var8);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.buffer.UnmodifiableBuffer>;
//<randoop.EqualsSymmetric [Lrandoop.Variable;@1935d4f 
//
//removable: 