package simulator;

import org.joda.time.DateTime;

public class ManagerDesk {

  CustomerQueue queue;

  private ManagerDesk() {
    queue = new CustomerQueue();
  }
  
  private static class InstanceHolder {
    private static final ManagerDesk Instance = new ManagerDesk();
  }

  public static ManagerDesk getInstance() {
    return InstanceHolder.Instance;
  }
  
  public void customerJoinQueue(CustomerGroup gp, DateTime dt) {
    queue.joinQueue(gp, dt);
    queue.updatePriority(dt);
  }
  
  public boolean isAnyCustomer() {
    return queue.queueSize() > 0;
  }

  // if priority equal, the most fit group first

  public CustomerGroup nextCustomer(DateTime dt) {
    return queue.getNextTicket(dt).getCustomerGroup();
  }

}