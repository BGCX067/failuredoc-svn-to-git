package failure.sequence;

import java.util.ArrayList;
import java.util.List;

import randoop.ContractCheckingVisitor;
import randoop.EqualsHashcode;
import randoop.EqualsReflexive;
import randoop.EqualsSymmetric;
import randoop.EqualsToNullRetFalse;
import randoop.ExecutionVisitor;
import randoop.MultiVisitor;
import randoop.ObjectContract;
import randoop.RegressionCaptureVisitor;
import randoop.main.GenInputsAbstract;

public class VisitorFactory {

	public static MultiVisitor createDefaultVisitor() {
		MultiVisitor visitor = new MultiVisitor();
		List<ExecutionVisitor> visitors = new ArrayList<ExecutionVisitor>();
	    //add all contracts
	    List<ObjectContract> contracts = new ArrayList<ObjectContract>();
	    // Now add all of Randoop's default contracts.
	    contracts.add(new EqualsReflexive());
	    contracts.add(new EqualsToNullRetFalse());
	    contracts.add(new EqualsHashcode());
	    contracts.add(new EqualsSymmetric());
	    ContractCheckingVisitor contractVisitor = new ContractCheckingVisitor(contracts,
	    GenInputsAbstract.offline ? false : true);
	    visitors.add(contractVisitor);
	    visitors.add(new RegressionCaptureVisitor());
	    //add to the mutli visitor
	    visitor.visitors.addAll(visitors);
	    return visitor;
	}
	
}