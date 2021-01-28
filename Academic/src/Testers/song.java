package Testers;

public class song {

	public static void main(String[] args) {
		for (int i = 1; i <= 8; i ++) {
			verseConstructor(i);
			System.out.println();
		}
	}
	
	static void verseConstructor(int verseNum) {
		if (verseNum == 1) {
			firstLine("fly");
			fly();
		}
		else if (verseNum == 2) {
			firstLine("spider");
			spider();
		}
		else if (verseNum == 3) {
			firstLine("bird");
			System.out.println("How absurd, to swallow a bird!");
			bird();
		}
		else if (verseNum == 4) {
			
		}
		else if (verseNum == 5) {
			
		}
		else if (verseNum == 6) {
			
		}
		else if (verseNum == 7) {
			
		}
		else {
			
		}
	}
	
	static void firstLine(String animal) {
		System.out.println("There was an old lady who swallowed a " + animal);
	}
	
	static void swallowLine(String animal1, String animal2) {
		System.out.println("She swallowed the " + animal1 + " to catch the " + animal2);
	}
	
	static void fly() {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
	}
	
	static void spider() {
		System.out.println("That wriggled and jiggled and wiggled inside her.");
		swallowLine("spider", "fly");
		fly();
	}
	
	static void bird() {
		swallowLine("bird", "spider");
		spider();
	}

}
