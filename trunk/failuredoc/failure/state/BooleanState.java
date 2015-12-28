package failure.state;

import java.lang.reflect.Array;
import java.util.Collection;

import randoop.util.Reflection;
import failure.FDUtils;
import failure.PalusUtil;

public class BooleanState {

	public final boolean[] bFields;
	
	public final String fieldName;
	
	public final Class<?> fieldType;
	
	public final Object fieldObj;
	
	private String property = null;
	
	public BooleanState(Class<?> fieldType, Object fieldObj, String fieldName) {
		FDUtils.checkNull(fieldType, "The filed type can not be null");
		FDUtils.checkNull(fieldName, "The filed name can not be null");
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		//it could be null
		this.fieldObj = fieldObj;
		this.bFields = this.compute_boolean_vectors();
		if(fieldObj != null) {
			FDUtils.checkTrue(Reflection.canBeUsedAs(fieldObj.getClass(), fieldType), "Type is not" +
					"compatible: fieldObj's type: " + fieldObj.getClass() + ", fieldType: " + fieldType);
		}
	}

	/**
	 * Accordinig to the type, check how many booleans are
	 * needed for representation
	 * */
	private boolean[] compute_boolean_vectors() {
		//System.out.println(this.fieldName + ",  " + this.fieldType.getName() + ", field obj: " + this.fieldObj.getClass());
		boolean[] vectors = null;
		if(this.fieldType.isPrimitive() || PalusUtil.isPrimitive(this.fieldType)) {
			return this.computeTypesForPrimitive(this.fieldType, this.fieldObj);
		} else if (this.fieldType.isEnum()) {
			return this.computeEnumVectors(this.fieldType, this.fieldObj);
		} else if (this.fieldType.isArray()) {
			vectors = new boolean[2];
			//is null, is empty
			if(this.fieldObj == null) {
				vectors[0] = true;
				vectors[1] = false;
				this.property = "null";
			} else {
				int length = Array.getLength(this.fieldObj);
				vectors[0] = false;
				vectors[1] = length == 0 ? true : false;
				this.property = length == 0 ? "an empty array" : "a non-empty array";
			}
			return vectors;
		} else if (Reflection.canBeUsedAs(this.fieldType, java.util.Collection.class) ) {
			vectors = new boolean[2];
			//is null, is empty?
			if(this.fieldObj == null) {
				vectors[0] = true;
				vectors[1] = false;
				this.property = "null";
			} else {
				vectors[0] = false;
				Collection<?> c = (Collection<?>)this.fieldObj;
				if(c.isEmpty()) {
					vectors[1] = true;
					this.property = "an empty collection";
				} else {
					vectors[1] = false;
					this.property = "a non-empty collection";
				}
			}
			return vectors;
		} else {
			//is null, is not null
			vectors = new boolean[1];
			if(this.fieldObj == null) {
				vectors[0] = true;
				this.property = "null";
			} else {
				vectors[0] = false;
				this.property = "not null";
			}
			return vectors;
		}
	}
	
