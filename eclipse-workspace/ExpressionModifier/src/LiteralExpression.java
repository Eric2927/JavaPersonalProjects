
public class LiteralExpression implements Expression {
	private CompoundExpression _parent;
	private String _term;

	public CompoundExpression getParent() {
		return _parent;
	}

	public void setParent(CompoundExpression parent) {
		_parent = parent;
	}

	public Expression deepCopy() {
		Expression copy = new LiteralExpression();
		String termCopy = new String(_term);
		((LiteralExpression) copy).setTerm(termCopy);
		return copy;
	}

	public void flatten() {
		// Do Nothing?
	}

	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
		for (int i = 0; i < indentLevel; i ++) {
			stringBuilder.append('\t');
		}
		stringBuilder.append(_term);
		stringBuilder.append('\n');
	}
	
	public void setTerm(String term) {
		_term = term;
	}

}
