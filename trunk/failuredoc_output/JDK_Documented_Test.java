import junit.framework.TestCase;
public class JDK_Documented_Test extends TestCase {
  public void test0() throws Throwable {
        //Test passes if var0 is type: class java.lang.Integer
        java.lang.Byte var0 = new java.lang.Byte((byte)1);
        java.util.List var1 = java.util.Collections.singletonList((java.lang.Object)var0);
        java.util.TreeSet var2 = new java.util.TreeSet((java.util.Collection)var1);
        java.lang.Object[] var3 = var2.toArray();
        java.lang.Long var4 = new java.lang.Long(10L);
        java.lang.Boolean var5 = new java.lang.Boolean(true);
        java.util.NavigableSet var6 = var2.headSet((java.lang.Object)var4, (boolean)var5);
        java.util.NavigableSet var7 = var2.descendingSet();
        java.lang.Integer var8 = new java.lang.Integer(10);
        //Test passes if var9  is changed to type: Byte, with value: -1
        java.lang.Integer var9 = new java.lang.Integer(0);
        java.util.List var10 = java.util.Collections.nCopies((int)var8, (java.lang.Object)var9);
        //Test passes if var11 is type: class java.lang.Byte
        java.lang.Object var11 = java.util.Collections.max((java.util.Collection)var10);
        java.lang.Byte var12 = new java.lang.Byte((byte)1);
        java.util.List var13 = java.util.Collections.singletonList((java.lang.Object)var12);
        java.util.TreeSet var14 = new java.util.TreeSet((java.util.Collection)var13);
        java.lang.Object[] var15 = var14.toArray();
        java.lang.Long var16 = new java.lang.Long(10L);
        java.lang.Boolean var17 = new java.lang.Boolean(true);
        java.util.NavigableSet var18 = var14.headSet((java.lang.Object)var16, (boolean)var17);
        //Test passes if var19  is changed to type: java.util.Collections$SynchronizedSortedSet, with value: []
        java.util.NavigableSet var19 = var2.tailSet(var11, (boolean)var17);
        java.util.SortedSet var20 = java.util.Collections.synchronizedSortedSet((java.util.SortedSet)var19);
        
        // Checks the contract:  var20.equals(var20)
        assertTrue("Contract failed: var20.equals(var20)", var20.equals(var20));
  }
}
