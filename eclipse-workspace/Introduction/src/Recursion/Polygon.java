package Recursion;

import java.util.ArrayList;

public class Polygon {
	
	//Data
	ArrayList<Point> points = new ArrayList<Point>();
	
	//Constructors
	public Polygon() {

	}
	
	//Methods
	public void addPoint(Point point) {
		points.add(point);
	}
	
	public Point getPoint(int index) {
		return points.get(index);
	}
	
	public double getArea() {
		if (points.size() <= 2) {
			throw new IllegalArgumentException("This is not a polygon.");
		}
		if (points.size() == 3) {
			return Math.abs(points.get(0).getX() * points.get(1).getY() + points.get(1).getX() * points.get(2).getY() + points.get(2).getX() * points.get(0).getY() - points.get(0).getY() * points.get(1).getX() - points.get(1).getY() * points.get(2).getX() - points.get(2).getY() * points.get(0).getX()) / 2;
		}
		else {
			Polygon smaller = new Polygon();
			for (int i = 1; i < points.size(); i ++) {
				smaller.addPoint(points.get(i));
			}
			return smaller.getArea() + Math.abs(points.get(points.size()-1).getX() * points.get(0).getY() + points.get(0).getX() * points.get(1).getY() + points.get(1).getX() * points.get(points.size()-1).getY() - points.get(points.size() - 1).getY() * points.get(0).getX() - points.get(0).getY() * points.get(1).getX() - points.get(1).getY() * points.get(points.size() - 1).getX()) / 2;
		}
	}
}
