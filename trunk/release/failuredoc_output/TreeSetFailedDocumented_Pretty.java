import java.util.ArrayList;
import java.util.TreeSet;
import java.lang.Object;
import java.util.Collections;
import java.util.Set;
import java.util.Collection;

import junit.framework.TestCase;
public class TreeSetFailedDocumented_Pretty extends TestCase { 
  public void test0() {
    Integer i0 = new Integer(1);
    ArrayList arrayList0 = new ArrayList(i0);
    //Test passes if obj0 implements Comparable
    Object obj0 = new Object();
    //Test passes if b0 is set to: false
    boolean b0 = arrayList0.add(obj0);
    //Test passes if treeSet0  is changed to type: java.util.Collections$UnmodifiableSortedSet, with value: []
    TreeSet treeSet0 = new TreeSet((Collection)arrayList0);
    //Test passes if set0  uses type: java.util.Collections$EmptySet, with value: []
    Set set0 = Collections.synchronizedSet((Set)treeSet0);
    
    // Checks the contract:  set0.equals(set0)
    assertTrue("Contract failed: set0.equals(set0)", set0.equals(set0));
  }

}