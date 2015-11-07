package simulator;
<<<<<<< HEAD

=======
>>>>>>> Event + Main
import org.joda.time.DateTime;


public class CustomerFinishEvent extends CustomerEvent {

<<<<<<< HEAD

  public CustomerFinishEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() {
    //cg.setState(new CustomerFinished());
  }
=======
	
	public CustomerFinishEvent(DateTime dt , CustomerGroup cg) {
		super(dt , cg);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute() {
		//cg.setState(new CustomerFinished());
		
	}
>>>>>>> Event + Main

}
