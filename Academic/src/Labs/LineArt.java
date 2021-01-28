package Labs;
// LineArt.java
// Student version of the Lab06 Line Art Graphics Program assignment.
import java.awt.*;
import java.applet.*;

public class LineArt extends Applet{
	public void paint(Graphics g){ 
		int width = 980;
		int height = 630;
		g.drawRect(10,10,width,height);
		for (int i = 1; i <= 52; i ++) {
			// The y-coordinates for the second point should have +10 mathematically, but it looks better without it.
			g.drawLine(990 - i * (width/52), 640, 990, i * (height/52));
			g.drawLine(i * (width/52) + 10, 640, 10, i * (height/52));
			g.drawLine(i * (width/52) + 10, 10, 990, i * (height/52));
			g.drawLine(990 - i * (width/52), 10, 10, i * (height/52));
		}
		int width1 = 510;
		int height1 = 300;
		g.drawRect(245, 176, width1, height1);
		for (int j = 1; j <= 26; j ++) {
			g.drawLine(width1 + 245 - j * (width1/26), height1 + 176, width1 + 245, 180 + j * (height1/26));
			g.drawLine(j * (width1/26) + 245, height1 + 176, 245, 176 + j * (height1/26));
			g.drawLine(j * (width1/26) + 245, 176, width1 + 245, 176 + j * (height1/26));
			g.drawLine(width1 + 245 - j * (width1/26), 176, 245, 176 + j * (height1/26));
		}
		
	}
}
