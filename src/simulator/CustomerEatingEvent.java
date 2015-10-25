package simulator;

import org.joda.time.DateTime;


public class CustomerEatingEvent extends CustomerEvent {

	public CustomerEatingEvent(DateTime dt , CustomerGroup cg) {
		super(dt , cg);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute() {
		//cg.setState(new CustomerEating);
		
	}
}
