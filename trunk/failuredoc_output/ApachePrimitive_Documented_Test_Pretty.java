import org.apache.commons.collections.primitives.RandomAccessCharList;
import org.apache.commons.collections.primitives.ShortList;
import org.apache.commons.collections.primitives.CharCollection;
import org.apache.commons.collections.primitives.CharList;
import org.apache.commons.collections.primitives.AbstractCharCollection;
import java.util.List;
import org.apache.commons.collections.primitives.adapters.CharListList;
import java.lang.String;
import org.apache.commons.collections.primitives.DoubleList;
import org.apache.commons.collections.primitives.adapters.ListShortList;
import org.apache.commons.collections.primitives.ArrayCharList;

import junit.framework.TestCase;
public class ApachePrimitive_Documented_Test_Pretty extends TestCase { 
  public void test0() {
    Integer i0 = new Integer(10);
    ArrayCharList arrayCharList0 = new ArrayCharList(i0);
    Integer i1 = new Integer(10);
    ArrayCharList arrayCharList1 = new ArrayCharList(i1);
    Character char0 = new Character('#');
    int i2 = arrayCharList1.indexOf((char)char0);
    Character char1 = new Character('4');
    boolean b0 = arrayCharList1.contains((char)char1);
    CharListList charListList0 = new CharListList((CharList)arrayCharList1);
    boolean b1 = arrayCharList0.retainAll((CharCollection)arrayCharList1);
    String str0 = arrayCharList1.toString();
    char[] char_array0 = new char[] { };
    char[] char_array1 = arrayCharList1.toArray(char_array0);
    ArrayCharList arrayCharList2 = new ArrayCharList();
    Integer i3 = new Integer(10);
    ArrayCharList arrayCharList3 = new ArrayCharList(i3);
    Character char2 = new Character('#');
    int i4 = arrayCharList3.indexOf((char)char2);
    int i5 = arrayCharList2.indexOf((char)char2);
    boolean b2 = arrayCharList1.add((char)char2);
    Integer i6 = new Integer(10);
    ArrayCharList arrayCharList4 = new ArrayCharList(i6);
    Integer i7 = new Integer(10);
    ArrayCharList arrayCharList5 = new ArrayCharList(i7);
    Character char3 = new Character('#');
    int i8 = arrayCharList5.indexOf((char)char3);
    Character char4 = new Character('4');
    boolean b3 = arrayCharList5.contains((char)char4);
    CharListList charListList1 = new CharListList((CharList)arrayCharList5);
    boolean b4 = arrayCharList4.retainAll((CharCollection)arrayCharList5);
    List list0 = org.apache.commons.collections.primitives.adapters.CharListList.wrap((CharList)arrayCharList5);
    Integer i9 = new Integer(10);
    ArrayCharList arrayCharList6 = new ArrayCharList(i9);
    Character char5 = new Character('#');
    int i10 = arrayCharList6.indexOf((char)char5);
    boolean b5 = arrayCharList5.containsAll((CharCollection)arrayCharList6);
    Integer i11 = new Integer(10);
    ArrayCharList arrayCharList7 = new ArrayCharList(i11);
    Integer i12 = new Integer(10);
    ArrayCharList arrayCharList8 = new ArrayCharList(i12);
    Character char6 = new Character('#');
    int i13 = arrayCharList8.indexOf((char)char6);
    Character char7 = new Character('4');
    boolean b6 = arrayCharList8.contains((char)char7);
    CharListList charListList2 = new CharListList((CharList)arrayCharList8);
    boolean b7 = arrayCharList7.retainAll((CharCollection)arrayCharList8);
    boolean b8 = arrayCharList6.removeAll((CharCollection)arrayCharList8);
    int i14 = arrayCharList8.size();
    char char8 = arrayCharList1.get((int)i14);
    Integer i15 = new Integer(10);
    ArrayCharList arrayCharList9 = new ArrayCharList(i15);
    Integer i16 = new Integer(10);
    ArrayCharList arrayCharList10 = new ArrayCharList(i16);
    Character char9 = new Character('#');
    int i17 = arrayCharList10.indexOf((char)char9);
    Character char10 = new Character('4');
    boolean b9 = arrayCharList10.contains((char)char10);
    CharListList charListList3 = new CharListList((CharList)arrayCharList10);
    boolean b10 = arrayCharList9.retainAll((CharCollection)arrayCharList10);
    String str1 = arrayCharList10.toString();
    char[] char_array2 = new char[] { };
    char[] char_array3 = arrayCharList10.toArray(char_array2);
    ArrayCharList arrayCharList11 = new ArrayCharList();
    Integer i18 = new Integer(10);
    ArrayCharList arrayCharList12 = new ArrayCharList(i18);
    Character char11 = new Character('#');
    int i19 = arrayCharList12.indexOf((char)char11);
    int i20 = arrayCharList11.indexOf((char)char11);
    boolean b11 = arrayCharList10.add((char)char11);
    boolean b12 = arrayCharList1.contains((char)char11);
    CharListList charListList4 = new CharListList((CharList)arrayCharList1);
    ListShortList listShortList0 = new ListShortList((List)charListList4);
    //Test passes if shortList0 is type: class org.apache.commons.collections.primitives.adapters.ListShortList, an example value: [1.0].
        //Current value: #
    ShortList shortList0 = org.apache.commons.collections.primitives.ShortCollections.unmodifiableShortList((ShortList)listShortList0);
    
    // Checks the contract:  shortList0.equals(shortList0)
    assertTrue("Contract failed: shortList0.equals(shortList0)", shortList0.equals(shortList0));
  }
  public void test1() {
    Integer i0 = new Integer(10);
    ArrayCharList arrayCharList0 = new ArrayCharList(i0);
    Integer i1 = new Integer(10);
    ArrayCharList arrayCharList1 = new ArrayCharList(i1);
    Character char0 = new Character('#');
    int i2 = arrayCharList1.indexOf((char)char0);
    Character char1 = new Character('4');
    boolean b0 = arrayCharList1.contains((char)char1);
    CharListList charListList0 = new CharListList((CharList)arrayCharList1);
    boolean b1 = arrayCharList0.retainAll((CharCollection)arrayCharList1);
    String str0 = arrayCharList1.toString();
    char[] char_array0 = new char[] { };
    char[] char_array1 = arrayCharList1.toArray(char_array0);
    ArrayCharList arrayCharList2 = new ArrayCharList();
    Integer i3 = new Integer(10);
    ArrayCharList arrayCharList3 = new ArrayCharList(i3);
    Character char2 = new Character('#');
    int i4 = arrayCharList3.indexOf((char)char2);
    int i5 = arrayCharList2.indexOf((char)char2);
    boolean b2 = arrayCharList1.add((char)char2);
    Integer i6 = new Integer(10);
    ArrayCharList arrayCharList4 = new ArrayCharList(i6);
    Integer i7 = new Integer(10);
    ArrayCharList arrayCharList5 = new ArrayCharList(i7);
    Character char3 = new Character('#');
    int i8 = arrayCharList5.indexOf((char)char3);
    Character char4 = new Character('4');
    boolean b3 = arrayCharList5.contains((char)char4);
    CharListList charListList1 = new CharListList((CharList)arrayCharList5);
    boolean b4 = arrayCharList4.retainAll((CharCollection)arrayCharList5);
    List list0 = org.apache.commons.collections.primitives.adapters.CharListList.wrap((CharList)arrayCharList5);
    Integer i9 = new Integer(10);
    ArrayCharList arrayCharList6 = new ArrayCharList(i9);
    Character char5 = new Character('#');
    int i10 = arrayCharList6.indexOf((char)char5);
    boolean b5 = arrayCharList5.containsAll((CharCollection)arrayCharList6);
    Integer i11 = new Integer(10);
    ArrayCharList arrayCharList7 = new ArrayCharList(i11);
    Integer i12 = new Integer(10);
    ArrayCharList arrayCharList8 = new ArrayCharList(i12);
    Character char6 = new Character('#');
    int i13 = arrayCharList8.indexOf((char)char6);
    Character char7 = new Character('4');
    boolean b6 = arrayCharList8.contains((char)char7);
    CharListList charListList2 = new CharListList((CharList)arrayCharList8);
    boolean b7 = arrayCharList7.retainAll((CharCollection)arrayCharList8);
    boolean b8 = arrayCharList6.removeAll((CharCollection)arrayCharList8);
    int i14 = arrayCharList8.size();
    char char8 = arrayCharList1.get((int)i14);
    Integer i15 = new Integer(10);
    ArrayCharList arrayCharList9 = new ArrayCharList(i15);
    Integer i16 = new Integer(10);
    ArrayCharList arrayCharList10 = new ArrayCharList(i16);
    Character char9 = new Character('#');
    int i17 = arrayCharList10.indexOf((char)char9);
    Character char10 = new Character('4');
    boolean b9 = arrayCharList10.contains((char)char10);
    CharListList charListList3 = new CharListList((CharList)arrayCharList10);
    boolean b10 = arrayCharList9.retainAll((CharCollection)arrayCharList10);
    String str1 = arrayCharList10.toString();
    char[] char_array2 = new char[] { };
    char[] char_array3 = arrayCharList10.toArray(char_array2);
    ArrayCharList arrayCharList11 = new ArrayCharList();
    Integer i18 = new Integer(10);
    ArrayCharList arrayCharList12 = new ArrayCharList(i18);
    Character char11 = new Character('#');
    int i19 = arrayCharList12.indexOf((char)char11);
    int i20 = arrayCharList11.indexOf((char)char11);
    boolean b11 = arrayCharList10.add((char)char11);
    boolean b12 = arrayCharList1.contains((char)char11);
    CharListList charListList4 = new CharListList((CharList)arrayCharList1);
    ListShortList listShortList0 = new ListShortList((List)charListList4);
    ShortList shortList0 = org.apache.commons.collections.primitives.adapters.ListShortList.wrap((List)charListList4);
    //Test passes if doubleList0 is: (org.apache.commons.collections.primitives.adapters.ListDoubleList).
        //Current value: #
    DoubleList doubleList0 = org.apache.commons.collections.primitives.adapters.ListDoubleList.wrap((List)charListList4);
    List list1 = org.apache.commons.collections.primitives.adapters.DoubleListList.wrap(doubleList0);
    //Test passes if doubleList1 is type: class org.apache.commons.collections.primitives.adapters.ListDoubleList, an example value: .
        //Current value: #
    DoubleList doubleList1 = org.apache.commons.collections.primitives.DoubleCollections.unmodifiableDoubleList(doubleList0);
    
    // Checks the contract:  doubleList1.equals(doubleList1)
    assertTrue("Contract failed: doubleList1.equals(doubleList1)", doubleList1.equals(doubleList1));
  }

}