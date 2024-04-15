package lab2;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel3
{
  // TODO - add instance variables as needed
	private int rabbitPopulation;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel3()
  {
    // TODO
	  rabbitPopulation =500;
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
	  rabbitPopulation /=2;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // TODO
	  rabbitPopulation =500;
  }
}
