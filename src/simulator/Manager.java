package simulator;

import org.joda.time.DateTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class Manager {

  private EventScheduler es;
  
  private ManagerDesk md;

  private ArrayList<Table> allTables;

  public Manager() {
    md = ManagerDesk.getInstance();
    allTables = new ArrayList<Table>();
    readTables();
  }

  public void readTables()
  {
	    try {
	    	File file = new File("Table.TXT");
	        Scanner scanner = new Scanner(file);
	        while (scanner.hasNext()) {
	          Table t = new Table(scanner.nextInt());
	          allTables.add(t);
	        }
	        scanner.close();
	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      }
  }
  
  /**
   * add event to event scheduler.
   * @param dt the time of the event occur.
   * @param cg the related customer group.
   */

  public void add( DateTime dt , CustomerGroup cg) {
    es = EventScheduler.getInstance();
    DateTime dtNew = dt.plusMinutes(5);
    md.customerJoinQueue(cg, dt);
    //es.generateWaitFoodEvent(cg,dtNew , table);
  }
  
  /**
   * Assign seat if someone is queuing.
   * @param dt = The current time.
   * @param changeAllowed = Whether it allow change seat.
   */
  public void stateUpdate(DateTime dt, boolean changeAllowed) {
    if (getRemainingSeats() > 0 && md.isAnyCustomer()) {
    	
      CustomerGroup cg = md.nextCustomer(dt);
      System.out.println("Time : " + dt.toString("HH:mm"));
      //seatAssign(md.nextCustomer(dt), changeAllowed);
      seatAssign(cg, changeAllowed);
      System.out.println(getRemainingSeats() + "  " + getAvailableSeats());
      for(Table t : allTables)
    	  System.out.println(t.getRemaining());
      
      Table table = seatAssign(cg , changeAllowed);
      //if(table == null)
    	  //table = seatAssign(cg , true);
      //DateTime dtNew = dt.plusMinutes(5);
      //if(table != null)
      es.generateWaitFoodEvent(cg,dt , table);
    }
  }
  
  public ArrayList<Table> getAllTables() {
    return allTables;
  }
  
  /**
   * Get current total remaining seat.
   * @return The remaining seat of ALL tables
   */
  public int getRemainingSeats() {
    int totalRemain = 0;
    for (Table t : allTables) {
      totalRemain += t.getRemaining();
    }
    return totalRemain;
  }
  
  public int getAvailableSeats() {
	    int totalRemain = 0;
	    for (Table t : allTables) {
	      totalRemain += t.getAvailable();
	    }
	    return totalRemain;
	  }
  
  /**
   * Get current total remaining seat in each table.
   * @return An array of integer.
   */
  public ArrayList<Integer> getRemainingSeatsInEachTable() {
    ArrayList<Integer> totalRemain = new ArrayList<Integer>();
    for (Table t : allTables) {
      totalRemain.add(t.getAvailable());
    }
    return totalRemain;
  }

  /**
   * Assign seat to specific customer group.
   * @param customer - The CustomerGroup Object
   * @param changeAllowed - Whether it allow the manager to change the other customer seat.
   */
  public Table seatAssign(CustomerGroup customer, Boolean changeAllowed) {
	  System.out.printf("Assign %s\n", changeAllowed ? "ADVANCE" : "DEFAULT");
    if (changeAllowed) {
    	if (SeatAssignAdvance.query(customer, allTables)) {
    		Table table = SeatAssignAdvance.assign(customer, allTables);
    		ArrayList<CustomerGroup> currWaiting = table.getWaitingCustomers();
    		Collections.sort(currWaiting, new CustomerGroupComparator());
    		int remaining = table.getRemaining();
    		for (CustomerGroup e : currWaiting) {
    			if(e.getSize() + remaining >= customer.getSize()) {
    				table.remove(e);
    				int i = table.add(customer);
    				customer.setState(new CustomerState("WAITING"));
    				seatAssign(e, false);
    				if (i == 0) System.out.printf("ADVANCE Customer added to table, %d, %d\n", getRemainingSeats(), getAvailableSeats());
    				return table;
    			}
    		}
    	} else {
    		System.out.println("not enough seats");
    	}
    } else {
    	if (SeatAssignDefault.query(customer, allTables)) {
    		Table table = SeatAssignAdvance.assign(customer, allTables);
    		int i = table.add(customer);
    		customer.setState(new CustomerState("WAITING"));
    		if (i == 0) System.out.printf("DEFAULT Customer added to table, %d, %d\n", getRemainingSeats(), getAvailableSeats());
    		return table;
    	} else {
    		System.out.println("not enough seats");
    	}
    }
    return null;
  }
  
  public void seatRelease(CustomerGroup customer, Table table) {
    table.remove(customer);
  }
  
  /**
   * Return all the customer who was seated.
   * @return all the customer.
   */
  public ArrayList<CustomerGroup> getAllCustomerGroups() {
    ArrayList<CustomerGroup> allCustomerGroups = new ArrayList<CustomerGroup>();
    
    for (Table t : allTables) {
      for (CustomerGroup c : t.getWaitingCustomers()) {
        allCustomerGroups.add(c);
      }
    }
    
    return allCustomerGroups;
  }
}
