package simulator;
import Events;

import java.util.*;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class EventScheduler {

  private static int OPEN_HOUR = 8;
  private static int TOTAL_HOUR = 1;
  private ArrayList<CustomerEvent> eventlist;
  DateTime dt = new DateTime(2015 , 10 , 15 , OPEN_HOUR , 0);
  //private Map<Integer , Integer> hourCoeff;
  // Key-value hour=key
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
    }
  }
  
  public void addEvents(CustomerEvent event) {
    eventlist.add(event);
    Collections.sort(eventlist , new DateTimeComparator());
  }

  /**
   * Generate arrive event with random time and customer.
   */
  public void generateArriveEvents() {
    for (int i = 0 ; i < TOTAL_HOUR ; i++) {
      // <-- should be generated by RNG ( how many customers )
      int count = generateTotalCustomersInhour(10 , i);
      //for(int j = 0 ; j < shutdown ; j++)    <-- outermost hour , inner : no. in minutes.
      for (int j = 0; j < count ; j++) {
        Random rng = new Random();
        DateTime jqeTime = dt.plusMinutes(rng.nextInt(61));
        CustomerGroup cg = new CustomerGroup(j, 1, new StateInQueue());
        CustomerJoinQueueEvent jqe = new CustomerJoinQueueEvent(jqeTime , cg , manager);
        addEvents(jqe);
      }
    }
  }

  private int generateTotalCustomersInhour(int coeff , int hour) {
    return 5;
  }
}
