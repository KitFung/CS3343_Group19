package simulator;

import java.util.Random;


public class RandomGenerator {
  public static final int MAX_WAIT_FOOD_MIN = 10;
  public static final int MIN_WAIT_FOOD_MIN = 3;

  public static final int MAX_MEAL_TIME_MIN = 40;
  public static final int MIN_MEAL_TIME_MIN = 10;
  //int randomNum = rand.nextInt((max - min) + 1) + min;

  static Random rnd = new Random();

  public static int getWaitFoodTime() {
    return rnd.nextInt(MAX_WAIT_FOOD_MIN - MIN_WAIT_FOOD_MIN + 1) + MIN_WAIT_FOOD_MIN;
  }

  public static int getEatingTime() {
    return rnd.nextInt(MAX_MEAL_TIME_MIN - MIN_MEAL_TIME_MIN + 1) + MIN_MEAL_TIME_MIN;
  }
}
