package failure.state;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import randoop.util.Reflection;

import failure.FDUtils;

public class BooleanVector {
	public final Object obj;
	public final Class<?> clz;
	public final BooleanState[] bStates;
	
	public BooleanVector(Object obj, Class<?> clz) {
		FDUtils.checkNull(clz, "The class can not be null.");
		if(obj != null) {
			FDUtils.checkNull(Reflection.canBeUsedAs(obj.getClass(), clz), "The class: "
					+  obj.getClass() + ", can not be used as : " + clz);
		}
		this.clz = clz;
		//but the object maybe null
		this.obj = obj;
		//calcuate the boolean vectors
		this.bStates = this.calcuate_boolean_states(this.clz, this.obj);
	}
	
	private BooleanState[] calcuate_boolean_states(Class<?> clz, Object obj) {
		if(obj == null) {
			return new BooleanState[0];
		}
		if(clz.isPrimitive() || FDUtils.isPrimitive(clz)) {
			BooleanState[] states = new BooleanState[1];
			states[0] = new BooleanState(clz, obj, clz.getSimpleName());
			return states;
		}
		if(clz.equals(String.class)) {
			BooleanState[] states = new BooleanState[1];
			states[0] = new BooleanState(clz, obj, clz.getSimpleName());
			return states;
		}
		
		//if it is not null
		List<BooleanState> states = new ArrayList<BooleanState>();
	    Field[] allFields = clz.getDeclaredFields();
	    for(Field field : allFields) {
	      if(!( Modifier.isStatic(field.getModifiers()))) {
	        Object fieldObj = this.getFieldValue(field, obj);
	    	BooleanState state = new BooleanState(field.getType(), fieldObj, field.getName());
	    	states.add(state);
	      }
	    }
	    
	    return (BooleanState[])states.toArray(new BooleanState[0]);
	}
	
	private Object getFieldValue(Field field, Object obj) {
		FDUtils.checkNull(obj, "the object can not be null.");
		Object fieldValue = null;
	    try {
	    	field.setAccessible(true);
	        fieldValue = field.get(obj);
	    } catch (IllegalArgumentException e) {
	         throw new RuntimeException(e);
	    } catch (IllegalAccessException e) {
	         throw new RuntimeException(e);
	    }
	    return fieldValue;
	}
	
	@Override
	public int hashCode() {
		//public final Class<?> clz;
//		public final BooleanState[] bStates;
		int code = 0;
		code = code + clz.hashCode();
		for(BooleanState s : bStates) {
			code = code + s.hashCode();
		}
		return code;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BooleanVector)) {
			return false;
		}
		BooleanVector other = (BooleanVector)obj;
		Class<?> otherType = other.clz;
		BooleanState[] otherStates = other.bStates;
		if(!otherType.equals(this.clz)) {
			return false;
		}
		if(otherStates.length != this.bStates.length) {
			return false;
		}
		for(int i = 0; i < otherStates.length; i++) {
			if(!otherStates[i].equals(this.bStates[i])) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.clz.getSimpleName());
		sb.append("{");
		if(this.obj == null) {
			sb.append("NULL");
		}
		int count = 0;
		for(BooleanState s : this.bStates) {
			if(count != 0) {
				sb.append(", ");
			}
			sb.append(s.toString());
			count++;
		}
		sb.append("}");
		return sb.toString();
	}
}