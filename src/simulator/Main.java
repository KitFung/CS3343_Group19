package simulator;
<<<<<<< HEAD

=======
>>>>>>> Event + Main
import java.util.Map;


public class Main {

<<<<<<< HEAD
  Map<Integer , Integer> hourCoeff;

  /**
   * The entry of this system.
   * @param args It suppose don't have any argument.
   */
  public static void main(String[] args) {
    EventScheduler es = EventScheduler.getInstance();
    //To do : read coeff from text file.
    es.generateArriveEvents();
    es.executeEvents();
  }
=======
	Map<Integer , Integer> hourCoeff;
	
	public static void main(String[] args) {
		EventScheduler es = EventScheduler.getInstance();
		//To do : read coeff from text file.
		es.generateArriveEvents();
		es.executeEvents();
		
	}
	
>>>>>>> Event + Main

}
