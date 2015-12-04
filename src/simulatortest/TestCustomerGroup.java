package simulatortest;

import junit.framework.TestCase;

import org.junit.Test;

import simulator.CustomerGroup;
import simulator.CustomerState;
import simulator.StateEating;
import simulator.StateInQueue;
import simulator.StateWaitingFood;


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
    CustomerGroup customer = new CustomerGroup(1, 2, new StateEating());
    int result = customer.getSize();
    assertEquals(2, result);
  }

  //Test Constructor and getId()
  @Test
  public void test2() {
    CustomerGroup customer = new CustomerGroup(1, 2, new StateWaitingFood());
    int result = customer.getId();
    assertEquals(1, result);
  }

  //Test Constructor and getState()
  @Test
  public void test3() {
    CustomerState inQueue = new StateInQueue();
    CustomerGroup customer = new CustomerGroup(1, 2, inQueue);
    CustomerState result = customer.getState();
    assertEquals(inQueue, result);
  }

  //Test Constructor, setState() and getState()
  @Test
  public void test4() {
    CustomerState inQueue = new StateInQueue();
    CustomerState waitingFood = new StateWaitingFood();
    CustomerGroup customer = new CustomerGroup(1, 2, inQueue);
    customer.setState(waitingFood);
    CustomerState result = customer.getState();
    assertEquals(waitingFood, result);
  }

}
