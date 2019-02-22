/**
 * This class simulates a bug moving along a horizontal line.
 * @author eric_li
 *
 */
public class Bug {
	
	private int position;
	private int move;
	
	/**
	 * Constructs a bug object that starts at a given initial position
	 * @param initialPosition the starting position of the bug
	 */
	public Bug(int initialPosition) {
		position = initialPosition;
		move = 1;
	}
	
	/**
	 * Gets the position of the bug
	 * @return the position of the bug
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Moves the bug by one unit
	 */
	public void move() {
		position = position + move;
	}
	
	/**
	 * Turns the bug to the other direction
	 */
	public void turn() {
		move = -move;
	}
}
