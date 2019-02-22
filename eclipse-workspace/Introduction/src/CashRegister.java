import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * This is a cash register class that acts like a cash register.
 * @author eric_li
 *
 */
public class CashRegister {
	
	//Data
	private double total;
	private double payment;
	private double tax;
	private ArrayList<CashRegisterItem> itemList;
	private double salesTotal;
	private double itemTotal;
	
	//Constructors
	/**
	 * Constructs a cash register object with the given tax rate
	 * @param taxRate the given tax rate
	 */
	public CashRegister(double taxRate) {
		total = 0;
		payment = 0;
		tax = taxRate/100;
		itemList = new ArrayList<CashRegisterItem>();
		itemTotal = 0;
		salesTotal = 0;
	}
	
	/**
	 * Constructs a cash register object with no tax
	 */
	public CashRegister() {
		total = 0;
		payment = 0;
		tax = 0;
		itemTotal = 0;
		salesTotal = 0;
		itemList = new ArrayList<CashRegisterItem>();
	}
	
	//Methods
	/**
	 * Adds an item with a given cost in US dollars
	 * @param cost the cost of the item in US dollars
	 */
	
	public void addItem(double cost) {
		total += cost;
		salesTotal += cost;
		itemTotal ++;
	}
	
	public void addItem(String name, double cost, int quantity) {
		CashRegisterItem a = new CashRegisterItem(name, cost, quantity);
		if (cost < 0) {
			throw new IllegalArgumentException("Cost is less than 0");
		}
		itemList.add(a);
		total += cost*quantity;
		salesTotal += cost*quantity;
		itemTotal += quantity;
	}
	
	/**
	 * Gets the total of all items in US dollars with tax
	 * @return the total of all items in US dollars, counting tax
	 */
	
	public double getTotal() {
		return total + total*tax;
	}
	
	/**
	 * Accepts total payment in US dollars
	 * @param payment the amount the customer paid in US dollars
	 */
	
	public void enterPayment(double payment) {
		this.payment += payment;
	}
	
	/**
	 * Gets the change due in US dollars
	 * @return the change due in US dollars
	 */
	
	public double giveChange() {
		return (total + total*tax) - payment;
	}
	
	/**
	 * Gets the total tax of the items in US dollars
	 * @return the total tax of the items in US dollars
	 */
	
	public double getTotalTax() {
		return total*tax;
	}
	
	/**
	 * Empties the cart for a new customer
	 */
	
	public void newCustomer() {
		total = 0;
		payment = 0;
		itemList = new ArrayList<CashRegisterItem>();
	}
	
	/**
	 * Resets the object's data except for tax to account for a new day
	 */
	public void reset() {
		total = 0;
		payment = 0;
		itemList = new ArrayList<CashRegisterItem>();
		salesTotal = 0;
		itemTotal = 0;
	}
	
	/**
	 * Gets the total of all the items in US dollars without tax
	 * @return total of all items in US dollars without tax
	 */
	
	public double getSubtotal() {
		return total;
	}
	
	/**
	 * Gets today's date
	 * @return today's date
	 */
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * Gets a receipt for the current cart
	 * @return a formatted receipt for current cart
	 */
	public StringBuffer getReceipt() {
		StringBuffer buf = new StringBuffer();
		buf.append("\t\t\t" + getDate() + "\n");
		buf.append("\t\t Market Basket\n");
		buf.append("\t\tMore for your dollar!\n\n");
		for (CashRegisterItem item : itemList) {
			buf.append(item.getQuantity() + " ");
			buf.append(item.getName() + "\t\t");
			buf.append("$" + item.getCost() + "\n");
		}
		buf.append("\nSUBTOTAL: " + total + "\n");
		buf.append("AMOUNT PAID: " + payment + "\n");
		buf.append("CHANGE DUE: " + giveChange() + "\n");
		buf.append("TOTAL: " + getTotal() + "\n");
		return buf;
	}
	
	/**
	 * Gets total profit accumulated over the day
	 * @return total profit accumulated over the day
	 */
	public double getSalesTotal() {
		return salesTotal;
	}
	
	/**
	 * Gets total number of objects accumulated over the day
	 * @return total number of objects accumulated over the day
	 */
	public double getSalesCount() {
		return itemTotal;
	}
}
