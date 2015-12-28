package failuredoc.analysis.inference;

import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSingleValuePropertyChecker extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestSingleValuePropertyChecker.class);
	}
	
	public void testSameValueInteger() {
		Object[] objs = new Object[]{1, 1, 1};
		AbstractPropertyChecker checker = new SingleValuePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testSameValueBoolean() {
		Object[] objs = new Object[]{true, true};
		AbstractPropertyChecker checker = new SingleValuePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testNOtSameValueInteger() {
		Object[] objs = new Object[]{1, 2, 1};
		AbstractPropertyChecker checker = new SingleValuePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
	
	public void testSameValueAllNone() {
		Object[] objs = new Object[]{null, null, null};
		AbstractPropertyChecker checker = new SingleValuePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testSameValueNotAllNone() {
		Object[] objs = new Object[]{1, null, null};
		AbstractPropertyChecker checker = new SingleValuePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
	
	public void testSameValueAllList() {
		Object[] objs = new Object[]{new LinkedList(), new LinkedList(), new LinkedList()};
		AbstractPropertyChecker checker = new SingleValuePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
}
