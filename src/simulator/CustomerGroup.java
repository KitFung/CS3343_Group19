package simulator;

import simulator.CustomerState.State;


public class CustomerGroup {
  private int id;
  private int number;
  private CustomerState state;
  
  /**
   * Constructor.
   */
  public CustomerGroup(int aaId, int aaNumber, CustomerState aaState) {
    this.id = aaId;
    this.number = aaNumber;
    this.state = aaState;
  }
  
  public int getSize() {
    return this.number;
  }
  
  public int getId() {
    return this.id;
  }
  
  public State getState() {
    return this.state.getState();
  }
  
  public void setState(CustomerState newState) {
    this.state = newState;
  }
}