package simulatortest;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import simulator.CustomerGroup;
import simulator.CustomerState;
import simulator.Table;

import java.util.ArrayList;

/*

public class TestTable extends TestCase {
  ArrayList<CustomerGroup> expected;
  ArrayList<CustomerGroup> actual;
  
  @Before
  @Override
  public void setUp() {
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
    int result = table.add(new CustomerGroup(1, 3, new CustomerState("QUEUE")));
    assertEquals(0, result);
  }
  
  @Test
  public void testAdd2() {
    Table table = new Table(4);
    int result = table.add(new CustomerGroup(1, 5, new CustomerState("QUEUE")));
    assertEquals(1, result);
  }
  
  @Test
  public void testAdd3() {
    Table table = new Table(4);
    table.add(new CustomerGroup(1, 3, new CustomerState("QUEUE")));
    int result = table.add(new CustomerGroup(2, 3, new CustomerState("QUEUE")));
    assertEquals(1, result);
  }
  
  @Test
  public void testRemove1() {
    Table table = new Table(4);
    int result = table.remove(new CustomerGroup(1, 3, new CustomerState("WAITING")));
    assertEquals(1, result);
  }
  
  @Test
  public void testRemove2() {
    Table table = new Table(4);
    CustomerGroup customer = new CustomerGroup(1, 3, new CustomerState("WAITING"));
    table.add(customer);
    int result = table.remove(customer);
    assertEquals(0, result);
  }
  
  @Test
  public void testGetAvailable() {
    Table table = new Table(8);
    table.add(new CustomerGroup(1, 3, new CustomerState("WAITING")));
    table.add(new CustomerGroup(2, 2, new CustomerState("EATING")));
    table.add(new CustomerGroup(3, 2, new CustomerState("EATING")));
    int result = table.getAvailable();
    assertEquals(4, result);
  }
  
  @Test
  public void testGetWatingCustomer() {
    Table table = new Table(8);
    CustomerGroup group1 = new CustomerGroup(1, 3, new CustomerState("WAITING"));
    CustomerGroup group2 = new CustomerGroup(2, 3, new CustomerState("WAITING"));
    CustomerGroup group3 = new CustomerGroup(3, 2, new CustomerState("EATING"));
    table.add(group1);
    table.add(group2);
    table.add(group3);
    expected.add(group1);
    expected.add(group2);
    actual = table.getWaitingCustomers();
    for(CustomerGroup e : actual) {
    	System.out.println(e.getId());
    }
    ListAssert.assertEquals(expected, actual);
  }
  
  @Test
  public void testClearTable() {
    Table table = new Table(4);
    CustomerGroup group1 = new CustomerGroup(1, 2, new CustomerState("WAITING"));
    CustomerGroup group2 = new CustomerGroup(2, 2, new CustomerState("WAITING"));
    table.add(group1);
    table.add(group2);
    table.clearTable();
    actual = table.getWaitingCustomers();
    ListAssert.assertEquals(expected, actual);
  }
}*/