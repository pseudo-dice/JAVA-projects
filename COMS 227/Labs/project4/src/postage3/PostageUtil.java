package postage3;
import static java.lang.Math.ceil;
public class PostageUtil
{
  /**
   * Returns the cost of postage for a letter of the given weight.
   * @param weight
   *   given weight in ounces
   * @return
   *   cost of postage for the weight
   */
  public static double computePostage(double weight)
  {
    // TODO
	  double cost = 0.47;
	  if (weight >1) {
		  cost = cost + ceil(weight - 1) * .21;
	  }
		        if (weight > 3.5) {
		            cost = cost+ 0.47;
		        }
		    
	return cost;
}
}