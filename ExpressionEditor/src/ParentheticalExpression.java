import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ParentheticalExpression implements CompoundExpression {

	private CompoundExpression parent;
	private Expression child;
	private HBox parentheticalExpLabel;
	
	public ParentheticalExpression() {
		parentheticalExpLabel = new HBox();
	}
	
	
	public CompoundExpression getParent() {		
		return parent;
	}

	public void setParent(CompoundExpression parent) {
		this.parent = parent;
	}
	
	public List<Expression> getChildren() {
		List<Expression> children = new ArrayList<Expression>();
		children.add(child);
		return children;
	}
	
	public void setChildren(List<Expression> children) {
		// Nothing
	}

	public Expression deepCopy() {
		CompoundExpression newExp = new ParentheticalExpression();
		Expression childCopy = child.deepCopy();
		childCopy.setParent(newExp);
		newExp.addSubexpression(childCopy);
		return newExp;
	}

	public void flatten() {
		child.flatten();
	}
	
	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
        for (int i = 0; i < indentLevel; i ++) {
            stringBuilder.append('\t');
        }
        stringBuilder.append("()");
        stringBuilder.append('\n'); 
        child.convertToString(stringBuilder, indentLevel + 1);
    }

	public void addSubexpression(Expression subexpression) {
		child = subexpression;
	}


	public Node getNode() {
		if (parentheticalExpLabel.getChildren().isEmpty()) {
			parentheticalExpLabel.getChildren().add(new Label("("));
			parentheticalExpLabel.getChildren().add(child.getNode());
			parentheticalExpLabel.getChildren().add(new Label(")"));
		}
		return parentheticalExpLabel;
	}


	public Expression findNextMostSpecificFocus(Point2D point) {
		if (child.getNode().contains(point.getX(), point.getY())) {
				return child;
			}
		return null;
	}


	@Override
	public Expression findHighlighted() {
		if (parentheticalExpLabel.getBorder() == RED_BORDER) {
			return this;
		}
		return child.findHighlighted();

	}


	@Override
	public boolean contains(double x, double y) {
		return (parentheticalExpLabel.getLayoutX() < x &&
				parentheticalExpLabel.getLayoutX() + parentheticalExpLabel.getLayoutBounds().getWidth() > x &&
				parentheticalExpLabel.getLayoutY() < y &&
				parentheticalExpLabel.getLayoutY() + parentheticalExpLabel.getLayoutBounds().getHeight() > y);
	}


	@Override
	public Expression findMostSpecificFocus(Point2D point, boolean root) {
		if (!root) {
			if (!this.contains(point.getX(), point.getY())) {
				return null;
			}
			point = parentheticalExpLabel.parentToLocal(point);
		}
		else {
			point = parentheticalExpLabel.sceneToLocal(point);
		}
		return child.findMostSpecificFocus(point, false);
	}
	
	@Override
	public void convertToGhost(boolean revert) {
		child.convertToGhost(revert);
		if (revert) {
			((Label) parentheticalExpLabel.getChildren().get(0)).setTextFill(Color.BLACK);
			((Label) parentheticalExpLabel.getChildren().get(parentheticalExpLabel.getChildren().size() - 1)).setTextFill(Color.BLACK);
		}
		else {
			((Label) parentheticalExpLabel.getChildren().get(0)).setTextFill(GHOST_COLOR);
			((Label) parentheticalExpLabel.getChildren().get(parentheticalExpLabel.getChildren().size() - 1)).setTextFill(GHOST_COLOR);
		}
	}
}
