import java.util.ArrayList;

public class MultiplicativeExpression implements CompoundExpression {
	private CompoundExpression _parent;
	private ArrayList<Expression> _children = new ArrayList<Expression>();

	public CompoundExpression getParent() {
		return _parent;
	}

	public void setParent(CompoundExpression parent) {
		_parent = parent;
	}

	public Expression deepCopy() {
		Expression copy = new MultiplicativeExpression();
		for (int i = 0; i < _children.size(); i ++) {
			Expression copySubexpression = _children.get(i).deepCopy();
			((MultiplicativeExpression) copy).addSubexpression(copySubexpression);
			copySubexpression.setParent((MultiplicativeExpression) copy);
		}
		return copy;
	}

	public void flatten() {

		/*
		for (int i = _children.size() - 1; i >= 0; i --) {
			_children.get(i).flatten();
		}
		if (_parent instanceof MultiplicativeExpression) {
			int index = _parent.removeSubexpression(this);
			for (int i = _children.size() - 1; i >= 0; i --) {
				_parent.addSubexpression(_children.get(i), index);
			}
			_parent = null;
		}
		*/
	}

	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
		for (int i = 0; i < indentLevel; i ++) {
			stringBuilder.append('\t');
		}
		stringBuilder.append('*');
		stringBuilder.append('\n');
		for (int i = 0; i < _children.size(); i ++) {
			_children.get(i).convertToString(stringBuilder, indentLevel + 1);
		}
	}
	
	public void addSubexpression(Expression subexpression) {
		_children.add(subexpression);
	}

	public void addSubexpression(Expression subexpression, int index) {
		_children.add(index, subexpression);
	}

	public int removeSubexpression(Expression subexpression) {
		int index = _children.indexOf(subexpression);
		_children.remove(subexpression);
		return index;
	}

}
