package failuredoc.analysis.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCollectionPropertyChecker extends TestCase {

	public static Test suite() {
		return new TestSuite(TestCollectionPropertyChecker.class);
	}
	
	public void testEmptyCollection() {
		Collection c1 = new LinkedList();
		Collection c2 = new LinkedHashSet();
		Collection c3 = new ArrayList();
		Object[] objs = new Object[]{c1, c2, c3};
		AbstractPropertyChecker checker = new CollectionPropertyChecker(objs);
		boolean result = checker.checkProperty();
		assertTrue(result);
		System.out.println(checker.propertyToString());
	}
	
	public void testNonEmptyCollection() {
		Collection c1 = new LinkedList();
		c1.add("aaa");
		Collection c2 = new LinkedHashSet();
		Collection c3 = new ArrayList();
		Object[] objs = new Object[]{c1, c2, c3};
		AbstractPropertyChecker checker = new CollectionPropertyChecker(objs);
		boolean result = checker.checkProperty();
		assertTrue(!result);
		System.out.println(checker.propertyToString());
	}
}