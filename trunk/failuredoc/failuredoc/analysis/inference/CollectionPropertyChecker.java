package failuredoc.analysis.inference;

import java.util.Collection;

import failure.FDLog;

import randoop.util.Reflection;

public class CollectionPropertyChecker extends SameTypeChecker {
	
	private int collection_property = Integer.MAX_VALUE;
	
	//the collection property
	private static int EMPTY = 0;
	//private static int CONTAINS_NULL = 1;
	//private static int NON_EMPTY = 2;
	
	public CollectionPropertyChecker(Object...objs) {
		super(objs);
	}
	
	@Override
	public boolean checkProperty() {
		//boolean hasTheSameType = super.checkProperty();
		boolean allCollections = this.areAllObjectsCollection();
		FDLog.log("All collections? " + allCollections);
		if(!allCollections) {
			return false;
		} else {
//		    boolean allCollection = Reflection.canBeUsedAs(super.getSameType(),
//		    		java.util.Collection.class);
//		    FDLog.log("All collections? " + allCollection + ", super class: " + super.getSameType());
//		    if(!allCollection) {
//		    	return false;
//		    } else {
		    	for(Object obj : objs) {
		    		if(obj.getClass().getName().equals("java.util.Collections$SingletonSet")) {
		    			FDLog.log("remove singleton.");
		    			continue;
		    		}
		    		Collection<?> c = (Collection<?>)obj;
		    		if(!c.isEmpty()) {
		    			FDLog.log(obj + " is not empty, type: " + obj.getClass());
		    			return false;
		    		} else {
		    			FDLog.log(obj + "  is empty, type: " + obj.getClass());
		    		}
		    	}
		    	//all are empty
		    	this.collection_property = EMPTY;
		    	return true;
//		    }
		}
	}
	
	private boolean areAllObjectsCollection() {
		for(Object obj : objs) {
			if(obj == null) {
				FDLog.log("an object is null when checking is it an empty collection: " + obj);
			} else {
				Class<?> clz = obj.getClass();
				if(!Reflection.canBeUsedAs(clz, java.util.Collection.class)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String propertyToString() {
		if(this.collection_property == EMPTY) {
			if(super.outputType != null) {
				return "is an empty " + super.outputType.getSimpleName();
			} else {
			    return "is an empty collection";
			}
		} else {
			return "is not an empty collection";
		}
	}
}
