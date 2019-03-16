package Miscellaneous;
/**
 * This program includes a function that encodes an input string using a three-rail system and a function that decodes an encrypted string.
 * @author eric_li
 *
 */
public class RailFenceEncryptor {

	public static void main(String[] args) {
		String userInput = "WEAREDISCOVERDFLEEATONCE";
		String encryptedInput = encode(userInput);
		String decryptedInput = decode(encryptedInput);
		System.out.println(encryptedInput);
		System.out.println(decryptedInput);
	}
	
	public static String encode(String input) {
		String encrypted = "";
		if (input.length() <= 3) {
			encrypted = input;
		}
		else {
			for (int i = 0; i < input.length(); i += 4) {
				encrypted = encrypted + input.charAt(i);
			}
			for (int i = 1; i < input.length(); i += 2) {
				encrypted = encrypted + input.charAt(i);
			}
			for (int i = 2; i < input.length(); i += 4) {
				encrypted = encrypted + input.charAt(i);
			}
		}
		return encrypted;
	}
	
	public static String decode(String input) {
		int midLength = input.length()/2;
		int topLength = (input.length() - 1)/4 + 1;
		int botLength = (input.length() - 4)/4 + 1;
		String topString = input.substring(0, topLength);
		String midString = input.substring(topLength, topLength + midLength);
		String botString = input.substring(topLength + midLength, topLength + midLength + botLength);
		char[] decodedChars = new char[input.length()];
		int counterTop = 0;
		int counterMid = 0;
		int counterBot = 0;
		for (int i = 0; i < input.length(); i ++) {
			if (i % 4 == 0) {
				decodedChars[i] = topString.charAt(counterTop);
				counterTop ++;
			}
			else if ((i + 2) % 4 == 0) {
				decodedChars[i] = botString.charAt(counterBot);
				counterBot ++;
			}
			else if ((i + 1) % 2 == 0) {
				decodedChars[i] = midString.charAt(counterMid);
				counterMid ++;
			}
		}
		String decryptedString = "";
		for (int i = 0; i < input.length(); i ++) {
			decryptedString = decryptedString + decodedChars[i];
		}
		return decryptedString;
	}

}
