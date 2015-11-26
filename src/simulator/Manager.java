package simulator;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Manager {

  //  private EventScheduler es;
  
  private ManagerDesk md;

  private ArrayList<Table> allTables;

  public Manager() {
    md = ManagerDesk.getInstance();
  }

  /**
   * add event to event scheduler.
   * @param dt the time of the event occur.
   * @param cg the related customer group.
   */

  public void add( DateTime dt , CustomerGroup cg) {
    // es = EventScheduler.getInstance();
    DateTime dtNew = dt.plusMinutes(5);
    md.customerJoinQueue(cg, dt);
    new CustomerWaitFoodEvent(dtNew , cg).addToScheduler();
  }
  
  /**
   * Assign seat if someone is queuing.
   * @param dt = The current time.
   * @param changeAllowed = Whether it allow change seat.
   */
  public void stateUpdate(DateTime dt, boolean changeAllowed) {
    if (getRemainingSeats() > 0 && md.isAnyCustomer()) {
      seatAssign(md.nextCustomer(dt), changeAllowed);
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
  public void seatAssign(CustomerGroup customer, Boolean changeAllowed) {
    if (changeAllowed) {
      Table table = SeatAssignAlgorithm.allowSeatChange(customer, allTables);
      if (table.getWaitingCustomers().size() > 0) {
        // ArrayList<CustomerGroup> waitingCustomers = table.getWaitingCustomers();
        table.clearTable();
        table.add(customer);
        for (CustomerGroup c : table.getWaitingCustomers()) {
          seatAssign(c, false);
        }
      } else {
        table.add(customer);
      }
    } else {
      SeatAssignAlgorithm.noSeatChange(customer, allTables).add(customer);
    }
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
