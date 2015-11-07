package simulator;
<<<<<<< HEAD

=======
>>>>>>> Event + Main
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
	//int randomNum = rand.nextInt((max - min) + 1) + min;
	
	public CustomerWaitFoodEvent(DateTime dt , CustomerGroup cg) {
		super(dt, cg);
		
		
	}

	@Override
	void execute() {
		//cg.setState(new CustomerWaitingFood());
		System.out.println(super.executeTime.toString() + " a customer is waiting for food");
		
		EventScheduler es = EventScheduler.getInstance();
		int waitTime = RandomGenerator.getWaitFoodTime();
		DateTime dtEat = super.getExecuteTime().plusMinutes(waitTime);
		
		CustomerEatingEvent cee = new CustomerEatingEvent(dtEat , super.cg);
		es.addEvents(cee);
		
		int eatTime = RandomGenerator.getEatingTime();
		DateTime dtFinish = dtEat.plusMinutes(eatTime);
		CustomerFinishEvent cfe = new CustomerFinishEvent(dtFinish , cg);
		es.addEvents(cfe);
		
	}
>>>>>>> Event + Main

}
