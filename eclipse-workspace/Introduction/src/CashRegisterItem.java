/**
 * This is an item for the cash register that stores its name, unit cost, and how many of it there are.
 * @author eric_li
 *
 */
public class CashRegisterItem {
	
	//Data
	private String name;
	private double cost;
	private int quantity;
	
	//Constructors
	/**
	 * Constructs a cash register item object with a given name, cost, and quantity.
	 * @param itemName the name of the item
	 * @param itemCost the unit cost of the item
	 * @param itemQuantity the number of items
	 */
	public CashRegisterItem(String itemName, double itemCost, int itemQuantity) {
		name = itemName;
		cost = itemCost;
		quantity = itemQuantity;
	}
	
	//Methods
	
	/**
	 * Gets the name of the item
	 * @return name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the cost of the item
	 * @return the cost of the item
	 */
	public double getCost() {
		return cost*quantity;
	}
	
	/**
	 * Gets the unit cost of the item
	 * @return the cost of one item
	 */
	public double getUnitCost() {
		return cost;
	}
	
	/**
	 * Gets the number of items
	 * @return the number of items
	 */
	public int getQuantity() {
		return quantity;
	}
}
