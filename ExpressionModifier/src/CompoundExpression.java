interface CompoundExpression extends Expression {
	/**
	 * Adds the specified expression as a child.
	 * @param subexpression the child expression to add
	 */
	
	void addSubexpression (Expression subexpression);
	
	void addSubexpression (Expression subexpression, int index);
	
	int removeSubexpression(Expression subexpression);
}
