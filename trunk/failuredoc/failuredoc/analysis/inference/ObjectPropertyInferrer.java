package failuredoc.analysis.inference;

import java.lang.reflect.Constructor;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import randoop.main.GenInputsAbstract;
import randoop.util.Reflection;

import failure.FDLog;
import failure.FDUtils;

/**
 * Given a set of objects, do the check, and output
 * a set of checked properties.
 * 
 * This class also follows the lattice, to output the
 * most accurate and precise property
 * 
 * #TODO a little bit hard code in the lattice part
 * */

//Implement if there is less than 2 values, just print it out

public class ObjectPropertyInferrer {
	
	/**
	 * object to check
	 * */
	public final Object[] objs;
	
	/**
	 * All satisfied checkers
	 * */
	public final List<AbstractPropertyChecker> satisified_checkers
	    = new LinkedList<AbstractPropertyChecker>();
	
	public ObjectPropertyInferrer(Object[]objs) {
		FDUtils.checkNull(objs, "The input objects can not be null.");
		FDUtils.checkTrue(objs.length > 0, "The object length should > 0");
		this.objs = objs;
	}
	
	public boolean checkDefaultProperties() {
		List<Class<? extends AbstractPropertyChecker>> checker_list =
			new LinkedList<Class<? extends AbstractPropertyChecker>>();
		
		checker_list.add(SingleValuePropertyChecker.class);
//		checker_list.add(IntegerPropertyChecker.class);
		checker_list.add(ScalaPropertyChecker.class);
		checker_list.add(SameAbStatePropertyChecker.class);
		checker_list.add(SameTypeChecker.class);
		checker_list.add(CollectionPropertyChecker.class);
		checker_list.add(ComparablePropertyChecker.class);
		checker_list.add(BooleanVectorPropertyChecker.class);
		
		return checkProperties(checker_list);
	}
	
