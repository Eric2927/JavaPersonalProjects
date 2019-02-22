import java.awt.Color;

public class ImageNegator {

	public static void main(String[] args) {
		Picture image = new Picture();
		image.pick();
		int height = image.getHeight();
		int width = image.getWidth();
		Color pixelcolor = new Color(0,0,0);
		int negated_pixelRed = 0;
		int negated_pixelGreen = 0;
		int negated_pixelBlue = 0;
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++) {
				pixelcolor = image.getColorAt(x,y);
				negated_pixelRed = 255 - pixelcolor.getRed();
				negated_pixelGreen = 255 - pixelcolor.getGreen();
				negated_pixelBlue = 255 - pixelcolor.getBlue();
				Color negated_pixelcolor = new Color(negated_pixelRed, negated_pixelGreen, negated_pixelBlue);
				image.setColorAt(x, y, negated_pixelcolor);
			}
		}

	}

}
