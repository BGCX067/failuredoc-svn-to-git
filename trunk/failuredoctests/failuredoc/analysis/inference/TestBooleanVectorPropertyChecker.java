package failuredoc.analysis.inference;

import java.util.ArrayList;
import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestBooleanVectorPropertyChecker extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestBooleanVectorPropertyChecker.class);
	}
	
	public void testCommonProperty() {
		ArrayList l1 = new ArrayList();
		l1.add("hello");
		//BooleanVector v1 = new BooleanVector(l1, ArrayList.class);
		
		LinkedList l2 = new LinkedList();
		l2.add("hello");
		//BooleanVector v2 = new BooleanVector(l2, LinkedList.class);
		
		AbstractPropertyChecker checker = new BooleanVectorPropertyChecker(l1, l2);
		assertTrue(checker.checkProperty());
		System.out.println("property: " + checker.propertyToString());
	}
	
	public void testNonCommonProperty() {
		ArrayList l1 = new ArrayList();
		l1.add("hello");
		//BooleanVector v1 = new BooleanVector(l1, ArrayList.class);
		
		LinkedList l2 = new LinkedList();
		//BooleanVector v2 = new BooleanVector(l2, LinkedList.class);
		
		AbstractPropertyChecker checker = new BooleanVectorPropertyChecker(l1, l2);
		assertTrue(!checker.checkProperty());
		//System.out.println("property: " + checker.propertyToString());
	}
}
