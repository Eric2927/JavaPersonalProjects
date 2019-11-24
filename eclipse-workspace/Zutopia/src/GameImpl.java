import javafx.scene.layout.*;


import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*; 
import javafx.scene.control.Label;
import javafx.scene.image.*;

import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import java.util.*;

public class GameImpl extends Pane implements Game {
	/**
	 * Defines different states of the game.
	 */
	public enum GameState {
		WON, LOST, ACTIVE, NEW
	}

	// Constants
	/**
	 * The width of the game board.
	 */
	public static final int WIDTH = 400;
	/**
	 * The height of the game board.
	 */
	public static final int HEIGHT = 600;

	// Instance variables
	private Ball ball;
	private Paddle paddle;
	private List<Animal> animals;

	/**
	 * Constructs a new GameImpl.
	 */
	public GameImpl () {
		setStyle("-fx-background-color: white;");

		restartGame(GameState.NEW);
	}

	public String getName () {
		return "Zutopia";
	}

	public Pane getPane () {
		return this;
	}

	private void restartGame (GameState state) {
		getChildren().clear();  // remove all components from the game

		// Create and add ball
		ball = new Ball();
		getChildren().add(ball.getCircle());  // Add the ball to the game board

		// Create and add animals ...
		animals = new ArrayList<Animal>();
		
		Random random = new Random(); // Used to generate random animals on the board
		Animal randomAnimal = null; // The compiler forced me to initialize it
		final double xInterval = randomAnimal.IMAGE_WIDTH_FRAC * WIDTH * 4 / 3; // Change in x between center of each animal/image
		final double yInterval = randomAnimal.IMAGE_HEIGHT_FRAC * HEIGHT * 6 / 5; // Change in y between center of each animal/image
		final double startOffsetX = 50 + randomAnimal.IMAGE_WIDTH_FRAC * WIDTH / 2; // Starting x for the leftmost animals/images
		final double startOffsetY = 50; // Starting y for the topmost animals/images
		// Generates 16 random animals with their corresponding sound, adds them to the list of animals
		// and adds it to the UI
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				String animalFile = new String();
				String animalSoundFile = new String();
				// Randomly assigns animal file name and its corresponding sound file name
				switch (random.nextInt(3)) {
				case 0:
					animalFile = "horse.jpg";
					animalSoundFile = "whinny.wav";
					break;
				case 1:
					animalFile = "duck.jpg";
					animalSoundFile = "quack.wav";
					break;
				case 2:
					animalFile = "goat.jpg";
					animalSoundFile = "bleat.wav";
					break;
				}
				// Instantiates a new random animal
				randomAnimal = new Animal(animalFile, animalSoundFile, startOffsetX + j * xInterval, startOffsetY + i * yInterval);
				animals.add(randomAnimal); // Adds animal to the list of animals
				getChildren().add(randomAnimal.getImage()); // Displays animal on UI
			}
			
		}

		// Create and add paddle
		paddle = new Paddle();
		getChildren().add(paddle.getRectangle());  // Add the paddle to the game board

		// Add start message
		final String message;
		if (state == GameState.LOST) {
			message = "Game Over\n";
		} else if (state == GameState.WON) {
			message = "You won!\n";
		} else {
			message = "";
		}
		final Label startLabel = new Label(message + "Click mouse to start");
		startLabel.setLayoutX(WIDTH / 2 - 50);
		startLabel.setLayoutY(HEIGHT / 2 + 100);
		getChildren().add(startLabel);

		// Add event handler to start the game
		setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
				GameImpl.this.setOnMouseClicked(null);

				// As soon as the mouse is clicked, remove the startLabel from the game board
				getChildren().remove(startLabel);
				run();
			}
		});

		// Add another event handler to steer paddle
		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// Whenever mouse is moved to new location on the game board, 
				// the paddle is moved to the same location
				paddle.moveTo(event.getSceneX(), event.getSceneY());
			}
		});
		
	}

	/**
	 * Begins the game-play by creating and starting an AnimationTimer.
	 */
	public void run () {
		// Instantiate and start an AnimationTimer to update the component of the game.
		new AnimationTimer () {
			// Meant to store if there had been a recent non-animal collision
			boolean recentSpecialCollision = false;
			int numTicksFromLastCollision = 0; // Number of ticks since the previous non-animal collision
			int numBottomCollisions = 0; // Number of collisions with bottom of game board
			private long lastNanoTime = -1;
			public void handle (long currentNanoTime) {
				if (lastNanoTime >= 0) {  // Necessary for first clock-tick.
					GameState state;
					if ((state = runOneTimestep(currentNanoTime - lastNanoTime, numBottomCollisions)) != GameState.ACTIVE) {
						// Once the game is no longer ACTIVE, stop the AnimationTimer.
						stop();
						// Restart the game, with a message that depends on whether
						// the user won or lost the game.
						restartGame(state);
					}
					// Loads the bounce sound
					final AudioClip bounceSound = new AudioClip(getClass().getClassLoader().getResource("boing.wav").toString());
					// Checks for non-animal collision only if there have not been any recent non-animal collisions
					if (!recentSpecialCollision) {
						// If ball collides with game board border or paddle,
						// a recent non-animal collision has happened. recentSpecialCollision is set to true.
						// Bounce sound is played if collision is detected.
						if (testForNormalWindowCollision() || testForSimpleObjectCollision(paddle)) {
							recentSpecialCollision = true;
							bounceSound.play();
						}
						// A separate test is made for bottom game board border collision, because
						// the number of bottom collisions needs to be incremented if the ball did 
						// collide with the bottom border.
						if (testForBottomWindowCollision()) {
							recentSpecialCollision = true;
							numBottomCollisions ++;
							bounceSound.play();
						}
					}
					// If there has been a recent non-animal collision, but it has been 5 or more ticks since that
					// collision, then that collision is no longer considered recent
					else if (numTicksFromLastCollision >= 5){
						numTicksFromLastCollision = 0;
						recentSpecialCollision = false;
					}
					// If there has been a recent non-animal collision and it has been less than 2 ticks since 
					// that collision, then the number of ticks since that collision is incremented
					else {
						numTicksFromLastCollision ++;
					}
					// Test for animal collision regardless
					for (int i = 0; i < animals.size(); i ++) {
						testForSimpleObjectCollision(animals.get(i));
					}
				}

				// Keep track of how much time actually transpired since the last clock-tick.
				lastNanoTime = currentNanoTime;
			}
		}.start();
	}

	/**
	 * Updates the position of the ball at each timestep and checks for potential satisfaction of
	 * victory or loss conditions.
	 * @param deltaNanoTime how much time (in nanoseconds) has transpired since the last update
	 * @return the current game state
	 */
	public GameState runOneTimestep (long deltaNanoTime, int numBottomCollisions) {
		ball.updatePosition(deltaNanoTime);
		// Checks if user lost (5 bottom collisions)
		if (numBottomCollisions == 5) {
			// Lose sound is loaded and played.
			final AudioClip loseSound = new AudioClip(getClass().getClassLoader().getResource("shatter.wav").toString());
			loseSound.play();
			return GameState.LOST;
		}
		// Checks if user won (no animals remaining)
		if (animals.isEmpty()) {
			// Win sound is loaded and played.
			final AudioClip winSound = new AudioClip(getClass().getClassLoader().getResource("chaching.wav").toString());
			winSound.play();
			return GameState.WON;
		}
		return GameState.ACTIVE;
	}
	
	/**
	 * Checks if the ball has collided with the top, left, and right borders
	 * of the game board. If so, handles the collision appropriately.
	 * @return true if the ball collided with said borders, false otherwise
	 */
	public boolean testForNormalWindowCollision() {
		// Ball collide with top of window
		if (ball.getTopmostY() <= 0) {
			// Appropriate collision behavior is applied to the ball.
			// Check the Ball class for details on this method
			ball.handleCollision(false);
		}
		// Ball collide with left of window
		else if (ball.getLeftmostX() <= 0) {
			ball.handleCollision(true);
		}
		// Ball collide with right of window
		else if (ball.getRightmostX() >= WIDTH) {
			ball.handleCollision(true);
		}
		else {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks for collision with the bottom border of game board and handles
	 * it appropriately.
	 * @return true if a collision was detected with the bottom border,
	 * false otherwise
	 */
	public boolean testForBottomWindowCollision() {
		if (ball.getBottommostY() >= HEIGHT) {
			ball.handleCollision(false);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the ball collided with some given rectangular object
	 * @param entity the object that the ball may be colliding with
	 * @return true if a collision is deteceted, false otherwise
	 */
	public boolean testForSimpleObjectCollision(SimpleCollidable rectangularEntity) {
		/**
		 * Ball collide with top of object
		 * Essentially, the logic here is: If the y value of the lowest point (visually) of the ball
		 * is below the y value of the top side (highest point technically) of the object, then a collision
		 * is detected. However, there are several other restraining conditions that have to be met.
		 * Firstly, the above condition could be satisfied if the ball is below the object. To prevent this,
		 * a condition checks if the y value of the highest point on the ball is above the bottom side of the
		 * object. However, these two conditions are still not enough to guarantee a collision with the top side
		 * of the given object because the ball can satisfy the current conditions even if it isn't right above the
		 * given object. Therefore, a bound on the x value of the ball is also added (two conditions) to ensure that
		 * the ball is indeed right above the object. Similar logic is applied for collisions on other sides. 
		 */
		if (ball.getBottommostY() >= rectangularEntity.getTopmostY() &&
			ball.getTopmostY() < rectangularEntity.getBottommostY() &&
			ball.getX() >= rectangularEntity.getLeftmostX() &&
			ball.getX() <= rectangularEntity.getRightmostX()) {
			ball.handleCollision(false);
		}
		// Ball collide with bottom of object
		else if (ball.getTopmostY() <= rectangularEntity.getBottommostY() &&
				ball.getBottommostY() > rectangularEntity.getTopmostY() &&
				ball.getX() >= rectangularEntity.getLeftmostX() &&
				ball.getX() <= rectangularEntity.getRightmostX()) {
			ball.handleCollision(false);
		}
		// Ball collide with left of object
		else if (ball.getRightmostX() >= rectangularEntity.getLeftmostX() &&
				ball.getLeftmostX() < rectangularEntity.getRightmostX() &&
				ball.getY() >= rectangularEntity.getTopmostY() &&
				ball.getY() <= rectangularEntity.getBottommostY()) {
			ball.handleCollision(true);
		}
		// Ball collides with right of object
		else if (ball.getLeftmostX() <= rectangularEntity.getRightmostX() &&
				ball.getRightmostX() > rectangularEntity.getLeftmostX() &&
				ball.getY() >= rectangularEntity.getTopmostY() &&
				ball.getY() <= rectangularEntity.getBottommostY()) {
			ball.handleCollision(true);
		}
		else {
			return false;
		}
		// Checks if the given object is an animal and the ball collided with said animal (implied by the else statement above)
		if (rectangularEntity instanceof Animal) {
			((Animal) rectangularEntity).playSound(); // Sound of the animal is played
			getChildren().remove(((Animal) rectangularEntity).getImage()); // Animal is removed from the board
			animals.remove(rectangularEntity); // Animal is removed from the list of animals
			ball.speedUp(1.1, 1.1); // Ball gets faster by a factor of 10% in both x and y directions
		}
		return true;
	}

}
