package Miscellaneous;
import java.awt.geom.Ellipse2D;

public class Geometry {
	
	public static double sphereVolume(double r) {
		return (4./3) * (Math.PI) * Math.pow(r, 3);
	}
	
	public static double sphereSurface(double r) {
		return 4. * Math.PI * Math.pow(r, 2);
	}
	
	public static double cylinderVolume(double r, double h) {
		return Math.PI * Math.pow(r, 2) * h;
	}
	
	public static double cylinderSurface(double r, double h) {
		return 2. * Math.PI * r * h + 2 * Math.PI * Math.pow(r, 2);
	}
	
	public static double coneVolume(double r, double h) {
		return Math.PI * Math.pow(r, 2) * h / 3.;
	}
	
	public static double coneSurface(double r, double h) {
		return Math.PI * r * (r + Math.sqrt(Math.pow(h, 2) + Math.pow(r, 2)));
	}
	
	public static double perimeter(Ellipse2D.Double e) {
		if (e.getWidth() == e.getHeight()) {
			return Math.PI * Math.pow(e.getWidth()/2, 2);
		}
		else if (e.getWidth() > e.getHeight()) {
			double major = e.getWidth()/2;
			double minor = e.getHeight()/2;
			return Math.PI *(3 * (major + minor) - Math.sqrt((3 * major + minor) + (major + 3 * minor)));
		}
		else if (e.getWidth() < e.getHeight()) {
			double minor = e.getWidth()/2;
			double major = e.getHeight()/2;
			return Math.PI *(3 * (major + minor) - Math.sqrt((3 * major + minor) + (major + 3 * minor)));
		}
		else {
			return -4;
		}
	}
	
	public static double area(Ellipse2D.Double e) {
		return Math.PI * e.getWidth() * e.getHeight();
	}
}
