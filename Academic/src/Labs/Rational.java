package Labs;
/**
 * A Rational class contains a numerator and a denominator and stores a fraction. 
 * This class can be used to manipulate rational number operations. 
 * @author eric_li
 */

import java.util.ArrayList;

public class Rational {
	
	private int num;
	private int den;
	private int rnum;
	private int rden;
	
	/**
	 * Constructs a default fraction 0/1
	 */
	public Rational() {
		num = 0;
		den = 1;
	}
	
	/**
	 * Constructs a fraction with a given numerator and denominator
	 * @param num the numerator
	 * @param den the denominator
	 */
	public Rational(int num, int den) {
		if (den == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero");
		}
		this.num = num;
		this.den = den;

	}
	
	/**
	 * Gets the numerator of the fraction
	 * @return the numerator
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * Gets the denominator of the fraction
	 * @return the denominator
	 */
	public int getDen() {
		return den;
	}
	
	/**
	 * Gets the decimal equivalent of the fraction
	 * @return the decimal equivalent
	 */
	public double getDecimal() {
		return (double)(num)/(double)(den);
	}
	
	/**
	 * Gets the reduced fraction as a String
	 * @return the reduced fraction
	 */
	public String getRational() {
		this.reduce();
		return rnum + "/" + rden;
	}
	
	/**
	 * Gets the original fraction as a String
	 * @return the original fraction
	 */
	public String getOriginal() {
		return num + "/" + den;
	}
	
	/**
	 * Multiples this rational number with the rational number in the second Rational class
	 * @param r2 an instance of the second rational number (fraction)
	 * @return a new Rational object that stores the product as a rational number (fraction)
	 */
	public Rational multiply(Rational r2) {
		Rational r3 = new Rational(num * r2.getNum(), den * r2.getDen());
		return r3;
	}
	
	/**
	 * Divides this rational number with the rational number in the second Rational class
	 * @param r2 an instance of the second rational number (fraction)
	 * @return a new Rational object that stores the quotient as a rational number (fraction)
	 */
	public Rational divide(Rational r2) {
		Rational r3 = new Rational(num * r2.getDen(), den * r2.getNum());
		return r3;
	}
	
	/**
	 * Adds this rational number with the rational number in the second Rational class
	 * @param r2 an instance of the second rational number (fraction)
	 * @return a new Rational object that stores the sum as a rational number (fraction)
	 */
	public Rational add(Rational r2) {
		Rational r3 = new Rational(num * r2.getDen() + r2.getNum() * den, den * r2.getDen());
		return r3;
	}
	
	/**
	 * Subtracts this rational number with the rational number in the second Rational class
	 * @param r2 an instance of the second rational number (fraction)
	 * @return a new Rational object that stores the product as a rational number (fraction)
	 */
	public Rational subtract(Rational r2) {
		Rational r3 = new Rational(num * r2.getDen() - r2.getNum() * den, den * r2.getDen());
		return r3;
	}
	
	/**
	 * Reduces the fraction
	 */
	private void reduce() {
		rnum = num/getGCF(num, den);
		rden = den/getGCF(num, den);
	}
	
	/**
	 * Gets the GCF (greatest common factor) of the given values
	 * @param val1 the first value
	 * @param val2 the second value
	 * @return the greatest common factor
	 */
	private int getGCF(int val1, int val2) {
		int GCF = 1;
		if (val1 != 0) {
			if (val1 < 0) {
				val1 = -val1;
			}
			if (val2 < 0) {
				val2 = -val2;
			}
			ArrayList<Integer> factors1 = new ArrayList<Integer>();
			ArrayList<Integer> factors2 = new ArrayList<Integer>();
			for (int i = 1; i <= val1; i ++) {
				if (val1 % i == 0) {
					factors1.add(i);
				}
			}
			for (int i = 1; i <= val2; i ++) {
				if (val2 % i == 0) {
					factors2.add(i);
				}
			}
			int x = factors1.size() - 1;
			int y = factors2.size() - 1;
			boolean loop = true;
			while (loop) {
				if (x == 0 || y == 0) {
					GCF = 1;
					loop = false;
				}
				else if (factors1.get(x) > factors2.get(y)) {
					x --;
				}
				else if (factors1.get(x) < factors2.get(y)) {
					y --;
				}
				else {
					GCF = factors1.get(x);
					loop = false;
				}
		
			}
		}
		return GCF;
	}
	
}
