package simulator;

import org.joda.time.DateTime;

import simulator.CustomerState.State;


public class CustomerGroup {
  private int id;
  private int number;
  private CustomerState state;
  private DateTime joinQueueTime = null;
  
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
  
  public void setJoinQueueTime(DateTime t)
  {
	  this.joinQueueTime = t;
  }
  
  public DateTime getJoinQueueTime()
  {
	  return this.joinQueueTime;
  }
}