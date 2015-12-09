package simulator;

import java.util.ArrayList;
import java.util.Collections;

public class SeatAssignAdvance {
	  public static boolean query(CustomerGroup customer,ArrayList<Table> allTables) {
	    Collections.sort(allTables, new TableAvailComparator());
	    for (Table t : allTables) {
	      if (t.getAvailable() >= customer.getSize()) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  public static Table assign(CustomerGroup customer,ArrayList<Table> allTables) {
	    Collections.sort(allTables, new TableAvailComparator());
	    for (Table t : allTables) {
	      if (t.getAvailable() >= customer.getSize()) {
	        return t;
	      }
	    }
	    return null;
	  }
}
