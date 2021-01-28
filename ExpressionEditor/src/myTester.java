
public class myTester {

	public static void main(String[] args) {
		SimpleExpressionParser hi = new SimpleExpressionParser();
		try {
			Expression o = hi.parse("2*x+3*y+4*z+(7+6*z)", false);
		} catch (ExpressionParseException e) {
			System.out.println("Idk");
			e.printStackTrace();
		}
	}

}
