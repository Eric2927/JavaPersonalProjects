// This is a point class that performs simple geometry

public class Point {
	
	private double x;
	private double y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	/**
	 * Gets the quadrant number
	 * @return quadrant number. If it is on the x or y axis, it returns 0
	 */
	public int getQuadrant() {
		if (x == 0 || y == 0) {
			return 0;
		}
		else if (x > 0 && y > 0) {
			return 1;
		}
		else if (x < 0 && y > 0) {
			return 2;
		}
		else if (x < 0 && y < 0) {
			return 3;
		}
		else {
			return 4;
		}
	}
	
	/**
	 * Sets the point as its contrapositive
	 */
	public void setContrapositive() {
		double ogX = x;
		x = -y;
		y = -ogX;
	}
	
	/**
	 * Gets the distance to entered point
	 * @param point1 another point
	 * @return the distance to the entered point
	 */
	public double getDistance(Point point1) {
		return Math.sqrt(Math.pow(x - point1.getX(), 2) + Math.pow(y - point1.getY(), 2));
	}
	
	/**
	 * Determines if the entered point has the same x value
	 * @param point1 another point
	 * @return true if the x coordinates are the same
	 */
	public boolean getVertical(Point point1) {
		if (x == point1.getX()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Finds the slope of the entered point and this point
	 * @param point1 another point
	 * @return The slope between the entered point and this point
	 */
	public double getSlope(Point point1) {
		try {
			return (y-point1.getY())/(x-point1.getX());
		}
		catch (Exception e) {
			return (Double) null;
		}
	}
	
	/**
	 * Finds out if this point and two entered points are collinear
	 * @param point1 another point
	 * @param point2 another point
	 * @return
	 */
	public boolean getCollinear(Point point1, Point point2) {
		if (this.getSlope(point1) == point1.getSlope(point2)) {
			return true;
		}
		return false;
		
	}
}
