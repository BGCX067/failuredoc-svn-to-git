package failuredoc.analysis.inference;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TestIntegerPropertyChecker extends TestCase {

	public static Test suite() {
		return new TestSuite(TestIntegerPropertyChecker.class);
	}
	
	public void testIntegerAllZero() {
		Object[] objs = new Object[]{0,0,0};
		AbstractPropertyChecker checker = new IntegerPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testIntegerAllGTZero() {
		Object[] objs = new Object[]{1,2,3};
		AbstractPropertyChecker checker = new IntegerPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testIntegerAllLTZero() {
		Object[] objs = new Object[]{-1,-2,-3};
		AbstractPropertyChecker checker = new IntegerPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testIntegerNoPattern() {
		Object[] objs = new Object[]{-1,-2,-3, 0};
		AbstractPropertyChecker checker = new IntegerPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
	
	public void testIntegerIllegalInputs() {
		Object[] objs = new Object[]{-1,null,-3, 0};
		AbstractPropertyChecker checker = new IntegerPropertyChecker(objs);
		try {
		boolean r = checker.checkProperty();
		} catch (AssertionError e) {
			return;
		}
		//assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
}
