package simulator;

import java.util.ArrayList;
import java.util.Collections;

public class SeatAssignDefault {
	  public static boolean query(CustomerGroup customer,ArrayList<Table> allTables) {
	    Collections.sort(allTables, new TableRemainingComparator());
	    
	    for (Table t : allTables) {
	      if (customer.getSize() <= t.getRemaining()) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	
	  public static Table assign(CustomerGroup customer,ArrayList<Table> allTables) {
	    Collections.sort(allTables, new TableRemainingComparator());
	    
	    for (Table t : allTables) {
	      if (customer.getSize() <= t.getRemaining()) {
	        return t;
	      }
	    }
	    return null;
	  }
}
