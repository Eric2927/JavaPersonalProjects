/**
 * This class stores student names and quiz scores.
 * @author eric_li
 *
 */
public class Student {
	
	private String name;
	private int totalScore;
	private int numberofQuizzes;
	
	/**
	 * Constructs a student with a given name
	 * @param name the student's name
	 */
	public Student(String name) {
		this.name = name;
		totalScore = 0;
		numberofQuizzes = 0;
	}
	
	/**
	 * Gets the name of the student
	 * @return name of the student
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds a quiz score to the student's information
	 * @param score the score that the student received on the test
	 */
	public void addQuiz(int score) {
		totalScore += score;
		numberofQuizzes ++;
	}
	
	/**
	 * Gets the average score of the student.
	 * @return the average score
	 */
	public int getAverageScore() {
		return totalScore/numberofQuizzes;
	}
}
