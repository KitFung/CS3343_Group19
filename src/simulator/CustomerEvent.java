package simulator;

import org.joda.time.DateTime;

public abstract class CustomerEvent{

	protected DateTime executeTime;
	protected CustomerGroup cg;
	
	public CustomerEvent(DateTime dt, CustomerGroup cg) 
	{
		executeTime = dt;
		this.cg = cg;
	}
	
	public DateTime getExecuteTime()
	{
		return executeTime;
	}
	
	abstract void execute();
}
