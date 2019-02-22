/**
 * This class is a child of the ChoiceQuestion class and is specially adapted to hold a multiple choice question with one or more correct answer(s).
 * @author eric_li
 *
 */
public class MultiChoiceQuestion extends ChoiceQuestion {
	
	/**
	 * Constructs a multiple choice question object
	 * @param question the question being asked
	 */
	public MultiChoiceQuestion(String question) {
		super(question);
	}
	
	/**
	 * Checks the input answer and determines if it contains all of the correct answers
	 */
	public boolean checkAnswer(String response) {
		boolean right = true;
		String correctChoices = super.getAnswer().replaceAll(" ", "");
		for (int i = 0; i < correctChoices.length(); i ++) {
			for (int j = 0; j < response.length(); j ++) {
				if (j == response.length() - 1 && response.charAt(j) != correctChoices.charAt(i)) {
					right = false;
					break;
				}
				if (response.charAt(j) == correctChoices.charAt(i)) {
					break;
				}
			}
			if (!right) {
				break;
			}
		}
		return right;
	}

}
