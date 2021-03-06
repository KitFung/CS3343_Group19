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
      Scanner scanner = new Scanner(file);
      while (scanner.hasNext()) {
        int key = scanner.nextInt();
        int value = scanner.nextInt();
        if (key > 24 || value > 100 || key < 0 || value < 0) {
          throw new IllegalArgumentException();
        }
        System.out.println("Key read : " + key + " value : " + value);
        map.put(key , value);
      }
      for (Map.Entry<Integer , Integer> entry : map.entrySet()) {
        Integer key = entry.getKey(); 
        Integer value = entry.getValue();
        System.out.println(key + " : " + value);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static int getCoeff(int hour) {
    return map.get(hour);
  }
}
