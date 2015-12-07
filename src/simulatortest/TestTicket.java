package simulatortest;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.junit.Test;

import simulator.CustomerGroup;
import simulator.CustomerState;
import simulator.Ticket;

public class TestTicket extends TestCase{
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testArrange1() {
    Ticket ticket1 = new Ticket(new CustomerGroup(1, 8, new CustomerState("WAITING")), new DateTime());
    Ticket ticket2 = new Ticket(new CustomerGroup(1, 8, new CustomerState("WAITING")), new DateTime());
    ticket2.updatePriority(100);
    assertEquals(-1, ticket1.compareTo(ticket2));
  }
  
  @Test
  public void testArrange2() {
    Ticket ticket1 = new Ticket(new CustomerGroup(1, 8, new CustomerState("WAITING")), new DateTime());
    Ticket ticket2 = new Ticket(new CustomerGroup(1, 8, new CustomerState("WAITING")), new DateTime());
    assertEquals(0, ticket1.compareTo(ticket2));
  }
  
  @Test
  public void testArrange3() {
    Ticket ticket1 = new Ticket(new CustomerGroup(1, 8, new CustomerState("WAITING")), new DateTime());
    Ticket ticket2 = new Ticket(new CustomerGroup(1, 8, new CustomerState("WAITING")), new DateTime());
    ticket2.updatePriority(100);
    ticket1.updatePriority(200);
    assertEquals(1, ticket1.compareTo(ticket2));
  }
}
