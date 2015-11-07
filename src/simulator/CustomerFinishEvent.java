package simulator;

import org.joda.time.DateTime;


public class CustomerFinishEvent extends CustomerEvent {
<<<<<<< HEAD
	
	public CustomerFinishEvent(DateTime dt , CustomerGroup cg) {
		super(dt , cg);
		// TODO Auto-generated constructor stub
	}
=======

>>>>>>> Event update

  public CustomerFinishEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() {
	  System.out.format("%s finished eating\n" , super.getExecuteStatementHeader());
    //cg.setState(new CustomerFinished());
  }

}
