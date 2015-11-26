package simulatortest;

import junit.framework.TestCase;
import junitx.framework.ListAssert;

import org.junit.Before;
import org.junit.Test;

import simulator.CustomerGroup;
import simulator.StateEating;
import simulator.StateInQueue;
import simulator.StateWaitingFood;
import simulator.Table;

import java.util.ArrayList;


public class TestTable extends TestCase {
  StateInQueue stateInQueue;
  StateWaitingFood stateWaitingFood;
  StateEating stateEating;
  ArrayList<CustomerGroup> expected;
  ArrayList<CustomerGroup> actual;
  
  @Before
  @Override
  public void setUp() {
    stateInQueue = new StateInQueue();
    stateWaitingFood = new StateWaitingFood();
    stateEating = new StateEating();
    expected = new ArrayList<CustomerGroup>();
    actual = new ArrayList<CustomerGroup>();
  }
  
  @Test
  public void testConstructor1() {
    Table table1 = new Table(2);
    assertTrue(table1 != null);
  }
  
  @Test
  public void testConstructor2() {
    Table table = new Table(4);
    assertTrue(table != null);
  }
  
  @Test
  public void testConstructor3() {
    Table table = new Table(8);
    assertTrue(table != null);
  }
  
  @Test 
  public void testConstructor4() {
    boolean thrown = false;
    try {
      new Table(0);
    } catch (IllegalArgumentException e) {
      thrown = true;
    } finally {
      assertTrue(thrown);
    }
  }
  
  @Test
  public void testAdd1() {
    Table table = new Table(4);
    int result = table.add(new CustomerGroup(1, 3, stateInQueue));
    assertEquals(0, result);
  }
  
  @Test
  public void testAdd2() {
    Table table = new Table(4);
    int result = table.add(new CustomerGroup(1, 5, stateInQueue));
    assertEquals(1, result);
  }
  
  @Test
  public void testAdd3() {
    Table table = new Table(4);
    table.add(new CustomerGroup(1, 3, new StateInQueue()));
    int result = table.add(new CustomerGroup(2, 3, stateInQueue));
    assertEquals(1, result);
  }
  
  @Test
  public void testRemove1() {
    Table table = new Table(4);
    int result = table.remove(new CustomerGroup(1, 3, stateWaitingFood));
    assertEquals(1, result);
  }
  
  @Test
  public void testRemove2() {
    Table table = new Table(4);
    CustomerGroup customer = new CustomerGroup(1, 3, stateWaitingFood);
    table.add(customer);
    int result = table.remove(customer);
    assertEquals(0, result);
  }
  
  @Test
  public void testGetAvailable() {
    Table table = new Table(8);
    table.add(new CustomerGroup(1, 3, stateWaitingFood));
    table.add(new CustomerGroup(2, 2, stateEating));
    table.add(new CustomerGroup(3, 2, stateEating));
    int result = table.getAvailable();
    assertEquals(4, result);
  }
  
  @Test
  public void testGetWatingCustomer() {
    Table table = new Table(8);
    CustomerGroup group1 = new CustomerGroup(1, 3, stateWaitingFood);
    CustomerGroup group2 = new CustomerGroup(2, 3, stateWaitingFood);
    CustomerGroup group3 = new CustomerGroup(3, 2, stateEating);
    table.add(group1);
    table.add(group2);
    table.add(group3);
    expected.add(group1);
    expected.add(group2);
    actual = table.getWaitingCustomers();
    ListAssert.assertEquals(expected, actual);
  }
  
  @Test
  public void testClearTable() {
    Table table = new Table(4);
    CustomerGroup group1 = new CustomerGroup(1, 2, stateWaitingFood);
    CustomerGroup group2 = new CustomerGroup(2, 2, stateWaitingFood);
    table.add(group1);
    table.add(group2);
    table.clearTable();
    actual = table.getWaitingCustomers();
    ListAssert.assertEquals(expected, actual);
  }
}
