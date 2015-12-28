
import junit.framework.*;

public class ApacheMathTest_simplified_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(ApacheMathTest_simplified_simplified0.class);
  }

  public void test1() throws Throwable {

    java.lang.Double var0 = new java.lang.Double((-1.0d));
    int var1 = org.apache.commons.math.util.MathUtils.compareTo((double)var0, (double)var0, (double)var0);
    org.apache.commons.math.dfp.DfpField var2 = new org.apache.commons.math.dfp.DfpField(var1);
    org.apache.commons.math.dfp.Dfp var3 = var2.getOne();
    org.apache.commons.math.dfp.Dfp var4 = var3.remainder(var3);
    org.apache.commons.math.dfp.Dfp var5 = var3.remainder(var4);
    
    // Checks the contract:  var5.equals(var5)
    assertTrue("Contract failed: var5.equals(var5)", var5.equals(var5));

  }

  public void test2() throws Throwable {

    org.apache.commons.math.complex.ComplexField var0 = org.apache.commons.math.complex.ComplexField.getInstance();
    org.apache.commons.math.complex.Complex var1 = var0.getZero();
    org.apache.commons.math.complex.Complex var2 = var1.sinh();
    org.apache.commons.math.complex.Complex var3 = var0.getZero();
    org.apache.commons.math.complex.Complex var4 = var1.sinh();
    org.apache.commons.math.complex.Complex var5 = var1.add(var1);
    org.apache.commons.math.complex.Complex var6 = var1.asin();
    
    // Checks the contract:  equals-hashcode on var1 and var6
    assertTrue("Contract failed: equals-hashcode on var1 and var6", var1.equals(var6) ? var1.hashCode() == var6.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var2 and var6
    assertTrue("Contract failed: equals-hashcode on var2 and var6", var2.equals(var6) ? var2.hashCode() == var6.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var3 and var6
    assertTrue("Contract failed: equals-hashcode on var3 and var6", var3.equals(var6) ? var3.hashCode() == var6.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var4 and var6
    assertTrue("Contract failed: equals-hashcode on var4 and var6", var4.equals(var6) ? var4.hashCode() == var6.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var5 and var6
    assertTrue("Contract failed: equals-hashcode on var5 and var6", var5.equals(var6) ? var5.hashCode() == var6.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var1
    assertTrue("Contract failed: equals-hashcode on var6 and var1", var6.equals(var1) ? var6.hashCode() == var1.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var2
    assertTrue("Contract failed: equals-hashcode on var6 and var2", var6.equals(var2) ? var6.hashCode() == var2.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var3
    assertTrue("Contract failed: equals-hashcode on var6 and var3", var6.equals(var3) ? var6.hashCode() == var3.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var4
    assertTrue("Contract failed: equals-hashcode on var6 and var4", var6.equals(var4) ? var6.hashCode() == var4.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var6 and var5
    assertTrue("Contract failed: equals-hashcode on var6 and var5", var6.equals(var5) ? var6.hashCode() == var5.hashCode() : true);

  }

  public void test3() throws Throwable {

    java.lang.Double var0 = new java.lang.Double((-1.0d));
    double[] var1 = new double[] { var0};
    org.apache.commons.math.linear.OpenMapRealVector var2 = new org.apache.commons.math.linear.OpenMapRealVector(var1);
    int var3 = var2.getDimension();
    java.lang.Double var4 = new java.lang.Double(0.0d);
    double[] var5 = new double[] { var0, var4, var0};
    org.apache.commons.math.linear.ArrayRealVector var6 = new org.apache.commons.math.linear.ArrayRealVector(var5);
    java.lang.Double var7 = new java.lang.Double(100.0d);
    org.apache.commons.math.linear.RealVector var8 = var6.mapPowToSelf((double)var7);
    org.apache.commons.math.linear.ArrayRealVector var9 = new org.apache.commons.math.linear.ArrayRealVector(var3, var0);
    org.apache.commons.math.linear.ArrayRealVector var10 = new org.apache.commons.math.linear.ArrayRealVector(var5);
    org.apache.commons.math.linear.RealVector var11 = var10.mapDivide((double)var0);
    org.apache.commons.math.linear.RealVector var12 = var9.projection((org.apache.commons.math.linear.RealVector)var2);
    
    // Checks the contract:  equals-hashcode on var8 and var11
    assertTrue("Contract failed: equals-hashcode on var8 and var11", var8.equals(var11) ? var8.hashCode() == var11.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var11 and var8
    assertTrue("Contract failed: equals-hashcode on var11 and var8", var11.equals(var8) ? var11.hashCode() == var8.hashCode() : true);

  }

}
