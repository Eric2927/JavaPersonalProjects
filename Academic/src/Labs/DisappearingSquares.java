package Labs;
import java.awt.*;
import java.applet.*;

/**
 * This class draws multiple squares of decreasing side lengths.
 * @author eric_li
 *
 */
public class DisappearingSquares extends Applet {
	
	public void paint(Graphics g) {
		int startingSide = 200;
		int screenWidth = 1000;
		drawSquare(g, 0, 100, startingSide, screenWidth);
		drawSquare2(g, 400, startingSide, screenWidth);
	}
	
	public void drawSquare(Graphics g, int x, int y, int s, int screenWidth) {
		if (s < 4 || x + s > screenWidth) {
			return;
		}
		else {
			g.fillRect(x, y, s, s);	
			drawSquare(g, x + s + 10, y + s - (int)(s * 3 / 4), s * 3 / 4, screenWidth);
		}
	}
	
	public void drawSquare2(Graphics g, int y, int s, int rightCorner) {
		if (s < 4 || rightCorner - s < 0) {
			return;
		}
		else {
			g.fillRect(rightCorner - s, y, s, s);
			drawSquare2(g, y, s * 3 / 4, rightCorner - s - 10);
		}
	}
}
