package simulator;
<<<<<<< HEAD

=======
>>>>>>> Event + Main
import org.joda.time.DateTime;


public class CustomerLeaveEvent extends CustomerEvent{

<<<<<<< HEAD
  public CustomerLeaveEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }

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

}
