package failuredoc.analysis.inference;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestComparablePropertyChecker extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestComparablePropertyChecker.class);
	}
	
	public void testAllComparable() {
		Object[] objs = new Object[]{1, "xxx", (byte)1};
		AbstractPropertyChecker checker = new  ComparablePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testHasNull() {
		Object[] objs = new Object[]{1, "xxx", (byte)1, null};
		AbstractPropertyChecker checker = new  ComparablePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
	
	public void testContainsNonComparable() {
		Object[] objs = new Object[]{1, "xxx", (byte)1, new Object()};
		AbstractPropertyChecker checker = new  ComparablePropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		System.out.println("no output: " + checker.propertyToString());
	}
}
