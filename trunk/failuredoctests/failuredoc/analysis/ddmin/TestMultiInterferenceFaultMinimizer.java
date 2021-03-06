package failuredoc.analysis.ddmin;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import failure.FDUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestMultiInterferenceFaultMinimizer extends TestCase {
	
	public static Test suite() {
		return new TestSuite(TestMultiInterferenceFaultMinimizer.class);
	}
	
	public void testMultiFault1() {
		List<Integer> list = FDUtils.create_array(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7});
		multi_faults_tests(8, list);
	}
	
	public void testMultiFault2() {
		List<Integer> list = FDUtils.create_array(new Integer[]{0, 3, 4, 5, 7});
		multi_faults_tests(8, list);
	}
	
	public void testMultiFault3() {
		List<Integer> list = FDUtils.create_array(new Integer[]{0, 1, 2, 3});
		multi_faults_tests(8, list);
	}
	
	public void testMultiFault4() {
		List<Integer> list = FDUtils.create_array(new Integer[]{ 5, 6, 7});
		multi_faults_tests(8, list);
	}
	
	public void testMultiFault5() {
		List<Integer> list = FDUtils.create_array(new Integer[]{1, 7});
		multi_faults_tests(8, list);
	}
	
	public void testMultiFault6() {
		List<Integer> list = FDUtils.create_array(new Integer[]{1, 3, 7});
		multi_faults_tests(9, list);
	}
	
	public void testMultiFault7() {
		List<Integer> list = FDUtils.create_array(new Integer[]{1, 3, 4, 5, 7});
		multi_faults_tests(9, list);
	}
	
	public void testMultiFault8() {
		List<Integer> list = FDUtils.create_array(new Integer[]{1, 2, 3, 4, 5, 7, 8});
		multi_faults_tests(9, list);
	}
	
	static void multi_faults_tests(int length, List<Integer> failed_indices) {
		FDUtils.checkTrue(failed_indices.size() > 0, "non-empty failed indices");
		for(int failure_index : failed_indices) {
		    FDUtils.checkTrue(length > failure_index && failure_index >= 0, "");
		}
		List<Integer> ints = new LinkedList<Integer>();
		for(int i = 0 ; i < length; i++) {
			ints.add(i);
		}
		//FDUtils.checkNull(failed_indices, "before null!");
		AbstractMinimizer<Integer> minimizer = new MultiInterferenceFaultsMinimizer(ints, failed_indices);
		List<Integer> results = minimizer.minimize();
		FDUtils.stdln("inside failed_indices: " + failed_indices);
		FDUtils.stdln("inside results: " + results);
		assertTrue(results.size() == failed_indices.size());
		Collections.sort(failed_indices);
		Collections.sort(minimizer.data);
		assertTrue(results.equals(failed_indices));
		FDUtils.stdln("count of divide: " + minimizer.getCount());
	}
}

class MultiInterferenceFaultsMinimizer extends AbstractMinimizer<Integer> {

	public final List<Integer> failed_indices;
	
	public MultiInterferenceFaultsMinimizer(List<Integer> data, List<Integer> failed_indices) {
		super(data);
		for(int i : failed_indices) {
		    super.check_index(i);
		}
		this.failed_indices = failed_indices;
	}

	@Override
	protected boolean is_still_fail(List<Integer> data) {
		if(data.isEmpty()) {
			return false;
		}
		
		if(data.containsAll(this.failed_indices)) {
			return true;
		}
		
		return false;
	}
}