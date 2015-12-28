
import junit.framework.*;

public class TimeAndMoneyTest_simplified0 extends TestCase {

  // Runs all the tests in this file.
  public static void main(String[] args) {
    junit.textui.TestRunner.run(TimeAndMoneyTest_simplified0.class);
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
    java.lang.Integer var2 = new java.lang.Integer(0);
    com.domainlanguage.time.Duration var3 = com.domainlanguage.time.Duration.days((int)var2);
    com.domainlanguage.time.Duration var4 = var1.minus(var3);
    
    // Checks the contract:  equals-hashcode on var1 and var4
    assertTrue("Contract failed: equals-hashcode on var1 and var4", var1.equals(var4) ? var1.hashCode() == var4.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on var4 and var1
    assertTrue("Contract failed: equals-hashcode on var4 and var1", var4.equals(var1) ? var4.hashCode() == var1.hashCode() : true);

  }

}
