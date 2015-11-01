package simulator;
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> Event + Main
=======

>>>>>>> Event update
import org.joda.time.DateTime;


public class CustomerEatingEvent extends CustomerEvent {

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Event update
  public CustomerEatingEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }
<<<<<<< HEAD

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
=======

  @Override
  void execute() {
	  System.out.format("%s is eating\n" , super.getExecuteStatementHeader());
    //cg.setState(new CustomerEating);
  }
>>>>>>> Event update

}
