package experiment.ddisolate;

import java.util.List;

import randoop.ExecutableSequence;
import randoop.ExecutionVisitor;
import failure.sequence.SequenceFactory;
import failure.sequence.VisitorFactory;

public class ApachePrimitiveIsolation {
	
	// randoop is buggy, need to work around this
//	Exception in thread "main" java.lang.Error: classForName(C)
//	at randoop.util.Reflection.classForName(Reflection.java:207)
//	at randoop.util.Reflection.classForName(Reflection.java:181)
//	at randoop.util.Reflection.memberForSignature2(Reflection.java:687)
//	at randoop.util.Reflection.getMemberForSignature(Reflection.java:652)
//	at randoop.util.Reflection.getMethodForSignature(Reflection.java:641)
//	at randoop.SerializableRMethod.readResolve(SerializableRMethod.java:19)
//	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	
	public static void main(String[] args) {
		String fileName = "./experiments/experiment/ddisolate/apacheprimitive.gz";
		List<ExecutableSequence> eseqs = SequenceFactory.readSequencesFromFile(fileName);
		System.out.println("size: " + eseqs.size());
		
		for(ExecutableSequence eseq : eseqs) {
			ExecutionVisitor visitor = VisitorFactory.createDefaultVisitor();
			FaultyStatementIsolator isolator = new FaultyStatementIsolator(eseq.sequence, visitor);
			Integer[] ints = isolator.isolateFaultyStatementIndices();
			System.out.println(eseq.toCodeString());
			for(int i : ints) {
				System.out.println(" - " + i);
			}
			System.out.println("   --------------------- ");
		}
	}
}


