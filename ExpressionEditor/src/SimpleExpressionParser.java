import java.util.*;
import java.util.function.BiFunction;

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
		//System.out.print(expression.convertToString(0));
		return expression;
	}
	
	protected Expression parseExpression (String str) {
		Expression expression = null;
		//Begin checking grammar (Assume E as starter)
			expression = parseE(str, expression);
			// Set parent of first root expression to null, suppress null pointer exception
			// If junit test were to be individually executed, not exception would be thrown
			try {
				expression.setParent(null);				
			} 
			catch (NullPointerException e) {				
			}
			
			return expression;
	}
	
	private static Expression parseE(String str, Expression exp) {
		Expression expression = parseA(str, exp);
		if(expression != null) {					
			return expression;
		}
		else {
			return parseX(str, exp);
		}
	}
	
	private static Expression parseX(String str, Expression exp) {
		if(str.charAt(0) == '(' && str.charAt(str.length()-1) == ')' && str.length() != 2) {
			CompoundExpression expression = new ParentheticalExpression();
			expression.setParent((CompoundExpression) exp);
			exp = expression;
			Expression middleExpression = parseE(str.substring(1, str.length() - 1), exp);
			if(middleExpression != null) {
				expression.addSubexpression(middleExpression);
				return expression;
			}
			else return null;
		}
		else {
			Expression expression = parseL(str, exp);
			if(expression != null) {
				return expression;
			}
			else return null;
		}
	}
	
	private static Expression parseL(String str, Expression exp) {
		Expression expression;
		if(!str.matches(".*[^a-z].*") || isNumber(str)) {
			expression = new LiteralExpression(str);
			expression.setParent((CompoundExpression) exp);
			return expression;
		}
		else return null;		
	}
	
	
	 private static Expression parseM(String str, Expression exp) {
	        return parseHelper(str, exp, '*', SimpleExpressionParser::parseM, SimpleExpressionParser::parseX);
	    }

	 private static Expression parseA(String str, Expression exp) {
	        return parseHelper(str, exp, '+', SimpleExpressionParser::parseA, SimpleExpressionParser::parseM);
	    }
	 
	private static Expression parseHelper(String str, Expression exp, char operator, BiFunction<String,Expression,Expression> f1, BiFunction<String,Expression,Expression> f2) {
		CompoundExpression expression = (CompoundExpression) exp;
        for(int i = 1; i < str.length() - 1; i++) {
            if(str.charAt(i) == operator) {
                if (operator == '+') {
                    expression = new AdditiveExpression();
                }
                else {
                    expression = new MultiplicativeExpression();
                }
                expression.setParent((CompoundExpression) exp);
                exp = expression;
                Expression frontExpression = f1.apply(str.substring(0, i), exp);
                Expression backExpression = parseM(str.substring(i+1), exp);
                if(frontExpression != null && backExpression != null) {
                    expression.addSubexpression(frontExpression);
                    expression.addSubexpression(backExpression);
                    return expression;
                }                        
            }
        }    
        return f2.apply(str, exp);        
    }
	
	private static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	
}
