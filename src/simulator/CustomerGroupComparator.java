package simulator;

import java.util.Comparator;

public class CustomerGroupComparator implements Comparator<CustomerGroup> {

  @Override
  public int compare(CustomerGroup o1, CustomerGroup o2) {
    Integer target = o1.getSize();
    if(o1.getSize() == o2.getSize()) {
    	target = o1.getId();
    	return target.compareTo(o2.getId());
    } else {
    	return target.compareTo(o2.getSize());
    }
  }
}
