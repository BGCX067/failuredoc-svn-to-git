package failuredoc.analysis.inference;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSameAbStatePropertyChecker extends TestCase {

	public static Test suite() {
		return new TestSuite(TestSameAbStatePropertyChecker.class);
	}
	
	public void testIllegalInputs() {
		
	}
	
	public void testSameAbStates() {
		List<String> l1 = new LinkedList<String>();
		l1.add("a");
		List<String> l2 = new LinkedList<String>();
		l2.add("a");
		l2.add("b");
		Object[] objs = new Object[]{l1, l2};
		AbstractPropertyChecker checker = new SameAbStatePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
		
		AbstractPropertyChecker checker1 = new SingleValuePropertyChecker(objs);
		boolean r1 = checker1.checkProperty();
		assertTrue(!r1);
	}
	
	public void testDiffAbStates() {
		List<String> l1 = new LinkedList<String>();
		List<String> l2 = new LinkedList<String>();
		l2.add("a");
		l2.add("b");
		Object[] objs = new Object[]{l1, l2};
		AbstractPropertyChecker checker = new SameAbStatePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
	
	public void testContainsNull() {
		List<String> l1 = new LinkedList<String>();
		l1.add("a");
		List<String> l2 = new LinkedList<String>();
		l2.add("a");
		l2.add("b");
		Object[] objs = new Object[]{l1, l2, null};
		AbstractPropertyChecker checker = new SameAbStatePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
	
	public void testAllNullSameType() {
		List<String> l1 = null;
		List<String> l2 = null;
		Object[] objs = new Object[]{l1, l2};
		AbstractPropertyChecker checker = new SameAbStatePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output for null: " + checker.propertyToString());
	}
	
	public void testDiffTypes() {
		List<String> l1 = new LinkedList<String>();
		l1.add("a");
		List<String> l2 = new ArrayList<String>();
		l2.add("a");
		l2.add("b");
		Object[] objs = new Object[]{l1, l2};
		AbstractPropertyChecker checker = new SameAbStatePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
}
