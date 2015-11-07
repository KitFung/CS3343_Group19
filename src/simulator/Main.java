package simulator;

<<<<<<< HEAD
=======
import java.io.File;
>>>>>>> Event update
import java.util.Map;


public class Main {

  Map<Integer , Integer> hourCoeff;
<<<<<<< HEAD

=======
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
=======
	  System.out.println(System.getProperty("user.dir"));
	  File file = new File(FILE_NAME);
	  CoeffStorage.readFile(file);
    EventScheduler es = EventScheduler.getInstance();
>>>>>>> Event update
    es.generateArriveEvents();
    es.executeEvents();
  }

}
