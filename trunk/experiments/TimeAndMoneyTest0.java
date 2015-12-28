
import junit.framework.*;

public class TimeAndMoneyTest0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(TimeAndMoneyTest0.class);
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
    com.domainlanguage.time.TimeInterval var12 = var3.startingFrom(var11);
    java.lang.Integer var13 = new java.lang.Integer(0);
    java.lang.Integer var14 = new java.lang.Integer(100);
    java.lang.Integer var15 = new java.lang.Integer(1);
    java.lang.Integer var16 = new java.lang.Integer(1);
    java.lang.Integer var17 = new java.lang.Integer(100);
    java.lang.Integer var18 = new java.lang.Integer((-1));
    java.lang.Integer var19 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var20 = com.domainlanguage.time.TimePoint.atGMT((int)var13, (int)var14, (int)var15, (int)var16, (int)var17, (int)var18, (int)var19);
    java.lang.Integer var21 = new java.lang.Integer(100);
    com.domainlanguage.time.Duration var22 = com.domainlanguage.time.Duration.years((int)var21);
    java.lang.Integer var23 = new java.lang.Integer(0);
    java.lang.Integer var24 = new java.lang.Integer(100);
    java.lang.Integer var25 = new java.lang.Integer(1);
    java.lang.Integer var26 = new java.lang.Integer(1);
    java.lang.Integer var27 = new java.lang.Integer(100);
    java.lang.Integer var28 = new java.lang.Integer((-1));
    java.lang.Integer var29 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var30 = com.domainlanguage.time.TimePoint.atGMT((int)var23, (int)var24, (int)var25, (int)var26, (int)var27, (int)var28, (int)var29);
    com.domainlanguage.time.TimeInterval var31 = var22.startingFrom(var30);
    java.lang.Integer var32 = new java.lang.Integer(100);
    com.domainlanguage.time.Duration var33 = com.domainlanguage.time.Duration.years((int)var32);
    java.lang.Integer var34 = new java.lang.Integer(0);
    java.lang.Integer var35 = new java.lang.Integer(100);
    java.lang.Integer var36 = new java.lang.Integer(1);
    java.lang.Integer var37 = new java.lang.Integer(1);
    java.lang.Integer var38 = new java.lang.Integer(100);
    java.lang.Integer var39 = new java.lang.Integer((-1));
    java.lang.Integer var40 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var41 = com.domainlanguage.time.TimePoint.atGMT((int)var34, (int)var35, (int)var36, (int)var37, (int)var38, (int)var39, (int)var40);
    com.domainlanguage.time.TimeInterval var42 = var33.startingFrom(var41);
    com.domainlanguage.time.TimePoint var43 = var42.start();
    boolean var44 = var30.isAfter(var42);
    java.lang.Integer var45 = new java.lang.Integer(0);
    java.lang.Integer var46 = new java.lang.Integer(100);
    java.lang.Integer var47 = new java.lang.Integer(1);
    java.lang.Integer var48 = new java.lang.Integer(1);
    java.lang.Integer var49 = new java.lang.Integer(100);
    java.lang.Integer var50 = new java.lang.Integer((-1));
    java.lang.Integer var51 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var52 = com.domainlanguage.time.TimePoint.atGMT((int)var45, (int)var46, (int)var47, (int)var48, (int)var49, (int)var50, (int)var51);
    java.lang.Double var53 = new java.lang.Double(0.0d);
    com.domainlanguage.money.Money var54 = com.domainlanguage.money.Money.euros((double)var53);
    java.lang.Short var55 = new java.lang.Short((short)(-1));
    boolean var56 = var54.equals((java.lang.Object)var55);
    com.domainlanguage.time.TimeInterval var57 = new com.domainlanguage.time.TimeInterval(var20, var44, var52, var56);
    com.domainlanguage.time.TimeInterval var58 = com.domainlanguage.time.TimeInterval.over(var11, var52);
    java.lang.Byte var59 = new java.lang.Byte((byte)100);
    boolean var60 = var52.equals((java.lang.Object)var59);
    java.lang.Integer var61 = new java.lang.Integer(0);
    java.lang.Integer var62 = new java.lang.Integer(100);
    java.lang.Integer var63 = new java.lang.Integer(1);
    java.lang.Integer var64 = new java.lang.Integer(1);
    java.lang.Integer var65 = new java.lang.Integer(100);
    java.lang.Integer var66 = new java.lang.Integer((-1));
    java.lang.Integer var67 = new java.lang.Integer((-1));
    com.domainlanguage.time.TimePoint var68 = com.domainlanguage.time.TimePoint.atGMT((int)var61, (int)var62, (int)var63, (int)var64, (int)var65, (int)var66, (int)var67);
    com.domainlanguage.time.Duration var69 = com.domainlanguage.time.Duration.days((int)var61);
    com.domainlanguage.time.TimePoint var70 = var52.plus(var69);
    com.domainlanguage.time.Duration var71 = var1.minus(var69);
    
    // Checks the contract:  equals-hashcode on var1 and var71
    assertTrue("Contract failed: equals-hashcode on var1 and var71", var1.equals(var71) ? var1.hashCode() == var71.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var71 and var1
    assertTrue("Contract failed: equals-hashcode on var71 and var1", var71.equals(var1) ? var71.hashCode() == var1.hashCode() : true);

  }

}
