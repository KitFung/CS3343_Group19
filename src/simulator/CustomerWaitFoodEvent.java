package simulator;

import org.joda.time.DateTime;


public class CustomerWaitFoodEvent extends CustomerEvent{

  private int tableNo;
  private Table t;
  
  public CustomerWaitFoodEvent(DateTime dt , CustomerGroup cg , Table table) {
    super(dt, cg);
    tableNo = table.getID();
    t = table;
  }

  @Override
  void execute() {
    cg.setState(new CustomerState("WAITING"));
    String log = String.format("%s sits in table#%d , waiting for food\n" , super.getExecuteStatementHeader() , tableNo);
    Logger.createLog(log);
  }

}
