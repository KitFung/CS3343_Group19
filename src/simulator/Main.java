package simulator;

import java.io.File;
import java.util.Map;


public class Main {

  Map<Integer , Integer> hourCoeff;
  private static String FILE_NAME = "Coeff.TXT";
  /**
   * The entry of this system.
   * @param args It suppose don't have any argument.
   */
  public static void main(String[] args) {
	  System.out.println(System.getProperty("user.dir"));
	  File file = new File(FILE_NAME);
	  CoeffStorage.readFile(file);
    EventScheduler es = EventScheduler.getInstance();
    es.generateArriveEvents();
    es.executeEvents();
  }

}
