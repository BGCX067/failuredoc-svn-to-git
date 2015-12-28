package failuredoc.analysis.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TestObjectPropertyInferrer extends TestCase {

	public static Test suite() {
		return new TestSuite(TestObjectPropertyInferrer.class);
	}
	
	public void testSingleValue1(){
		Object[] objs = new Object[]{1, 1, 1, 1};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(b);
		System.out.println(inferrer.checkedPropertiesToString());
	}
	
	public void testNoSingleValue1(){
		Object[] objs = new Object[]{1, 2, 1, 1};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(b);
		System.out.println(inferrer.checkedPropertiesToString());
	}
	
	public void testSingleValue2() {
		Object[] objs = new Object[]{new LinkedList(), new LinkedList()};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(b);
		System.out.println(inferrer.checkedPropertiesToString());
	}
	
	public void testEmptyCollection() {
		Collection a1 = new LinkedList();
		Collection a2 = new LinkedHashSet();
		//System.out.println(a1.equals(a2));
		Object[] objs = new Object[]{a1, a2};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(b);
		System.out.println(inferrer.checkedPropertiesToString());
	}
	
	public void testSameAbs() {
		Collection a1 = new LinkedList();
		Collection a2 = new LinkedList();
		a1.add("a");
		a2.add(1);
		Object[] objs = new Object[]{a1, a2};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(b);
		System.out.println(inferrer.checkedPropertiesToString());
	}
	
	public void testAllNull() {
		Object[] objs = new Object[]{(Object)null, (String)null};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(b);
		System.out.println(inferrer.checkedPropertiesToString());
	}
	
	public void testAllComparable() {
		Object[] objs = new Object[]{(Integer)1, (byte)1, "xyz"};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(b);
		System.out.println(inferrer.checkedPropertiesToString());
	}
	
	public void testNonPropertyMatched() {
		Object[] objs = new Object[]{(Integer)1, (byte)1, "xyz", new Object()};
		ObjectPropertyInferrer inferrer = new ObjectPropertyInferrer(objs);
		boolean b = inferrer.checkDefaultProperties();
		assertTrue(!b);
		//System.out.println("no output: " + inferrer.checkedPropertiesToString());
	}
}
