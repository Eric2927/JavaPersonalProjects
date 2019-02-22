/**
 * This class is a child of the Question class, and it is specially designed to hold a math question that accepts a numeric answer.
 * @author eric_li
 *
 */
public class NumericQuestion extends Question {
	
	//Constructors
	/**
	 * Constructs a numeric question with a given question text and answer
	 * @param q the question being asked
	 * @param a the answer to the question
	 */
	public NumericQuestion(String q, String a) {
		super(q, a);
	}
	
	//Methods
	/**
	 * Checks if the input response is correct (within 0.01 of the correct answer)
	 */
	public boolean checkAnswer(String response) {
		if (Double.parseDouble(response) >= Double.parseDouble(super.getAnswer()) - 0.01 && Double.parseDouble(response) <= Double.parseDouble(super.getAnswer()) + 0.01) {
			return true;
		}
		else {
			return false;
		}
	}
}
