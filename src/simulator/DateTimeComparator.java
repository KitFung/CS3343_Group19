package simulator;

import java.util.Comparator;

public class DateTimeComparator implements Comparator<CustomerEvent> {

  @Override
  public int compare(CustomerEvent ce1, CustomerEvent ce2) {
    return ce1.getExecuteTime().compareTo(ce2.getExecuteTime());
  }

}
