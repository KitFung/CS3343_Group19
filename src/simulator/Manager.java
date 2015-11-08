package simulator;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Manager {

  private EventScheduler es;

  private ArrayList<Table> allTables;

  public Manager() {
    //es = EventScheduler.getInstance();
  }

  /**
   * add event to event scheduler.
   * @param dt the time of the event occur.
   * @param cg the related customer group.
   */

  public void add( DateTime dt , CustomerGroup cg) {
    es = EventScheduler.getInstance();
    DateTime dtNew = dt.plusMinutes(5);
    new CustomerWaitFoodEvent(dtNew , cg).addToScheduler();
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
   * Assign seat to specific customer group.
   * @param customer - The CustomerGroup Object
   * @param changeAllowed - Whether it allow the manager to change the other customer seat.
   */
  public void seatAssign(CustomerGroup customer, Boolean changeAllowed) {
    if (changeAllowed) {
      Table table = SeatAssignAlgorithm.allowSeatChange(customer, allTables);
      if (table.getWaitingCustomers().size() > 0) {
        for (CustomerGroup c : table.getWaitingCustomers()) {
          seatAssign(c, false);
        }
      }
      table.add(customer);
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
