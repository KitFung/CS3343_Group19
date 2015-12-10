package simulatortest;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import simulator.CustomerGroup;
import simulator.CustomerState;
import simulator.SeatAssignAdvance;
import simulator.Table;

public class TestSeatAssignAdvance extends TestCase {
	ArrayList<Table> allTables;
	Table table1;
	Table table2;
	Table table3;
	
	@Before
	@Override
	public void setUp() {
		table1 = new Table(2);
		table2 = new Table(2);
		table3 = new Table(8);
		table1.add(new CustomerGroup(1, 2, new CustomerState("WAITING")));
		table3.add(new CustomerGroup(2, 2, new CustomerState("WAITING")));
		table3.add(new CustomerGroup(2, 4, new CustomerState("EATING")));
		allTables = new ArrayList<Table>();
		allTables.add(table1);
		allTables.add(table2);
		allTables.add(table3);
	}

	@Test
	public void testQuery1() {
		Boolean result = SeatAssignAdvance.query(new CustomerGroup(1, 4, new CustomerState("QUEUE")), allTables);
		assertTrue(result);
	}
	
	@Test
	public void testAssign1() {
		Table result = SeatAssignAdvance.assign(new CustomerGroup(1, 4, new CustomerState("QUEUE")), allTables);
		assertEquals(table3, result);
	}
	
	@Test
	public void testQuery2() {
		Boolean result = SeatAssignAdvance.query(new CustomerGroup(1, 6, new CustomerState("QUEUE")), allTables);
		assertFalse(result);
	}
	
	@Test
	public void testAssign2() {
		Table result = SeatAssignAdvance.assign(new CustomerGroup(1, 6, new CustomerState("QUEUE")), allTables);
		assertNull(result);
	}
}
