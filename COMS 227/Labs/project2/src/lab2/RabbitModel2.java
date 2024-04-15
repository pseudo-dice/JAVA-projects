package lab2;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel2
{
  // TODO - add instance variables as needed
	private int rabbitPopulation;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel2()
  {
    // TODO
	  rabbitPopulation =0;
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
	  rabbitPopulation ++;
	// Check if 5 years have passed, if so, reset the population to 0
      if (rabbitPopulation % 5 == 0) {
          rabbitPopulation = 0;
      }
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
