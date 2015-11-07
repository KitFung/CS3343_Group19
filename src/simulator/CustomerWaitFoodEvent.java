package simulator;

import org.joda.time.DateTime;


public class CustomerWaitFoodEvent extends CustomerEvent{

<<<<<<< HEAD
  //int randomNum = rand.nextInt((max - min) + 1) + min;

  public CustomerWaitFoodEvent(DateTime dt , CustomerGroup cg) {
    super(dt, cg);
  }

  @Override
  void execute() {
    //cg.setState(new CustomerWaitingFood());
    int waitTime = RandomGenerator.getWaitFoodTime();
    int eatTime = RandomGenerator.getEatingTime();
    EventScheduler es = EventScheduler.getInstance();
    DateTime dtEat = super.getExecuteTime().plusMinutes(waitTime);
    DateTime dtFinish = dtEat.plusMinutes(eatTime);
    CustomerEatingEvent cee = new CustomerEatingEvent(dtEat , super.cg);
    CustomerFinishEvent cfe = new CustomerFinishEvent(dtFinish , cg);
  
    System.out.println(super.executeTime.toString() + " a customer is waiting for food");

    es.addEvents(cee);
    es.addEvents(cfe);

  }
=======
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
>>>>>>> Event update

}
