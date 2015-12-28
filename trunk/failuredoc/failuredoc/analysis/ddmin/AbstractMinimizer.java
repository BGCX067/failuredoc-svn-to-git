package failuredoc.analysis.ddmin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;

public abstract class AbstractMinimizer<T> {
	
	public final List<T> data;
	
	/**
	 * How many round
	 * */
	private int count = 0;
	
	public AbstractMinimizer(List<T> data) {
		FDUtils.checkNull(data, "The given list can not be null.");
		FDUtils.checkTrue(!data.isEmpty(), "can not be empty.");
		this.data = data;
	}
	
	public int getCount() {
		return count;
	}
	
	protected int size() {
		return data.size();
	}
	
	protected T get(int index) {
		check_index(index);
		return data.get(index);
	}
	
	protected void check_index(int index) {
		FDUtils.checkTrue(index >= 0 && index < data.size(), "The index: "
				+ index + " is not valid, the size is: " + data.size());
	}
	
	protected boolean apply_data(int[] data_indices) {
		List<T> list = new LinkedList<T>();
		for(int index : data_indices) {
			check_index(index);
			list.add(get(index));
		}
		count++;
		return is_still_fail(list);
	}
	
	public List<T> minimize() {
		int[] indices = this.findMinIndices();
		List<T> ret = new LinkedList<T>();
		for(int index : indices) {
			ret.add(get(index));
		}
		return ret;
	}
	
	private int[] findMinIndices() {
		int[] indices = ddmin();
		checkNoDup(indices);
		return indices;
	}
	
	/**
	 * The core part of implementing the ddmin algorithm.
	 * */
	private int[] ddmin() {
		//check the initial setting
		FDUtils.checkTrue(this.is_still_fail(this.data), "All the configurations should be faield: " + this.data);
		FDUtils.checkTrue(!this.apply_data(new int[]{}), "The empty configuration should not fail");
		//create a list of full indices of the data
		int[] full_indices = new int[this.data.size()];
		for(int i = 0; i < full_indices.length; i++) {
			full_indices[i] = i;
		}
		//call the real minimization algorithm
//		return  ddmin2(full_indices, 2);
		return dd2(full_indices, new int[]{});
	}
	
	/**
	 * The design invariant is "c + r leads to test fails", but
	 * "r leads test pass"
	 * */
	private int[] dd2(int[] c, int[] r) {
		checkNoOverlap(c, r);
		boolean result_cr = this.apply_data(merge(c, r));
		boolean result_r = this.apply_data(r);
		FDUtils.checkTrue(result_cr, "Test should fail.");
		FDUtils.checkTrue(!result_r, "Test should pass.");
		if(c.length == 1) {
			//found the erroouneous configuration
			return c;
		}
		//divide c into c1, and c2, two sub-arrays
		int[] c1 = firstHalf(c);
		int[] c2 = secondHalf(c);
		FDUtils.stdln("c1 after divide: " + FDUtils.convertArrayToFlatString(c1));
		FDUtils.stdln("c2 after divide: " + FDUtils.convertArrayToFlatString(c2));
		checkSubArrays(c, c1, c2);
		int[] rc1 = merge(r, c1);
		int[] rc2 = merge(r, c2);
		boolean result_rc1 = this.apply_data(rc1);
		boolean result_rc2 = this.apply_data(rc2);
		FDUtils.stdln("result of c1 and r, fails: " + result_rc1 + ": " + FDUtils.convertArrayToFlatString(rc1));
		FDUtils.stdln("result of c2 and r, fails: " + result_rc2 + ": " + FDUtils.convertArrayToFlatString(rc2));
		//has a failure
		if(result_rc1 || result_rc2) {
			//pass
			int[] ret = new int[0];
			if(result_rc1) {
				FDUtils.stdln("** c1 and r fails, calling dd2(c1, r)");
				int[] rc1_result = dd2(c1, r);
				ret = merge(ret, rc1_result);
			}
			if(result_rc2) {
				FDUtils.stdln("** c2 and r fails, calling dd2(c2, r)");
				int[] rc2_result = dd2(c2, r);
				ret = merge(ret, rc2_result);
			}
//			FDUtils.checkTrue(this.apply_data(ret), "The result of dd (merge) should fail!"
//					+ FDUtils.convertArrayToFlatString(ret));
			return ret;
		} else {
			FDUtils.stdln("-- interference, search dd2(c1, rc2) and dd2(c2, rc1) ");
			int[] result1 = dd2(c1, rc2);
			int[] result2 = dd2(c2, rc1);
			int[] ret = merge(result1, result2);
//			FDUtils.checkTrue(this.apply_data(ret), "The result of dd interference should fail: "
//					+ FDUtils.convertArrayToFlatString(ret));
			return ret;
		}
	}
	
//	private int[] ddmin2(int[] cs, int n) {
//		//xxx
//		return null;
//	}
	
