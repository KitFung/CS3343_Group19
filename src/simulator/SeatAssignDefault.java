package simulator;

import java.util.ArrayList;
import java.util.Collections;

public class SeatAssignDefault {
	  public static boolean query(CustomerGroup customer,ArrayList<Table> allTables) {
	    Collections.sort(allTables, new TableRemainingComparator());
	    for (Table t : allTables) {
	      if (t.getRemaining() >= customer.getSize()) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  public static Table assign(CustomerGroup customer,ArrayList<Table> allTables) {
	    Collections.sort(allTables, new TableRemainingComparator());
	    for (Table t : allTables) {
	      if (t.getRemaining() >= customer.getSize())  {
	        return t;
	      }
	    }
	    return null;
	  }
}
