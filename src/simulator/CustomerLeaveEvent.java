package simulator;
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> Event + Main
=======

>>>>>>> Event update
import org.joda.time.DateTime;


public class CustomerLeaveEvent extends CustomerEvent{

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Event update
  public CustomerLeaveEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }
<<<<<<< HEAD

  @Override
  void execute() {
    //call the manager to free the desks
  }
=======
	public CustomerLeaveEvent(DateTime dt , CustomerGroup cg) {
		super(dt , cg);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute() {
		//call the manager to free the desks
		
	}
>>>>>>> Event + Main
=======

  @Override
  void execute() {
	  System.out.format("%s leaves the restaurant" , super.getExecuteStatementHeader());
    //call the manager to free the desks
  }
>>>>>>> Event update

}
