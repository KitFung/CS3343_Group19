package simulator;

import org.joda.time.DateTime;


public class CustomerWaitFoodEvent extends CustomerEvent{

  private int tableNo;
  private Table t;
  
  public CustomerWaitFoodEvent(DateTime dt , CustomerGroup cg , Table table) {
    super(dt, cg);
    tableNo = table.getSize();
    t = table;
  }

  @Override
  void execute() {
    //cg.setState(new CustomerWaitingFood());
    System.out.format("%s sits in table#%d , waiting for food\n" , 
        super.getExecuteStatementHeader() , tableNo);
  }

}
