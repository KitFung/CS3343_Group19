package simulatortest;

import junit.framework.TestCase;

import org.junit.Test;

import simulator.CustomerGroup;
import simulator.CustomerGroupComparator;
import simulator.CustomerState;
import simulator.CustomerState.State;


public class TestCustomerGroup extends TestCase {
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  //Test Constructor and getSize()
  @Test
  public void test1() {
    CustomerGroup customer = new CustomerGroup(1, 2, new CustomerState("EATING"));
    int result = customer.getSize();
    assertEquals(2, result);
  }

  //Test Constructor and getId()
  @Test
  public void test2() {
    CustomerGroup customer = new CustomerGroup(1, 2, new CustomerState("WAITING"));
    int result = customer.getId();
    assertEquals(1, result);
  }

  //Test Constructor and getState()
  @Test
  public void test3() {
    CustomerState inQueue = new CustomerState("QUEUE");
    CustomerGroup customer = new CustomerGroup(1, 2, inQueue);
    State result = customer.getState();
    assertEquals(inQueue.getState(), result);
  }

  //Test Constructor, setState() and getState()
  @Test
  public void test4() {
    CustomerState inQueue = new CustomerState("QUEUE");
    CustomerState waitingFood = new CustomerState("WAITING");
    CustomerGroup customer = new CustomerGroup(1, 2, inQueue);
    customer.setState(waitingFood);
    State result = customer.getState();
    assertEquals(waitingFood.getState(), result);
  }

  @Test 
  public void test5() {
	  CustomerState defState = new CustomerState("TEST");
	  CustomerGroup customer = new CustomerGroup(1, 2, defState);
	  assertEquals("EMPTY", customer.getState().toString());
  }
  
  //Test Comparator
  @Test
  public void test6() {
	  CustomerState inQueue = new CustomerState("QUEUE");
	  CustomerGroup cg1 = new CustomerGroup(1, 2, inQueue);
	  CustomerGroup cg2 = new CustomerGroup(2, 2, inQueue);
	  int result = new CustomerGroupComparator().compare(cg1, cg2);
	  assertTrue( result < 0);
  }
  
  //Test Comparator
  @Test
  public void test7() {
	  CustomerState inQueue = new CustomerState("QUEUE");
	  CustomerGroup cg1 = new CustomerGroup(1, 2, inQueue);
	  CustomerGroup cg2 = new CustomerGroup(2, 4, inQueue);
	  int result = new CustomerGroupComparator().compare(cg1, cg2);
	  assertTrue( result < 0);
  }
}
