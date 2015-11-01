package simulator;
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> Event + Main
=======

import java.io.File;
>>>>>>> Event update
import java.util.Map;


public class Main {

<<<<<<< HEAD
<<<<<<< HEAD
  Map<Integer , Integer> hourCoeff;

=======
  Map<Integer , Integer> hourCoeff;
  private static String FILE_NAME = "Coeff.TXT";
>>>>>>> Event update
  /**
   * The entry of this system.
   * @param args It suppose don't have any argument.
   */
  public static void main(String[] args) {
<<<<<<< HEAD
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
=======
	  System.out.println(System.getProperty("user.dir"));
	  File file = new File(FILE_NAME);
	  CoeffStorage.readFile(file);
    EventScheduler es = EventScheduler.getInstance();
    es.generateArriveEvents();
    es.executeEvents();
  }
>>>>>>> Event update

}
