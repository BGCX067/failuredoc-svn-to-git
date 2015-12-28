import junit.framework.TestCase;
public class JDKRealRport extends TestCase {
  public void test0() throws Throwable {
        java.util.ArrayList var0 = new java.util.ArrayList();
        java.lang.Integer var1 = new java.lang.Integer(1);
        boolean var2 = var0.add((java.lang.Object)var1);
        //Test passes if var3 is: (int)>0
        java.lang.Long var3 = new java.lang.Long((-1L));
        //Test passes if var4 is set to: false
        boolean var4 = var0.add((java.lang.Object)var3);
        java.lang.Integer[] var5 = new java.lang.Integer[] { };
        // Checks that no java.lang.ArrayStoreException is thrown.
        try {
          java.lang.Object[] var6 = var0.toArray((java.lang.Object[])var5);
        } catch (java.lang.ArrayStoreException e) {
          fail("Statement throw java.lang.ArrayStoreException.");
        }
  }
}
