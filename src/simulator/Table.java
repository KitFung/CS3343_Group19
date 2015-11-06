package simulator;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

//enum Tablesize{
//  TWO(2),
//  FOUR(4),
//  EIGHT(8);
//  
//  private int size;
//  
//  private Tablesize(int size)
//  {
//    this.size=size;
//  }
//}

public class Table {

  private static final int[] availTableSize = {2, 4, 8};
  
  private int size;
  private int occupied;
  private ArrayList<CustomerGroup> allCustomers;
  
  /**
   * @Todo - create core concise exception class.
   * @param size - should be one of 2, 4, 8
   * @throws Exception - Not valid params - size
   */
  public Table(int size) throws IllegalArgumentException {
    if (ArrayUtils.contains(availTableSize, size)) {
      this.size = size;
      allCustomers = new ArrayList<CustomerGroup>();
      occupied = 0;
    } else {
      throw new IllegalArgumentException("Invalid table size");
    }
  }
  
  /**
   * Add new customer group to table.
   * @param customer - the new customergroup.
   * @return success/ failure.
   */
  public int add(CustomerGroup customer) {
    if (customer.getSize() + occupied > size) {
      return 1;
    } else {
      occupied += customer.getSize();
      allCustomers.add(customer);
      return 0;
    }
  }
  
  /**
   * Remove the specific group from table.
   * @param customer - the specific customer group.
   * @return success/failure
   */
  public int remove(CustomerGroup customer) {
    if (allCustomers.contains(customer)) {
      if (customer.getSize() > occupied) {
        return 1;
      } else {
        occupied -= customer.getSize();
        allCustomers.remove(customer);
        return 0;
      }
    }
    return 1;
  }
  
  public int getSize() {
    return size;
  }
  
  public int getOccupied() {
    return occupied;
  }
  
  public int getRemaining() {
    return size - occupied;
  }
  
  /**
   * Get the current available table (include the waiting food customer).
   * @return the number of available size.
   */
  public int getAvailable() {
    int avail = 0;
    for (CustomerGroup customer : allCustomers) {
      if (customer.getState() instanceof StateWaitingFood) {
        avail += customer.getSize();
      }
    }
    return avail;
  }
  
  /**
   * Find out how many customer group are waiting for food in this table.
   * @return the Arraylist of customer who are waiting food.
   */
  public ArrayList<CustomerGroup> getWaitingCustomers() {
    ArrayList<CustomerGroup> availCustomers = new ArrayList<CustomerGroup>();
    for (CustomerGroup customer : allCustomers) {
      if (customer.getState() instanceof StateWaitingFood) {
        availCustomers.add(customer);
      }
    }
    return availCustomers;
  }
  
  public void clearTable() {
    allCustomers.clear();
  }
}
