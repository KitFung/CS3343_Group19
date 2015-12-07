package simulatortest;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import simulator.CustomerGroup;
import simulator.CustomerState;
import simulator.Table;
import simulator.TableAvailComparator;
import simulator.TableRemainingComparator;

public class TestTableComparator extends TestCase{
  Table table1;
  Table table2;
  TableAvailComparator availComparator;
  TableRemainingComparator remainingComparator;
  

  @Before
  @Override
  public void setUp() {
    table1 = new Table(8);
    table2 = new Table(8);
    availComparator = new TableAvailComparator();
    remainingComparator = new TableRemainingComparator();
  }

  @Test
  public void testAvailComparator1() {
    table1.add(new CustomerGroup(0, 4, new CustomerState("WAITING")));
    table2.add(new CustomerGroup(1, 3, new CustomerState("WAITING")));
    table2.add(new CustomerGroup(2, 2, new CustomerState("EATING")));
    int result = availComparator.compare(table1, table2);
    assertTrue(result > 0);
  }
  
  @Test
  public void testRemainingComparator() {
    table1.add(new CustomerGroup(0, 6, new CustomerState("WAITING")));
    table2.add(new CustomerGroup(1, 3, new CustomerState("WAITING")));
    table2.add(new CustomerGroup(2, 2, new CustomerState("EATING")));
    int result = remainingComparator.compare(table1, table2);
    assertTrue(result < 0);
  }
}

