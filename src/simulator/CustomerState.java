package simulator;

public class CustomerState {
	public enum State {
		EMPTY, QUEUE, WAITING, EATING
	}
	
	private State foo;
	
	public CustomerState(String arg) {
		stateChange(arg);
	}
	
	public void stateChange(String arg) {
		arg.toUpperCase();
		switch(arg) {
		case "QUEUE":
			foo = State.QUEUE;
			break;
		case "WAITING":
			foo = State.WAITING;
			break;
		case "EATING":
			foo = State.EATING;
			break;
		default:
			foo = State.EMPTY;
			break;
		}
	}
	
	public State getState() {
		return foo;
	}
	
  @Override
	  public String toString() {
		  return foo.toString();
	  }
}
