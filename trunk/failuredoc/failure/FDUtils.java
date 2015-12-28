package failure;

import failure.sequence.ExecutionResult;

import randoop.ExecutableSequence;
import randoop.RConstructor;
import randoop.RMethod;
import randoop.Sequence;
import randoop.StatementKind;
import randoop.main.GenInputsAbstract;
import randoop.util.Files;
import randoop.util.Reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import plume.Pair;

public class FDUtils {
	
   private static final List<String> nonTransformedPrefixes = Arrays
      .asList(new String[] { "java/", /* "com/sun/", */"javax/", "sun/",
          "test/", "org/objectweb/asm/",
          "org/xmlpull/" });
  
	public static void checkTrue(boolean condition, String message) {
		if(!condition) {
			throw new AssertionError(message);
		}
	}
	
	public static void stdln(String str) {
		if(!GenInputsAbstract.failuredoc_verbose)
			return;
		System.out.println(str);
	}
	
	public static void std(String str) {
		if(!GenInputsAbstract.failuredoc_verbose)
			return;
		System.out.print(str);
	}
	
	public static void stdln(Object obj) {
		if(!GenInputsAbstract.failuredoc_verbose)
			return;
		System.out.println(obj);
	}
	
	public static void std(Object obj) {
		if(!GenInputsAbstract.failuredoc_verbose)
			return;
		System.out.print(obj);
	}
	
