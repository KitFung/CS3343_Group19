package simulator;

import org.joda.time.DateTime;

import java.util.PriorityQueue;


class CustomerQueue {

  /**
   * Formula to calculate priority.
   * first condition: waited time / 60s
   * optional: level
   */

  PriorityQueue<Ticket> customerQueue;

  public CustomerQueue() {
    customerQueue = new PriorityQueue<Ticket>();
  }
  
  public int queueSize() {
    return this.customerQueue.size();
  }

  public void updatePriority(DateTime currentTime) {
    for (Ticket e:this.customerQueue) {
      double additionalPriority = 
          (e.getTime().getMillis() - currentTime.getMillis()) * 1000 / 60;
      e.updatePriority(additionalPriority);
    }
  }
  
  public void joinQueue(CustomerGroup gp, DateTime dt) {
    customerQueue.add(new Ticket(gp, dt));
  }

  public Ticket getNextTicket(DateTime currentTime) {
    this.updatePriority(currentTime);
    return customerQueue.poll();
  }
  
}