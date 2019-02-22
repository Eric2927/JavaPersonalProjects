import java.awt.*;
import java.applet.*;

/**
 * This class draws a fractal of rectangles.
 * @author eric_li
 *
 */
public class SquareFractal extends Applet {
	
	public void paint(Graphics g) {
		drawSquare1(g, 1024, 768);
	}
	
	public void drawSquare1(Graphics g, int maxX, int maxY) {
		int midX = maxX/2;
		int midY = maxY/2;
		int startWidth = maxX/4;
		int startHeight = maxY/4;
		int tlX = midX - (startWidth/2);
		int tlY = midY - (startHeight/2);
		g.fillRect(tlX, tlY, startWidth, startHeight);
		drawTopLeft(g, tlX, tlY, startWidth, startHeight, maxX, maxY);
		drawTopRight(g, tlX, tlY, startWidth, startHeight, maxX, maxY);
		drawBottomLeft(g, tlX, tlY, startWidth, startHeight, maxX, maxY);
		drawBottomRight(g, tlX, tlY, startWidth, startHeight, maxX, maxY);
		
	}
	
	public void drawTopLeft(Graphics g, int ogX, int ogY, int ogWidth, int ogHeight, int maxX, int maxY) {
		int width = ogWidth / 2;
		int height = ogHeight / 2;
		int x = ogX - width;
		int y = ogY - height;
		if (x < 0 || y < 0 || width < 1 || height < 1) {
			return;
		}
		else {
			g.fillRect(x, y, width, height);
			drawTopLeft(g, x, y, width, height, maxX, maxY);
			drawTopRight(g, x, y, width, height, maxX, maxY);
			drawBottomLeft(g, x, y, width, height, maxX, maxY);
		}
		
	}
	
	public void drawTopRight(Graphics g, int ogX, int ogY, int ogWidth, int ogHeight, int maxX, int maxY) {
		int width = ogWidth / 2;
		int height = ogHeight / 2;
		int x = ogX + ogWidth;
		int y = ogY - height;
		if (x > maxX || y < 0 || width < 1 || height < 1) {
			return;
		}
		else {
			g.fillRect(x, y, width, height);
			drawTopLeft(g, x, y, width, height, maxX, maxY);
			drawTopRight(g, x, y, width, height, maxX, maxY);
			drawBottomRight(g, x, y, width, height, maxX, maxY);
		}
	}
	
	public void drawBottomLeft(Graphics g, int ogX, int ogY, int ogWidth, int ogHeight, int maxX, int maxY) {
		int width = ogWidth / 2;
		int height = ogHeight / 2;
		int x = ogX - width;
		int y = ogY + ogHeight;
		if (x < 0 || y > maxY || width < 1 || height < 1) {
			return;
		}
		else {
			g.fillRect(x, y, width, height);
			drawTopLeft(g, x, y, width, height, maxX, maxY);
			drawBottomLeft(g, x, y, width, height, maxX, maxY);
			drawBottomRight(g, x, y, width, height, maxX, maxY);
		}
	}
	
	public void drawBottomRight(Graphics g, int ogX, int ogY, int ogWidth, int ogHeight, int maxX, int maxY) {
		int width = ogWidth / 2;
		int height = ogHeight / 2;
		int x = ogX + ogWidth;
		int y = ogY + ogHeight;
		if (x > maxX || y > maxY || width < 1 || height < 1) {
			return;
		}
		else {
			g.fillRect(x, y, width, height);
			drawTopRight(g, x, y, width, height, maxX, maxY);
			drawBottomLeft(g, x, y, width, height, maxX, maxY);
			drawBottomRight(g, x, y, width, height, maxX, maxY);
		}
	}
	
	public void delay(long n) {
		n *= 1000;
		long startDelay = System.nanoTime();
		long endDelay = 0;
		while (endDelay - startDelay < n) {
			endDelay = System.nanoTime();
		}
	}

}
