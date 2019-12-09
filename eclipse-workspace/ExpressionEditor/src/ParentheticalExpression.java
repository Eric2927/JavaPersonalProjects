
public class ParentheticalExpression implements CompoundExpression {

	private CompoundExpression parent;
	private Expression child;
	
	public ParentheticalExpression() {		
	}
	
	
	public CompoundExpression getParent() {		
		return parent;
	}

	public void setParent(CompoundExpression parent) {
		this.parent = parent;
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
}
