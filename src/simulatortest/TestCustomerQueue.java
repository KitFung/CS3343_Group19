package simulatortest;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.junit.Test;

import simulator.CustomerGroup;
import simulator.CustomerQueue;
import simulator.CustomerState;

public class TestCustomerQueue extends TestCase{

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testNullQueuing() {
    CustomerQueue customerQueue = new CustomerQueue();
    customerQueue.updatePriority(new DateTime());
    assertEquals(0, customerQueue.queueSize());
  }
  
  @Test
  public void testGetNextTicket1() {
    CustomerQueue customerQueue = new CustomerQueue();
    DateTime dt = new DateTime();
    DateTime dt2 = dt.minusHours(1);
    DateTime dt3 = dt.plusHours(1);
    
    CustomerGroup gp1 = new CustomerGroup(1, 2, new CustomerState("QUEUE"));
    CustomerGroup gp2 = new CustomerGroup(2, 4, new CustomerState("QUEUE"));
    customerQueue.joinQueue(gp1, dt);
    customerQueue.joinQueue(gp2, dt2);
    assertEquals(gp2, customerQueue.getNextTicket(dt3).getCustomerGroup());
  }
  
  @Test
  public void testGetNextTicket2() {
    CustomerQueue customerQueue = new CustomerQueue();
    DateTime dt = new DateTime();
    DateTime dt2 = dt.minusHours(1);
    DateTime dt3 = dt.plusHours(1);
    
    CustomerGroup gp1 = new CustomerGroup(1, 2, new CustomerState("QUEUE"));
    CustomerGroup gp2 = new CustomerGroup(2, 4, new CustomerState("QUEUE"));
    customerQueue.joinQueue(gp2, dt2);
    customerQueue.joinQueue(gp1, dt);
    assertEquals(gp2, customerQueue.getNextTicket(dt3).getCustomerGroup());
  }
  
  @Test
  public void testGetNextTicket3() {
    CustomerQueue customerQueue = new CustomerQueue();
    DateTime dt = new DateTime();
    DateTime dt2 = dt.minusSeconds(10);
    DateTime dt3 = dt.plusSeconds(10);
    
    CustomerGroup gp1 = new CustomerGroup(1, 2, new CustomerState("QUEUE"));
    CustomerGroup gp2 = new CustomerGroup(2, 4, new CustomerState("QUEUE"));
    customerQueue.joinQueue(gp1, dt);
    customerQueue.joinQueue(gp2, dt2);
    assertEquals(gp1, customerQueue.getNextTicket(dt3).getCustomerGroup());
  }
  
  @Test
  public void testGetNextTicket4() {
    CustomerQueue customerQueue = new CustomerQueue();
    DateTime dt = new DateTime();
    DateTime dt2 = dt.minusSeconds(10);
    DateTime dt3 = dt.plusSeconds(10);
    
    CustomerGroup gp1 = new CustomerGroup(1, 2, new CustomerState("QUEUE"));
    CustomerGroup gp2 = new CustomerGroup(2, 4, new CustomerState("QUEUE"));
    customerQueue.joinQueue(gp2, dt2);
    customerQueue.joinQueue(gp1, dt);
    assertEquals(gp2, customerQueue.getNextTicket(dt3).getCustomerGroup());
  }
}
