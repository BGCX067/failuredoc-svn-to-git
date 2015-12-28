
import junit.framework.*;

public class ApacheMathTest0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(ApacheMathTest0.class);
  }

  public void test1() throws Throwable {

    java.lang.Double var0 = new java.lang.Double((-1.0d));
    java.lang.Double var1 = new java.lang.Double(10.0d);
    java.lang.Double var2 = new java.lang.Double(100.0d);
    int var3 = org.apache.commons.math.util.MathUtils.compareTo((double)var0, (double)var1, (double)var2);
    java.lang.Integer var4 = new java.lang.Integer(0);
    java.lang.Long var5 = new java.lang.Long(100L);
    int var6 = org.apache.commons.math.util.MathUtils.pow((int)var4, (long)var5);
    int var7 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var3, (int)var4);
    org.apache.commons.math.dfp.DfpField var8 = new org.apache.commons.math.dfp.DfpField(var3);
    org.apache.commons.math.dfp.Dfp var9 = var8.getOne();
    java.lang.Double var10 = new java.lang.Double((-1.0d));
    java.lang.Double var11 = new java.lang.Double(10.0d);
    java.lang.Double var12 = new java.lang.Double(100.0d);
    int var13 = org.apache.commons.math.util.MathUtils.compareTo((double)var10, (double)var11, (double)var12);
    java.lang.Integer var14 = new java.lang.Integer(0);
    java.lang.Long var15 = new java.lang.Long(100L);
    int var16 = org.apache.commons.math.util.MathUtils.pow((int)var14, (long)var15);
    int var17 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var13, (int)var14);
    org.apache.commons.math.dfp.DfpField var18 = new org.apache.commons.math.dfp.DfpField(var13);
    org.apache.commons.math.dfp.Dfp var19 = var18.getOne();
    org.apache.commons.math.dfp.Dfp var20 = var8.newDfp(var19);
    java.lang.Double var21 = new java.lang.Double((-1.0d));
    java.lang.Double var22 = new java.lang.Double(10.0d);
    java.lang.Double var23 = new java.lang.Double(100.0d);
    int var24 = org.apache.commons.math.util.MathUtils.compareTo((double)var21, (double)var22, (double)var23);
    java.lang.Integer var25 = new java.lang.Integer(0);
    java.lang.Long var26 = new java.lang.Long(100L);
    int var27 = org.apache.commons.math.util.MathUtils.pow((int)var25, (long)var26);
    int var28 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var24, (int)var25);
    org.apache.commons.math.dfp.DfpField var29 = new org.apache.commons.math.dfp.DfpField(var24);
    org.apache.commons.math.dfp.Dfp var30 = var29.getOne();
    boolean var31 = var19.greaterThan(var30);
    java.lang.Double var32 = new java.lang.Double((-1.0d));
    java.lang.Double var33 = new java.lang.Double(10.0d);
    java.lang.Double var34 = new java.lang.Double(100.0d);
    int var35 = org.apache.commons.math.util.MathUtils.compareTo((double)var32, (double)var33, (double)var34);
    java.lang.Integer var36 = new java.lang.Integer(0);
    java.lang.Long var37 = new java.lang.Long(100L);
    int var38 = org.apache.commons.math.util.MathUtils.pow((int)var36, (long)var37);
    int var39 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var35, (int)var36);
    org.apache.commons.math.dfp.DfpField var40 = new org.apache.commons.math.dfp.DfpField(var35);
    org.apache.commons.math.dfp.Dfp var41 = var40.getOne();
    org.apache.commons.math.dfp.Dfp var42 = var40.getOne();
    org.apache.commons.math.dfp.Dfp var43 = var19.subtract(var42);
    java.lang.Double var44 = new java.lang.Double((-1.0d));
    java.lang.Double var45 = new java.lang.Double(10.0d);
    java.lang.Double var46 = new java.lang.Double(100.0d);
    int var47 = org.apache.commons.math.util.MathUtils.compareTo((double)var44, (double)var45, (double)var46);
    java.lang.Integer var48 = new java.lang.Integer(0);
    java.lang.Long var49 = new java.lang.Long(100L);
    int var50 = org.apache.commons.math.util.MathUtils.pow((int)var48, (long)var49);
    int var51 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var47, (int)var48);
    org.apache.commons.math.dfp.DfpField var52 = new org.apache.commons.math.dfp.DfpField(var47);
    org.apache.commons.math.dfp.Dfp var53 = var52.getOne();
    java.lang.Double var54 = new java.lang.Double((-1.0d));
    java.lang.Double var55 = new java.lang.Double(10.0d);
    java.lang.Double var56 = new java.lang.Double(100.0d);
    int var57 = org.apache.commons.math.util.MathUtils.compareTo((double)var54, (double)var55, (double)var56);
    java.lang.Integer var58 = new java.lang.Integer(0);
    java.lang.Long var59 = new java.lang.Long(100L);
    int var60 = org.apache.commons.math.util.MathUtils.pow((int)var58, (long)var59);
    int var61 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var57, (int)var58);
    org.apache.commons.math.dfp.DfpField var62 = new org.apache.commons.math.dfp.DfpField(var57);
    org.apache.commons.math.dfp.Dfp var63 = var62.getOne();
    java.lang.Double var64 = new java.lang.Double((-1.0d));
    java.lang.Double var65 = new java.lang.Double(10.0d);
    java.lang.Double var66 = new java.lang.Double(100.0d);
    int var67 = org.apache.commons.math.util.MathUtils.compareTo((double)var64, (double)var65, (double)var66);
    java.lang.Integer var68 = new java.lang.Integer(0);
    java.lang.Long var69 = new java.lang.Long(100L);
    int var70 = org.apache.commons.math.util.MathUtils.pow((int)var68, (long)var69);
    int var71 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var67, (int)var68);
    org.apache.commons.math.dfp.DfpField var72 = new org.apache.commons.math.dfp.DfpField(var67);
    org.apache.commons.math.dfp.Dfp var73 = var72.getOne();
    org.apache.commons.math.dfp.Dfp var74 = var62.newDfp(var73);
    java.lang.Double var75 = new java.lang.Double((-1.0d));
    java.lang.Double var76 = new java.lang.Double(10.0d);
    java.lang.Double var77 = new java.lang.Double(100.0d);
    int var78 = org.apache.commons.math.util.MathUtils.compareTo((double)var75, (double)var76, (double)var77);
    java.lang.Integer var79 = new java.lang.Integer(0);
    java.lang.Long var80 = new java.lang.Long(100L);
    int var81 = org.apache.commons.math.util.MathUtils.pow((int)var79, (long)var80);
    int var82 = org.apache.commons.math.util.MathUtils.mulAndCheck((int)var78, (int)var79);
    org.apache.commons.math.dfp.DfpField var83 = new org.apache.commons.math.dfp.DfpField(var78);
    org.apache.commons.math.dfp.Dfp var84 = var83.getOne();
    boolean var85 = var73.greaterThan(var84);
    org.apache.commons.math.dfp.Dfp var86 = var53.remainder(var84);
    org.apache.commons.math.dfp.Dfp var87 = var19.remainder(var86);
    
    // Checks the contract:  var87.equals(var87)
    assertTrue("Contract failed: var87.equals(var87)", var87.equals(var87));

  }

  public void test2() throws Throwable {

    org.apache.commons.math.complex.ComplexField var0 = org.apache.commons.math.complex.ComplexField.getInstance();
    org.apache.commons.math.complex.Complex var1 = var0.getZero();
    org.apache.commons.math.complex.Complex var2 = var1.sinh();
    org.apache.commons.math.complex.ComplexField var3 = var1.getField();
    org.apache.commons.math.complex.ComplexField var4 = org.apache.commons.math.complex.ComplexField.getInstance();
    org.apache.commons.math.complex.Complex var5 = var4.getZero();
    org.apache.commons.math.complex.Complex var6 = var5.sinh();
    org.apache.commons.math.complex.Complex var7 = var1.add(var6);
    org.apache.commons.math.complex.Complex var8 = var6.asin();
    
    // Checks the contract:  equals-hashcode on var1 and var8
    assertTrue("Contract failed: equals-hashcode on var1 and var8", var1.equals(var8) ? var1.hashCode() == var8.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var2 and var8
    assertTrue("Contract failed: equals-hashcode on var2 and var8", var2.equals(var8) ? var2.hashCode() == var8.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var5 and var8
    assertTrue("Contract failed: equals-hashcode on var5 and var8", var5.equals(var8) ? var5.hashCode() == var8.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var8
    assertTrue("Contract failed: equals-hashcode on var6 and var8", var6.equals(var8) ? var6.hashCode() == var8.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var7 and var8
    assertTrue("Contract failed: equals-hashcode on var7 and var8", var7.equals(var8) ? var7.hashCode() == var8.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var8 and var1
    assertTrue("Contract failed: equals-hashcode on var8 and var1", var8.equals(var1) ? var8.hashCode() == var1.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var8 and var2
    assertTrue("Contract failed: equals-hashcode on var8 and var2", var8.equals(var2) ? var8.hashCode() == var2.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var8 and var5
    assertTrue("Contract failed: equals-hashcode on var8 and var5", var8.equals(var5) ? var8.hashCode() == var5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var8 and var6
    assertTrue("Contract failed: equals-hashcode on var8 and var6", var8.equals(var6) ? var8.hashCode() == var6.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var8 and var7
    assertTrue("Contract failed: equals-hashcode on var8 and var7", var8.equals(var7) ? var8.hashCode() == var7.hashCode() : true);

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
    org.apache.commons.math.linear.RealVector var14 = var8.ebeDivide((org.apache.commons.math.linear.RealVector)var13);
    java.lang.Double var15 = new java.lang.Double((-1.0d));
    java.lang.Double var16 = new java.lang.Double(10.0d);
    java.lang.Double var17 = new java.lang.Double(100.0d);
    int var18 = org.apache.commons.math.util.MathUtils.compareTo((double)var15, (double)var16, (double)var17);
    org.apache.commons.math.linear.RealVector var19 = var13.mapPowToSelf((double)var17);
    org.apache.commons.math.linear.ArrayRealVector var20 = new org.apache.commons.math.linear.ArrayRealVector(var3, var17);
    java.lang.Double var21 = new java.lang.Double((-1.0d));
    double[] var22 = new double[] { var21};
    org.apache.commons.math.linear.OpenMapRealVector var23 = new org.apache.commons.math.linear.OpenMapRealVector(var22);
    java.lang.Double var24 = new java.lang.Double((-1.0d));
    double[] var25 = new double[] { var24};
    org.apache.commons.math.linear.OpenMapRealVector var26 = new org.apache.commons.math.linear.OpenMapRealVector(var25);
    org.apache.commons.math.linear.OpenMapRealVector var27 = var23.add(var26);
    org.apache.commons.math.linear.OpenMapRealVector var28 = new org.apache.commons.math.linear.OpenMapRealVector(var27);
    java.lang.Double var29 = new java.lang.Double((-1.0d));
    java.lang.Double var30 = new java.lang.Double(0.0d);
    java.lang.Double var31 = new java.lang.Double((-1.0d));
    double[] var32 = new double[] { var29, var30, var31};
    org.apache.commons.math.linear.ArrayRealVector var33 = new org.apache.commons.math.linear.ArrayRealVector(var32);
    java.lang.Double var34 = new java.lang.Double((-1.0d));
    java.lang.Double var35 = new java.lang.Double(0.0d);
    java.lang.Double var36 = new java.lang.Double((-1.0d));
    double[] var37 = new double[] { var34, var35, var36};
    org.apache.commons.math.linear.ArrayRealVector var38 = new org.apache.commons.math.linear.ArrayRealVector(var37);
    org.apache.commons.math.linear.RealVector var39 = var33.ebeDivide((org.apache.commons.math.linear.RealVector)var38);
    java.lang.Double var40 = new java.lang.Double(0.0d);
    java.lang.Double var41 = new java.lang.Double((-1.0d));
    java.lang.Double var42 = new java.lang.Double((-1.0d));
    int var43 = org.apache.commons.math.util.MathUtils.compareTo((double)var40, (double)var41, (double)var42);
    org.apache.commons.math.linear.RealVector var44 = var33.mapDivide((double)var41);
    org.apache.commons.math.linear.RealVector var45 = var28.mapSubtractToSelf((double)var41);
    org.apache.commons.math.linear.RealVector var46 = var20.projection(var45);
    
    // Checks the contract:  equals-hashcode on var19 and var44
    assertTrue("Contract failed: equals-hashcode on var19 and var44", var19.equals(var44) ? var19.hashCode() == var44.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var44 and var19
    assertTrue("Contract failed: equals-hashcode on var44 and var19", var44.equals(var19) ? var44.hashCode() == var19.hashCode() : true);

  }

}
