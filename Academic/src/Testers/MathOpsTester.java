package Testers;
public class MathOpsTester
{
	public static void main(String args[])
	{
		intMathMethods();
		decMathMethods();
		doublePrecision();
		integerOverflow();
		int m = 18;
		int n = 4;
		double x = 2.5;
		double y = -1.5;
		System.out.println((1 - (1 - (1 - (1 - (1 - n))))));
    }

	public static void intMathMethods()
	{
		int a = 0;
		int b = 25;
		int c = 10;

		System.out.println("Question #2: intMathMethods()");
		a = b + c;					// Addition
		System.out.println(b + " + " + c + " = " + a); 
		a = b - c;					// Subtraction
		System.out.println(b + " - " + c + " = " + a); 
		a = b * c;					// Multiplication
		System.out.println(b + " * " + c + " = " + a);
		a = b / c;					// Integer Division
		System.out.println(b + " / " + c + " = " + a);
		a = b % c;					// Remainder Division
		System.out.println(b + " % " + c + " = " + a);
 		System.out.println();
	}	
	
	public static void decMathMethods()
	{
		double d1 = 0;
		double d2 = 10.0;
		double d3 = 3.33333333;
      
		System.out.println("Question #3: decMathMethods()");
		d1 = d2 + d3;                                    
		System.out.println(d2 + " + " + d3 + " = " + d1);
	d1 = d2 - d3;                                    
	System.out.println(d2 + " - " + d3 + " = " + d1);
	d1 = d2 * d3;                                    
		System.out.println(d2 + " * " + d3 + " = " + d1);
	d1 = d2 / d3;                                    
	System.out.println(d2 + " / " + d3 + " = " + d1);
	System.out.println();
    }
	
	public static void doublePrecision()
	{
		double num1 = 10.0;
		double num2 = 3.0;
		double num3 = num1 / num2;
		
		System.out.println("Question #4: doublePrecision()");
		System.out.println("num1: " + num1);
		System.out.println("num2: " + num2);
		System.out.println("num3: " + num3);
			
		System.out.println();				
	}
	public static void integerOverflow()
	{
		System.out.println("Question #6: integerOverflow()");

		int intNum = 1000;
		System.out.println("intNum: " + intNum);
			
		intNum = intNum * 1000;
		System.out.println("intNum: " + intNum);
			
		intNum = intNum * 1000;
		System.out.println("intNum: " + intNum);
			
		intNum = intNum * 1000;
		System.out.println("intNum: " + intNum);
	}

}
