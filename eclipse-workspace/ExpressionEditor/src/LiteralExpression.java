import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LiteralExpression implements Expression {

	private CompoundExpression parent;
	private String value;
	private Label literalLabel;
	
	public LiteralExpression(String value) {
		this.value = value;
		literalLabel = new Label(value);
	}
	
	public CompoundExpression getParent() {		
		return parent;
	}

	public void setParent(CompoundExpression parent) {
		this.parent = parent;		
	}
	


	public Expression deepCopy() {		
		Expression newExp = new LiteralExpression(value);
		return newExp;
	}

	
	public void flatten() {
		return;
	}

	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
        for (int i = 0; i < indentLevel; i ++) {
            stringBuilder.append('\t');
        }
        stringBuilder.append(value);
        stringBuilder.append('\n');
    }

	@Override
	public Node getNode() {
		return literalLabel;
	}

	public Expression findNextMostSpecificFocus(Point2D point) {
		return null;
	}

	@Override
	public Expression findHighlighted() {
		if (literalLabel.getBorder() == RED_BORDER) {
			return this;
		}
		return null;
	}

	@Override
	public boolean contains(double x, double y) {
		return (literalLabel.getLayoutX() < x &&
				literalLabel.getLayoutX() + literalLabel.getLayoutBounds().getWidth() > x &&
				literalLabel.getLayoutY() < y &&
				literalLabel.getLayoutY() + literalLabel.getLayoutBounds().getHeight() > y);
	}

	@Override
	public Expression findMostSpecificFocus(Point2D point, boolean root) {
		if (this.contains(point.getX(), point.getY())) {
			return this;
		}
		return null;
	}
	
	@Override
	public void convertToGhost(boolean revert) {
		if (revert) {
			literalLabel.setTextFill(Color.BLACK);
		}
		else {
			literalLabel.setTextFill(GHOST_COLOR);
		}
	}
}
