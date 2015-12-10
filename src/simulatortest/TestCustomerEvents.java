package simulatortest;

import java.io.File;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import simulator.CoeffStorage;
import simulator.CustomerEatingEvent;
import simulator.CustomerEvent;
import simulator.CustomerGroup;
import simulator.CustomerJoinQueueEvent;
import simulator.CustomerState;
import simulator.EventScheduler;
import simulator.Logger;
import simulator.CustomerState.State;
import simulator.Table;
import junit.framework.TestCase;

public class TestCustomerEvents  extends TestCase {
	
	@Before
    public void setUp() {
		File file = new File("Coeff.TXT");
		CoeffStorage.readFile(file);
		Logger.init("ForTestCase");
    }
	
	  @Test
	  public void testEatingEvent() {
		
		EventScheduler es = EventScheduler.getInstance();
		DateTime dt = new DateTime();
	    CustomerGroup cg = new CustomerGroup(1, 2, new CustomerState("EMPTY"));
		es.generateEatingEvent(cg, dt);
		es.executeEvents();
		
		CustomerState eating = new CustomerState("EATING");
		State result = cg.getState();
	    assertEquals(eating.getState() , result);
	  }
	  
	  @Test
	  public void testWaitingEvent() {
			EventScheduler es = EventScheduler.getInstance();
			DateTime dt = new DateTime();
		    CustomerGroup cg = new CustomerGroup(1, 2, new CustomerState("EMPTY"));
		    cg.setJoinQueueTime(dt);
		    
			es.generateWaitFoodEvent(cg, dt, new Table(2));
			es.executeEvents();
			
			CustomerState waiting = new CustomerState("WAITING");
			State result = cg.getState();
		    assertEquals(waiting.getState() , result);
	  }
}
