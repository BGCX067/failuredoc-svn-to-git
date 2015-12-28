import junit.framework.TestCase;
public class TreeSetFailedDocumented extends TestCase {
  public void test0() throws Throwable {
        java.lang.Integer var0 = new java.lang.Integer(1);
        java.util.ArrayList var1 = new java.util.ArrayList(var0);
        //Test passes if var2 implements Comparable
        java.lang.Object var2 = new java.lang.Object();
        //Test passes if var3 is set to: false
        boolean var3 = var1.add(var2);
        //Test passes if var4  is changed to type: java.util.Collections$UnmodifiableSortedSet, with value: []
        java.util.TreeSet var4 = new java.util.TreeSet((java.util.Collection)var1);
        //Test passes if var5  uses type: java.util.Collections$EmptySet, with value: []
        java.util.Set var5 = java.util.Collections.synchronizedSet((java.util.Set)var4);
        
        // Checks the contract:  var5.equals(var5)
        assertTrue("Contract failed: var5.equals(var5)", var5.equals(var5));
  }
}
