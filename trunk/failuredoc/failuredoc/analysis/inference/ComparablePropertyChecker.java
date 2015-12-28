package failuredoc.analysis.inference;

public class ComparablePropertyChecker extends AbstractPropertyChecker {
	
	boolean allComparable = false;
	
	public ComparablePropertyChecker(Object...objs) {
		super(objs);
	}
	
	@Override
	public boolean checkProperty() {
		for(Object obj : super.objs) {
			if(!(obj instanceof Comparable<?>)) {
				return false;
			}
		}
		this.allComparable = true;
		return true;
	}

	@Override
	public String propertyToString() {
		if(allComparable) {
			return "implements Comparable";
		} else {
			return "";
		}
	}
}