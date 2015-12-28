package failure.sequence.prettyprint;

import java.util.HashMap;
import java.util.Map;

import randoop.Sequence;
import failure.FDUtils;

public class VariableRenamer {

	public final Sequence sequence;
	
	public final Map<Integer, String> name_mapping;
	
	public VariableRenamer(Sequence sequence) {
		FDUtils.checkNull(sequence, "The given sequence should not be null.");
		this.sequence = sequence;
		//start renaming
		this.name_mapping = this.rename();
	}
	
	public String getRenamedVar(int index) {
		String name = this.name_mapping.get(index);
		if(name == null) {
			FDUtils.checkTrue(sequence.getStatementKind(index).getOutputType().equals(void.class),
					"The index: " + index + "-th output should be void.");
			FDUtils.checkNull(null, "No name for: " + index + "-th statement.");
		}
		return name;
	}
	
	/**
	 * Call in constructor
	 * */
	private Map<String, Integer> name_counting_map = new HashMap<String, Integer>();
	private Map<Integer, String> rename() {
		Map<Integer, String> index_var_map = new HashMap<Integer, String>();
		for(int i = 0; i < this.sequence.size(); i++) {
			Class<?> outputType = this.sequence.getStatementKind(i).getOutputType();
			if(outputType.equals(void.class)) {
				continue;
			}
			String rename = getVariableName(outputType);
			if(!name_counting_map.containsKey(rename)) {
				index_var_map.put(new Integer(i), rename + "0");
				//update it
				name_counting_map.put(rename, 1);
			} else {
				int num = name_counting_map.get(rename);
				index_var_map.put(new Integer(i), rename + num);
				//update the map
				name_counting_map.put(rename, num + 1);
			}
		}
		
		return index_var_map;
	}
	
	/**
	 * Transforms to a better name for each class
	 * */
	private static String getVariableName(Class<?> clz) {
		FDUtils.checkTrue(!clz.equals(void.class), "No void");
		//the array
		if(clz.isArray()) {
			while(clz.isArray()) {
				clz = clz.getComponentType();
			}
			return getVariableName(clz) + "_array";
		}
		
		if(clz.equals(Object.class)) {
			return "obj";
		} else if (clz.equals(String.class)) {
			return "str";
		} else if (clz.equals(Class.class)) {
			return "clazz";
		} else if(clz.isPrimitive() || FDUtils.isPrimitive(clz)) {
			//the primirive transformation
			if(clz.equals(int.class) || clz.equals(Integer.class)) {
				return "i";
			} else if (clz.equals(double.class) || clz.equals(Double.class)) {
				return "d";
			} else if (clz.equals(float.class) || clz.equals(Float.class)) {
				return "f";
			} else if (clz.equals(short.class) || clz.equals(Short.class)) {
				return "s";
			} else if (clz.equals(boolean.class) || clz.equals(Boolean.class)) {
				return "b";
			} else if (clz.equals(char.class) || clz.equals(Character.class)) {
				return "char";
			} else if (clz.equals(long.class) || clz.equals(Long.class)) {
				return "long";
			} else if (clz.equals(byte.class) || clz.equals(Byte.class)) {
				return "byte";
			} else {
				throw new RuntimeException("Run primitive type: " + clz);
			}
		} else {
			String name = clz.getSimpleName();
			if(Character.isUpperCase(name.charAt(0))) {
				return name.substring(0, 1).toLowerCase() + name.substring(1);
			} else {
				return name + "_instance";
			}
		} 
		
		
//		{//all primitive types, only use the first character
//			  double ,Double -> d
//			  short, Short -> s
//			  int,  Integer -> int
//			  bool, Boolean -> b
//			  float,  Float -> f
//			  char, Character -> char
//			  byte, Byte -> bt
//			  long,  Long -> long
//			}
//			String -> str
//			Object -> obj
//			Class -> clz
//			Other class: make the first character un-capitalized!
//			For illegal names -> un-captialize  + ¡°_instance¡±
//			Heuristic: if there are more than one words (exceeding a pre-defined length) -> only get the last one, and make it un-capitalized.
//			for array types:   name + ¡°_array¡±
	}
	
	public static void main(String[] args) {
		System.out.println(VariableRenamer.getVariableName(int.class));
		System.out.println(VariableRenamer.getVariableName(int[][].class));
	}
}