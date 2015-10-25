package simulator;

import org.joda.time.DateTime;


public class CustomerFinishEvent extends CustomerEvent {
	
	public CustomerFinishEvent(DateTime dt , CustomerGroup cg) {
		super(dt , cg);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute() {
		//cg.setState(new CustomerFinished());
		
	}

}
