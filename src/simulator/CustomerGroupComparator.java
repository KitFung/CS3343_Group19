package simulator;

import java.util.Comparator;

public class CustomerGroupComparator implements Comparator<CustomerGroup> {

  @Override
  public int compare(CustomerGroup o1, CustomerGroup o2) {
    Integer target = o1.getSize();
    return target.compareTo(o2.getSize());
  }
}
