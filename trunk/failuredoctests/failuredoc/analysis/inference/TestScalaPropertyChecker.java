package failuredoc.analysis.inference;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestScalaPropertyChecker extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestScalaPropertyChecker.class);
	}
	
	public void testScalaProperty1() {
		Object[] objs = new Object[]{0,0,0};
		AbstractPropertyChecker checker = new ScalaPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testScalaProperty2() {
		Object[] objs = new Object[]{1,1,1};
		AbstractPropertyChecker checker = new ScalaPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testScalaProperty3() {
		Object[] objs = new Object[]{1f,1f,1f};
		AbstractPropertyChecker checker = new ScalaPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testScalaProperty4() {
		Object[] objs = new Object[]{1d,1d,1d};
		AbstractPropertyChecker checker = new ScalaPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testScalaProperty5() {
		Object[] objs = new Object[]{(byte)1,(byte)1,(byte)1};
		AbstractPropertyChecker checker = new ScalaPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(r);
		System.out.println(checker.propertyToString());
	}
	
	public void testScalaPropertyMix() {
		Object[] objs = new Object[]{1d,1,true};
		AbstractPropertyChecker checker = new ScalaPropertyChecker(objs);
		boolean r = checker.checkProperty();
		assertTrue(!r);
		//System.out.println( "no output: " + checker.propertyToString());
	}
}
