import java.util.ArrayList;
/**
 * This class is a child of the Question class and is specially adapted to hold a multiple choice question with one correct answer.
 * @author eric_li
 *
 */
public class ChoiceQuestion extends Question {
	
	//Data
	private ArrayList<String> choices;
	
	//Constructors
	
	/**
	 * Constructs a choice question with a given question and no choices
	 * @param questionText the question being asked
	 */
	public ChoiceQuestion(String questionText) {
		super(questionText);
		choices = new ArrayList<String>();
	}
	
	//Methods
	/**
	 * Adds a choice to the list of possible answers
	 * @param choice the choice being added
	 * @param correct whether the choice is the right answer or not
	 */
	public void addChoice(String choice, boolean correct) {
		choices.add(choice);
		if (correct) {
			String choiceString = Integer.toString(choices.size());
			setAnswer(super.getAnswer() + choiceString + " ");
		}
	}
	
	/**
	 * Displays the question and lists the choices
	 */
	public void display() {
		super.display();
		for (int i = 0; i < choices.size(); i ++) {
			int choiceNumber = i + 1;
			System.out.println(choiceNumber + ": " + choices.get(i));
		}
	}
	
}
 