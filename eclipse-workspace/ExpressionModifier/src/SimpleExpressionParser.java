import java.util.*;
import java.util.function.Function;
/**
 * Starter code to implement an ExpressionParser. Your parser methods should use the following grammar:
 * E := A | X
 * A := A+M | M
 * M := M*M | X
 * X := (E) | L
 * L := [0-9]+ | [a-z]
 */
public class SimpleExpressionParser implements ExpressionParser {
	/*
	 * Attempts to create an expression tree -- flattened as much as possible -- from the specified String.
         * Throws a ExpressionParseException if the specified string cannot be parsed.
	 * @param str the string to parse into an expression tree
	 * @param withJavaFXControls you can just ignore this variable for R1
	 * @return the Expression object representing the parsed expression tree
	 */
	public Expression parse (String str, boolean withJavaFXControls) throws ExpressionParseException {
		// Remove spaces -- this simplifies the parsing logic
		str = str.replaceAll(" ", "");
		Expression expression = parseExpression(str);
		if (expression == null) {
			// If we couldn't parse the string, then raise an error
			throw new ExpressionParseException("Cannot parse expression: " + str);
		}

		// Flatten the expression before returning
		expression.flatten();
		return expression;
	}
	
	protected static Expression parseExpression (String str) {
		Expression expression = parseA(str);
		if (expression == null) {
			expression = parseX(str);
		}
		return expression;
	}

	private static Expression parseA(String str) {
		return parseCompoundHelper(str, SimpleExpressionParser::parseM, SimpleExpressionParser::parseA, '+');
		/*Expression expression = parseM(str);
		if (expression == null) {
			for (int i = 1; i < str.length() - 1; i ++) {
				// Ask about garbage collection pls
				Expression firstExpression = parseA(str.substring(0,i));
				Expression secondExpression = parseM(str.substring(i+1));
				if (str.charAt(i) == '+' &&
						firstExpression != null &&
						secondExpression != null) {
					expression = new AdditiveExpression();
					((AdditiveExpression) expression).addSubexpression(firstExpression);
					((AdditiveExpression) expression).addSubexpression(secondExpression);
					firstExpression.setParent((AdditiveExpression) expression);
					secondExpression.setParent((AdditiveExpression) expression);
				}
			}
		}
		return expression; */
	}
	
	private static Expression parseM(String str) {
		return parseCompoundHelper(str, SimpleExpressionParser::parseX, SimpleExpressionParser::parseM, '*');
		/*Expression expression = parseX(str);
		if (expression == null) {
			for (int i = 1; i < str.length() - 1; i ++) {
				Expression firstExpression = parseM(str.substring(0,i));
				Expression secondExpression = parseM(str.substring(i+1));
				if (str.charAt(i) == '*' &&
						firstExpression != null &&
						secondExpression != null) {
					expression = new MultiplicativeExpression();
					((MultiplicativeExpression) expression).addSubexpression(firstExpression);
					((MultiplicativeExpression) expression).addSubexpression(secondExpression);
					firstExpression.setParent((MultiplicativeExpression) expression);
					secondExpression.setParent((MultiplicativeExpression) expression);
				}
			}
		}
		return expression; */
	}
	
	private static Expression parseCompoundHelper(String str, Function<String, Expression> f1, Function<String, Expression> f2, char operator) {
		Expression expression = f1.apply(str);
		if (expression == null) {
			for (int i = 1; i < str.length() - 1; i ++) {
				Expression firstExpression = f2.apply(str.substring(0,i));
				Expression secondExpression = parseM(str.substring(i+1));
				if (str.charAt(i) == operator &&
						firstExpression != null &&
						secondExpression != null) {
					if (operator == '*') {
						expression = new MultiplicativeExpression();
					}
					else {
						expression = new AdditiveExpression();
					}
					((CompoundExpression) expression).addSubexpression(firstExpression);
					((CompoundExpression) expression).addSubexpression(secondExpression);
					firstExpression.setParent((CompoundExpression) expression);
					secondExpression.setParent((CompoundExpression) expression);
				}
			}
		}
		return expression;
	}
	
	private static Expression parseX(String str) {
		//System.out.println(str + "<-Fine");
		Expression expression = parseL(str);
		if (expression == null && str.length() > 2) {
			//System.out.println(str.substring(1, str.length() - 1) + "<-");
			Expression innerExpression = parseExpression(str.substring(1, str.length() - 1));
			if (str.charAt(0) == '(' &&
					str.charAt(str.length() - 1) == ')' &&
					innerExpression != null) {
				expression = new ParentheticalExpression();
				((ParentheticalExpression) expression).addSubexpression(innerExpression);
				innerExpression.setParent((ParentheticalExpression) expression);
			}
		}
		return expression;
	}
	
	// Do we include uppercase letters?
	private static Expression parseL(String str) {
		for (int i = 0; i < str.length(); i ++) {
			// Numbers based on ASCII encoding
			if (str.charAt(i) < 48 ||
					str.charAt(i) > 122 ||
					(str.charAt(i) > 57 && str.charAt(i) < 97)) {
				return null;
			}
		}
		LiteralExpression expression = new LiteralExpression();
		expression.setTerm(str);
		return expression;
	}
}
