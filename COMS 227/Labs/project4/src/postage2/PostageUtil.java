package postage2;
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
	  double cost;
	  if (weight <= 1) {
		        cost = .47;
	  }
		    else {
		        if (weight > 1) {
		            cost = .47 + ceil(weight - 1) * .21;}
		        else {
		        	if (weight > 3.5) {
		            cost = .94 + ceil(weight - 1) * .21;}
		        	else {
		        		cost = .47 + ceil(weight - 1) * .21;
		        	}
		    }
		    }
	return cost;
}
}