	/**
	 * if ints has odd length, the first half has one more element
	 * */
	protected static int[] firstHalf(int[] ints) {
		int length = ints.length;
		FDUtils.checkTrue(length > 0, "length > 0");
		int endExcludeIndex = (length % 2 == 0) ? length/2 : (length + 1)/2;
		int[] ret = new int[endExcludeIndex];
		for(int i = 0; i < endExcludeIndex; i++) {
			ret[i] = ints[i];
		}
		return ret;
	}
	
	protected static int[] secondHalf(int[] ints) {
		int length = ints.length;
		FDUtils.checkTrue(length > 0, "length > 0");
		int startIncludeIndex = (length % 2 == 0) ? length/2 : (length + 1)/2;
		int[] ret = new int[length - startIncludeIndex];
		for(int i = startIncludeIndex; i < ints.length; i++) {
			ret[i - startIncludeIndex] = ints[i];
		}
		return ret;
	}
	
	protected static int[]  complement(int[] whole_array, int[] minus_array) {
		checkNoDup(whole_array);
		checkNoDup(minus_array);
		for(int i : minus_array) {
			FDUtils.checkTrue(checkElementIn(whole_array, i), "Element: " + i + " is not in array.");
		}
		int length = whole_array.length - minus_array.length;
		int[] ret = new int[length];
		int count = 0;
		for(int i = 0; i < whole_array.length; i++) {
			if(!checkElementIn(minus_array, whole_array[i])) {
				ret[count++] = whole_array[i];
			}
		}
		FDUtils.checkTrue(count + minus_array.length == whole_array.length, "Length not correct, count: "
				+ count + ", minus_array: " + minus_array.length + " whole_array: " + whole_array.length);
		return ret;
	}
	
	protected static int[] merge(int[] array1, int[] array2) {
		checkNoDup(array1);
		checkNoDup(array2);
		int total_size = array1.length;
		for(int i : array2) {
			if(!checkElementIn(array1, i)) {
				total_size++;
			}
		}
		//know the size of array
		int[] ret = new int[total_size];
		int index = 0;
		for(int i = 0; i < array1.length; i++) {
			ret[index++] = array1[i];
		}
		for(int i = 0; i < array2.length; i++) {
			if(!checkElementIn(array1, array2[i])) {
				ret[index++] = array2[i];
			}
		}
		//return the array
		return ret;
	}
	
	protected static void checkSubArrays(int[] whole, int[] sub1, int[] sub2) {
		int[] merged = merge(sub1, sub2);
		FDUtils.checkTrue(whole.length == merged.length, "Length not equal");
		for(int i : whole) {
			checkElementIn(merged, i);
		}
	}
	
	protected static void checkNoOverlap(int[] array1, int[] array2) {
		for(int i : array1) {
			FDUtils.checkTrue(!checkElementIn(array2, i), "Element: " + i + " should not in array2.");
		}
	}
	
	protected static boolean checkElementIn(int[] array, int element) {
		for(int i : array) {
			if(i == element) {
				return true;
			}
		}
		return false;
	}
	
	protected static void checkNoDup(int[] indices) {
		Arrays.sort(indices);
		for(int i = 0; i < indices.length; i++) {
			if(i == indices.length - 1) {
				continue;
			}
			FDUtils.checkTrue(indices[i] != indices[i+1], "Duplicates for i and i+1, when i is: " + i +
					", and the value is: " + indices[i]);
		}
	}
	
	/**
	 * True means the failure occurs again. False means the failure does not
	 * occurs.
	 * */
	protected abstract boolean is_still_fail(List<T> data);
}