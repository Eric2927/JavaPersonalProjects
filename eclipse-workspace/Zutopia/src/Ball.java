import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class that implements a ball with a position and velocity.
 */
public class Ball implements SimpleCollidable {
	// Constants
	/**
	 * The radius of the ball.
	 */
	public static final int BALL_RADIUS = 8;
	/**
	 * The initial velocity of the ball in the x direction.
	 */
	public static final double INITIAL_VX = 1e-7;
	/**
	 * The initial velocity of the ball in the y direction.
	 */
	public static final double INITIAL_VY = 1e-7;

	// Instance variables
	// (x,y) is the position of the center of the ball.
	// vx, vy are the velocities of the ball in x and y directions, respectively
	private double x, y;
	private double vx, vy;
	private Circle circle;

	/**
	 * @return the Circle object that represents the ball on the game board.
	 */
	public Circle getCircle () {
		return circle;
	}

	/**
	 * Constructs a new Ball object at the centroid of the game board
	 * with a default velocity that points down and right.
	 */
	public Ball () {
		x = GameImpl.WIDTH/2;
		y = GameImpl.HEIGHT/2;
		vx = INITIAL_VX;
		vy = INITIAL_VY;

		circle = new Circle(BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
		circle.setLayoutX(x - BALL_RADIUS);
		circle.setLayoutY(y - BALL_RADIUS);
		circle.setFill(Color.BLUE);
	}

	/**
	 * Updates the position of the ball, given its current position and velocity,
	 * based on the specified elapsed time since the last update.
	 * @param deltaNanoTime the number of nanoseconds that have transpired since the last update
	 */
	public void updatePosition (long deltaNanoTime) {
		double dx = vx * deltaNanoTime;
		double dy = vy * deltaNanoTime;
		x += dx;
		y += dy;

		circle.setTranslateX(x - (circle.getLayoutX() + BALL_RADIUS));
		circle.setTranslateY(y - (circle.getLayoutY() + BALL_RADIUS));
	}
	
	/**
	 * Modifies ball behavior to account for a collision
	 * @param side represents if the collision was a side (left or right side of ball made contact) collision.
	 * Should be true if the collision was a side collision, false if the collision was a top or bottom collision
	 */
	public void handleCollision(boolean side) {
		// If side collision, then velocity in x direction is negated. Velocity in y direction remains unchanged
		if (side) {
			vx = -vx;
		}
		// If top or bottom collision, then velocity in y direction is negated. Velocity in x direction remains unchanged.
		else {
			vy = -vy;
		}
	}
	
	/**
	 * Speeds up the ball by the given modifier
	 * @param xModifier the multiplier to the velocity in x-direction of the ball
	 * @param yModifier the multiplier to the velocity in y-direction of the ball
	 */
	public void speedUp(double xModifier, double yModifier) {
		vx *= xModifier;
		vy *= yModifier;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	@Override
	public double getTopmostY() {
		return y - BALL_RADIUS;
	}

	@Override
	public double getBottommostY() {
		return y + BALL_RADIUS;
	}

	@Override
	public double getLeftmostX() {
		return x - BALL_RADIUS;
	}

	@Override
	public double getRightmostX() {
		// TODO Auto-generated method stub
		return x + BALL_RADIUS;
	}
}
