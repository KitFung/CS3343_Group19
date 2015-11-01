package simulator;

import org.joda.time.DateTime;


public class CustomerLeaveEvent extends CustomerEvent{

  public CustomerLeaveEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() {
	  System.out.format("%s leaves the restaurant" , super.getExecuteStatementHeader());
    //call the manager to free the desks
  }

}
