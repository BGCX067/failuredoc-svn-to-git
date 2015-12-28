import org.apache.commons.collections.set.ListOrderedSet;
import java.lang.Object;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.collection.AbstractCollectionDecorator;
import java.util.Collection;

import junit.framework.TestCase;
public class ApacheListOrderSet_Documented_Test_Pretty extends TestCase { 
  public void test0() {
    ListOrderedSet listOrderedSet0 = new ListOrderedSet();
    List list0 = listOrderedSet0.asList();
    List list1 = listOrderedSet0.asList();
    listOrderedSet0.clear();
    Integer i0 = new Integer((-1));
    ListOrderedSet listOrderedSet1 = new ListOrderedSet();
    Integer i1 = new Integer((-1));
    ListOrderedSet listOrderedSet2 = new ListOrderedSet();
    List list2 = listOrderedSet2.asList();
    ListOrderedSet listOrderedSet3 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet2);
    boolean b0 = listOrderedSet1.addAll((int)i1, (Collection)listOrderedSet2);
    boolean b1 = listOrderedSet0.addAll((int)i0, (Collection)listOrderedSet2);
    ListOrderedSet listOrderedSet4 = new ListOrderedSet();
    List list3 = listOrderedSet4.asList();
    List list4 = listOrderedSet4.asList();
    ListOrderedSet listOrderedSet5 = new ListOrderedSet();
    List list5 = listOrderedSet5.asList();
    ListOrderedSet listOrderedSet6 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet5);
    ListOrderedSet listOrderedSet7 = new ListOrderedSet();
    List list6 = listOrderedSet7.asList();
    ListOrderedSet listOrderedSet8 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet5, list6);
    boolean b2 = listOrderedSet4.containsAll((Collection)list6);
    ListOrderedSet listOrderedSet9 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet0, list6);
    ListOrderedSet listOrderedSet10 = new ListOrderedSet();
    int i2 = listOrderedSet10.size();
    boolean b3 = listOrderedSet0.add((Object)i2);
    //Test passes if s0 is: (java.lang.Integer)0
    Short s0 = new Short((short)1);
    //Test passes if b4 is: (java.lang.Boolean)false
    boolean b4 = listOrderedSet0.add((Object)s0);
    ListOrderedSet listOrderedSet11 = new ListOrderedSet();
    List list7 = listOrderedSet11.asList();
    List list8 = listOrderedSet11.asList();
    listOrderedSet11.clear();
    Integer i3 = new Integer((-1));
    ListOrderedSet listOrderedSet12 = new ListOrderedSet();
    Integer i4 = new Integer((-1));
    ListOrderedSet listOrderedSet13 = new ListOrderedSet();
    List list9 = listOrderedSet13.asList();
    ListOrderedSet listOrderedSet14 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet13);
    boolean b5 = listOrderedSet12.addAll((int)i4, (Collection)listOrderedSet13);
    boolean b6 = listOrderedSet11.addAll((int)i3, (Collection)listOrderedSet13);
    ListOrderedSet listOrderedSet15 = new ListOrderedSet();
    List list10 = listOrderedSet15.asList();
    List list11 = listOrderedSet15.asList();
    ListOrderedSet listOrderedSet16 = new ListOrderedSet();
    List list12 = listOrderedSet16.asList();
    ListOrderedSet listOrderedSet17 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet16);
    ListOrderedSet listOrderedSet18 = new ListOrderedSet();
    List list13 = listOrderedSet18.asList();
    ListOrderedSet listOrderedSet19 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet16, list13);
    boolean b7 = listOrderedSet15.containsAll((Collection)list13);
    ListOrderedSet listOrderedSet20 = org.apache.commons.collections.set.ListOrderedSet.decorate((Set)listOrderedSet11, list13);
    ListOrderedSet listOrderedSet21 = new ListOrderedSet();
    int i5 = listOrderedSet21.size();
    //Test passes if b8 is: (java.lang.Boolean)false
    boolean b8 = listOrderedSet11.add((Object)i5);
    boolean b9 = listOrderedSet0.removeAll((Collection)listOrderedSet11);
    
    // Checks the contract:  equals-hashcode on listOrderedSet0 and listOrderedSet20
    assertTrue("Contract failed: equals-hashcode on listOrderedSet0 and listOrderedSet20", listOrderedSet0.equals(listOrderedSet20) ? listOrderedSet0.hashCode() == listOrderedSet20.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on listOrderedSet9 and listOrderedSet20
    assertTrue("Contract failed: equals-hashcode on listOrderedSet9 and listOrderedSet20", listOrderedSet9.equals(listOrderedSet20) ? listOrderedSet9.hashCode() == listOrderedSet20.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on listOrderedSet11 and listOrderedSet9
    assertTrue("Contract failed: equals-hashcode on listOrderedSet11 and listOrderedSet9", listOrderedSet11.equals(listOrderedSet9) ? listOrderedSet11.hashCode() == listOrderedSet9.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on listOrderedSet20 and listOrderedSet9
    assertTrue("Contract failed: equals-hashcode on listOrderedSet20 and listOrderedSet9", listOrderedSet20.equals(listOrderedSet9) ? listOrderedSet20.hashCode() == listOrderedSet9.hashCode() : true);
    
    // Checks the contract:  equals-symmetric on listOrderedSet0 and listOrderedSet20.
    assertTrue("Contract failed: equals-symmetric on listOrderedSet0 and listOrderedSet20.", listOrderedSet0.equals(listOrderedSet20) ? listOrderedSet20.equals(listOrderedSet0) : true);
    
    // Checks the contract:  equals-symmetric on listOrderedSet11 and listOrderedSet9.
    assertTrue("Contract failed: equals-symmetric on listOrderedSet11 and listOrderedSet9.", listOrderedSet11.equals(listOrderedSet9) ? listOrderedSet9.equals(listOrderedSet11) : true);
  }

}