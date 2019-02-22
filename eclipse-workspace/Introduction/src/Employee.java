/**
 * This is an employee class that allows you to add employees and manage salaries
 * @author eric_li
 *
 */
public class Employee {
	
	private String name;
	private double salary;
	
	/**
	 * Constructs an employee object with a name and a salary
	 * @param name the employee's name
	 * @param salary the employee's salary
	 */
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	
	/**
	 * Gets the name of the employee
	 * @return the name of the employee
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the salary of the employee
	 * @return the salary of the employee
	 */
	public double getSalary() {
		return salary;
	}
	
	/**
	 * Raises the salary of the employee by the entered percentage
	 * @param byPercent the percentage by which the salary is increased
	 */
	public void raiseSalary(double byPercent) {
		salary += salary*(byPercent/100);
	}
}
