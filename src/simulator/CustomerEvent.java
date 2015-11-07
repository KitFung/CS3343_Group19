package simulator;

import org.joda.time.DateTime;

public abstract class CustomerEvent{

	protected DateTime executeTime;
	protected CustomerGroup cg;
	protected EventScheduler es = EventScheduler.getInstance();
	
	public CustomerEvent(DateTime dt, CustomerGroup cg) {
		executeTime = dt;
		this.cg = cg;
	}

	public DateTime getExecuteTime() {
		return executeTime;
	}

	abstract void execute();

	public void addToScheduler() {
		es.addEvent(this);
		
	}
	
	/**
	 * Example return : "13:00 : Group#5 "
	 **/
	protected String getExecuteStatementHeader()
	{
		return String.format("%s : Group#%d (%d ppl)", executeTime.toString("HH:mm") , cg.getId() , cg.getSize());
	}
}
