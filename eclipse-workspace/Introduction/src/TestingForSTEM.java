
public class TestingForSTEM {

	public static void main(String[] args) {
		/* double number = 0;
		int n = 23;
		for (double i = 0; i <= n; i ++) {
		number = Math.pow(2, i/n);
		System.out.println(number);
		} */
		
		double observed = Math.pow(2, 14/21.);
		double expected = 8/5.;
		double percent_error = ((observed - expected) / expected) * 100;
		System.out.println(percent_error + "%");
	}

}
