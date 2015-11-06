package simulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
  
  private static String fileName;
  static File log;
  
  public Logger(String fn) {
    fileName = fn;
    
  }
  
  /**
   * Creating Log and print log.
   */
  public static void createLog(String msg) {
    System.out.println(msg);
    
    log = new File(fileName + ".txt");
    try {
      if (log.exists() == false) {
        log.createNewFile();
      }
      PrintWriter out = new PrintWriter(new FileWriter(log, true));
      out.append(msg + System.getProperty("line.separator"));
      out.close();
    } catch (IOException e) {
      System.out.println("Could not log.");
    }
    
  }
    
}
