

public class BinaryConverter {
	
	public static void binaryConverter (String str) {
		int str_length = str.length();
		int power = 0;
		int base10_sum = 0;
		for (int i = 0; i < str_length; i ++) {
			power = str_length - i - 1;
			base10_sum += Character.getNumericValue(str.charAt(i)) * Math.pow(2, power);
		}
		String output = Integer.toString(base10_sum);
		System.out.println(output);
	}
	
	public static void main(String[] args) {

	binaryConverter("1111110");
	
	}

}
