package simulator;

import org.joda.time.DateTime;

public class CustomerJoinQueueEvent extends CustomerEvent{

  Manager manager;
  CustomerGroup cg;
<<<<<<< HEAD

=======
>>>>>>> Event update
  /**
   * The event when the customer join the queue.
   * 
   * @param dt The time when the event happen.
   * @param cg The corresponding customer group.
   * @param manager The restaurant manager.
   */
  public CustomerJoinQueueEvent(DateTime dt , CustomerGroup cg , Manager manager) {
    super(dt , cg);
    this.manager = manager;
<<<<<<< HEAD
    System.out.println("a join quene event created to be executed at " + dt.toString());
    //QueueManager.appendCustomer
=======
    //System.out.println("a join quene event created to be executed at " + dt.toString("HH:mm"));
>>>>>>> Event update
  }

  @Override
  void execute() {
<<<<<<< HEAD
    //queueManger.add(super.cg);
    manager.add(super.executeTime, super.cg);
    System.out.println(super.getExecuteTime() + " : jqe executed");
=======
	  System.out.format("%s Joins the queue\n" , super.getExecuteStatementHeader());
    //queueManger.add(super.cg);
    manager.add(super.executeTime, super.cg);
>>>>>>> Event update
  }

}
