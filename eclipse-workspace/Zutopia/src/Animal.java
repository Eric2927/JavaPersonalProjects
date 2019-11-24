import javafx.scene.media.*;            
import javafx.scene.layout.*; 
import javafx.scene.control.Label;
import javafx.scene.image.*;

public class Animal implements SimpleCollidable {
	
	//Fraction of game board that each animal image takes up
	public static final double IMAGE_WIDTH_FRAC = 0.15;
	public static final double IMAGE_HEIGHT_FRAC = 0.08;
	
	// (x,y) represents center of image
	private double _x, _y;
	private Label _imageLabel; // Label of animal on the game board
	private final AudioClip _animalSound; // Sound of animal
	
	/**
	 * Makes a new animal object
	 * @param fileName name of the file containing image of animal
	 * @param soundFileName name of the file containing the sound of the animal
	 * @param x the x value of the center of the animal on the game board
	 * @param y the y value of the center of the animal on the game board
	 */
	public Animal(String fileName, String soundFileName, double x, double y) {
		_x = x;
		_y = y;
		double imageWidth = IMAGE_WIDTH_FRAC * GameImpl.WIDTH;
		double imageHeight = IMAGE_HEIGHT_FRAC * GameImpl.HEIGHT;
		final Image image = new Image(getClass().getResourceAsStream(fileName), imageWidth, imageHeight, false, false);
		_imageLabel = new Label("", new ImageView(image));
		_imageLabel.setLayoutX(_x - image.getWidth()/2);
		_imageLabel.setLayoutY(_y - image.getHeight()/2);
		_animalSound = new AudioClip(getClass().getClassLoader().getResource(soundFileName).toString());
	}
	
	/**
	 * @return the label object to represent the animal on the game board
	 */
	public Label getImage() {
		return _imageLabel;
	}
	
	/**
	 * Plays the sound of the animal
	 */
	public void playSound() {
		_animalSound.play();
	}
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}

	@Override
	public double getTopmostY() {
		return _y - IMAGE_HEIGHT_FRAC * GameImpl.HEIGHT / 2;
	}

	@Override
	public double getBottommostY() {
		return _y + IMAGE_HEIGHT_FRAC * GameImpl.HEIGHT / 2;
	}

	@Override
	public double getLeftmostX() {
		return _x - IMAGE_WIDTH_FRAC * GameImpl.WIDTH / 2;
	}

	@Override
	public double getRightmostX() {
		return _x + IMAGE_WIDTH_FRAC * GameImpl.WIDTH / 2;
	}

}
