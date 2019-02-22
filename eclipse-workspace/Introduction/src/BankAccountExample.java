// Every BackAccount object has its own balance and accountNumber instance variables, but there is only
// a single copy of the lastAssignedVariable.  That variable is stored in a separate location, outside
// any BackAccount objects.

public class BankAccountExample {
	
	private double balance;
	private int accountNumber;
	private static int lastAssignedNumber = 1000;
	
	// Default Constructor not here for brevity purposes
	
	public BankAccountExample(double amount)
	{
		balance = amount;
		lastAssignedNumber++;
		accountNumber = lastAssignedNumber;
	}
	
	/**
	 * Gets the bank account number 
	 * @return the bank account number
	 */
	public int getAccountNumber()
	{
		return accountNumber;
	}
	
	/**
	 * Gets the bank account balance
	 * @return the bank account balance
	 */
	public double getBalance()
	{
		return balance;
	}

}
