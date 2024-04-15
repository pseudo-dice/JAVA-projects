package lab2;

/**
 * Try out the Basketball class.
 */
public class BasketballTest
{
  /**
   * Entry point.
   */
  public static void main(String[] args)
  {
    // Construct an instance and examine its attributes
    Basketball b = new Basketball(4.0);
    System.out.println(b.getDiameter());
    System.out.println(b.isDribbleable());
    
    // Construct another instance with a diameter of 6
    Basketball b2 = new Basketball(6.0);
    
    // Deflate the first one
    b.deflate();
    //Inflate the second one
    b2.inflate();
    
    // First one is now dribbleable
    System.out.println("First basketball: " + b.isDribbleable());
    
    // Second one is unchanged
    System.out.println("Second basketball: " + b2.isDribbleable());
    
  }
}