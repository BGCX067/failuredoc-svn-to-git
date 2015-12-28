import java.util.NavigableSet;
import java.util.TreeSet;
import java.lang.Object;
import java.util.AbstractCollection;
import java.util.List;
import java.util.SortedSet;
import java.util.Collections;
import java.util.Collection;

import junit.framework.TestCase;
public class JDK_Documented_Test_Pretty extends TestCase { 
  public void test0() {
    //Test passes if byte0 is type: class java.lang.Integer
    Byte byte0 = new Byte((byte)1);
    List list0 = Collections.singletonList((Object)byte0);
    TreeSet treeSet0 = new TreeSet((Collection)list0);
    Object[] obj_array0 = treeSet0.toArray();
    Long long0 = new Long(10L);
    Boolean b0 = new Boolean(true);
    NavigableSet navigableSet0 = treeSet0.headSet((Object)long0, (boolean)b0);
    NavigableSet navigableSet1 = treeSet0.descendingSet();
    Integer i0 = new Integer(10);
    //Test passes if i1  is changed to type: Byte, with value: -1
    Integer i1 = new Integer(0);
    List list1 = Collections.nCopies((int)i0, (Object)i1);
    //Test passes if obj0 is type: class java.lang.Byte
    Object obj0 = Collections.max((Collection)list1);
    Byte byte1 = new Byte((byte)1);
    List list2 = Collections.singletonList((Object)byte1);
    TreeSet treeSet1 = new TreeSet((Collection)list2);
    Object[] obj_array1 = treeSet1.toArray();
    Long long1 = new Long(10L);
    Boolean b1 = new Boolean(true);
    NavigableSet navigableSet2 = treeSet1.headSet((Object)long1, (boolean)b1);
    //Test passes if navigableSet3  is changed to type: java.util.Collections$SynchronizedSortedSet, with value: []
    NavigableSet navigableSet3 = treeSet0.tailSet(obj0, (boolean)b1);
    SortedSet sortedSet0 = Collections.synchronizedSortedSet((SortedSet)navigableSet3);
    
    // Checks the contract:  sortedSet0.equals(sortedSet0)
    assertTrue("Contract failed: sortedSet0.equals(sortedSet0)", sortedSet0.equals(sortedSet0));
  }

}