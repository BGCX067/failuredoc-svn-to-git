package failure.sequence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import randoop.ArrayDeclaration;
import randoop.ExecutableSequence;
import randoop.PrimitiveOrStringOrNullDecl;
import randoop.RConstructor;
import randoop.RMethod;
import randoop.Sequence;
import randoop.StatementKind;
import randoop.StatementKindParseException;
import randoop.main.GenInputsAbstract;

public class SequenceFactory {
	
	/**
	 * Creates the following sequence:
	 * 
	    0. int i = 1;
		1. ArrayList l = new ArrayList(i);
		2. Object o = new Object();
		3. l.add(o);
		4. TreeSet t = new TreeSet(l);
		5. Set s = Collections.synchronizedSet(t);
	 * @throws StatementKindParseException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * */
	public static Sequence createTreeSetFailedSequence() {
		//int i = 1;
		try {
		    StatementKind newI = PrimitiveOrStringOrNullDecl.parse("int:1");
		    StatementKind newArrayList = RConstructor.parse("java.util.ArrayList.<init>(int)");
		    StatementKind newObject = RConstructor.getRConstructor(Object.class.getConstructor());
		    StatementKind addToList = RMethod.getRMethod(ArrayList.class.getMethod("add", Object.class));
		    StatementKind newTS = RConstructor.getRConstructor(TreeSet.class.getConstructor(Collection.class));
		    StatementKind syncS = RMethod.getRMethod(Collections.class.getMethod("synchronizedSet", Set.class));
		
		    Sequence s = new Sequence();
		    s = s.extend(newI);
		    s = s.extend(newArrayList, s.getVariable(0));
		    s = s.extend(newObject);
		    s = s.extend(addToList,    s.getVariable(1), s.getVariable(2));
		    s = s.extend(newTS,   s.getVariable(1));
		    s = s.extend(syncS,   s.getVariable(4));
		return s;
		} catch (Throwable e) {
			throw new Error("Error in creating sequence.");
		}
	}
	
	/**
	 * Creates the following sequence:
	 * 
	   0. ArrayList<Number> nums = new ArrayList<Number>();
       1. Integer i0 = new Integer(1);
       2.  nums.add(i0);
        //Tests passes if l0 is an int type
       3. Long l0 = new Long(-1);
       4. nums.add(l0);

        // No compile-time warning, but throws ArrayStoreException:
       //5. int i = nums.size();
       5. Integer[] is = new Integer[0];
       6. Integer[] ints = nums.toArray(is);
	 * */
	public static Sequence createJDKReportedFailedSequence() {
		try {
			StatementKind emptyArrayList =  RConstructor.getRConstructor(ArrayList.class.getConstructor());
			StatementKind newI = PrimitiveOrStringOrNullDecl.parse("int:1");
			StatementKind addToListI = RMethod.getRMethod(ArrayList.class.getMethod("add", Object.class));
			StatementKind newL = PrimitiveOrStringOrNullDecl.parse("long:-1");
			StatementKind addToListL = RMethod.getRMethod(ArrayList.class.getMethod("add", Object.class));
			//StatementKind newSize = RMethod.getRMethod(ArrayList.class.getMethod("size"));
			StatementKind newIntsArray = ArrayDeclaration.parse("java.lang.Integer[0]");
			StatementKind toArrayL = RMethod.getRMethod(ArrayList.class.getMethod("toArray", Object[].class));
		    
		    Sequence s = new Sequence();
		    s = s.extend(emptyArrayList);
		    s = s.extend(newI);
		    s = s.extend(addToListI, s.getVariable(0), s.getVariable(1));
		    s = s.extend(newL);
		    s = s.extend(addToListL, s.getVariable(0), s.getVariable(3));
		    //s = s.extend(newSize, s.getVariable(0));
		    s = s.extend(newIntsArray);
		    s = s.extend(toArrayL, s.getVariable(0), s.getVariable(5));
		    
		    return s;
		} catch (Throwable e) {
			throw new Error(e);
		}
	}
	
	public static void main(String[] args)  {
//		{
//			Sequence s = createTreeSetFailedSequence();
//			System.out.println(s);
//			ExecutableSequence eseq = new ExecutableSequence(s);
//			eseq.execute(SequenceExplainer.createDefaultVisitor());
//
//			System.out.println(eseq.hasFailure());
//			System.out.println(eseq.toCodeString());
//		}
		{
			GenInputsAbstract.forbidden_exception = "java.lang.ArrayStoreException";
			Sequence s = createJDKReportedFailedSequence();
			System.out.println(s);
			ExecutableSequence eseq = new ExecutableSequence(s);
			eseq.execute(SequenceExplainer.createDefaultVisitor());

			System.out.println(eseq.hasFailure());
			System.out.println(eseq.toCodeString());
		}
		
		
		
//		ArrayList nums = new ArrayList();
//        Integer i0 = new Integer(1);
//        nums.add(i0);
//        //Tests passes if l0 is an int type
//        Long l0 = new Long(-1);
//        nums.add(l0);
//
//        // No compile-time warning, but throws ArrayStoreException:
//        Object[] is = new Integer[0];
//        Object[] ints = nums.toArray(is);
	}
	
	/**
	 * Read a list of executable sequences from file
	 * */
	public static List<ExecutableSequence> readSequencesFromFile(String fileName) {
		try {
			FileInputStream fileis = new FileInputStream(fileName);
			ObjectInputStream objectis = new ObjectInputStream(new GZIPInputStream(fileis));
			List<ExecutableSequence> seqsFromFile = (List<ExecutableSequence>)objectis.readObject();
			return seqsFromFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Error(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}
	
	public static void writeSequenceToFile(List<ExecutableSequence> eseqs, String fileName) {
		try {
		    FileOutputStream fileos = new FileOutputStream(fileName);
		    ObjectOutputStream objectos = new ObjectOutputStream(new GZIPOutputStream(fileos));
		    objectos.writeObject(eseqs);
		    objectos.close();
		    fileos.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}
}