//remove 19 will lead to no failure
//
//
//skip the failure index: 72
//for sequence: 
//java.lang.Integer var0 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var2 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var3 = new org.apache.commons.collections.primitives.ArrayCharList(var2);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var4 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var5 = var3.indexOf((char)var4);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var6 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var7 = var3.contains((char)var6);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var8 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var9 = var1.retainAll((org.apache.commons.collections.primitives.CharCollection)var3);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.String var10 = var3.toString();
//// <NormalExecution object=object-of-type-java.lang.String>;
//char[] var11 = new char[] { };
//// <NormalExecution object=object-of-type-[C>;
//char[] var12 = var3.toArray(var11);
//// <NormalExecution object=object-of-type-[C>;
//org.apache.commons.collections.primitives.ArrayCharList var13 = new org.apache.commons.collections.primitives.ArrayCharList();
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var14 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var15 = new org.apache.commons.collections.primitives.ArrayCharList(var14);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var16 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var17 = var15.indexOf((char)var16);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//int var18 = var13.indexOf((char)var16);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//boolean var19 = var3.add((char)var16);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.Integer var20 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var21 = new org.apache.commons.collections.primitives.ArrayCharList(var20);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var22 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var23 = new org.apache.commons.collections.primitives.ArrayCharList(var22);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var24 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var25 = var23.indexOf((char)var24);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var26 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var27 = var23.contains((char)var26);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var28 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var23);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var29 = var21.retainAll((org.apache.commons.collections.primitives.CharCollection)var23);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.util.List var30 = org.apache.commons.collections.primitives.adapters.CharListList.wrap((org.apache.commons.collections.primitives.CharList)var23);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//java.lang.Integer var31 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var32 = new org.apache.commons.collections.primitives.ArrayCharList(var31);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var33 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var34 = var32.indexOf((char)var33);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//boolean var35 = var23.containsAll((org.apache.commons.collections.primitives.CharCollection)var32);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.Integer var36 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var37 = new org.apache.commons.collections.primitives.ArrayCharList(var36);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var38 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var39 = new org.apache.commons.collections.primitives.ArrayCharList(var38);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var40 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var41 = var39.indexOf((char)var40);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var42 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var43 = var39.contains((char)var42);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var44 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var39);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var45 = var37.retainAll((org.apache.commons.collections.primitives.CharCollection)var39);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//boolean var46 = var32.removeAll((org.apache.commons.collections.primitives.CharCollection)var39);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//int var47 = var39.size();
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//char var48 = var3.get((int)var47);
//// <NormalExecution object=object-of-type-java.lang.Character>;
//java.lang.Integer var49 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var50 = new org.apache.commons.collections.primitives.ArrayCharList(var49);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var51 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var52 = new org.apache.commons.collections.primitives.ArrayCharList(var51);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var53 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var54 = var52.indexOf((char)var53);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var55 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var56 = var52.contains((char)var55);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var57 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var52);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var58 = var50.retainAll((org.apache.commons.collections.primitives.CharCollection)var52);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.String var59 = var52.toString();
//// <NormalExecution object=object-of-type-java.lang.String>;
//char[] var60 = new char[] { };
//// <NormalExecution object=object-of-type-[C>;
//char[] var61 = var52.toArray(var60);
//// <NormalExecution object=object-of-type-[C>;
//org.apache.commons.collections.primitives.ArrayCharList var62 = new org.apache.commons.collections.primitives.ArrayCharList();
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var63 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var64 = new org.apache.commons.collections.primitives.ArrayCharList(var63);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var65 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var66 = var64.indexOf((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//int var67 = var62.indexOf((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//boolean var68 = var52.add((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//boolean var69 = var3.contains((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var70 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//org.apache.commons.collections.primitives.adapters.ListShortList var71 = new org.apache.commons.collections.primitives.adapters.ListShortList((java.util.List)var70);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.ListShortList>;
//org.apache.commons.collections.primitives.ShortList var72 = org.apache.commons.collections.primitives.ShortCollections.unmodifiableShortList((org.apache.commons.collections.primitives.ShortList)var71);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.decorators.UnmodifiableShortList>;
//<randoop.EqualsReflexive [Lrandoop.Variable;@1ca9e7 
//
//removable: 
//remove 19 will lead to no failure
//skip the failure index: 75
//for sequence: 
//java.lang.Integer var0 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var1 = new org.apache.commons.collections.primitives.ArrayCharList(var0);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var2 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var3 = new org.apache.commons.collections.primitives.ArrayCharList(var2);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var4 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var5 = var3.indexOf((char)var4);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var6 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var7 = var3.contains((char)var6);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var8 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var9 = var1.retainAll((org.apache.commons.collections.primitives.CharCollection)var3);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.String var10 = var3.toString();
//// <NormalExecution object=object-of-type-java.lang.String>;
//char[] var11 = new char[] { };
//// <NormalExecution object=object-of-type-[C>;
//char[] var12 = var3.toArray(var11);
//// <NormalExecution object=object-of-type-[C>;
//org.apache.commons.collections.primitives.ArrayCharList var13 = new org.apache.commons.collections.primitives.ArrayCharList();
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var14 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var15 = new org.apache.commons.collections.primitives.ArrayCharList(var14);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var16 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var17 = var15.indexOf((char)var16);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//int var18 = var13.indexOf((char)var16);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//boolean var19 = var3.add((char)var16);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.Integer var20 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var21 = new org.apache.commons.collections.primitives.ArrayCharList(var20);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var22 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var23 = new org.apache.commons.collections.primitives.ArrayCharList(var22);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var24 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var25 = var23.indexOf((char)var24);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var26 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var27 = var23.contains((char)var26);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var28 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var23);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var29 = var21.retainAll((org.apache.commons.collections.primitives.CharCollection)var23);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.util.List var30 = org.apache.commons.collections.primitives.adapters.CharListList.wrap((org.apache.commons.collections.primitives.CharList)var23);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//java.lang.Integer var31 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var32 = new org.apache.commons.collections.primitives.ArrayCharList(var31);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var33 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var34 = var32.indexOf((char)var33);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//boolean var35 = var23.containsAll((org.apache.commons.collections.primitives.CharCollection)var32);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.Integer var36 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var37 = new org.apache.commons.collections.primitives.ArrayCharList(var36);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var38 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var39 = new org.apache.commons.collections.primitives.ArrayCharList(var38);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var40 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var41 = var39.indexOf((char)var40);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var42 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var43 = var39.contains((char)var42);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var44 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var39);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var45 = var37.retainAll((org.apache.commons.collections.primitives.CharCollection)var39);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//boolean var46 = var32.removeAll((org.apache.commons.collections.primitives.CharCollection)var39);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//int var47 = var39.size();
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//char var48 = var3.get((int)var47);
//// <NormalExecution object=object-of-type-java.lang.Character>;
//java.lang.Integer var49 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var50 = new org.apache.commons.collections.primitives.ArrayCharList(var49);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var51 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var52 = new org.apache.commons.collections.primitives.ArrayCharList(var51);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var53 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var54 = var52.indexOf((char)var53);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//java.lang.Character var55 = new java.lang.Character('4');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//boolean var56 = var52.contains((char)var55);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var57 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var52);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//boolean var58 = var50.retainAll((org.apache.commons.collections.primitives.CharCollection)var52);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//java.lang.String var59 = var52.toString();
//// <NormalExecution object=object-of-type-java.lang.String>;
//char[] var60 = new char[] { };
//// <NormalExecution object=object-of-type-[C>;
//char[] var61 = var52.toArray(var60);
//// <NormalExecution object=object-of-type-[C>;
//org.apache.commons.collections.primitives.ArrayCharList var62 = new org.apache.commons.collections.primitives.ArrayCharList();
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Integer var63 = new java.lang.Integer(10);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//org.apache.commons.collections.primitives.ArrayCharList var64 = new org.apache.commons.collections.primitives.ArrayCharList(var63);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.ArrayCharList>;
//java.lang.Character var65 = new java.lang.Character('#');
//// <NormalExecution object=object-of-type-java.lang.Character>;
//int var66 = var64.indexOf((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//int var67 = var62.indexOf((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Integer>;
//boolean var68 = var52.add((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//boolean var69 = var3.contains((char)var65);
//// <NormalExecution object=object-of-type-java.lang.Boolean>;
//org.apache.commons.collections.primitives.adapters.CharListList var70 = new org.apache.commons.collections.primitives.adapters.CharListList((org.apache.commons.collections.primitives.CharList)var3);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.CharListList>;
//org.apache.commons.collections.primitives.adapters.ListShortList var71 = new org.apache.commons.collections.primitives.adapters.ListShortList((java.util.List)var70);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.ListShortList>;
//org.apache.commons.collections.primitives.ShortList var72 = org.apache.commons.collections.primitives.adapters.ListShortList.wrap((java.util.List)var70);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.ListShortList>;
//org.apache.commons.collections.primitives.DoubleList var73 = org.apache.commons.collections.primitives.adapters.ListDoubleList.wrap((java.util.List)var70);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.ListDoubleList>;
//java.util.List var74 = org.apache.commons.collections.primitives.adapters.DoubleListList.wrap(var73);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.adapters.DoubleListList>;
//org.apache.commons.collections.primitives.DoubleList var75 = org.apache.commons.collections.primitives.DoubleCollections.unmodifiableDoubleList(var73);
//// <NormalExecution object=object-of-type-org.apache.commons.collections.primitives.decorators.UnmodifiableDoubleList>;
//<randoop.EqualsReflexive [Lrandoop.Variable;@2b1649 
//
//removable: 