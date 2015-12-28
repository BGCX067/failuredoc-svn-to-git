package failure.state;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestBooleanState  extends TestCase {
	
	enum Sample{s1, s2, s3, s4, s5}
	
	public void testEnum() {
		BooleanState s = new BooleanState(Sample.class, Sample.s1, "s");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Sample.class, Sample.s2, "s");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Sample.class, Sample.s3, "s");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Sample.class, Sample.s4, "s");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Sample.class, Sample.s5, "s");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testPrimitiveInt() {
		BooleanState s = new BooleanState(Integer.class, 1, "int");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Integer.class, 0, "int");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Integer.class, -1, "int");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testBoolean() {
		BooleanState s = new BooleanState(Boolean.class, true, "b");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Boolean.class, false, "b");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testFloat() {
		BooleanState s = new BooleanState(Float.class, 0.1f, "f");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Float.class, 0.0f, "f");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Float.class, -0.1f, "f");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testDouble() {
		BooleanState s = new BooleanState(Double.class, 0.1d, "d");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Double.class, 0.0d, "d");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
		s = new BooleanState(Double.class, -0.1d, "d");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testNullCollection() {
		BooleanState s = new BooleanState(ArrayList.class, null, "list");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testEmptyCollection() {
		BooleanState s = new BooleanState(ArrayList.class, new ArrayList<String>(), "list");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testNoEmptyCollection() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("xx");
		BooleanState s = new BooleanState(ArrayList.class, l, "list");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testNullArray() {
		BooleanState s = new BooleanState(Integer[].class, null, "array");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testEmptyArray() {
		BooleanState s = new BooleanState(Integer[].class, new Integer[0], "array");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
	
	public void testNonEmptyArray() {
		BooleanState s = new BooleanState(Integer[].class, new Integer[2], "array");
		System.out.println(s);
		System.out.println("    " + s.propertyToString()); 
	}
}
