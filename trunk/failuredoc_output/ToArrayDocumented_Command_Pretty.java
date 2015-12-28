import java.util.ArrayList;
import java.lang.Object;
import java.lang.Integer;

import junit.framework.TestCase;
public class ToArrayDocumented_Command_Pretty extends TestCase { 
  public void test0() {
    ArrayList arrayList0 = new ArrayList();
    Integer i0 = new Integer(1);
    boolean b0 = arrayList0.add((Object)i0);
    //Test passes if long0 is type: class java.lang.Integer
    Long long0 = new Long((-1L));
    //Test passes if b1 is set to: false
    boolean b1 = arrayList0.add((Object)long0);
    //Test passes if i_array0  is changed to type: [Ljava.lang.Object;, with value: [Ljava.lang.Object;@18d0720
    Integer[] i_array0 = new Integer[] { };
    // Checks that no java.lang.ArrayStoreException is thrown.
    try {
      Object[] obj_array0 = arrayList0.toArray((Object[])i_array0);
    } catch (java.lang.ArrayStoreException e) {
      fail("Statement throw java.lang.ArrayStoreException.");
    }
  }

}