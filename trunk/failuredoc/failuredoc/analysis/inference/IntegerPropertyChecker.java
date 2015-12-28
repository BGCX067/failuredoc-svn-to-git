package failuredoc.analysis.inference;

import failure.FDUtils;

@Deprecated
public class IntegerPropertyChecker extends SameTypeChecker {
	
	private int range = Integer.MAX_VALUE;
	
	//represent the case
	private static final int EQUAL_ZERO = 0;
	private static final int GT_ZERO = 1;
	private static final int LT_ZERO = -1;
	private static final int GET_ZERO = 2;
	private static final int LET_ZERO = -2;
	
	public IntegerPropertyChecker(Object...objs) {
		super(objs);
	}
	
	@Override
	public boolean checkProperty() {
		boolean hasTheSameType = super.checkProperty();
		if(hasTheSameType && super.getSameType().equals(Integer.class)) {
			return this.hasIntProperty();
		}
		//not integer type
		return false;
	}
	
	private boolean hasIntProperty() {
		boolean allEqualZero = true;
		boolean allGTZero = true;
		boolean allLTZero = true;
		for(Object obj : objs) {
			FDUtils.checkTrue(obj instanceof Integer, "It should be int type");
			Integer i = (Integer)obj;
			if(i == 0) {
				//fasified two categories
				allGTZero = false;
				allLTZero = false;
			}
			if(i > 0) {
				allLTZero = false;
				allEqualZero = false;
			}
			if(i < 0) {
				allEqualZero = false;
				allGTZero = false;
			}
		}
		//set the integer property
		if(allEqualZero && !allGTZero && !allLTZero) {
			this.range = EQUAL_ZERO; //all equal 0
			return true;
		}
		if(!allEqualZero && allGTZero && !allLTZero) {
			this.range = GT_ZERO;
			return true;
		}
		if(!allEqualZero && !allGTZero && allLTZero) {
			this.range = LT_ZERO;
			return true;
		}
		return false;
	}
	
	@Override
	public String propertyToString() {
		String property = null;
		if(this.range == EQUAL_ZERO) {
			property = "(int)0";
		} else if (this.range == LT_ZERO) {
			property = "(int)<0";
		} else if (this.range == GT_ZERO) {
			property = "(int)>0";
		} else {
			return "";
		}
		return "is: " + property;
	}
	
}