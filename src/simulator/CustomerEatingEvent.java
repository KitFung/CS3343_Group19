package simulator;
<<<<<<< HEAD

=======
>>>>>>> Event + Main
import org.joda.time.DateTime;


public class CustomerEatingEvent extends CustomerEvent {

<<<<<<< HEAD
  public CustomerEatingEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() {
    //cg.setState(new CustomerEating);
  }
=======
	public CustomerEatingEvent(DateTime dt , CustomerGroup cg) {
		super(dt , cg);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute() {
		//cg.setState(new CustomerEating);
		
	}
>>>>>>> Event + Main

}
