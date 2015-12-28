package failuredoc.analysis.inference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import failure.FDUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSameTypeChecker extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestSameTypeChecker.class);
	}
	
	public void testSampleTypeChecker1() {
		Object[] objs = {1, 2, new Integer(3)};
		AbstractPropertyChecker checker = new SameTypeChecker(objs);
		boolean check_property = checker.checkProperty();
		assertTrue(check_property);
	    FDUtils.stdln(checker.propertyToString());
	}
	
	public void testSampleTypeChecker2() {
		Object[] objs = {"string1", "string2", ""};
		AbstractPropertyChecker checker = new SameTypeChecker(objs);
		boolean check_property = checker.checkProperty();
		assertTrue(check_property);
	    FDUtils.stdln(checker.propertyToString());
	}
	
	public void testSampleTypeChecker3() {
		Object[] objs = {new ArrayList<String>(), new LinkedList<String>(), new HashSet<String>()};
		AbstractPropertyChecker checker = new SameTypeChecker(objs);
		boolean check_property = checker.checkProperty();
		assertTrue(check_property);
	    FDUtils.stdln(checker.propertyToString());
	}
	
	public void testNotTheSameType() {
		Object[] objs = {"string1", "string2", 1};
		AbstractPropertyChecker checker = new SameTypeChecker(objs);
		boolean check_property = checker.checkProperty();
		assertTrue(!check_property);
	    FDUtils.stdln(checker.propertyToString());
	}
	
	public void testNotTheSameTypeContainsNull() {
		Object[] objs = {"string1", "string2", null};
		AbstractPropertyChecker checker = new SameTypeChecker(objs);
		boolean check_property = checker.checkProperty();
		assertTrue(check_property);
	    FDUtils.stdln("no output: " + checker.propertyToString());
	}
}
