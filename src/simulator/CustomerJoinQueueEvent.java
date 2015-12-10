package simulator;

import org.joda.time.DateTime;

public class CustomerJoinQueueEvent extends CustomerEvent{

  Manager manager;
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
  }

  @Override
  void execute() {
    Logger.createLog(String.format("%s Joins the queue\n" , super.getExecuteStatementHeader()));
    manager.add(super.executeTime, super.cg);
  }

}
