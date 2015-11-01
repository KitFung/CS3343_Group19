package simulator;

<<<<<<< HEAD
=======
import java.io.File;
>>>>>>> Event update
import java.util.Map;


public class Main {

  Map<Integer , Integer> hourCoeff;
  private static String FILE_NAME = "Coeff.TXT";

  /**
   * The entry of this system.
   * @param args It suppose don't have any argument.
   */
  public static void main(String[] args) {
    EventScheduler es = EventScheduler.getInstance();
    //To do : read coeff from text file.
	  System.out.println(System.getProperty("user.dir"));
	  File file = new File(FILE_NAME);
	  CoeffStorage.readFile(file);
    EventScheduler es = EventScheduler.getInstance();
    es.generateArriveEvents();
    es.executeEvents();
  }

}
