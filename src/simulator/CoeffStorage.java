package simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoeffStorage {
  static Map<Integer , Integer> map = new HashMap<Integer , Integer>();
  // Key-value hour=key

  /**
   * Read the key-value pair from the file.
   * @param file = The target file.
   * @throws IllegalArgumentException = The stored data is wrong/ not fit the requirement.
   */
  @SuppressWarnings("resource")
  public static void readFile(File file) throws IllegalArgumentException {
    try {
    	int totalLine = 0;
      Scanner scanner = new Scanner(file);
      while (scanner.hasNext() & totalLine < 24) {
    	  totalLine++;
        int key = scanner.nextInt();
        int value = scanner.nextInt();
        if (key > 24 || value > 100 || key < 0 || value < 0) {
          throw new IllegalArgumentException("Key or Value Out of range");
        }
        System.out.println("Key read : " + key + " value : " + value);
        map.put(key , value);
      }
      
      if (totalLine != 24)
    	  throw new IllegalArgumentException("Missing or too many key-value pair");
      
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static int getCoeff(int hour) {
    return map.get(hour);
  }
}
