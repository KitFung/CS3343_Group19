package simulator;

import java.io.File;

import org.joda.time.LocalTime;

public class Main {

  private static String FILE_NAME = "Coeff.TXT";

  /**
   * The entry of this system.
   * @param args It suppose don't have any argument.
   */
  public static void main(String[] args) {
	  RandomGenerator.setSeed(3343);
    File file = new File(FILE_NAME);
    CoeffStorage.readFile(file);
    
    EventScheduler es = EventScheduler.getInstance();
    Logger.createLog("--------------New Day Begin " + LocalTime.now().toDateTimeToday().toString("dd-MMMM") + "----------------");
    es.generateArriveEvents();
    es.executeEvents();

  }
}
