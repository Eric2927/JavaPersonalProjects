package Testers;
import java.util.ArrayList;

import java.util.Scanner;

import ArrayLists.*;
import Decisions.*;
import ElevensSolitaire.*;
import ImplementingClasses.*;
import Inheritence.*;
import Introduction.*;
import Iteration.*;
import Labs.*;
import Miscellaneous.*;
import Recursion.*;
import StaticArrays.*;

public class test {

	public static void main(String[] args) {
		/*
		// Cash register tester (for all of the extra features only):
		CashRegister reg = new CashRegister();
		CashRegister taxReg = new CashRegister(6.25);
		reg.addItem("Cereal", 4.00, 2);
		reg.addItem("Cereal2", 2.00, 1);
		reg.addItem("Java", 90.00, 1);
		taxReg.addItem("Cereal", 4.00, 2);
		taxReg.addItem("Cereal2", 2.00, 1);
		taxReg.addItem("Java", 90.00, 1);
		System.out.println("Subtotal without tax: " + reg.getSubtotal());
		System.out.println("Subtotal with tax: " + taxReg.getSubtotal());
		System.out.println("Total tax without tax: " + reg.getTotalTax());
		System.out.println("Total tax with tax: " + taxReg.getTotalTax());
		System.out.println("Total without tax: " + reg.getTotal());
		System.out.println("Total with tax: " + taxReg.getTotal());
		reg.enterPayment(90.00);
		taxReg.enterPayment(100.00);
		System.out.println("Change without tax: " + reg.giveChange());
		System.out.println("Change with tax: " + taxReg.giveChange());
		reg.newCustomer();
		taxReg.newCustomer();
		reg.addItem("Python", 10.00, 4);
		taxReg.addItem("Python", 10.00, 4);
		System.out.println("Without tax (customer 2): " + reg.getTotal());
		System.out.println("With tax (customer 2): " + taxReg.getTotal());
		System.out.println("Total Items Sold (no tax reg): " + reg.getSalesCount());
		System.out.println("Total Items Sold (tax reg): " + taxReg.getSalesCount());
		System.out.println("Sales Total (no tax reg): " + reg.getSalesTotal());
		System.out.println("Sales Total (tax reg): " + reg.getSalesTotal());
		reg.reset();
		taxReg.reset();
		System.out.println("Total after reset (no tax): " + reg.getTotal());
		System.out.println("Total after reset (with tax): " + taxReg.getTotal());
		
		System.out.print("\n\n");
		
		
		
		// Bank account tester
		BankAccount acc = new BankAccount();
		acc.deposit(1000);
		acc.withdraw(500);
		acc.withdraw(400);
		System.out.println("Expected Result: $100");
		System.out.println("Actual Result: $" + acc.getBalance());
		acc.addInterest(10);
		System.out.println("Expected Result2: $110");
		System.out.println("Actual Result: $" + acc.getBalance());
		
		System.out.print("\n\n");
		
		
		// Employee tester
		Employee shelly = new Employee("Sheldon Cooper", 50000);
		System.out.println(shelly.getName());
		System.out.println(shelly.getSalary());
		shelly.raiseSalary(10);
		System.out.println("Expected New Salary: 55000");
		System.out.println("Actual New Salary: " + shelly.getSalary());
		
		System.out.print("\n\n");
		
		
		// Car tester
		Car myCar = new Car(50);
		myCar.addGas(20);
		System.out.println("Original gas in tank: " + myCar.getGasInTank());
		myCar.drive(100);
		System.out.println("Predicted final gas in tank: 18");
		System.out.println("Final gas in tank: " + myCar.getGasInTank());
		
		System.out.print("\n\n");
		
		// Student tester
		Student me = new Student("Eric");
		System.out.println("Name of Student: " + me.getName());
		me.addQuiz(50);
		me.addQuiz(100);
		me.addQuiz(70);
		me.addQuiz(80);
		System.out.println("Expected Average Score: 75");
		System.out.println("Average Score: " + me.getAverageScore());
		
		System.out.print("\n\n");
		
		// AntPopulation Tester (Ant Simulation)
		AntPopulation myColony = new AntPopulation(10);
		myColony.breed();
		myColony.spray();
		System.out.println("Round 1: " + myColony.getAnts());
		myColony.breed();
		myColony.spray();
		System.out.println("Round 2: " + myColony.getAnts());
		myColony.breed();
		myColony.spray();
		System.out.println("Round 3: " + myColony.getAnts());
		
		System.out.print("\n\n");
		
		// Bug Tester
		Bug myboi = new Bug(0);
		System.out.println("Original Position: " + myboi.getPosition());
		myboi.move();
		myboi.move();
		System.out.println("Predicted Position: 2");
		System.out.println("Actual Position: " + myboi.getPosition());
		myboi.turn();
		myboi.move();
		System.out.println("Predicted Position: 1");
		System.out.println("Actual Position: " + myboi.getPosition());
		
		System.out.print("\n\n");
		
		Rational reduceable = new Rational(16,10);
		System.out.println("Original (reduceable): " + reduceable.getOriginal());
		System.out.println("Reduced (reduceable): " + reduceable.getRational());
		System.out.println("Decimal (reduceable): " + reduceable.getDecimal());
		Rational irreduceable = new Rational(3,7);
		System.out.println("Original (irreduceable): " + irreduceable.getOriginal());
		System.out.println("Reduced (irreduceable): " + irreduceable.getRational());
		System.out.println("Decimal (irreduceable): " + irreduceable.getDecimal());
		
		System.out.print("\n\n");
		
		Question Q1 = new Question("When was Mass Academy founded?", "1992");
		Question Q2 = new Question("What is the WPI mascot?", "Goat");
		ChoiceQuestion Q3 = new ChoiceQuestion("Who was the founder of Mass Academy?");
		Q3.addChoice("Michael Barney", false);
		Q3.addChoice("Arthur Chase", true);
		Q3.addChoice("Grant Perkins", false);
		Q3.addChoice("Jesus Cologne", false);
		NumericQuestion Q4 = new NumericQuestion("What is 1+1?", "1.7");
		FillInQuestion Q5 = new FillInQuestion("A^2 + B^2 = C^2 is called the _Pythagorean Theorem_");
		MultiChoiceQuestion Q6 = new MultiChoiceQuestion("Which of the following is a musical instrument?");
		Q6.addChoice("Flute", true);
		Q6.addChoice("Trumpet", true);
		Q6.addChoice("Uncle Grime", false);
		Q6.addChoice("Oboe", true);
		Q6.addChoice("Dr. James Grime", false);
		ArrayList<Question> quiz = new ArrayList<Question>();
		quiz.add(Q1);
		quiz.add(Q2);
		quiz.add(Q3);
		quiz.add(Q4);
		quiz.add(Q5);
		quiz.add(Q6);
		Scanner pencil = new Scanner(System.in);
		for (Question q : quiz) {
			q.display();
			System.out.println("Please enter your response below:");
			String response = pencil.nextLine();
			System.out.println(q.checkAnswer(response));
		}
		
		Sentence a = new Sentence("racecar");
		System.out.println(a.isPalindrome());
		System.out.println(a.reverse());
		System.out.println(a.find("car"));
		System.out.println(a.find("hahahaha"));
		System.out.println(a.indexOf("car"));
		System.out.println(a.indexOf("hahahaha"));
		System.out.println(a.substringGenerator());
		
		
		int[] yeet = {1, 4, 6, 2, 7};
		Dataset numbers = new Dataset(yeet, 1, 4);
		System.out.println(numbers.getSum());
		System.out.println(numbers.getMaximum()); */
		
		Point b = new Point();
		Point c = new Point(0,2);
		Point d = new Point(2,4);
		Point e = new Point(3,4);
		Point f = new Point(5,2);
		Point g = new Point(3,0);
		Polygon plsWork = new Polygon();
		plsWork.addPoint(b);
		plsWork.addPoint(c);
		plsWork.addPoint(d);
		plsWork.addPoint(e);
		plsWork.addPoint(f);
		plsWork.addPoint(g);
		System.out.println(plsWork.getArea());
		Point h = new Point(0, 3);
		Point i = new Point(3, 3);
		Polygon square = new Polygon();
		square.addPoint(b);
		square.addPoint(g);
		square.addPoint(h);
		square.addPoint(i);
		System.out.println(square.getArea());
		Polygon triangle = new Polygon();
		triangle.addPoint(b);
		triangle.addPoint(g);
		triangle.addPoint(h);
		System.out.println(triangle.getArea());
	}
	
}
