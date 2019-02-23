package Iteration;
import java.util.Random;
/**
 * This program uses dart throwing to approximate the irrational constant pi.
 * @author eric_li
 *
 */
public class DartThrowing {

	public static void main(String[] args) {
		Random randomboi = new Random();
		double x = 0;
		double y = 0;
		double radius = 0;
		int counter = 0;
		double dartsThrown = 0;
		for (long i = 0; i <= 100000000; i ++) {
			x = randomboi.nextDouble() * 2 - 1;
			y = randomboi.nextDouble() * 2 - 1;
			radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			if (radius <= 1) {
				counter ++;
			}
			else {
				// Nothing
			}
			dartsThrown ++;
			
		}
		double pi = 4 * counter / dartsThrown;
		System.out.println(pi);
	}

}
