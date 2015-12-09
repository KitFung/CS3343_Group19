package simulatortest;

import junit.framework.TestCase;
import simulator.Logger;

public class TestLogger extends TestCase {
  /**
   *
   * @see junit.framework.TestCase#setUp()
   */

  @Override
  public void setUp() {}

  public void testCreateLog() {  
    boolean result = Logger.createLog("test");  
    assertEquals(result, true);
  }
}