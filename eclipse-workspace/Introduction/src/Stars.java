import java.applet.*;
import java.awt.*;
import java.util.Random;

public class Stars extends Applet{

	public void paint(Graphics g) {
		Random myman = new Random();
		for (int i = 0; i < 10; i ++) {
			int r = myman.nextInt(100) + 40;
			int x1 = myman.nextInt(900);
			int y1 = myman.nextInt(700);
			
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
