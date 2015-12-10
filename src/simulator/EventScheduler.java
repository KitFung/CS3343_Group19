package simulator;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class EventScheduler {

  private ArrayList<CustomerEvent> eventlist;
  DateTime dt = new LocalDate().toDateTimeAtStartOfDay();
  private Manager manager;
  private Map<Integer , Integer> customerServed;	//hour-value pair
  private Map<Integer , Integer> customerGroupServed;	//hour-value pair
  private Map<Integer , Integer> waitTime; 			//hour-value pair
  private Map<Integer , Integer> customerInQueue;
  
  
  private static EventScheduler instance = new EventScheduler();

  public static EventScheduler getInstance() {
    return instance;
  }

  private EventScheduler() {
    eventlist = new ArrayList<CustomerEvent>();
    manager = new Manager();
    customerGroupServed = new HashMap<Integer , Integer>();
    customerServed = new HashMap<Integer , Integer>();
    waitTime = new HashMap<Integer , Integer>();
    customerInQueue = new HashMap<Integer , Integer>();
    
    for(int i = 0 ; i < 24 ; i ++)
    {
    	waitTime.put(i, 0);
    	customerInQueue.put(i, 0);
    	customerServed.put(i , 0);
    	customerGroupServed.put(i,0);
    	customerInQueue.put(i,0);
    	
    }
  }

  /**
   * Execute the events which place inside the eventlist.
   */
  public void executeEvents() {
	  DateTime executeTime;
	  CustomerEvent ce;
	  int hour = 0;
	  int executeHour;
    while (eventlist.size() > 0) {
    	ce = eventlist.get(0);
    	executeTime = ce.getExecuteTime();
    	executeHour = Integer.parseInt(executeTime.toString("HH"));
    	
    	if(hour < executeHour)
    	{
    		if(customerGroupServed.get(hour) > 0)
    			Logger.createLog(String.format("Customers served in %d:00 = %d", hour , customerServed.get(hour)));
    		
    		customerInQueue.put(hour, ManagerDesk.getInstance().getCustomerInQueue());    		
    		hour = executeHour;
    		Logger.createLog("-------" + hour + ":00 -------");
    		Logger.createLog("Number of customerGroups in queue : " + ManagerDesk.getInstance().getCustomerInQueue());
    	}
    	ce.execute();
    	eventlist.remove(0);
    	manager.stateUpdate(executeTime, false);
    }
    
    printDayEndEventReport();
  }

  private void printDayEndEventReport()
  {
	  int total = 0 , totalGroup = 0 , totalWaitTime = 0;
	  float overallAvgWaitTime;
	  Logger.createLog("=================End of the Day==================");
	  Logger.createLog("--------DayEnd Report---------");
	  for(int i = 0 ; i < 24 ; i++)
	  {
		  int c = customerServed.get(i);
		  if(c > 0)
		  {
			  int cg = customerGroupServed.get(i);
			  int t = waitTime.get(i);
			  float average;
			  
			  total += c;
			  totalGroup += cg;
			  totalWaitTime += t;
			  average = cg == 0 ? 0 : (float)t/cg;

			  Logger.createLog(String.format("average queuing time at %d:00 = %f minutes" , i , average));
		  }
		  
		  if(customerInQueue.get(i) > 0)
			  Logger.createLog(String.format("Number of customers in queue at %d:00 = %d" , i , customerInQueue.get(i)));
	  }
	  overallAvgWaitTime = (float) totalWaitTime / totalGroup;
	  
	  Logger.createLog("Total CustomerGroup Served : " + totalGroup);
	  Logger.createLog("Total Customer Served : " + total);
	  Logger.createLog("Overall queuing time : " + overallAvgWaitTime + " minutes");
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
    int totalInHour , totalGroupsInHour , hourCoeff , customersInGroup;
    DateTime jqeTime;
    CustomerGroup cg;
    
    for (int hour = 0 ; hour < 24 ; hour++) {
      hourCoeff = CoeffStorage.getCoeff(hour);
      totalGroupsInHour = RandomGenerator.getTotalCustomerGroupInHour(hourCoeff);
      totalInHour = 0;
      for (int i = 0; i < totalGroupsInHour ; i++) {
        customersInGroup = RandomGenerator.getCustomerInGroup();
        jqeTime = dt.plusMinutes(RandomGenerator.getJoinQueueTime());
        cg = new CustomerGroup(id, customersInGroup, new CustomerState("QUEUE"));
        generateJoinQueueEvent(cg , jqeTime);
        totalInHour += customersInGroup;
        
        id++;
      }
      
      customerServed.put(Integer.parseInt(dt.toString("HH")), totalInHour);
      customerGroupServed.put(Integer.parseInt(dt.toString("HH")), totalGroupsInHour);
      dt = dt.plusHours(1);
    }
  }

  public void generateJoinQueueEvent(CustomerGroup cg , DateTime executeTime)
  {
	  CustomerJoinQueueEvent jqe = new CustomerJoinQueueEvent(executeTime , cg , manager);
	  addEvent(jqe);
	  cg.setJoinQueueTime(executeTime);
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
	  extractWaitTimeInQueue(cg.getJoinQueueTime() , executeTime);
  }

  public void generateEatingEvent(CustomerGroup cg , DateTime executeTime)
  {
	  CustomerEatingEvent cee = new CustomerEatingEvent(executeTime , cg);
	  addEvent(cee);
  }
  
  public void generateLeaveEvent(CustomerGroup cg , DateTime executeTime , Table table)
  {
	  CustomerLeaveEvent cle = new CustomerLeaveEvent(executeTime , cg , table);
	  addEvent(cle);
  }
  
  private void extractWaitTimeInQueue(DateTime start , DateTime end)
  {
	  System.out.println(start.toString() + " ; " + end.toString());
	  
	  Integer hour = start.getHourOfDay();
	  Integer diff = waitTime.get(hour);
	  Integer difference = (Minutes.minutesBetween(start, end).getMinutes());
	  diff += difference;
	  
	  System.out.println(hour + " ; " + diff + " ; " + difference);
	  waitTime.put(hour, diff);
  }
}
