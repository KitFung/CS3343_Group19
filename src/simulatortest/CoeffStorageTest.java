package simulatortest;

import junit.framework.TestCase;
import org.junit.Test;

import simulator.CoeffStorage;

import java.io.File;

public class CoeffStorageTest extends TestCase{
  
  @Test
  public void testInvalid() {
    File file = new File("Coeff_Invalid.TXT");
    boolean thrown = false;
    try {
      CoeffStorage.readFile(file);
    } catch (IllegalArgumentException e) {
      thrown = true;
    }
    assertEquals(thrown , true);
  }
  
  
  @Test
  public void testValid() {
    File file = new File("Coeff.TXT");
    boolean thrown = false;
    try {
      CoeffStorage.readFile(file);
    } catch (IllegalArgumentException e) {
      thrown = true;
    }
    assertEquals(thrown , false);
  }
}
