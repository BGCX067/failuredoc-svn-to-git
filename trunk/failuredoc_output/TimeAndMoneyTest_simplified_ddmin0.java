
import junit.framework.*;

public class TimeAndMoneyTest_simplified_ddmin0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(TimeAndMoneyTest_simplified_ddmin0.class);
  }

  public void test1() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(0);
    java.lang.Integer var1 = new java.lang.Integer(100);
    java.lang.Integer var2 = new java.lang.Integer(1);
    java.lang.Integer var3 = new java.lang.Integer(1);
    java.lang.Integer var4 = new java.lang.Integer(100);
    java.lang.Integer var5 = new java.lang.Integer((-1));
    java.lang.Integer var6 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var7 = com.domainlanguage.time.TimePoint.atGMT((int)var0, (int)var1, (int)var2, (int)var3, (int)var4, (int)var5, (int)var6);
    com.domainlanguage.time.TimeInterval var8 = com.domainlanguage.time.TimeInterval.everFrom(var7);
    
    // Checks the contract:  var8.equals(var8)
    assertTrue("Contract failed: var8.equals(var8)", var8.equals(var8));

  }

  public void test2() throws Throwable {

    java.lang.Integer var0 = new java.lang.Integer(100);
    com.domainlanguage.time.Duration var1 = com.domainlanguage.time.Duration.days((int)var0);
    java.lang.Integer var2 = new java.lang.Integer(100);
    com.domainlanguage.time.Duration var3 = com.domainlanguage.time.Duration.years((int)var2);
    java.lang.Integer var4 = new java.lang.Integer(0);
    java.lang.Integer var5 = new java.lang.Integer(100);
    java.lang.Integer var6 = new java.lang.Integer(1);
    java.lang.Integer var7 = new java.lang.Integer(1);
    java.lang.Integer var8 = new java.lang.Integer(100);
    java.lang.Integer var9 = new java.lang.Integer((-1));
    java.lang.Integer var10 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var11 = com.domainlanguage.time.TimePoint.atGMT((int)var4, (int)var5, (int)var6, (int)var7, (int)var8, (int)var9, (int)var10);
    java.lang.Integer var12 = new java.lang.Integer(0);
    java.lang.Integer var13 = new java.lang.Integer(100);
    java.lang.Integer var14 = new java.lang.Integer(1);
    java.lang.Integer var15 = new java.lang.Integer(1);
    java.lang.Integer var16 = new java.lang.Integer(100);
    java.lang.Integer var17 = new java.lang.Integer((-1));
    java.lang.Integer var18 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var19 = com.domainlanguage.time.TimePoint.atGMT((int)var12, (int)var13, (int)var14, (int)var15, (int)var16, (int)var17, (int)var18);
    java.lang.Integer var20 = new java.lang.Integer(100);
    com.domainlanguage.time.Duration var21 = com.domainlanguage.time.Duration.years((int)var20);
    java.lang.Integer var22 = new java.lang.Integer(0);
    java.lang.Integer var23 = new java.lang.Integer(100);
    java.lang.Integer var24 = new java.lang.Integer(1);
    java.lang.Integer var25 = new java.lang.Integer(1);
    java.lang.Integer var26 = new java.lang.Integer(100);
    java.lang.Integer var27 = new java.lang.Integer((-1));
    java.lang.Integer var28 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var29 = com.domainlanguage.time.TimePoint.atGMT((int)var22, (int)var23, (int)var24, (int)var25, (int)var26, (int)var27, (int)var28);
    java.lang.Integer var30 = new java.lang.Integer(100);
    com.domainlanguage.time.Duration var31 = com.domainlanguage.time.Duration.years((int)var30);
    java.lang.Integer var32 = new java.lang.Integer(0);
    java.lang.Integer var33 = new java.lang.Integer(100);
    java.lang.Integer var34 = new java.lang.Integer(1);
    java.lang.Integer var35 = new java.lang.Integer(1);
    java.lang.Integer var36 = new java.lang.Integer(100);
    java.lang.Integer var37 = new java.lang.Integer((-1));
    java.lang.Integer var38 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var39 = com.domainlanguage.time.TimePoint.atGMT((int)var32, (int)var33, (int)var34, (int)var35, (int)var36, (int)var37, (int)var38);
    com.domainlanguage.time.TimeInterval var40 = var31.startingFrom(var39);
    boolean var41 = var29.isAfter(var40);
    java.lang.Integer var42 = new java.lang.Integer(0);
    java.lang.Integer var43 = new java.lang.Integer(100);
    java.lang.Integer var44 = new java.lang.Integer(1);
    java.lang.Integer var45 = new java.lang.Integer(1);
    java.lang.Integer var46 = new java.lang.Integer(100);
    java.lang.Integer var47 = new java.lang.Integer((-1));
    java.lang.Integer var48 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var49 = com.domainlanguage.time.TimePoint.atGMT((int)var42, (int)var43, (int)var44, (int)var45, (int)var46, (int)var47, (int)var48);
    java.lang.Double var50 = new java.lang.Double(0.0d);
    java.lang.Integer var51 = new java.lang.Integer(0);
    java.lang.Integer var52 = new java.lang.Integer(100);
    java.lang.Integer var53 = new java.lang.Integer(1);
    java.lang.Integer var54 = new java.lang.Integer(1);
    java.lang.Integer var55 = new java.lang.Integer(100);
    java.lang.Integer var56 = new java.lang.Integer((-1));
    java.lang.Integer var57 = new java.lang.Integer((-1));
    com.domainlanguage.time.Duration var58 = com.domainlanguage.time.Duration.days((int)var51);
    com.domainlanguage.time.Duration var59 = var1.minus(var58);
    
    // Checks the contract:  equals-hashcode on var1 and var59
    assertTrue("Contract failed: equals-hashcode on var1 and var59", var1.equals(var59) ? var1.hashCode() == var59.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var59 and var1
    assertTrue("Contract failed: equals-hashcode on var59 and var1", var59.equals(var1) ? var59.hashCode() == var1.hashCode() : true);

  }

}
