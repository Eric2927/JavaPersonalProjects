// Lab04
// The AWT Graphics Program
import java.awt.*;
import java.applet.*;

public class AWTGraphics extends Applet {
	public void paint(Graphics g) {
		// DRAW CUBE
		g.drawRect(100, 100, 300, 300);
		g.drawRect(180, 180, 300, 300);
		g.drawLine(100, 100, 180, 180);
		g.drawLine(400, 100, 480, 180);
		g.drawLine(100, 400, 180, 480);
		g.drawLine(400, 400, 480, 480);
		
		// DRAW SPHERE
		g.drawOval(140, 140, 300, 300);
		
		g.drawOval(140, 160, 300, 260);
		g.drawOval(140, 180, 300, 220);
		g.drawOval(140, 200, 300, 180);
		g.drawOval(140, 220, 300, 140);
		g.drawOval(140, 240, 300, 100);
		g.drawOval(140, 260, 300, 60);
		g.drawOval(140, 280, 300, 20);
		
		g.drawOval(160, 140, 260, 300);
		g.drawOval(180, 140, 220, 300);
		g.drawOval(200, 140, 180, 300);
		g.drawOval(220, 140, 140, 300);
		g.drawOval(240, 140, 100, 300);
		g.drawOval(260, 140, 60, 300);
		g.drawOval(280, 140, 20, 300);
		
		// DRAW INSCRIBED/CIRCUMSCRIBED TRIANGLE
		g.drawOval(600, 140, 320, 320);
		g.drawLine(760, 140, 600, 300);
		g.drawLine(760, 140, 920, 300);
		g.drawLine(600, 300, 920, 300);
		g.drawOval(694, 168, 132, 132);
		
		// DRAW MAMS (or your own block initials)
		g.fillRect(690, 480, 10, 50);
		g.fillRect(700, 480, 20, 10);
		g.fillRect(700, 500, 20, 10);
		g.fillRect(700, 520, 20, 10);
		g.fillRect(730, 480, 10, 50);
		g.fillRect(740, 520, 20, 10);
		
		// DRAW PACMEN FLOWER
		g.fillArc(900, 600, 50, 50, 140, -280);
		g.fillArc(932, 568, 50, 50, 50, -280);
		g.fillArc(964, 600, 50, 50, 320, -280);
		g.fillArc(932, 632, 50, 50, 230, -280);
		
	}
}