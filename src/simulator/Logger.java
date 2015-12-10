package simulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.joda.time.DateTime;

public class Logger {
  
  private final static String FILENAME = "log";
  static File log;
  
  public static void init(String time)
  {
	  log = new File(FILENAME + "-" + time + ".txt");
	  log.delete();
	  try {
		log.createNewFile();
	} catch (IOException e) {
		System.err.println("Error during creation of log file");
	}
  }
  
  /**
   * Creating Log and print log.
   */
  public static boolean createLog(String msg) {
	  System.out.println(msg);
	  PrintWriter out;
	  try {
		  out = new PrintWriter(new FileWriter(log, true));
		  out.append(msg + System.getProperty("line.separator"));
		  out.close();
	  } catch (IOException e) {
		  System.out.println("Could not log");
	  }
	  return true;
  }

}
