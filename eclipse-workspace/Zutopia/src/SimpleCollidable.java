/**
 * Interface for a simple collidable object (rectangular objects and regular circular objects)
 */
interface SimpleCollidable {
	
	/**
	 * Gets the x value of the center of the object
	 * @return x value of the center of the object
	 */
	public double getX();
	
	/**
	 * Gets the y value of the center of the object
	 * @return y value of the center of the object
	 */
	public double getY();
	
	/**
	 * Gets the visually highest y value (lowest numerically) of the object if circular
	 * Gets the y value of the top side if rectangular
	 * @return the y value of the visually highest point of the object 
	 */
	public double getTopmostY();
	
	/**
	 * Gets the visually lowest y value (highest numerically) of the object if circular
	 * Gets the y value of the bottom side if rectangular
	 * @return the y value of the visually lowest point of the object
	 */
	public double getBottommostY();
	
	/**
	 * Gets the leftmost x value of the object if circular
	 * Gets the x value of the left side of the object if rectangular
	 * @return the x value of the leftmost point of the object
	 */
	public double getLeftmostX();
	
	/**
	 * Gets the rightmost x value of the object if circular
	 * Gets the x value of the right side of the object if rectangular
	 * @return the x value of the rightmost point of the object
	 */
	public double getRightmostX();

}
