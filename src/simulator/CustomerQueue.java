package simulator;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;


public class CustomerQueue {

  ArrayList<Ticket> customerQueue;

  public CustomerQueue() {
    customerQueue = new ArrayList<Ticket>();
  }
  
  public int queueSize() {
    return this.customerQueue.size();
  }

  /**
   * Update the priority to all the customer group in the queue.
   * Formula to calculate priority.
   * first condition: (current time - start queue time) / 60s
   * @param currentTime The time when call updatePriority.
   */
  public void updatePriority(DateTime currentTime) {
    for (Ticket e:this.customerQueue) {
      double additionalPriority = 
          (currentTime.getMillis() - e.getTime().getMillis()) / 1000 / 60;
      e.updatePriority(additionalPriority);
    }
  }
  
  public void joinQueue(CustomerGroup gp, DateTime dt) {
    customerQueue.add(new Ticket(gp, dt));
  }

  /**
   * Get the next ticket in queue  according to priority.
   * If no more ticker, return null.
   * @param currentTime The time when you get next ticket.
   * @return next ticket if Ticket exist else null
   */
  public Ticket getNextTicket(DateTime currentTime) {
    this.updatePriority(currentTime);
    if (customerQueue.size() == 0) {
      return null;
    }
    Collections.sort(customerQueue,  Collections.reverseOrder());
    return customerQueue.remove(0);
  }
  
}