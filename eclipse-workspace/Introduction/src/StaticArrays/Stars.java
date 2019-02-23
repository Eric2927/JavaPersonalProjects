package StaticArrays;
import java.applet.*;
import java.awt.*;
import java.util.Random;
/**
 * This program draws 10 stars of random sizes at random positions in a java applet.
 * @author eric_li
 *
 */
public class Stars extends Applet{

	public void paint(Graphics g) {
		Random completelyRandom = new Random();
		for (int i = 0; i < 10; i ++) {
			int r = completelyRandom.nextInt(100) + 40;
			int x1 = completelyRandom.nextInt(900);
			int y1 = completelyRandom.nextInt(700);
			
			int[] x = new int[10];
			int[] y = new int[10];
			int counter = 0;
			for (int theta = 18; theta < 360; theta += 72) {
				x[counter] = x1 + (int)(r * Math.cos(Math.toRadians(theta)));
				x[counter + 1] = x1 + (int)((r/3) * Math.cos(Math.toRadians(theta + 36)));
				y[counter] = y1 - (int)(r * Math.sin(Math.toRadians(theta)));
				y[counter + 1] = y1 - (int)((r/3) * Math.sin(Math.toRadians(theta + 36)));
				counter += 2;
			}
		g.fillPolygon(x, y, 10);
		}
	}

}
