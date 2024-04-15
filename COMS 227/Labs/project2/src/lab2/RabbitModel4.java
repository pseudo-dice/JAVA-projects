package lab2;

import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel4
{
  // TODO - add instance variables as needed
	private int rabbitPopulation;
	private Random randomGenerator;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel4()
  {
    // TODO
	  rabbitPopulation =0;
	  randomGenerator = new Random();
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // TODO - returns a dummy value so code will compile
    return rabbitPopulation;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
    // TODO
	  int randomIncrease = randomGenerator.nextInt(10);
	  rabbitPopulation += randomIncrease;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // TODO
	  rabbitPopulation =0;
  }
}