	public static void checkNull(Object o, String message) {
		if(o == null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static List<ExecutableSequence> failureSequences(Collection<ExecutableSequence> sequences) {
		List<ExecutableSequence> failed_sequences = new LinkedList<ExecutableSequence>();
		for(ExecutableSequence eseq : sequences) {
			if(eseq.hasFailure()) {
				failed_sequences.add(eseq);
				checkTrue(!eseq.hasNonExecutedStatements(), "all statements should be executed!");
				if(GenInputsAbstract.forbidden_exception == null) {
				    checkTrue(!eseq.throwsException(), "should not throw exception!");
				}
			}
		}
		return failed_sequences;
	}
	
	public static ExecutionResult shortestSequence(List<ExecutionResult> results) {
		FDUtils.checkTrue(results.size() > 0, "The list results can not be empty.");
		
		Sequence currentSequence = null;
		ExecutionResult retResult = null;
		int size = Integer.MAX_VALUE;
		
		for(ExecutionResult result : results) {
			if(result.replacing_sequence.size() < size) {
				size = result.replacing_sequence.size();
				currentSequence = result.replacing_sequence;
				retResult = result;
			}
		}
		
		FDUtils.checkNull(currentSequence, "The current sequence could not be null.");
		FDUtils.checkNull(retResult, "The return result could not be null.");
		
		return retResult;
	}
	
	public static String transClassNameDotToSlash(String name) {
        return name.replace('.', '/');
    }

    public static String transClassNameSlashToDot(String name) {
        return name.replace('/', '.');
    }
    
    public static int copyFile(File inFile, File outFile) throws IOException {
      FileInputStream in = new FileInputStream(inFile);
      FileOutputStream out = new FileOutputStream(outFile);
      int size = copyStream(in, out);
      in.close();
      out.close();

      return size;
  }
    
    public static void writeToFile(String content, File file) {
    	try {
    		if(!file.exists()) {
    			file.createNewFile();
    		}
    		Files.writeToFile(content, file);
    	} catch(IOException e) {
    		throw new RuntimeException(e);
    	}
    }
    
  public static int copyStream(InputStream in, OutputStream out)
          throws IOException {
      int size = 0;
      // Transfer bytes from in to out
      byte[] buf = new byte[1024];
      int len;

      while ((len = in.read(buf)) > 0) {
          size += len;
          out.write(buf, 0, len);
      }

      return size;
  }
  
    public static boolean shouldInstrumentThisClass(String className) {
        for (String p : nonTransformedPrefixes) {
            if (className.startsWith(p)) {
                return false;
            }
        }

    return true;
    }

    public static boolean isPrimitiveOrStringType(Class<?> clz) {
      return clz.isPrimitive() || isPrimitive(clz) || isStringType(clz);
    }
    
    public static boolean isPrimitiveOrStringOneDimensionArrayType(Class<?> clz) {
      if(!clz.isArray()) {
        return false;
      } else {
        Class<?> componentType = clz.getComponentType();
        if(componentType.isArray()) {
          //mutliple dimension array
          return false;
        } else {
          return isPrimitiveOrStringType(componentType);
        }
      }
    }
    
    public static boolean isNonPrimitiveOrStringOneDimensionArray(Class<?> clz) {
      if(!clz.isArray()) {
        return false;
      } else {
        Class<?> componentType = clz.getComponentType();
        return !isPrimitiveOrStringType(componentType);
      }
    }
    
    public static int[] computeObjectIdInArray(Object array) {
      FDUtils.checkNull(array, "The array object could not be null");
      FDUtils.checkTrue(array.getClass().isArray(), "The give object: " + array  + "is not an array type.");
      
      int length = Array.getLength(array);
      int[] retIDs = new int[length];
      for(int i = 0; i < length; i++) {
        Object obj = Array.get(array, i);
        retIDs[i] = System.identityHashCode(obj);
      }
      
      return retIDs;
    }
    
    public static String convertListToFlatString(List<Object> list) {
    	Object objs = (Object[])list.toArray(new Object[0]);
    	return convertArrayToFlatString(objs);
    }
    
    public static String convertArrayToFlatString(Object array) {
      FDUtils.checkNull(array, "The array object could not be null");
      FDUtils.checkTrue(array.getClass().isArray(), "The give object: " + array  + "is not an array type.");
      StringBuilder sb = new StringBuilder();
      
      int length = Array.getLength(array);
      sb.append("[");
      for(int i = 0; i < length; i++) {
        Object obj = Array.get(array, i);
        if(obj == null) {
          sb.append("null");
        } else {
          sb.append(obj);
        }
        if(i != length - 1) {
          sb.append(", ");
        }
      }
      sb.append("]");
      
      return sb.toString();
    }
    
	public static<T> List<T> create_array(T...ints) {
		List<T> list = new LinkedList<T>();
		for(T i : ints) {
			list.add(i);
		}
		return list;
	}
	
	public static boolean isPrimitive(String className) {
		return className.equals("boolean") || className.equals("char") || className.equals("byte")
		    || className.equals("short") || className.equals("int") || className.equals("float")
		    || className.equals("long") || className.equals("double");
	}
	
	public static Class<?> getBoxedClassForPrimitiveType(String className) {
		if(!isPrimitive(className)) {
			throw new RuntimeException(className + " is not a primitive type!");
		}
		if(className.equals("boolean")) {
			return Boolean.class;
		} else if(className.equals("char")) {
			return Character.class;
		} else if(className.equals("byte")) {
			return Byte.class;
		} else if(className.equals("short")) {
			return Short.class;
		} else if(className.equals("int")) {
			return Integer.class;
		} else if(className.equals("float")) {
			return Float.class;
		} else if(className.equals("long")) {
			return Long.class;
		} else if(className.equals("double")) {
			return Double.class;
		} else {
			throw new RuntimeException("Unexpected primitive type: " + className);
		}
	}
	
	public static Class<?> getClassForPrimitiveType(String className) {
		if(!isPrimitive(className)) {
			throw new RuntimeException(className + " is not a primitive type!");
		}
		if(className.equals("boolean")) {
			return boolean.class;
		} else if(className.equals("char")) {
			return char.class;
		} else if(className.equals("byte")) {
			return byte.class;
		} else if(className.equals("short")) {
			return short.class;
		} else if(className.equals("int")) {
			return int.class;
		} else if(className.equals("float")) {
			return float.class;
		} else if(className.equals("long")) {
			return long.class;
		} else if(className.equals("double")) {
			return double.class;
		} else {
			throw new RuntimeException("Unexpected primitive type: " + className);
		}
	}
	
	public static boolean isPrimitive(Class<?> clz) {
	  if (clz == java.lang.Boolean.class || clz == java.lang.Character.class
	      || clz == java.lang.Byte.class || clz == java.lang.Short.class
	      || clz == java.lang.Integer.class || clz == java.lang.Float.class
          || clz == java.lang.Long.class || clz == java.lang.Double.class) {
          return true;
      }
      return false;
	}
	
	public static boolean isStringType(Class<?> clz) {
	  return clz == java.lang.String.class;
	}
	
	public static Object createOneDimenPrimitiveOrStringArrayFromString(Class<?> componentType,
	    String value) {
	  String[] values = null;
	  return createOneDimenPrimitiveOrStringArrayFromStrings(componentType, values);
	}
	
//	public static String convertPrimitiveOrStringArray(Object obj) {
//	  PalusUtil.checkNull(obj);
//	  PalusUtil.checkTrue(PalusUtil.isPrimitiveOrStringOneDimensionArrayType(obj.getClass()));
//	  
//	}
	
	private static String concatenateStrings(String[] values) {
	  StringBuilder sb = new StringBuilder();
	  for(int i = 0; i < values.length; i++) {
	    sb.append(values[i]);
	    if(i != values.length - 1) {
	      sb.append(SEP);
	    }
	  }
	  return sb.toString();
	}
	
	private static String SEP = "_P_A__";
	
	private static String[] splitValue(String value) {
	  return value.split(SEP);
	}
	
	private static Object createOneDimenPrimitiveOrStringArrayFromStrings(Class<?> componentType,
	    String[] values) {
	  FDUtils.checkNull(componentType, "The component type should not be null.");
	  FDUtils.checkTrue(isPrimitiveOrStringType(componentType), "The component type is not primitive.");
	  FDUtils.checkNull(values, "The string[] values could not be null.");
	  //create the array by reflection
	  Object array = Array.newInstance(componentType, values.length);
	  for(int i = 0; i < values.length; i++) {
	    String value = values[i];
	    Object item = createPrimitiveOrStringValueFromString(componentType, value);
	    Array.set(array, i, item);
	  }
	  
	  return array;
	}
	
	public static Object createPrimitiveOrStringValueFromString(Class<?> t, String value) {
	    FDUtils.checkTrue(t.isPrimitive() || FDUtils.isStringType(t), "Type t: " + t +
	    		" is not a primitive or string type.");
	    
	    if(FDUtils.isStringType(t)) {
	      return value;
	    } else if ( t == int.class) {
	      return new Integer(value);
	    } else if ( t == float.class) {
	      return new Float(value);
	    } else if ( t == short.class) {
	      return new Short(value);
	    } else if ( t == byte.class) {
	      return new Byte(value);
	    } else if ( t == long.class) {
	      return new Long(value);
	    } else if ( t == double.class) {
	      return new Double(value);
	    } else if ( t == char.class) {
	      FDUtils.checkTrue(value.length() == 1, "The length of value: " + value +
	    		  " to represent a char should only have 1-length.");
	      return new Character(value.charAt(0));
	    } else if ( t == boolean.class) {
	      return Boolean.getBoolean(value);
	    }
	    
	    throw new RuntimeException("not string or primitive type: " + t);
	  }
	
	public static String primitiveTypeToClassName(Class<?> t) {
		FDUtils.checkTrue(t.isPrimitive() || FDUtils.isPrimitive(t), "Class: " + t + " is not primitive.");
		if(t.equals(int.class) || t.equals(Integer.class)) {
			return "int";
		}
		if(t.equals(double.class) || t.equals(Double.class)) {
			return "double";
		}
		if(t.equals(float.class) || t.equals(Float.class)) {
			return "float";
		}
		if(t.equals(short.class) || t.equals(Short.class)) {
			return "short";
		}
		if(t.equals(long.class) || t.equals(Long.class)) {
			return "long";
		}
		if(t.equals(boolean.class) || t.equals(Boolean.class)) {
			return "boolean";
		}
		if(t.equals(char.class) || t.equals(Character.class)) {
			return "char";
		}
		if(t.equals(byte.class) || t.equals(Byte.class)) {
			return "byte";
		}
		throw new RuntimeException("unexpected type: " + t);
	}
	
	/**
	   * Dump object array to flat string
	   * */
	  public static String objectsToString(Object... objects) {
	    StringBuilder sb = new StringBuilder();
	    
	    for(int i = 0; i < objects.length; i++) {
	      Object o = objects[i];
	      if(o == null) {
	        sb.append("null");
	      } else {
	        String name = "toString() exception in " + o.getClass();
	        try {
	          name = o.toString();
	        } catch (Throwable e) {
	          //do nothing
	        }
	        sb.append(name);
	      }
	      if(i != objects.length - 1) {
	        sb.append(", ");
	      }
	    }
	    
	    return sb.toString();
	  }
	  
	  /**
	   * Get the declaring class of constructor and method statement
	   * If it is other kind of statement, return null.
	   * */
	  public static Class<?> getDeclaringClass(StatementKind statement) {
	    if(statement instanceof RMethod) {
	      RMethod rmethod = (RMethod)statement;
	      return rmethod.getMethod().getDeclaringClass();
	    } else if(statement instanceof RConstructor) {
	      RConstructor rconstructor = (RConstructor)statement;
	      return rconstructor.getConstructor().getDeclaringClass();
	    } else {
	      return null;
	    }
	  }
	  
	  /**
	   * class cache pair 
	   * */
	  private static Map<Pair<Class<?>, Class<?>>, Class<?>> cached_class =
		  new LinkedHashMap<Pair<Class<?>, Class<?>>, Class<?>>();
	  /**
	   * Computes the common supertype of two classes. Does not
	   * consider the itnerfaces yet.
	   * #TODO
	   * */
	  public static Class<?> commonSuperType(Class<?> c1, Class<?> c2) {
		  FDUtils.checkTrue(c1 != null && c2 != null,
				  "Neither c1 or c2 could be null.");
		  FDUtils.checkTrue(!c1.equals(void.class) && !c2.equals(void.class),
				  "Neither c1 or c2 could be void.");
		  
		  //check the interfaces
		  if(c1.isInterface() || c2.isInterface()) {
			  return Object.class;
		  }
		  //check the cache first
		  Pair<Class<?>, Class<?>> p1 = new Pair<Class<?>, Class<?>>(c1, c2);
		  Pair<Class<?>, Class<?>> p2 = new Pair<Class<?>, Class<?>>(c2, c1);
		  if(cached_class.containsKey(p1)) {
			  return cached_class.get(p1);
		  }
		  if(cached_class.containsKey(p2)) {
			  return cached_class.get(p2);
		  }
		  
		  //convert to boxing type
		  c1 = c1.isPrimitive() ? FDUtils.getBoxedClassForPrimitiveType(c1.getName()) : c1;
		  c2 = c2.isPrimitive() ? FDUtils.getBoxedClassForPrimitiveType(c2.getName()) : c2;
		  //both are classes, now computes its common types
		  if(Reflection.canBeUsedAs(c1, c2)) {
			//c1 can be used as c2
			return c2;  
		  }
		  if(Reflection.canBeUsedAs(c2, c1)) {
			  return c1;
		  }
		  
		  //now c1, c2 belongs to different branches
		  //computes its least commonest
		  Class<?> superClassOfC1 = c1.getSuperclass();
		  while(!superClassOfC1.equals(Object.class)) {
			  if(Reflection.canBeUsedAs(c2, superClassOfC1)) {
				  return superClassOfC1;
			  }
			  superClassOfC1 = superClassOfC1.getSuperclass();
		  }
		  //add to the cache
		  cached_class.put(new Pair<Class<?>, Class<?>>(c1, c2), superClassOfC1);
		  
		  return superClassOfC1;
	  }
	  
	  /**
	   * Safe comparison, considering the null cases.
	   * */
	  public static boolean isEqual(Object obj1, Object obj2) {
		  //FDLog.log("in isEqual: obj1: " + obj1 + ", obj2: " + obj2 + "\n");
		  if(obj1 == null && obj2 == null) {
			  return true;
		  } else if (obj1 == null && obj2 != null) {
			  return false;
		  } else if (obj1 != null && obj2 == null) {
			  return false;
		  } else {
			  return obj1.equals(obj2);
		  }
	  }
}
