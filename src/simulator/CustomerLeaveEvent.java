package simulator;

import org.joda.time.DateTime;


public class CustomerLeaveEvent extends CustomerEvent{

	Table t;
	
  public CustomerLeaveEvent(DateTime dt , CustomerGroup cg , Table table) {
    super(dt , cg);
    t = table;
  }

  @Override
  void execute() {
    System.out.format("%s Finishes meal and leaves the restaurant" , super.getExecuteStatementHeader());
    t.remove(cg);
  }

}
