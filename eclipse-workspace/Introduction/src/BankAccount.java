/**
 * This class models a bank account.
 * @author eric_li
 *
 */
public class BankAccount {
	
	private double balance;
	
	/**
	 * Constructs a bank account with no money in it
	 */
	public BankAccount() {
		balance = 0;
	}
	
	/**
	 * Constructs a bank account with a given initial balance
	 * @param initialBalance the starting balance
	 */
	public BankAccount(double initialBalance) {
		balance = initialBalance;
	}
	
	/**
	 * Gets the balance of the account
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Adds interest to the balance according to entered rate
	 * @param rate the rate at which the interest is added
	 */
	public void addInterest(double rate) {
		balance += (rate/100)*balance;
	}
	
	/**
	 * Deposits the entered amount into the account
	 * @param amount the amount deposited into the account
	 */
	public void deposit(double amount) {
		balance += amount;
	}
	
	/**
	 * Withdraws the entered amount from the account
	 * @param amount the amount withdrawn from the account
	 */
	public void withdraw(double amount) {
		balance -= amount;
	}
}
