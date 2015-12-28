import junit.framework.TestCase;
public class ApachePrimitive_Documented_Test extends TestCase {
  public void test0() throws Throwable {
        java.lang.Integer var0 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
        java.lang.Integer var2 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var3 = new org.apache.commons.collections.primitives.ArrayCharList(var2);
        java.lang.Character var4 = new java.lang.Character('#');
        int var5 = var3.indexOf((char)var4);
        java.lang.Character var6 = new java.lang.Character('4');
        boolean var7 = var3.contains((char)var6);
        org.apache.commons.collections.primitives.adapters.CharListList var8 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
        boolean var9 = var1.retainAll((org.apache.commons.collections.primitives.CharCollection)var3);
        java.lang.String var10 = var3.toString();
        char[] var11 = new char[] { };
        char[] var12 = var3.toArray(var11);
        org.apache.commons.collections.primitives.ArrayCharList var13 = new org.apache.commons.collections.primitives.ArrayCharList();
        java.lang.Integer var14 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var15 = new org.apache.commons.collections.primitives.ArrayCharList(var14);
        java.lang.Character var16 = new java.lang.Character('#');
        int var17 = var15.indexOf((char)var16);
        int var18 = var13.indexOf((char)var16);
        boolean var19 = var3.add((char)var16);
        java.lang.Integer var20 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var21 = new org.apache.commons.collections.primitives.ArrayCharList(var20);
        java.lang.Integer var22 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var23 = new org.apache.commons.collections.primitives.ArrayCharList(var22);
        java.lang.Character var24 = new java.lang.Character('#');
        int var25 = var23.indexOf((char)var24);
        java.lang.Character var26 = new java.lang.Character('4');
        boolean var27 = var23.contains((char)var26);
        org.apache.commons.collections.primitives.adapters.CharListList var28 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var23);
        boolean var29 = var21.retainAll((org.apache.commons.collections.primitives.CharCollection)var23);
        java.util.List var30 = org.apache.commons.collections.primitives.adapters.CharListList.wrap((org.apache.commons.collections.primitives.CharList)var23);
        java.lang.Integer var31 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var32 = new org.apache.commons.collections.primitives.ArrayCharList(var31);
        java.lang.Character var33 = new java.lang.Character('#');
        int var34 = var32.indexOf((char)var33);
        boolean var35 = var23.containsAll((org.apache.commons.collections.primitives.CharCollection)var32);
        java.lang.Integer var36 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var37 = new org.apache.commons.collections.primitives.ArrayCharList(var36);
        java.lang.Integer var38 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var39 = new org.apache.commons.collections.primitives.ArrayCharList(var38);
        java.lang.Character var40 = new java.lang.Character('#');
        int var41 = var39.indexOf((char)var40);
        java.lang.Character var42 = new java.lang.Character('4');
        boolean var43 = var39.contains((char)var42);
        org.apache.commons.collections.primitives.adapters.CharListList var44 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var39);
        boolean var45 = var37.retainAll((org.apache.commons.collections.primitives.CharCollection)var39);
        boolean var46 = var32.removeAll((org.apache.commons.collections.primitives.CharCollection)var39);
        int var47 = var39.size();
        char var48 = var3.get((int)var47);
        java.lang.Integer var49 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var50 = new org.apache.commons.collections.primitives.ArrayCharList(var49);
        java.lang.Integer var51 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var52 = new org.apache.commons.collections.primitives.ArrayCharList(var51);
        java.lang.Character var53 = new java.lang.Character('#');
        int var54 = var52.indexOf((char)var53);
        java.lang.Character var55 = new java.lang.Character('4');
        boolean var56 = var52.contains((char)var55);
        org.apache.commons.collections.primitives.adapters.CharListList var57 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var52);
        boolean var58 = var50.retainAll((org.apache.commons.collections.primitives.CharCollection)var52);
        java.lang.String var59 = var52.toString();
        char[] var60 = new char[] { };
        char[] var61 = var52.toArray(var60);
        org.apache.commons.collections.primitives.ArrayCharList var62 = new org.apache.commons.collections.primitives.ArrayCharList();
        java.lang.Integer var63 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var64 = new org.apache.commons.collections.primitives.ArrayCharList(var63);
        java.lang.Character var65 = new java.lang.Character('#');
        int var66 = var64.indexOf((char)var65);
        int var67 = var62.indexOf((char)var65);
        boolean var68 = var52.add((char)var65);
        boolean var69 = var3.contains((char)var65);
        org.apache.commons.collections.primitives.adapters.CharListList var70 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
        org.apache.commons.collections.primitives.adapters.ListShortList var71 = new org.apache.commons.collections.primitives.adapters.ListShortList((java.util.List)var70);
        org.apache.commons.collections.primitives.ShortList var72 = org.apache.commons.collections.primitives.ShortCollections.unmodifiableShortList((org.apache.commons.collections.primitives.ShortList)var71);
        //Test passes if var72 is type: class org.apache.commons.collections.primitives.adapters.ListShortList, an example value: [1.0].
        //Current value: #
        
        // Checks the contract:  var72.equals(var72)
        assertTrue("Contract failed: var72.equals(var72)", var72.equals(var72));
  }
  public void test1() throws Throwable {
        java.lang.Integer var0 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
        java.lang.Integer var2 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var3 = new org.apache.commons.collections.primitives.ArrayCharList(var2);
        java.lang.Character var4 = new java.lang.Character('#');
        int var5 = var3.indexOf((char)var4);
        java.lang.Character var6 = new java.lang.Character('4');
        boolean var7 = var3.contains((char)var6);
        org.apache.commons.collections.primitives.adapters.CharListList var8 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
        boolean var9 = var1.retainAll((org.apache.commons.collections.primitives.CharCollection)var3);
        java.lang.String var10 = var3.toString();
        char[] var11 = new char[] { };
        char[] var12 = var3.toArray(var11);
        org.apache.commons.collections.primitives.ArrayCharList var13 = new org.apache.commons.collections.primitives.ArrayCharList();
        java.lang.Integer var14 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var15 = new org.apache.commons.collections.primitives.ArrayCharList(var14);
        java.lang.Character var16 = new java.lang.Character('#');
        int var17 = var15.indexOf((char)var16);
        int var18 = var13.indexOf((char)var16);
        boolean var19 = var3.add((char)var16);
        java.lang.Integer var20 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var21 = new org.apache.commons.collections.primitives.ArrayCharList(var20);
        java.lang.Integer var22 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var23 = new org.apache.commons.collections.primitives.ArrayCharList(var22);
        java.lang.Character var24 = new java.lang.Character('#');
        int var25 = var23.indexOf((char)var24);
        java.lang.Character var26 = new java.lang.Character('4');
        boolean var27 = var23.contains((char)var26);
        org.apache.commons.collections.primitives.adapters.CharListList var28 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var23);
        boolean var29 = var21.retainAll((org.apache.commons.collections.primitives.CharCollection)var23);
        java.util.List var30 = org.apache.commons.collections.primitives.adapters.CharListList.wrap((org.apache.commons.collections.primitives.CharList)var23);
        java.lang.Integer var31 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var32 = new org.apache.commons.collections.primitives.ArrayCharList(var31);
        java.lang.Character var33 = new java.lang.Character('#');
        int var34 = var32.indexOf((char)var33);
        boolean var35 = var23.containsAll((org.apache.commons.collections.primitives.CharCollection)var32);
        java.lang.Integer var36 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var37 = new org.apache.commons.collections.primitives.ArrayCharList(var36);
        java.lang.Integer var38 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var39 = new org.apache.commons.collections.primitives.ArrayCharList(var38);
        java.lang.Character var40 = new java.lang.Character('#');
        int var41 = var39.indexOf((char)var40);
        java.lang.Character var42 = new java.lang.Character('4');
        boolean var43 = var39.contains((char)var42);
        org.apache.commons.collections.primitives.adapters.CharListList var44 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var39);
        boolean var45 = var37.retainAll((org.apache.commons.collections.primitives.CharCollection)var39);
        boolean var46 = var32.removeAll((org.apache.commons.collections.primitives.CharCollection)var39);
        int var47 = var39.size();
        char var48 = var3.get((int)var47);
        java.lang.Integer var49 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var50 = new org.apache.commons.collections.primitives.ArrayCharList(var49);
        java.lang.Integer var51 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var52 = new org.apache.commons.collections.primitives.ArrayCharList(var51);
        java.lang.Character var53 = new java.lang.Character('#');
        int var54 = var52.indexOf((char)var53);
        java.lang.Character var55 = new java.lang.Character('4');
        boolean var56 = var52.contains((char)var55);
        org.apache.commons.collections.primitives.adapters.CharListList var57 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var52);
        boolean var58 = var50.retainAll((org.apache.commons.collections.primitives.CharCollection)var52);
        java.lang.String var59 = var52.toString();
        char[] var60 = new char[] { };
        char[] var61 = var52.toArray(var60);
        org.apache.commons.collections.primitives.ArrayCharList var62 = new org.apache.commons.collections.primitives.ArrayCharList();
        java.lang.Integer var63 = new java.lang.Integer(10);
        org.apache.commons.collections.primitives.ArrayCharList var64 = new org.apache.commons.collections.primitives.ArrayCharList(var63);
        java.lang.Character var65 = new java.lang.Character('#');
        int var66 = var64.indexOf((char)var65);
        int var67 = var62.indexOf((char)var65);
        boolean var68 = var52.add((char)var65);
        boolean var69 = var3.contains((char)var65);
        org.apache.commons.collections.primitives.adapters.CharListList var70 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
        org.apache.commons.collections.primitives.adapters.ListShortList var71 = new org.apache.commons.collections.primitives.adapters.ListShortList((java.util.List)var70);
        org.apache.commons.collections.primitives.ShortList var72 = org.apache.commons.collections.primitives.adapters.ListShortList.wrap((java.util.List)var70);
        org.apache.commons.collections.primitives.DoubleList var73 = org.apache.commons.collections.primitives.adapters.ListDoubleList.wrap((java.util.List)var70);
        //Test passes if var73 is: (org.apache.commons.collections.primitives.adapters.ListDoubleList).
        //Current value: #
        java.util.List var74 = org.apache.commons.collections.primitives.adapters.DoubleListList.wrap(var73);
        org.apache.commons.collections.primitives.DoubleList var75 = org.apache.commons.collections.primitives.DoubleCollections.unmodifiableDoubleList(var73);
        //Test passes if var75 is type: class org.apache.commons.collections.primitives.adapters.ListDoubleList, an example value: .
        //Current value: #
        
        // Checks the contract:  var75.equals(var75)
        assertTrue("Contract failed: var75.equals(var75)", var75.equals(var75));
  }
}