	/**
	 * Checks if any (or more properties matches).
	 * 
	 * Follow the lattice, and add the most general properties
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * */
	public boolean checkProperties(List<Class<? extends AbstractPropertyChecker>> check_classes) {
	    try {
	    	return this.checkPropertiesException(check_classes);
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	private boolean checkPropertiesException(List<Class<? extends AbstractPropertyChecker>> check_classes) throws Exception {
		FDUtils.checkTrue(check_classes.size() > 0, "The checkers size should > 0.");
		
		//for single object
		if(this.objs.length == 1 && GenInputsAbstract.use_profile_over_value) {
		    AbstractPropertyChecker checker = new SameAbStatePropertyChecker(this.objs);
		    boolean result = checker.checkProperty();
		    FDUtils.checkTrue(result, "Single object must satisify the ab state property");
		    this.satisified_checkers.add(checker);
			return true;
		}
		
		List<AbstractPropertyChecker> all_satisified_checkers = new LinkedList<AbstractPropertyChecker>();
		for(Class<? extends AbstractPropertyChecker> check_class : check_classes) {
			//get the constructor
			Constructor<? extends AbstractPropertyChecker> constructor = check_class.getConstructor(Object[].class);
			FDUtils.checkNull(constructor, "The constructor should not be null.");
			Object[] args = new Object[]{this.objs};
			AbstractPropertyChecker checker = constructor.newInstance(args);
			if(checker.checkProperty()) {
				all_satisified_checkers.add(checker);
			}
		}
		//climb the lattice, which contains the following property checkers:
		//we have several properties following from strong to weak:
		//1. SingleValuePropertyChecker
		//   2. --> IntegerPropertyChecker
		//       3. --> SameAbStatePropertyChecker
		//          4. --> SameTypeChecker
		//          4. --> CollectionPropertyChecker
		//       3. --> Comparable PropertyChecker
		//5. BooleanVectorChecker
		for(AbstractPropertyChecker checker : all_satisified_checkers) {
			Class<?> clz = checker.getClass();
			if(clz.equals(SingleValuePropertyChecker.class)) {
				this.satisified_checkers.clear();
				this.satisified_checkers.add(checker);
				//add the only checker, which is single value
				break;
			} else if (clz.equals(ScalaPropertyChecker.class)) {
				boolean hasSingleValue = false;
				List<AbstractPropertyChecker> removes = new LinkedList<AbstractPropertyChecker>();
				for(AbstractPropertyChecker ae : this.satisified_checkers) {
					if(ae.getClass().equals(SingleValuePropertyChecker.class)) {
						hasSingleValue = true;
						//break;
					} else {
						removes.add(ae);
					}
				}
				//delete all subs
				this.satisified_checkers.removeAll(removes);
				if(!hasSingleValue) {
					//add this checker
					this.satisified_checkers.add(checker);
				}
			} else if (clz.equals(SameAbStatePropertyChecker.class)) {
				boolean hasIntegerOrSingleValue = false;
				List<AbstractPropertyChecker> removes = new LinkedList<AbstractPropertyChecker>();
				for(AbstractPropertyChecker ae : this.satisified_checkers) {
					if(ae.getClass().equals(SingleValuePropertyChecker.class) || ae.getClass().equals(ScalaPropertyChecker.class)) {
						hasIntegerOrSingleValue = true;
					} else if (ae.getClass().equals(SameTypeChecker.class) || ae.getClass().equals(CollectionPropertyChecker.class)) {
						removes.add(ae);
					}
				}
				//deletes all subs
				this.satisified_checkers.remove(removes);
				if(!hasIntegerOrSingleValue) {
					this.satisified_checkers.add(checker);
				}
			} else if (clz.equals(SameTypeChecker.class)) {
				//just add to the result checker
				if(!this.hasChecker(this.satisified_checkers, SingleValuePropertyChecker.class)
						&& !this.hasChecker(this.satisified_checkers, ScalaPropertyChecker.class)
						&& !this.hasChecker(this.satisified_checkers, SameAbStatePropertyChecker.class)) {
				   this.satisified_checkers.add(checker);
				}
			} else if (clz.equals(CollectionPropertyChecker.class)) {
				//just add to the result checker
				if(!this.hasChecker(this.satisified_checkers, SingleValuePropertyChecker.class)
						&& !this.hasChecker(this.satisified_checkers, ScalaPropertyChecker.class)
						&& !this.hasChecker(this.satisified_checkers, SameAbStatePropertyChecker.class)) {
				    this.satisified_checkers.add(checker);
				}
			} else if (clz.equals(ComparablePropertyChecker.class)) {
				//just add to the result checker
				if(!this.hasChecker(this.satisified_checkers, SingleValuePropertyChecker.class)
						&& !this.hasChecker(this.satisified_checkers, ScalaPropertyChecker.class)) {
				    this.satisified_checkers.add(checker);
				}
			} else {
				throw new RuntimeException("Unknown checker type: " + clz);
			}
		}
		
		//to see if we need to delete comparable checker
		Class<?> sameType = null;
		for(AbstractPropertyChecker checker : this.satisified_checkers) {
			if(checker instanceof SameTypeChecker) {
				sameType = ((SameTypeChecker)checker).getSameType();
				break;
			}
		}
		if(sameType != null && Reflection.canBeUsedAs(sameType, Comparable.class)) {
			AbstractPropertyChecker comparablechecker = null;
			for(AbstractPropertyChecker checker : this.satisified_checkers) {
				if(checker instanceof ComparablePropertyChecker) {
					comparablechecker = checker;
					break;
				}
			}
			if(comparablechecker != null) {
				this.satisified_checkers.remove(comparablechecker);
			}
		}
		
		//do pruning by heuristic
		if(GenInputsAbstract.aggressive_pruning) {
			this.prune_property_checker_by_heuristic();
		}
		
		//return true if the set of satisified checkers are not empty
		return !this.satisified_checkers.isEmpty();
	}
	
	private boolean hasChecker(List<AbstractPropertyChecker> checkers, Class<? extends AbstractPropertyChecker> clz) {
		for(AbstractPropertyChecker checker : checkers) {
			if(checker.getClass().equals(clz)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * output all checked properties as string 
	 * **/
	public String checkedPropertiesToString() {
		FDUtils.checkTrue(!this.satisified_checkers.isEmpty(), "The satisfied checker list is not empty.");
		StringBuilder sb = new StringBuilder();
		
		int count = 0;
		for(AbstractPropertyChecker checker : this.satisified_checkers) {
//			System.out.println(checker);
			if(count != 0) {
				sb.append(", or ");
			}
			sb.append(checker.propertyToString());
			count++;
		}
		
		return sb.toString();
	}
	
	private void prune_property_checker_by_heuristic() {
		if(this.satisified_checkers.isEmpty()) {
			return;
		}
		Set<AbstractPropertyChecker> removes = new LinkedHashSet<AbstractPropertyChecker>();
		for(AbstractPropertyChecker checker : this.satisified_checkers) {
			if(checker.getClass().equals(SingleValuePropertyChecker.class)) {
				if(checker.propertyToString().indexOf("$") != -1) {
					removes.add(checker);
				}
			}
			if(checker.propertyToString().trim().equals("")) {
				removes.add(checker);
			}
		}
		FDLog.log("Remove " + removes.size() + " checkers, which are: " + removes);
		this.satisified_checkers.removeAll(removes);
	}
}