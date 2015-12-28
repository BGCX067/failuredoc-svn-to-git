package failuredoc.analysis.inference;

import randoop.StatementKind;
import randoop.Variable;
import failure.FDUtils;

public abstract class AbstractPropertyChecker {
	
	/**
	 * The objects for inference
	 * */
	public final Object[] objs;
	
	/**
	 * The output type of statement
	 * */
	protected Class<?> outputType = null;
	
	/**
	 * The invoke statement (replaced expressions)
	 * */
	protected StatementKind statment = null;
	
	/**
	 * The output vars
	 * */
	protected Variable outputVar = null;
	
	/**
	 * The input vars
	 * */
	protected Variable[] vars = null;
	
	/**
	 * Observed failed object
	 * */
	protected boolean hasSetFailedObj = false;
	protected Object failedObj = null;
	
	/**
	 * The abstract constructor
	 * */
	public AbstractPropertyChecker(Object...objs) {
		FDUtils.checkNull(objs, "The passing objects should not be null.");
		FDUtils.checkTrue(objs.length > 0, "The length of object should > 0");
		this.objs = objs;
	}
	
	/**
	 * Set up the output type
	 * */
	public void setOutputType(Class<?> outputType) {
		FDUtils.checkNull(outputType, "The output type should not be null.");
		this.outputType = outputType;
	}
	
	/**
	 * Sets up the statement kind
	 * */
	public void setStatement(StatementKind statement) {
		FDUtils.checkNull(statement, "The statement can not be null.");
		this.statment = statement;
	}
	
	/**
	 * Sets the variables
	 * */
	public void setInputVariables(Variable[] vars) {
		FDUtils.checkNull(vars, "The vars can not be null.");
		this.vars = vars;
	}
	
	/**
	 * Sets the output variable
	 * */
	public void setOutputVariable(Variable var) {
		FDUtils.checkNull(var, "The var can not be null.");
		this.outputVar = var;
	}
	
	/**
	 * Set an observed failed object
	 * */
	public void setFailedObject(Object obj) {
		this.failedObj = obj;
		this.hasSetFailedObj = true;
	}

	/**
	 * Checks whether the property this class represents
	 * is satisfied or not.
	 * */
	public abstract boolean checkProperty();
	
	/**
	 * Returns the satisfied property to string value for
	 * output.
	 * */
	public abstract String propertyToString();
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}