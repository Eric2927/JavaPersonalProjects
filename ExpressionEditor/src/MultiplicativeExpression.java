import java.util.*;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MultiplicativeExpression implements CompoundExpression {

	private CompoundExpression parent;
	private List<Expression> children;
	private HBox multiplicativeExpLabel;
	
	public MultiplicativeExpression() {
		children = new ArrayList<Expression>();
		multiplicativeExpLabel = new HBox();
	}
	
	public CompoundExpression getParent() {		
		return parent;
	}

	public void setParent(CompoundExpression parent) {
		this.parent = parent;
	}
	
	public List<Expression> getChildren() {
		return children;
	}
	
	public void setChildren(List<Expression> children) {
		this.children = children;
	}

	public Expression deepCopy() {
		CompoundExpression newExp = new MultiplicativeExpression();
		for(Expression e : children) {	
			Expression childCopy = e.deepCopy();
			childCopy.setParent(newExp);
			newExp.addSubexpression(childCopy);
		}
		return newExp;
	}
	
	public void flatten() {
        Iterator<Expression> it = children.iterator();
		List<Expression> newChildren = new ArrayList<Expression>();
		int index = 1;
		int oldSize = children.size();
		while(it.hasNext() && index <= oldSize) {
			Expression e = it.next();
			index++;
			if(e instanceof MultiplicativeExpression) {
				for(Expression eofC : ((MultiplicativeExpression) e).children) {
					if(eofC instanceof MultiplicativeExpression) {
						for(Expression eofCC : ((MultiplicativeExpression) eofC).children) {
							eofCC.flatten();
							newChildren.add(eofCC);							
						}						
					}
					else {
						eofC.flatten();
						newChildren.add(eofC);					
					}
				}
				it.remove();
			}
			else {
				newChildren.add(e);
			}			
		}
		children = newChildren;
		for (int i = 0; i < children.size(); i ++) {
			children.get(i).setParent(this);
		}
	}

	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
        for (int i = 0; i < indentLevel; i ++) {
            stringBuilder.append('\t');
        }
        stringBuilder.append('*');
        stringBuilder.append('\n');
        for (int i = 0; i < children.size(); i ++) {
            children.get(i).convertToString(stringBuilder, indentLevel + 1);
        }
    }

	public void addSubexpression(Expression subexpression) {
		subexpression.setParent(this);
		children.add(subexpression);
	}

	public Node getNode() {
		if (multiplicativeExpLabel.getChildren().isEmpty()) {
			for (int i = 0; i < children.size(); i ++) {
				multiplicativeExpLabel.getChildren().add(children.get(i).getNode());
				if (i != children.size() - 1) {
					multiplicativeExpLabel.getChildren().add(new Label("*"));
				}
			}
		}
		return multiplicativeExpLabel;
	}

	public Expression findNextMostSpecificFocus(Point2D point) {
		for (int i = 0; i < children.size(); i ++) {
			if (children.get(i).contains(point.getX(), point.getY())) {
				return children.get(i);
			}
		}
		return null;
	}

	public Expression findHighlighted() {
		if (multiplicativeExpLabel.getBorder() == RED_BORDER) {
			return this;
		}
		else {
			for (int i = 0; i < children.size(); i ++) {
				Expression expression = children.get(i).findHighlighted();
				if (expression != null) {
					return expression;
				}
			}
			return null;
		}
	}

	public boolean contains(double x, double y) {
		return (multiplicativeExpLabel.getLayoutX() < x &&
				multiplicativeExpLabel.getLayoutX() + multiplicativeExpLabel.getLayoutBounds().getWidth() > x &&
				multiplicativeExpLabel.getLayoutY() < y &&
				multiplicativeExpLabel.getLayoutY() + multiplicativeExpLabel.getLayoutBounds().getHeight() > y);
	}

	@Override
	public Expression findMostSpecificFocus(Point2D point, boolean root) {
		if (!root) {
			if (!this.contains(point.getX(), point.getY())) {
				return null;
			}
			point = multiplicativeExpLabel.parentToLocal(point);
		}
		else {
			point = multiplicativeExpLabel.sceneToLocal(point);
		}
		for (int i = 0; i < children.size(); i ++) {
			Expression expression = children.get(i).findMostSpecificFocus(point, false);
			if (expression != null) {
				return expression;
			}
		}
		return null;
	}
	
	@Override
	public void convertToGhost(boolean revert) {
		for (int i = 0; i < children.size(); i ++) {
			children.get(i).convertToGhost(revert);
		}
		for (int i = 0; i < multiplicativeExpLabel.getChildren().size(); i ++) {
			if (multiplicativeExpLabel.getChildren().get(i) instanceof Label) {
				if (revert) {
					((Label) multiplicativeExpLabel.getChildren().get(i)).setTextFill(Color.BLACK);
				}
				else {
					((Label) multiplicativeExpLabel.getChildren().get(i)).setTextFill(GHOST_COLOR);
				}
			}
		}
	}
}
