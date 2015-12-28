package failure.state;

public class StateToString {

	public static String stateToString(State s) {
		switch(s) {
		case BOOLEAN_TRUE: return "true";
		case BOOLEAN_FALSE: return "false";
		case CHAR_ZERO: return "(char)0";
		case CHAR_NON_ZERO: return "(char)!=0";
		case BYTE_ZERO: return "(byte)0";
		case BYTE_NON_ZERO: return "(byte)!=0";
		case SHORT_EQ_ZERO: return "(short)0";
		case SHORT_GT_ZERO: return "(short)>0";
		case SHORT_LT_ZERO: return "(short)<0";
		
		case INT_EQ_ZERO: return "(int)0";
		case INT_GT_ZERO: return "(int)>0";
		case INT_LT_ZERO: return "(int)<0";
		
		case FLOAT_EQ_ZERO: return "(float)0";
		case FLOAT_GT_ZERO: return "(float)>0";
		case FLOAT_LT_ZERO: return "(float)<0";
		
		case LONG_EQ_ZERO: return "(long)0";
		case LONG_GT_ZERO: return "(long)>0";
		case LONG_LT_ZERO: return "(long)<0";
		
		case DOUBLE_EQ_ZERO: return "(double)0";
		case DOUBLE_GT_ZERO: return "(double)>0";
		case DOUBLE_LT_ZERO: return "(double)<0";
		
		case OBJ_NULL : return "null";
		case OBJ_NOT_NULL : return "not null";
		  
		case ARRAY_EMPTY : return "empty array";
		case ARRAY_NOT_EMPTY : return "non-empty array";
		
		case COLLECTION_NOT_EMPTY : return "non-empty collection";
		case COLLECTION_EMPTY : return "empty collection";
		}
		throw new RuntimeException("Error state: " + s);
	}
	
}