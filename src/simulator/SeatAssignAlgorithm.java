package simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SeatAssignAlgorithm {
  /**
   * Call it when seat change is allowed.
   * @param customer - the group who need seat.
   * @param allTables - all the tables.
   * @return - which table is available to this group.
   */
  public static Table allowSeatChange(CustomerGroup customer, ArrayList<Table> allTables) {
    Collections.sort(allTables, new TableAvailComparator());
    
    for (Table t : allTables) {
      if (customer.getSize() <= t.getAvailable()) {
        return t;
      }
    }
    return null;
  }
  
  /**
   * Call it when seat change is not allowed.
   * @param customer - the group who need seat.
   * @param allTables - all the tables.
   * @return - which table is available to this group.
   */
  public static Table noSeatChange(CustomerGroup customer,ArrayList<Table> allTables) {
    Collections.sort(allTables, new TableRemainingComparator());
    
    for (Table t : allTables) {
      if (customer.getSize() <= t.getRemaining()) {
        return t;
      }
    }
    return null;
  }
}
