package Testers;
import java.util.Scanner;

import ElevensSolitaire.Deck;

/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
		String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
		int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10};
		Deck exampleDeck = new Deck(ranks, suits, values);
		System.out.println(exampleDeck.toString());
		System.out.println(exampleDeck.deal());
		System.out.println(exampleDeck.deal());
	}
}
