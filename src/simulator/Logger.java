package simulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.joda.time.DateTime;

public class Logger {
  
  private final static String FILENAME = "log";
  static File log;

  /**
   * Creating Log and print log.
   */
  public static boolean createLog(String msg) {
    System.out.println(msg);
    
    log = new File(FILENAME + ".txt");
    try {
      if (!log.exists()) {
        log.createNewFile();
      }
      PrintWriter out = new PrintWriter(new FileWriter(log, true));
      out.append(msg + System.getProperty("line.separator"));
      out.close();
    } catch (IOException e) {
      System.out.println("Could not log.");
    }
    return true;
  }
    
}
