package failure.state;

import java.util.ArrayList;
import java.util.LinkedList;

import junit.framework.TestCase;

public class TestBooleanVector extends TestCase {

	public void testNull() {
		BooleanVector v = new BooleanVector( null, ArrayList.class);
		System.out.println(v);
	}
	
	public void testNotNull() {
		ArrayList l = new ArrayList();
		l.add("hello");
		BooleanVector v = new BooleanVector(l, ArrayList.class);
		System.out.println(v);
	}
	
	public void testNotNull1() {
		LinkedList l = new LinkedList();
		l.add("hello");
		BooleanVector v = new BooleanVector(l, LinkedList.class);
		System.out.println(v);
	}
	
	public void testString() {
		BooleanVector v = new BooleanVector("hello", String.class);
		System.out.println(v);
	}
	
	public void testPrimtivies() {
		BooleanVector v = new BooleanVector(1, Integer.class);
		System.out.println(v);
		
	}
}