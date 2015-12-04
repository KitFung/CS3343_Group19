package simulator;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;


public class EventScheduler {

  private ArrayList<CustomerEvent> eventlist;
  DateTime dt = new DateTime(2015 , 10 , 15 , 0 , 0);
  private Manager manager;
  
  
  private static EventScheduler instance = new EventScheduler();

  public static EventScheduler getInstance() {
    return instance;
  }

  private EventScheduler() {
    eventlist = new ArrayList<CustomerEvent>();
    manager = new Manager();
  }

  /**
   * Execute the events which place inside the eventlist.
   */
  public void executeEvents() {
    while (eventlist.size() > 0) {
      eventlist.get(0).execute();
      eventlist.remove(0);
      manager.stateUpdate(dt, true);
    }
  }

  public void addEvent(CustomerEvent ce) {
    eventlist.add(ce);
    Collections.sort(eventlist , new DateTimeComparator());
  }

  /**
   * Generate arrive event with random time and customer.
   */
  public void generateArriveEvents() {
    int id = 0;
    for (int hour = 0 ; hour < 24 ; hour++) {
      int hourCoeff = CoeffStorage.getCoeff(hour);
      int totalCustomerInHour = RandomGenerator.getTotalCustomerGroupInHour(hourCoeff);
      for (int i = 0; i < totalCustomerInHour ; i++) {
        int customersInGroup = RandomGenerator.getCustomerInGroup();
        DateTime jqeTime = dt.plusMinutes(RandomGenerator.getJoinQueueTime());
        CustomerGroup cg = new CustomerGroup(id, customersInGroup, new CustomerState("QUEUE"));
        CustomerJoinQueueEvent jqe = new CustomerJoinQueueEvent(jqeTime , cg , manager);
        generateJoinQueueEvent(cg , jqeTime);
        id++;
      }
      dt = dt.plusHours(1);
    }
  }
  
  public void generateJoinQueueEvent(CustomerGroup cg , DateTime executeTime)
  {
	  CustomerJoinQueueEvent jqe = new CustomerJoinQueueEvent(executeTime , cg , manager);
	  addEvent(jqe);
  }
  
  public void generateWaitFoodEvent(CustomerGroup cg , DateTime executeTime , Table table)
  {
	  CustomerWaitFoodEvent cwe = new CustomerWaitFoodEvent(executeTime , cg , table);
	  addEvent(cwe);
	  
	  int waitTime = RandomGenerator.getWaitFoodTime();
	  int eatTime = RandomGenerator.getEatingTime();
	  DateTime dtEat = executeTime.plusMinutes(waitTime);
	  DateTime dtFinish = dtEat.plusMinutes(eatTime);
	    
	  generateEatingEvent(cg, dtEat);
	  generateLeaveEvent(cg, dtFinish, table);
  }

  public void generateEatingEvent(CustomerGroup cg , DateTime executeTime)
  {
	  CustomerEatingEvent cee = new CustomerEatingEvent(executeTime , cg);
	  addEvent(cee);
  }
  
  public void generateFinishEvent(CustomerGroup cg , DateTime executeTime , Table table)
  {
	  
  }
  
  public void generateLeaveEvent(CustomerGroup cg , DateTime executeTime , Table table)
  {
	  CustomerLeaveEvent cle = new CustomerLeaveEvent(executeTime , cg , table);
	  addEvent(cle);
  }
}
