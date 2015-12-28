package failuredoc.analysis.inference;

import randoop.main.GenInputsAbstract;
import failure.FDLog;
import failure.FDUtils;

/**
 * Did not consider interfaces
 * */
public class SameTypeChecker extends AbstractPropertyChecker {

	/**
	 * If there is a common type (except for Object), set
	 * the type to be non-null.
	 * */
	private Class<?> type = null;
	
	public SameTypeChecker(Object...objs) {
		super(objs);
	}
	
	@Override
	public boolean checkProperty() {
		Class<?> commonType = null;
		for(Object obj : super.objs) {
			//if already at the top of the lattice
			if(commonType != null && commonType.equals(Object.class)) {
				break;
			}
			//if not
			if(obj == null) {
				FDLog.log("ERROR: the object is null.");
				continue;
			} else {
				if(commonType == null) {
					commonType = obj.getClass();
				} else {
					//compute the common type between
					//commonType & obj.getClass();
					Class<?> objType = obj.getClass();
					commonType = FDUtils.commonSuperType(commonType, objType);
				}
			}
		}
		if(commonType != null && !commonType.equals(Object.class)) {
			//set the value
			this.type = commonType;
			//return true
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String propertyToString() {
		if(GenInputsAbstract.remove_likely_useless && (this.type.isMemberClass() || this.type.isAnonymousClass())) {
			FDLog.log("Remove liley useless class: " + this.type);
			return "";
		}
		if(this.type == null) {
			return "";
		}
		if(this.type.equals(super.outputType)) {
			FDLog.log("remove the same type: " + this.type + " in sametype checker");
			return "";
		}
		String typeName = this.type.getName().startsWith("java.lang.") ? this.type.getSimpleName():
			this.type.getName();
		return "is type: " + this.type.getName();
	}

	public Class<?> getSameType() {
		return type;
	}
}
