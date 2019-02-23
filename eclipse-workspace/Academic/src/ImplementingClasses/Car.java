package ImplementingClasses;
/**
 * This is a class that simulates a car driving.
 * @author eric_li
 *
 */
public class Car {
	
	private double gas;
	private double efficiency;
	
	/**
	 * Constructs a car object with a given fuel efficiency
	 * @param efficiency fuel efficiency of the car
	 */
	public Car(double efficiency) {
		gas = 0;
		this.efficiency = efficiency;
	}
	
	/**
	 * Gets the gas in the tank
	 * @return gas in tank
	 */
	public double getGasInTank() {
		return gas;
	}
	
	/**
	 * Adds the entered amount of gas into the tank
	 * @param gas amount of gas added to the gas tank
	 */
	public void addGas(double gas) {
		this.gas += gas;
	}
	
	/**
	 * Makes the car drive a certain distance and calculates the remaining gas accordingly
	 * @param distance distance that the car travels
	 */
	public void drive(double distance) {
		gas -= distance/efficiency;
	}
}
