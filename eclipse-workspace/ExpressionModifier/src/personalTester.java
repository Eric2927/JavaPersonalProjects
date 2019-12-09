
public class personalTester {

	public static void main(String[] args) {
		SimpleExpressionParser hi = new SimpleExpressionParser();
		try {
			Expression myE = hi.parse("10*x*z + 2*(15+y)", false);
			System.out.print(myE.convertToString(0));
		} catch (ExpressionParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error buddy");
			e.printStackTrace();
		}
			
		
	}

}
