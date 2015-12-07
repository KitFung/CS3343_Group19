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
    Logger.createLog(String.format("%s (Table#%d)Finishes meal and leaves\n" , super.getExecuteStatementHeader() , t.getID()));
    t.remove(cg);
  }

}
