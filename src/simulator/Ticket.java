package simulator;

import org.joda.time.DateTime;

class Ticket implements Comparable<Ticket> {
  
  private CustomerGroup gp;
  private DateTime time;
  private double priority;

  Ticket(CustomerGroup defaultGroup, DateTime startTime) {
    gp = defaultGroup;
    time = startTime;
    priority = 1;
  }
  
  public CustomerGroup getCustomer() {
    return this.gp;
  }
  
  public DateTime getTime() {
    return this.time;
  }
  
  public double getPriority() {
    return this.priority;
  }
  
  public CustomerGroup getCustomerGroup() {
    return this.gp;
  }
  
  public void updatePriority(double newPriority) {
    this.priority += newPriority;
  }

  @Override
  public int compareTo(Ticket otherTicket) {
    if (this.priority > otherTicket.getPriority()) {
      return 1;
    } else if (this.priority < otherTicket.getPriority()) {
      return -1;
    }
    return 0;
  }
}