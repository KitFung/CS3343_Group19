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
    es = EventScheduler.getInstance();
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
    md.customerJoinQueue(cg, dt);
  }
  
  /**
   * Assign seat if someone is queuing.
   * @param dt = The current time.
   * @param changeAllowed = Whether it allow change seat.
   */
  public void stateUpdate(DateTime dt, boolean changeAllowed) {
    if (getRemainingSeats() > 0 && md.isAnyCustomer()) {
    	
      CustomerGroup cg = md.nextCustomer(dt);
      Table table = seatAssign(cg , changeAllowed);
      //System.out.println("Time : " + dt.toString("HH:mm"));
      //seatAssign(md.nextCustomer(dt), changeAllowed);
      //seatAssign(cg, changeAllowed);
      //System.out.println(getRemainingSeats() + "  " + getAvailableSeats());
      //for(Table t : allTables)
      //System.out.println(t.getRemaining());
      
      
      if(table == null)
    	  table = seatAssign(cg , true);
      //DateTime dtNew = dt.plusMinutes(5);
      if(table != null)
      {
		  Logger.createLog("Group#" + cg.getId() + " is assigned to Table#" + table.getID());
    	  es.generateWaitFoodEvent(cg,dt , table);
    	  printTableUsage();
      } else {
    	  //no seats for the customergroup even under advanced allocation
    	  System.out.printf("Missed Group# %d \n\n", cg.getId());
    	  //TODO MISSED_CUSTOMER_COUNT += cg.getSize()
      }
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
	  System.out.printf("Assigning %s\n\n", changeAllowed ? "ADVANCE" : "DEFAULT");
    if (changeAllowed) {
    	if (SeatAssignAdvance.query(customer, allTables)) {
    		Table table = SeatAssignAdvance.assign(customer, allTables);
    		ArrayList<CustomerGroup> currWaiting = table.getWaitingCustomers();
    		Collections.sort(currWaiting, new CustomerGroupComparator());
    		int remaining = table.getRemaining();
    		for (CustomerGroup e : currWaiting) {
    			if(e.getSize() + remaining >= customer.getSize() && customer.getSize() > e.getSize()) {
    				if(SeatAssignDefault.query(e, allTables)) {
    					table.remove(e);
    					table.add(customer);
    					customer.setState(new CustomerState("WAITING"));
        				seatAssign(e, false);
        				return table;
    				}
    			}
    		}
    	} else {
    		System.out.println("not enough seats\n");
    	}
    } else {
    	if (SeatAssignDefault.query(customer, allTables)) {
    		Table table = SeatAssignDefault.assign(customer, allTables);
    			table.add(customer);
    			customer.setState(new CustomerState("WAITING"));
            	return table;
        	} else {
        		System.out.println("not enough seats\n");
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
  
  @SuppressWarnings("unchecked")
public void printTableUsage()
  {
	  ArrayList<Table> sortedTables = (ArrayList<Table>) allTables.clone();
	  Collections.sort(sortedTables , new TableSizeComparator());
	  Logger.createLog("======= Table usage : ==========");
	  Logger.createLog(String.format("Total Seats remaining : %d", getRemainingSeats()));
	  for (Table t : sortedTables) {
		  String log = "";
		  log += String.format("Table#%d (size %d , remain %d) : ", t.getID() , t.getSize() , t.getRemaining());
		  for(CustomerGroup gp : t.getAllCustomers())
			  log += String.format("[%s]Group#%d (%d ppl) , ",gp.getState(), gp.getId() ,  gp.getSize());
		  Logger.createLog(log);
	  }
	  Logger.createLog("==============================");
  }
  
}
