import org.apache.commons.math.linear.AbstractRealVector;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexField;
import org.apache.commons.math.dfp.DfpField;
import org.apache.commons.math.linear.OpenMapRealVector;
import org.apache.commons.math.util.MathUtils;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.dfp.Dfp;

import junit.framework.TestCase;
public class ApacheMath_Documented_Test_Pretty extends TestCase { 
  public void test0() {
    Double d0 = new Double((-1.0d));
    Double d1 = new Double(10.0d);
    Double d2 = new Double(100.0d);
    int i0 = MathUtils.compareTo((double)d0, (double)d1, (double)d2);
    Integer i1 = new Integer(0);
    Long long0 = new Long(100L);
    int i2 = MathUtils.pow((int)i1, (long)long0);
    int i3 = MathUtils.mulAndCheck((int)i0, (int)i1);
    DfpField dfpField0 = new DfpField(i0);
    Dfp dfp0 = dfpField0.getOne();
    Double d3 = new Double((-1.0d));
    Double d4 = new Double(10.0d);
    Double d5 = new Double(100.0d);
    int i4 = MathUtils.compareTo((double)d3, (double)d4, (double)d5);
    Integer i5 = new Integer(0);
    Long long1 = new Long(100L);
    int i6 = MathUtils.pow((int)i5, (long)long1);
    int i7 = MathUtils.mulAndCheck((int)i4, (int)i5);
    DfpField dfpField1 = new DfpField(i4);
    Dfp dfp1 = dfpField1.getOne();
    Dfp dfp2 = dfpField0.newDfp(dfp1);
    Double d6 = new Double((-1.0d));
    Double d7 = new Double(10.0d);
    Double d8 = new Double(100.0d);
    int i8 = MathUtils.compareTo((double)d6, (double)d7, (double)d8);
    Integer i9 = new Integer(0);
    Long long2 = new Long(100L);
    int i10 = MathUtils.pow((int)i9, (long)long2);
    int i11 = MathUtils.mulAndCheck((int)i8, (int)i9);
    DfpField dfpField2 = new DfpField(i8);
    Dfp dfp3 = dfpField2.getOne();
    boolean b0 = dfp1.greaterThan(dfp3);
    Double d9 = new Double((-1.0d));
    Double d10 = new Double(10.0d);
    Double d11 = new Double(100.0d);
    int i12 = MathUtils.compareTo((double)d9, (double)d10, (double)d11);
    Integer i13 = new Integer(0);
    Long long3 = new Long(100L);
    int i14 = MathUtils.pow((int)i13, (long)long3);
    int i15 = MathUtils.mulAndCheck((int)i12, (int)i13);
    DfpField dfpField3 = new DfpField(i12);
    Dfp dfp4 = dfpField3.getOne();
    Dfp dfp5 = dfpField3.getOne();
    Dfp dfp6 = dfp1.subtract(dfp5);
    Double d12 = new Double((-1.0d));
    Double d13 = new Double(10.0d);
    Double d14 = new Double(100.0d);
    int i16 = MathUtils.compareTo((double)d12, (double)d13, (double)d14);
    Integer i17 = new Integer(0);
    Long long4 = new Long(100L);
    int i18 = MathUtils.pow((int)i17, (long)long4);
    int i19 = MathUtils.mulAndCheck((int)i16, (int)i17);
    DfpField dfpField4 = new DfpField(i16);
    Dfp dfp7 = dfpField4.getOne();
    Double d15 = new Double((-1.0d));
    Double d16 = new Double(10.0d);
    Double d17 = new Double(100.0d);
    int i20 = MathUtils.compareTo((double)d15, (double)d16, (double)d17);
    Integer i21 = new Integer(0);
    Long long5 = new Long(100L);
    int i22 = MathUtils.pow((int)i21, (long)long5);
    int i23 = MathUtils.mulAndCheck((int)i20, (int)i21);
    DfpField dfpField5 = new DfpField(i20);
    Dfp dfp8 = dfpField5.getOne();
    Double d18 = new Double((-1.0d));
    Double d19 = new Double(10.0d);
    Double d20 = new Double(100.0d);
    int i24 = MathUtils.compareTo((double)d18, (double)d19, (double)d20);
    Integer i25 = new Integer(0);
    Long long6 = new Long(100L);
    int i26 = MathUtils.pow((int)i25, (long)long6);
    int i27 = MathUtils.mulAndCheck((int)i24, (int)i25);
    DfpField dfpField6 = new DfpField(i24);
    Dfp dfp9 = dfpField6.getOne();
    Dfp dfp10 = dfpField5.newDfp(dfp9);
    Double d21 = new Double((-1.0d));
    Double d22 = new Double(10.0d);
    Double d23 = new Double(100.0d);
    int i28 = MathUtils.compareTo((double)d21, (double)d22, (double)d23);
    Integer i29 = new Integer(0);
    Long long7 = new Long(100L);
    int i30 = MathUtils.pow((int)i29, (long)long7);
    int i31 = MathUtils.mulAndCheck((int)i28, (int)i29);
    DfpField dfpField7 = new DfpField(i28);
    Dfp dfp11 = dfpField7.getOne();
    boolean b1 = dfp9.greaterThan(dfp11);
    //Test passes if dfp12 is type: org.apache.commons.math.dfp.Dfp and its fields: "mant" has value: non-empty array, and "sign" has value: (byte)!=0, and "exp" has value: (int)>0, and "nans" has value: (byte)0
    Dfp dfp12 = dfp7.remainder(dfp11);
    Dfp dfp13 = dfp1.remainder(dfp12);
    
    // Checks the contract:  dfp13.equals(dfp13)
    assertTrue("Contract failed: dfp13.equals(dfp13)", dfp13.equals(dfp13));
  }
  public void test1() {
    ComplexField complexField0 = ComplexField.getInstance();
    Complex complex0 = complexField0.getZero();
    Complex complex1 = complex0.sinh();
    ComplexField complexField1 = complex0.getField();
    ComplexField complexField2 = ComplexField.getInstance();
    Complex complex2 = complexField2.getZero();
    Complex complex3 = complex2.sinh();
    Complex complex4 = complex0.add(complex3);
    Complex complex5 = complex3.asin();
    
    // Checks the contract:  equals-hashcode on complex0 and complex5
    assertTrue("Contract failed: equals-hashcode on complex0 and complex5", complex0.equals(complex5) ? complex0.hashCode() == complex5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complecomplex5 and complex5
    assertTrue("Contract failed: equals-hashcode on complecomplex5 and complex5", complex5.equals(complex5) ? complex5.hashCode() == complex5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex2 and complex5
    assertTrue("Contract failed: equals-hashcode on complex2 and complex5", complex2.equals(complex5) ? complex2.hashCode() == complex5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex3 and complex5
    assertTrue("Contract failed: equals-hashcode on complex3 and complex5", complex3.equals(complex5) ? complex3.hashCode() == complex5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex4 and complex5
    assertTrue("Contract failed: equals-hashcode on complex4 and complex5", complex4.equals(complex5) ? complex4.hashCode() == complex5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex5 and complex0
    assertTrue("Contract failed: equals-hashcode on complex5 and complex0", complex5.equals(complex0) ? complex5.hashCode() == complex0.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex5 and complex1
    assertTrue("Contract failed: equals-hashcode on complex5 and complex1", complex5.equals(complex1) ? complex5.hashCode() == complex1.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex5 and complex2
    assertTrue("Contract failed: equals-hashcode on complex5 and complex2", complex5.equals(complex2) ? complex5.hashCode() == complex2.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex5 and complex3
    assertTrue("Contract failed: equals-hashcode on complex5 and complex3", complex5.equals(complex3) ? complex5.hashCode() == complex3.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on complex5 and complex4
    assertTrue("Contract failed: equals-hashcode on complex5 and complex4", complex5.equals(complex4) ? complex5.hashCode() == complex4.hashCode() : true);
  }
  public void test2() {
    Double d0 = new Double((-1.0d));
    double[] d_array0 = new double[] { d0};
    OpenMapRealVector openMapRealVector0 = new OpenMapRealVector(d_array0);
    int i0 = openMapRealVector0.getDimension();
    Double d1 = new Double((-1.0d));
    Double d2 = new Double(0.0d);
    Double d3 = new Double((-1.0d));
    double[] d_array1 = new double[] { d1, d2, d3};
    ArrayRealVector arrayRealVector0 = new ArrayRealVector(d_array1);
    Double d4 = new Double((-1.0d));
    //Test passes if d5 is: (double)<0
    Double d5 = new Double(0.0d);
    Double d6 = new Double((-1.0d));
    double[] d_array2 = new double[] { d4, d5, d6};
    ArrayRealVector arrayRealVector1 = new ArrayRealVector(d_array2);
    RealVector realVector0 = arrayRealVector0.ebeDivide((RealVector)arrayRealVector1);
    Double d7 = new Double((-1.0d));
    Double d8 = new Double(10.0d);
    Double d9 = new Double(100.0d);
    int i1 = MathUtils.compareTo((double)d7, (double)d8, (double)d9);
    //Test passes if realVector1 is type: org.apache.commons.math.linear.ArrayRealVector and its fields: "data" has value: empty array
    RealVector realVector1 = arrayRealVector1.mapPowToSelf((double)d9);
    ArrayRealVector arrayRealVector2 = new ArrayRealVector(i0, d9);
    Double d10 = new Double((-1.0d));
    double[] d_array3 = new double[] { d10};
    OpenMapRealVector openMapRealVector1 = new OpenMapRealVector(d_array3);
    Double d11 = new Double((-1.0d));
    double[] d_array4 = new double[] { d11};
    OpenMapRealVector openMapRealVector2 = new OpenMapRealVector(d_array4);
    OpenMapRealVector openMapRealVector3 = openMapRealVector1.add(openMapRealVector2);
    OpenMapRealVector openMapRealVector4 = new OpenMapRealVector(openMapRealVector3);
    Double d12 = new Double((-1.0d));
    Double d13 = new Double(0.0d);
    Double d14 = new Double((-1.0d));
    double[] d_array5 = new double[] { d12, d13, d14};
    ArrayRealVector arrayRealVector3 = new ArrayRealVector(d_array5);
    Double d15 = new Double((-1.0d));
    Double d16 = new Double(0.0d);
    Double d17 = new Double((-1.0d));
    double[] d_array6 = new double[] { d15, d16, d17};
    ArrayRealVector arrayRealVector4 = new ArrayRealVector(d_array6);
    RealVector realVector2 = arrayRealVector3.ebeDivide((RealVector)arrayRealVector4);
    Double d18 = new Double(0.0d);
    Double d19 = new Double((-1.0d));
    Double d20 = new Double((-1.0d));
    int i2 = MathUtils.compareTo((double)d18, (double)d19, (double)d20);
    //Test passes if realVector3 is type: class org.apache.commons.math.linear.AbstractRealVector
    RealVector realVector3 = arrayRealVector3.mapDivide((double)d19);
    RealVector realVector4 = openMapRealVector4.mapSubtractToSelf((double)d19);
    RealVector realVector5 = arrayRealVector2.projection(realVector4);
    
    // Checks the contract:  equals-hashcode on realVector1 and realVector3
    assertTrue("Contract failed: equals-hashcode on realVector1 and realVector3", realVector1.equals(realVector3) ? realVector1.hashCode() == realVector3.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on realVector3 and realVector1
    assertTrue("Contract failed: equals-hashcode on realVector3 and realVector1", realVector3.equals(realVector1) ? realVector3.hashCode() == realVector1.hashCode() : true);
  }

}