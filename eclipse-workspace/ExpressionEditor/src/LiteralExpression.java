
public class LiteralExpression implements Expression {

	private CompoundExpression parent;
	private String value;
	
	public LiteralExpression(String value) {
		this.value = value;
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
}
