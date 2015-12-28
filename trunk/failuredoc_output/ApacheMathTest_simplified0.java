
import junit.framework.*;

public class ApacheMathTest_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(ApacheMathTest_simplified0.class);
  }

  public void test1() throws Throwable {

    java.lang.Double var0 = new java.lang.Double((-1.0d));
    java.lang.Double var1 = new java.lang.Double(10.0d);
    java.lang.Double var2 = new java.lang.Double(100.0d);
    int var3 = org.apache.commons.math.util.MathUtils.compareTo((double)var0, (double)var1, (double)var2);
    org.apache.commons.math.dfp.DfpField var4 = new org.apache.commons.math.dfp.DfpField(var3);
    org.apache.commons.math.dfp.Dfp var5 = var4.getOne();
    java.lang.Double var6 = new java.lang.Double((-1.0d));
    java.lang.Double var7 = new java.lang.Double(10.0d);
    java.lang.Double var8 = new java.lang.Double(100.0d);
    int var9 = org.apache.commons.math.util.MathUtils.compareTo((double)var6, (double)var7, (double)var8);
    org.apache.commons.math.dfp.DfpField var10 = new org.apache.commons.math.dfp.DfpField(var9);
    org.apache.commons.math.dfp.Dfp var11 = var10.getOne();
    java.lang.Double var12 = new java.lang.Double((-1.0d));
    java.lang.Double var13 = new java.lang.Double(10.0d);
    java.lang.Double var14 = new java.lang.Double(100.0d);
    int var15 = org.apache.commons.math.util.MathUtils.compareTo((double)var12, (double)var13, (double)var14);
    org.apache.commons.math.dfp.DfpField var16 = new org.apache.commons.math.dfp.DfpField(var15);
    org.apache.commons.math.dfp.Dfp var17 = var16.getOne();
    org.apache.commons.math.dfp.Dfp var18 = var11.remainder(var17);
    org.apache.commons.math.dfp.Dfp var19 = var5.remainder(var18);
    
    // Checks the contract:  var19.equals(var19)
    assertTrue("Contract failed: var19.equals(var19)", var19.equals(var19));

  }

  public void test2() throws Throwable {

    org.apache.commons.math.complex.ComplexField var0 = org.apache.commons.math.complex.ComplexField.getInstance();
    org.apache.commons.math.complex.Complex var1 = var0.getZero();
    org.apache.commons.math.complex.Complex var2 = var1.sinh();
    org.apache.commons.math.complex.ComplexField var3 = org.apache.commons.math.complex.ComplexField.getInstance();
    org.apache.commons.math.complex.Complex var4 = var3.getZero();
    org.apache.commons.math.complex.Complex var5 = var4.sinh();
    org.apache.commons.math.complex.Complex var6 = var1.add(var5);
    org.apache.commons.math.complex.Complex var7 = var5.asin();
    
    // Checks the contract:  equals-hashcode on var1 and var7
    assertTrue("Contract failed: equals-hashcode on var1 and var7", var1.equals(var7) ? var1.hashCode() == var7.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var2 and var7
    assertTrue("Contract failed: equals-hashcode on var2 and var7", var2.equals(var7) ? var2.hashCode() == var7.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var4 and var7
    assertTrue("Contract failed: equals-hashcode on var4 and var7", var4.equals(var7) ? var4.hashCode() == var7.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var5 and var7
    assertTrue("Contract failed: equals-hashcode on var5 and var7", var5.equals(var7) ? var5.hashCode() == var7.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var7
    assertTrue("Contract failed: equals-hashcode on var6 and var7", var6.equals(var7) ? var6.hashCode() == var7.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var7 and var1
    assertTrue("Contract failed: equals-hashcode on var7 and var1", var7.equals(var1) ? var7.hashCode() == var1.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var7 and var2
    assertTrue("Contract failed: equals-hashcode on var7 and var2", var7.equals(var2) ? var7.hashCode() == var2.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var7 and var4
    assertTrue("Contract failed: equals-hashcode on var7 and var4", var7.equals(var4) ? var7.hashCode() == var4.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var7 and var5
    assertTrue("Contract failed: equals-hashcode on var7 and var5", var7.equals(var5) ? var7.hashCode() == var5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var7 and var6
    assertTrue("Contract failed: equals-hashcode on var7 and var6", var7.equals(var6) ? var7.hashCode() == var6.hashCode() : true);

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
    java.lang.Double var9 = new java.lang.Double(100.0d);
    org.apache.commons.math.linear.RealVector var10 = var8.mapPowToSelf((double)var9);
    org.apache.commons.math.linear.ArrayRealVector var11 = new org.apache.commons.math.linear.ArrayRealVector(var3, var9);
    java.lang.Double var12 = new java.lang.Double((-1.0d));
    double[] var13 = new double[] { var12};
    org.apache.commons.math.linear.OpenMapRealVector var14 = new org.apache.commons.math.linear.OpenMapRealVector(var13);
    java.lang.Double var15 = new java.lang.Double((-1.0d));
    double[] var16 = new double[] { var15};
    org.apache.commons.math.linear.OpenMapRealVector var17 = new org.apache.commons.math.linear.OpenMapRealVector(var16);
    org.apache.commons.math.linear.OpenMapRealVector var18 = var14.add(var17);
    org.apache.commons.math.linear.OpenMapRealVector var19 = new org.apache.commons.math.linear.OpenMapRealVector(var18);
    java.lang.Double var20 = new java.lang.Double((-1.0d));
    java.lang.Double var21 = new java.lang.Double(0.0d);
    java.lang.Double var22 = new java.lang.Double((-1.0d));
    double[] var23 = new double[] { var20, var21, var22};
    org.apache.commons.math.linear.ArrayRealVector var24 = new org.apache.commons.math.linear.ArrayRealVector(var23);
    java.lang.Double var25 = new java.lang.Double((-1.0d));
    org.apache.commons.math.linear.RealVector var26 = var24.mapDivide((double)var25);
    org.apache.commons.math.linear.RealVector var27 = var19.mapSubtractToSelf((double)var25);
    org.apache.commons.math.linear.RealVector var28 = var11.projection(var27);
    
    // Checks the contract:  equals-hashcode on var10 and var26
    assertTrue("Contract failed: equals-hashcode on var10 and var26", var10.equals(var26) ? var10.hashCode() == var26.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var26 and var10
    assertTrue("Contract failed: equals-hashcode on var26 and var10", var26.equals(var10) ? var26.hashCode() == var10.hashCode() : true);

  }

}