	private boolean[] computeTypesForPrimitive(Class<?> fieldType, Object fieldObj) {
		boolean[] vectors = null;
		if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
			vectors = new boolean[1];
			vectors[0] = fieldObj.equals(true) ? true : false;
			this.property = String.valueOf(fieldObj);
		} else {
			vectors = null;
			if (fieldType == java.lang.Byte.class || fieldType == byte.class) {
				vectors = new boolean[1];
				// =0 ? !=0?
				java.lang.Byte byteValue = (java.lang.Byte) fieldObj;
				if (byteValue == 0) {
					vectors[0] = true;
					this.property = "0";
				} else {
					vectors[1] = false;
					this.property = "not 0";
				}
			} else if (fieldType == java.lang.Character.class
					|| fieldType == char.class) {
				vectors = new boolean[1];
				// =0 ?
				java.lang.Character charValue = (java.lang.Character) fieldObj;
				if (charValue == '\u0000') {
					vectors[0] = true;
					this.property = "0";
				} else {
					vectors[1] = false;
					this.property = "not 0";
				}
			} else if (fieldType == java.lang.Short.class
					|| fieldType == short.class) {
				// < 0, = 0, > 0
				java.lang.Short shortValue = (java.lang.Short) fieldObj;
				if (shortValue == 0) {
					vectors = this.createBooleanVectorsEqualZero();
					this.property = "=0";
				} else if (shortValue > 0) {
					vectors = this.createBooleanVectorsGreaterZero();
					this.property = ">0";
				} else {
					vectors = this.createBooleanVectorsLessZero();
					this.property = "<0";
				}
			} else if (fieldType == java.lang.Integer.class
					|| fieldType == int.class) {
				// < 0, = 0, > 0
				java.lang.Integer intValue = (java.lang.Integer) fieldObj;
				if (intValue == 0) {
					vectors = this.createBooleanVectorsEqualZero();
					this.property = "=0";
				} else if (intValue > 0) {
					vectors = this.createBooleanVectorsGreaterZero();
					this.property = ">0";
				} else {
					vectors = this.createBooleanVectorsLessZero();
					this.property = "<0";
				}
			} else if (fieldType == java.lang.Float.class
					|| fieldType == float.class) {
				java.lang.Float floatValue = (java.lang.Float) fieldObj;
				if (floatValue == 0.0f) {
					vectors = this.createBooleanVectorsEqualZero();
					this.property = "0.0f";
				} else if (floatValue > 0.0f) {
					vectors = this.createBooleanVectorsGreaterZero();
					this.property = ">0.0f";
				} else {
					vectors = this.createBooleanVectorsLessZero();
					this.property = "<0.0f";
				}
			} else if (fieldType == java.lang.Long.class
					|| fieldType == long.class) {
				java.lang.Long longValue = (java.lang.Long) fieldObj;
				if (longValue == 0l) {
					vectors = this.createBooleanVectorsEqualZero();
					this.property = "0";
				} else if (longValue > 0l) {
					vectors = this.createBooleanVectorsGreaterZero();
					this.property = ">0";
				} else {
					vectors = this.createBooleanVectorsLessZero();
					this.property = "<0";
				}
			} else if (fieldType == java.lang.Double.class
					|| fieldType == double.class) {
				java.lang.Double doubleValue = (java.lang.Double) fieldObj;
				if (doubleValue == 0d) {
					vectors = this.createBooleanVectorsEqualZero();
					this.property = "=0.0d";
				} else if (doubleValue > 0d) {
					vectors = this.createBooleanVectorsGreaterZero();
					this.property = ">0.0d";
				} else {
					vectors = this.createBooleanVectorsLessZero();
					this.property = "<0.0d";
				}
			} else {
				throw new RuntimeException("Unexpected type: " + fieldType);
			}
		}
		return vectors;
	}
	
	private boolean[] computeEnumVectors(Class<?> fieldType, Object fieldObj) {
		FDUtils.checkTrue(fieldType.isEnum(), "The field type: " + fieldType + " is not enum.");
		FDUtils.checkTrue(fieldObj != null, "The object should not be null for enum type");
		Object[] enums = fieldType.getEnumConstants();
		boolean vectors[] = new boolean[enums.length];
		for(int i = 0; i < vectors.length; i++) {
			vectors[i] = false;
		}
		int index = -1;
		for(int i = 0; i < vectors.length; i++) {
			if(enums[i].equals(fieldObj)) {
				index = i;
				break;
			}
		}
		//set the true value
		FDUtils.checkTrue(index != -1, "The index is not correct");
		vectors[index] = true;
		//set the property
		this.property = String.valueOf(fieldObj);
		//return the boolean vector
		return vectors;
	}
	
	private boolean[] createBooleanVectorsEqualZero() {
		// < 0, = 0, >0
		boolean[] v = new boolean[3];
		v[0] = false;
		v[1] = true;
		v[2] = false;
		return v;
	}
	
	private boolean[] createBooleanVectorsLessZero() {
		// < 0, = 0, >0
		boolean[] v = new boolean[3];
		v[0] = true;
		v[1] = false;
		v[2] = false;
		return v;
	}
	
	private boolean[] createBooleanVectorsGreaterZero() {
		// < 0, = 0, >0
		boolean[] v = new boolean[3];
		v[0] = false;
		v[1] = false;
		v[2] = true;
		return v;
	}
	
	@Override
	public int hashCode() {
//		public final boolean[] bFields;
//		public final String fieldName;
//		public final Class<?> fieldType;
//		public final Object fieldObj;
		int code = 0;
		for(boolean b : bFields) {
			code += b ? 13 : 29;
		}
		code += 3*fieldName.hashCode();
		code += 17*fieldType.hashCode();
		return code;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BooleanState)) {
			return false;
		} else {
			BooleanState other = (BooleanState)obj;
			boolean[] otherField = other.bFields;
			String otherFieldName = other.fieldName;
			Class<?> otherFieldType = other.fieldType;
			if(otherField.length != this.bFields.length) {
				return false;
			}
			for(int i = 0; i < otherField.length; i++) {
				if(otherField[i] != this.bFields[i]) {
					return false;
				}
			}
			//we do not compare the object
			return this.fieldName.equals(otherFieldName) && this.fieldType.equals(otherFieldType);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Field Name: " + this.fieldName);
		sb.append("(");
		for(int i = 0; i < bFields.length; i++) {
			if(i != 0) {
				sb.append(", ");
			}
			sb.append(bFields[i]);
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String propertyToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("field");
		sb.append(" ");
		sb.append(this.fieldName);
		sb.append(" is ");
		sb.append(this.getProperty());
		return sb.toString();
	}
	
	private String getProperty() {
		return this.property;
	}
}