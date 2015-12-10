package simulator;

import org.joda.time.DateTime;

import simulator.CustomerEvent;


public class CustomerEatingEvent extends CustomerEvent {

  public CustomerEatingEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() {
	  cg.setState(new CustomerState("EATING"));
	  Logger.createLog(String.format("%s is having meal\n" , super.getExecuteStatementHeader()));
  }
  
}
