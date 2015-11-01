package simulator;
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> Event + Main
=======

>>>>>>> Event update
import org.joda.time.DateTime;


public class CustomerFinishEvent extends CustomerEvent {

<<<<<<< HEAD
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
=======

  public CustomerFinishEvent(DateTime dt , CustomerGroup cg) {
    super(dt , cg);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() {
	  System.out.format("%s finished eating\n" , super.getExecuteStatementHeader());
    //cg.setState(new CustomerFinished());
  }
>>>>>>> Event update

}
