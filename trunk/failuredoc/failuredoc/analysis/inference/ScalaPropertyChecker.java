package failuredoc.analysis.inference;

import failure.FDUtils;

public class ScalaPropertyChecker extends SameTypeChecker {
	
    private int range = Integer.MAX_VALUE;
	
	//represent the case
	private static final int EQUAL_ZERO = 0;
	private static final int GT_ZERO = 1;
	private static final int LT_ZERO = -1;
	private static final int GET_ZERO = 2;
	private static final int LET_ZERO = -2;
	
	public ScalaPropertyChecker(Object...objs) {
		super(objs);
	}

	@Override
	public boolean checkProperty() {
		boolean hasTheSameType = super.checkProperty();
		if(!hasTheSameType) {
			return false;
		} else {
			Class<?> clz = super.getSameType();
			FDUtils.checkNull(clz, "The common type should not be null.");
			if(!isScalaType(clz)) {
				return false;
			} else {
				return this.hasScalaProperty();
			}
		}
	}
	
	private boolean isScalaType(Class<?> clz) {
		if(clz.equals(boolean.class) || clz.equals(Boolean.class)) {
			return false;
		}
		if(clz.equals(char.class) || clz.equals(Character.class)) {
			return false;
		}
		if(clz.isPrimitive()) {
			return true;
		}
		return FDUtils.isPrimitive(clz);
	}
	
	/**
	 * The type is int, float, short, byte, double, long 
	 * */
	private boolean hasScalaProperty() {
		boolean allEqualZero = true;
		boolean allGTZero = true;
		boolean allLTZero = true;
		for(Object obj : objs) {
			Double i = Double.valueOf(obj.toString());//(Double)obj;
			if(i == 0d) {
				//fasified two categories
				allGTZero = false;
				allLTZero = false;
			}
			if(i > 0d) {
				allLTZero = false;
				allEqualZero = false;
			}
			if(i < 0d) {
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
		String typeName = FDUtils.primitiveTypeToClassName(super.getSameType());
		String property = null;
		if(this.range == EQUAL_ZERO) {
			property = "(" + typeName + ")0";
		} else if (this.range == LT_ZERO) {
			property = "(" + typeName + ")<0";
		} else if (this.range == GT_ZERO) {
			property = "(" + typeName + ")>0";
		} else {
			return "";
		}
		return "is: " + property;
	}
	
//	public static void main(String[] args) {
//		int x = 0;
//		int y = 1;
//		System.out.println((double)x == (double)0);
//		System.out.println((double)y == (double)0);
//	}

}
