package simulator;

import java.util.Comparator;

public class TableSizeComparator implements Comparator<Table> {

	  @Override
	  public int compare(Table o1, Table o2) {
		  Integer id = o1.getID();
		  Integer size = o1.getSize();
		  if(size.equals(o2.getSize()))
			  return id.compareTo(o2.getID());
		  else
			  return size.compareTo(o2.getSize());
	  }
}
