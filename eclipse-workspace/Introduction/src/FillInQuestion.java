/**
 * This class is a child of the Question class and it models a Fill In Question
 * @author eric_li
 *
 */
public class FillInQuestion extends Question {
	
	//Constructor
	/**
	 * Constructs a FillIn Question with a given question and the answer surrounded underscores to indicate the word being filled in.
	 * @param q the question being asked
	 */
	public FillInQuestion(String q) {
		super();
		String correctAnswer = "";
		int startIndex = 0;
		int endIndex = 0;
		int counter = 0;
		for (int i = 0; i < q.length(); i ++) {
			if (q.charAt(i) == 95 && counter == 0) {
				startIndex = i + 1;
				counter ++;
			}
			if (q.charAt(i) == 95 && counter == 1) {
				endIndex = i - 1;
			}
		}
		for (int i = startIndex; i <= endIndex; i ++) {
			correctAnswer = correctAnswer + String.valueOf(q.charAt(i));
		}
		super.setAnswer(correctAnswer);
		q = q.replaceAll(correctAnswer, "");
		setQuestion(q);
	}

}
