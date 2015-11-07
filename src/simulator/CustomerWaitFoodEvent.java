package simulator;

import org.joda.time.DateTime;


public class CustomerWaitFoodEvent extends CustomerEvent{

	private int tableNo;

	public CustomerWaitFoodEvent(DateTime dt , CustomerGroup cg) {
		super(dt, cg);
		tableNo = -1;
	}

	@Override
	void execute() {
		//cg.setState(new CustomerWaitingFood());
		System.out.format("%s sits in table#%d , waiting for food\n" , super.getExecuteStatementHeader() , tableNo);
		int waitTime = RandomGenerator.getWaitFoodTime();
		int eatTime = RandomGenerator.getEatingTime();
		DateTime dtEat = super.getExecuteTime().plusMinutes(waitTime);
		DateTime dtFinish = dtEat.plusMinutes(eatTime);
		CustomerEatingEvent cee = new CustomerEatingEvent(dtEat , super.cg);
		CustomerFinishEvent cfe = new CustomerFinishEvent(dtFinish , cg);

		cee.addToScheduler();
		cfe.addToScheduler();
	}

}
