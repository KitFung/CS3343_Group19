package simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoeffStorage {
	static Map<Integer , Integer> map = new HashMap<Integer , Integer>();
	// Key-value hour=key
	
	
	public static void readFile(File file)
	{
		try {
			//Todo : add exception checking for > 0 , < 24
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext())
			{
				map.put(scanner.nextInt(), scanner.nextInt());
				//scanner.nextLine();
			}
			
			for (Map.Entry<Integer , Integer> entry : map.entrySet()) 
			{ 
				Integer key = entry.getKey(); 
				Integer value = entry.getValue();
				System.out.println(key + " : " + value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static int getCoeff(int hour)
	{
		return map.get(hour);
	}
}
