package Inheritence;
/**
 * This class stores a question and a correct answer
 * @author eric_li
 *
 */
public class Question {
	
	//Data
	private String question;
	private String answer;
	
	//Constructors
	
	/**
	 * Constructs a question with a given question and correct answer
	 * @param questionText the question being asked
	 * @param questionAnswer the correct answer
	 */
	public Question(String questionText, String questionAnswer) {
		question = questionText;
		answer = questionAnswer;
	}
	
	/**
	 * Constructs a question with no set answer yet
	 * @param questionText the question being asked
	 */
	public Question(String questionText)  {
		question = questionText;
		answer = "";
	}
	
	/**
	 * Constructs an default object of this class without a preset question nor answer
	 */
	public Question() {
		question = "";
		answer = "";
	}
	
	//Methods
	
	/**
	 * Sets the answer as the input
	 * @param correctResponse the answer to the question
	 */
	public void setAnswer(String correctResponse) {
		answer = correctResponse;
	}
	
	/**
	 * Sets the question as the input
	 * @param q the question being asked
	 */
	public void setQuestion(String q) {
		question = q;
	}
	
	/**
	 * Gets the answer to the question
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * Checks a response for correctness
	 * @param response the user answer
	 * @return true if response is correct, false otherwise
	 */
	public boolean checkAnswer(String response) {
		return answer.trim().equalsIgnoreCase(response);
	}
	
	/**
	 * Displays the question
	 */
	public void display() {
		System.out.println(question);
	}
	
	/**
	 * Displays the question and the answer (as an answer key)
	 */
	public void ToString() {
		System.out.println(question + "\nAnswer: " + answer);
	}
	
}
