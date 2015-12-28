package failuredoc.analysis.inference;

import java.lang.reflect.Field;

import failure.FDUtils;
import failure.state.AbstractState;
import failure.state.StateToString;

public class SameAbStatePropertyChecker extends AbstractPropertyChecker {
	/**
	 * The common abstract state. If there is no abstract state,
	 * abState is null.
	 * */
	private AbstractState abState = null; 
	
	public SameAbStatePropertyChecker(Object...objs) {
		super(objs);
	}
	
	@Override
	public boolean checkProperty() {
		boolean hasSameType = this.hasTheSameClass();
		if(!hasSameType) {
		    return false;
		} else {
			Class<?> c = this.getFirstNonNullClass();
			if(c == null) {
				return false;
			} else {
				if(this.objs.length == 1) {
					this.abState = new AbstractState(this.objs[0], c);
					return true;
				} else {
					for(int i = 0; i < this.objs.length - 1; i++) {
						AbstractState ab1 = new AbstractState(super.objs[i], c);
						AbstractState ab2 = new AbstractState(super.objs[i+1], c);
						if(!ab1.equals(ab2)) {
							return false;
						}
					}
					//all have the same abstate
					this.abState = new AbstractState(super.objs[0], c);
					return true;
				}
			}
		}
	}

	@Override
	public String propertyToString() {
		if(this.abState == null) {
			return "";
		} else {
			return this.readableAbState();
		}
	}

	/**
	 * All private methods 
	 * */
	private Class<?> getFirstNonNullClass() {
		for(Object obj : objs) {
			if(obj == null) {
				continue;
			} else {
				return obj.getClass();
			}
		}
		return null;
	}
	
	private boolean hasTheSameClass() {
		if(super.objs.length == 1) {
			return true;
		} else {
			for(int i = 0; i < super.objs.length - 1; i ++) {
				Object o1 = super.objs[i];
				Object o2 = super.objs[i+1];
				if(o1 == null || o2 == null) {
					continue;
				} else {
					Class<?> c1 = o1.getClass();
					Class<?> c2 = o2.getClass();
					if(c1.equals(c2)) {
						continue;
					} else {
						return false;
					}
				}
			}
			return true;
		}
	}
	
	private String readableAbState() {
		FDUtils.checkNull(this.abState, "The abstract state can ot be null.");
		StringBuilder sb = new StringBuilder();
		
		sb.append("is type: ");
		sb.append(this.abState.getStateClass().getName());
		sb.append(" and its fields: ");
		for(int i = 0; i < this.abState.getStateSize(); i++) {
			if(i != 0) {
				sb.append(", and ");
			}
			Field field = this.abState.getStateFields()[i];
			String fieldName = field.getName();
			sb.append("\"");
			sb.append(fieldName);
			sb.append("\"");
			sb.append(" has value: ");
			sb.append(StateToString.stateToString(this.abState.getStates()[i]));
		}
		return sb.toString();
	}
}
