package ImplementingClasses;
/**
 * This class simulates an ant population
 * @author eric_li
 *
 */
public class AntPopulation {
	
	private int population;
	
	/**
	 * Constructs an ant population object with a given initial population
	 * @param initialPopulation the colony's initial ant population
	 */
	public AntPopulation(int initialPopulation) {
		population = initialPopulation;
	}
	
	/**
	 * Gets the amount of ants in the colony
	 * @return the number of ants in the colony
	 */
	public int getAnts() {
		return population;
	}
	
	/**
	 * Doubles the amount of ants in the colony
	 */
	public void breed() {
		population += population;
	}
	
	/**
	 * Reduces the amount of ants in the colony by 10%
	 */
	public void spray() {
		population -= population*0.1;
	}
}
