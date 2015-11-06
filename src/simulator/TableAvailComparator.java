package simulator;

import java.util.Comparator;

class TableAvailComparator implements Comparator<Table> {

  @Override
  public int compare(Table o1, Table o2) {
    Integer target = o1.getAvailable(); 
    return target.compareTo(o2.getAvailable());
  }
}
