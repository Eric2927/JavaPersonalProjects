package Miscellaneous;

/**
 * This class implements a method that finds the number of total dots in a pentagonal arrangement of dots that increases in size. 
 * @author eric_li
 *
 */
public class PentagonDots {

	public static void main(String[] args) {
		System.out.println(PentagonalNumber(2));
		System.out.println(PentagonalNumber(5));
	}
	
	public static int PentagonalNumber(int num) {
		return 1 + 5*num*(num-1)/2;
	}

}
