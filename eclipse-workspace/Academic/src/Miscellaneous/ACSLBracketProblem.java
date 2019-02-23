package Miscellaneous;
import java.util.Scanner;
import java.util.ArrayList;

public class ACSLBracketProblem {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String exp1 = input.nextLine();
		String exp2 = input.nextLine();
		String exp3 = input.nextLine();
		String exp4 = input.nextLine();
		String exp5 = input.nextLine();
		System.out.println(something(exp1));
		System.out.println(something(exp2));
		System.out.println(something(exp3));
		System.out.println(something(exp4));
		System.out.println(something(exp5));

		
	}
	
	public static String something(String expression) {
		ArrayList<Integer> possiblePositions = new ArrayList<Integer>();
		ArrayList<Character> operations = new ArrayList<Character>();
		operations.add('+');
		operations.add('-');
		operations.add('*');
		operations.add('/');
		ArrayList<Character> brackets = new ArrayList<Character>();
		brackets.add('{');
		brackets.add('[');
		brackets.add('(');
		brackets.add('}');
		brackets.add(']');
		brackets.add(')');
		ArrayList<Character> digits = new ArrayList<Character>();
		digits.add('0');
		digits.add('1');
		digits.add('2');
		digits.add('3');
		digits.add('4');
		digits.add('5');
		digits.add('6');
		digits.add('7');
		digits.add('8');
		digits.add('9');
		if (expression.contains("{") && !expression.contains("}")) {
			for (int i = expression.indexOf(93) + 1; i < expression.length(); i++) {
				if (!operations.contains(expression.charAt(i-1)) && !digits.contains(expression.charAt(i))) {
						possiblePositions.add(i+1);
				}
			}
			possiblePositions.add(expression.length()+1);
		}
		if (expression.contains("[") && !expression.contains("]")) {
			for (int i = expression.indexOf(41) + 1; i <= expression.indexOf(125); i++) {
				if (!operations.contains(expression.charAt(i-1)) && !digits.contains(expression.charAt(i))) {
					possiblePositions.add(i+1);
				}
			}
		}
		if (expression.contains("(") && !expression.contains(")")) {
			for (int i = expression.indexOf(40) + 1; i <= expression.indexOf(93); i ++) {
				if (!operations.contains(expression.charAt(i-1)) && !digits.contains(expression.charAt(i))) {
					possiblePositions.add(i+1);
				}
			}
		}
		if (expression.contains("}") && !expression.contains("{")) {
			possiblePositions.add(1);
			for (int i = 1; i <= expression.indexOf(91); i ++) {
				if (!digits.contains(expression.charAt(i-1)) && !operations.contains(expression.charAt(i))) {
					possiblePositions.add(i+1);
				}
			}
		}
		if (expression.contains("]") && !expression.contains("[")) {
			if (expression.indexOf(123) == 0) {
				for (int i = expression.indexOf(123) + 1; i <= expression.indexOf(40); i ++) {
					if (!digits.contains(expression.charAt(i-1)) && !operations.contains(expression.charAt(i))) {
						possiblePositions.add(i+1);
					}
				}
			}
			else {
				for (int i = expression.indexOf(123) + 1; i <= expression.indexOf(40); i ++) {
					if (!digits.contains(expression.charAt(i-1)) && !operations.contains(expression.charAt(i))) {
						possiblePositions.add(i+1);
					}
				}
			}
		}
		if (expression.contains(")") && !expression.contains("(")) {
			for (int i = expression.indexOf(91) + 1; i <= expression.indexOf(41); i ++) {
				if (!digits.contains(expression.charAt(i-1)) && !operations.contains(expression.charAt(i))) {
					possiblePositions.add(i+1);
				}
			}
		}
		System.out.println(possiblePositions);
		String output = "";
		for (int i = 0; i < possiblePositions.size(); i ++) {
			output.concat(possiblePositions.get(i).toString());
		}
		return output;
	}

}
