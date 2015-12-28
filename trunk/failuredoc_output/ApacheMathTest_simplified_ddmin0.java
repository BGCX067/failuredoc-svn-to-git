
import junit.framework.*;

public class ApacheMathTest_simplified_ddmin0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(ApacheMathTest_simplified_ddmin0.class);
  }

  public void test1() throws Throwable {

    java.lang.Double var0 = new java.lang.Double((-1.0d));
    java.lang.Double var1 = new java.lang.Double(10.0d);
    java.lang.Double var2 = new java.lang.Double(100.0d);
    int var3 = org.apache.commons.math.util.MathUtils.compareTo((double)var0, (double)var1, (double)var2);
    java.lang.Integer var4 = new java.lang.Integer(0);
    java.lang.Long var5 = new java.lang.Long(100L);
    org.apache.commons.math.dfp.DfpField var6 = new org.apache.commons.math.dfp.DfpField(var3);
    java.lang.Double var7 = new java.lang.Double((-1.0d));
    java.lang.Double var8 = new java.lang.Double(10.0d);
    java.lang.Double var9 = new java.lang.Double(100.0d);
    int var10 = org.apache.commons.math.util.MathUtils.compareTo((double)var7, (double)var8, (double)var9);
    java.lang.Integer var11 = new java.lang.Integer(0);
    org.apache.commons.math.dfp.DfpField var12 = new org.apache.commons.math.dfp.DfpField(var10);
    org.apache.commons.math.dfp.Dfp var13 = var12.getOne();
    java.lang.Double var14 = new java.lang.Double((-1.0d));
    java.lang.Double var15 = new java.lang.Double(10.0d);
    java.lang.Double var16 = new java.lang.Double(100.0d);
    int var17 = org.apache.commons.math.util.MathUtils.compareTo((double)var14, (double)var15, (double)var16);
    java.lang.Integer var18 = new java.lang.Integer(0);
    java.lang.Long var19 = new java.lang.Long(100L);
    org.apache.commons.math.dfp.DfpField var20 = new org.apache.commons.math.dfp.DfpField(var17);
    org.apache.commons.math.dfp.Dfp var21 = var20.getOne();
    java.lang.Double var22 = new java.lang.Double((-1.0d));
    java.lang.Double var23 = new java.lang.Double(10.0d);
    java.lang.Double var24 = new java.lang.Double(100.0d);
    int var25 = org.apache.commons.math.util.MathUtils.compareTo((double)var22, (double)var23, (double)var24);
    java.lang.Integer var26 = new java.lang.Integer(0);
    org.apache.commons.math.dfp.DfpField var27 = new org.apache.commons.math.dfp.DfpField(var25);
    java.lang.Double var28 = new java.lang.Double((-1.0d));
    java.lang.Double var29 = new java.lang.Double(10.0d);
    java.lang.Double var30 = new java.lang.Double(100.0d);
    int var31 = org.apache.commons.math.util.MathUtils.compareTo((double)var28, (double)var29, (double)var30);
    org.apache.commons.math.dfp.DfpField var32 = new org.apache.commons.math.dfp.DfpField(var31);
    org.apache.commons.math.dfp.Dfp var33 = var32.getOne();
    java.lang.Double var34 = new java.lang.Double((-1.0d));
    java.lang.Double var35 = new java.lang.Double(10.0d);
    java.lang.Double var36 = new java.lang.Double(100.0d);
    int var37 = org.apache.commons.math.util.MathUtils.compareTo((double)var34, (double)var35, (double)var36);
    org.apache.commons.math.dfp.DfpField var38 = new org.apache.commons.math.dfp.DfpField(var37);
    org.apache.commons.math.dfp.Dfp var39 = var38.getOne();
    org.apache.commons.math.dfp.Dfp var40 = var21.remainder(var39);
    org.apache.commons.math.dfp.Dfp var41 = var13.remainder(var40);
    
    // Checks the contract:  var41.equals(var41)
    assertTrue("Contract failed: var41.equals(var41)", var41.equals(var41));

  }

  public void test2() throws Throwable {

    org.apache.commons.math.complex.ComplexField var0 = org.apache.commons.math.complex.ComplexField.getInstance();
    org.apache.commons.math.complex.Complex var1 = var0.getZero();
    org.apache.commons.math.complex.ComplexField var2 = org.apache.commons.math.complex.ComplexField.getInstance();
    org.apache.commons.math.complex.Complex var3 = var2.getZero();
    org.apache.commons.math.complex.Complex var4 = var3.sinh();
    org.apache.commons.math.complex.Complex var5 = var4.asin();
    
    // Checks the contract:  equals-hashcode on var1 and var5
    assertTrue("Contract failed: equals-hashcode on var1 and var5", var1.equals(var5) ? var1.hashCode() == var5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var3 and var5
    assertTrue("Contract failed: equals-hashcode on var3 and var5", var3.equals(var5) ? var3.hashCode() == var5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var4 and var5
    assertTrue("Contract failed: equals-hashcode on var4 and var5", var4.equals(var5) ? var4.hashCode() == var5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var5 and var1
    assertTrue("Contract failed: equals-hashcode on var5 and var1", var5.equals(var1) ? var5.hashCode() == var1.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var5 and var3
    assertTrue("Contract failed: equals-hashcode on var5 and var3", var5.equals(var3) ? var5.hashCode() == var3.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var5 and var4
    assertTrue("Contract failed: equals-hashcode on var5 and var4", var5.equals(var4) ? var5.hashCode() == var4.hashCode() : true);

  }

  public void test3() throws Throwable {

    java.lang.Double var0 = new java.lang.Double((-1.0d));
    double[] var1 = new double[] { var0};
    org.apache.commons.math.linear.OpenMapRealVector var2 = new org.apache.commons.math.linear.OpenMapRealVector(var1);
    int var3 = var2.getDimension();
    java.lang.Double var4 = new java.lang.Double((-1.0d));
    java.lang.Double var5 = new java.lang.Double(0.0d);
    java.lang.Double var6 = new java.lang.Double((-1.0d));
    double[] var7 = new double[] { var4, var5, var6};
    org.apache.commons.math.linear.ArrayRealVector var8 = new org.apache.commons.math.linear.ArrayRealVector(var7);
    java.lang.Double var9 = new java.lang.Double((-1.0d));
    java.lang.Double var10 = new java.lang.Double(0.0d);
    java.lang.Double var11 = new java.lang.Double((-1.0d));
    double[] var12 = new double[] { var9, var10, var11};
    org.apache.commons.math.linear.ArrayRealVector var13 = new org.apache.commons.math.linear.ArrayRealVector(var12);
    java.lang.Double var14 = new java.lang.Double((-1.0d));
    java.lang.Double var15 = new java.lang.Double(10.0d);
    java.lang.Double var16 = new java.lang.Double(100.0d);
    org.apache.commons.math.linear.RealVector var17 = var13.mapPowToSelf((double)var16);
    org.apache.commons.math.linear.ArrayRealVector var18 = new org.apache.commons.math.linear.ArrayRealVector(var3, var16);
    java.lang.Double var19 = new java.lang.Double((-1.0d));
    double[] var20 = new double[] { var19};
    org.apache.commons.math.linear.OpenMapRealVector var21 = new org.apache.commons.math.linear.OpenMapRealVector(var20);
    java.lang.Double var22 = new java.lang.Double((-1.0d));
    double[] var23 = new double[] { var22};
    org.apache.commons.math.linear.OpenMapRealVector var24 = new org.apache.commons.math.linear.OpenMapRealVector(var23);
    org.apache.commons.math.linear.OpenMapRealVector var25 = var21.add(var24);
    org.apache.commons.math.linear.OpenMapRealVector var26 = new org.apache.commons.math.linear.OpenMapRealVector(var25);
    java.lang.Double var27 = new java.lang.Double((-1.0d));
    java.lang.Double var28 = new java.lang.Double(0.0d);
    java.lang.Double var29 = new java.lang.Double((-1.0d));
    double[] var30 = new double[] { var27, var28, var29};
    org.apache.commons.math.linear.ArrayRealVector var31 = new org.apache.commons.math.linear.ArrayRealVector(var30);
    java.lang.Double var32 = new java.lang.Double((-1.0d));
    java.lang.Double var33 = new java.lang.Double(0.0d);
    java.lang.Double var34 = new java.lang.Double((-1.0d));
    double[] var35 = new double[] { var32, var33, var34};
    org.apache.commons.math.linear.ArrayRealVector var36 = new org.apache.commons.math.linear.ArrayRealVector(var35);
    java.lang.Double var37 = new java.lang.Double(0.0d);
    java.lang.Double var38 = new java.lang.Double((-1.0d));
    org.apache.commons.math.linear.RealVector var39 = var31.mapDivide((double)var38);
    org.apache.commons.math.linear.RealVector var40 = var26.mapSubtractToSelf((double)var38);
    org.apache.commons.math.linear.RealVector var41 = var18.projection(var40);
    
    // Checks the contract:  equals-hashcode on var17 and var39
    assertTrue("Contract failed: equals-hashcode on var17 and var39", var17.equals(var39) ? var17.hashCode() == var39.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var39 and var17
    assertTrue("Contract failed: equals-hashcode on var39 and var17", var39.equals(var17) ? var39.hashCode() == var17.hashCode() : true);

  }

}
