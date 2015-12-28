package failuredoc.analysis.inference;

import java.lang.reflect.Method;

import failure.FDLog;
import failure.FDUtils;
import randoop.RMethod;
import randoop.StatementKind;
import randoop.main.GenInputsAbstract;
import randoop.util.PrimitiveTypes;
import randoop.util.Reflection;

public class SingleValuePropertyChecker extends AbstractPropertyChecker {

	private boolean hasSingleValue = false;
	
	private Object single_value = null;
	
	public static final String NOT_ADD = " is not added to ";
	
	public static final String LINE_IS = "line is: ";
	
	public SingleValuePropertyChecker(Object...objs) {
		super(objs);
	}
	
	@Override
	public boolean checkProperty() {
		try {
			if (super.objs.length == 1) {
				hasSingleValue = true;
				single_value = objs[0];
				return true;
			} else {
				for (int i = 0; i < super.objs.length - 1; i++) {
					Object obj1 = super.objs[i];
					Object obj2 = super.objs[i + 1];
					if (obj1 == null && obj2 == null) {
						continue;
					} else if ((obj1 == null && obj2 != null)
							|| (obj1 != null && obj2 == null)) {
						this.hasSingleValue = false;
						return false;
					} else {
						if (!obj1.equals(obj2)) {
							this.hasSingleValue = false;
							return false;
						}
					}
				}
				// all have the same value
				this.hasSingleValue = true;
				this.single_value = super.objs[0];
				return true;
			}
		} catch (Throwable e) {
			FDLog.log("exception : " + e
					+ " when checkproperty of single value");
			return false;
		}
	}

	@Override
	public String propertyToString() {
		String comment = "";
		if(this.hasSingleValue) {
			
			//deal with collections
			if(isCollectionAdditionOperation(super.statment) && super.vars != null && this.single_value.equals(false)) {
				FDUtils.checkTrue(super.vars.length == 2, "The length of vars is: " + super.vars.length + ", not 2.");
				comment = super.vars[1].getName() + NOT_ADD + super.vars[0].getName();
				return comment;
			}
			
			//deal with likely-useless
			if(GenInputsAbstract.remove_likely_useless) {
				if(this.single_value != null
						&& (this.single_value.getClass().isAnonymousClass() || this.single_value.getClass().isMemberClass())) {
					comment = "";
					FDLog.log("Remove likely useless inner class: " + this.single_value);
					return comment;
				}
			}
			
			//decides whether the single value should be casted or not
			boolean shouldAddCast = true;
			if(super.outputType != null && this.single_value != null) {
				if(this.single_value.getClass().equals(super.outputType)) {
					//different declared types
					shouldAddCast = false;
				}
				if(super.outputType.isPrimitive()) {
					//exclude for primitive types
					if(PrimitiveTypes.boxedType(super.outputType).equals(this.single_value.getClass())) {
						shouldAddCast = false;
					}
				}
			}
			comment = "" + this.single_value;
			if(shouldAddCast) {
//				boolean canBeUsed = false;
				String type = this.single_value == null ? "" : this.single_value.getClass().getName();
				if(type.startsWith("java.lang.")) {
					type = this.single_value.getClass().getSimpleName();
				}
				if(Reflection.canBeUsedAs(this.single_value.getClass(), super.outputType)) {
					comment = " uses type: " + type + ", with value: " + comment;
				} else {
			        comment = " is changed to type: " + type + ", with value: " + comment;
			        if(this.single_value != null) {
			        	Class<?> valuetype = this.single_value.getClass();
			        	if(PrimitiveTypes.isBoxedOrPrimitiveOrStringType(valuetype) && super.outputVar != null) {
			        		String typeName = valuetype.getSimpleName();
			        		comment = LINE_IS + typeName  + " " + super.outputVar.getName() + " = new "
			        		    + typeName + "(" +  this.single_value + ")";
			        	}
			        }
				}
			} else {
				if(super.hasSetFailedObj && FDUtils.isEqual(this.single_value, super.failedObj)) {
					//no need
					FDLog.log("remove the comment due the equality in single value: " + this.single_value);
					System.out.println("remove the comment due the equality in single value: " + this.single_value);
					comment = "";
				} else {
				    comment = "is set to: " + comment;
				}
			}
			return comment;
		} else {
			return comment;
		}
	}
	
	public static boolean isCollectionAdditionOperation(StatementKind stmt) {
		if(stmt == null) {
			return false;
		} else {
			if(stmt instanceof RMethod) {
				RMethod rmethod = (RMethod)stmt;
				if(!rmethod.isStatic()) {
					Method method = rmethod.getMethod();
					if(Reflection.canBeUsedAs(method.getDeclaringClass(), java.util.Collection.class)
							&& method.getName().startsWith("add") && method.getReturnType().equals(boolean.class)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
