package general;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {
	public static JDA jda;
	public static String prefix = "::";
	
	public static void main(String args[]) throws LoginException {
		File tokenFile = new File("Token.txt");
		try {
			Scanner inFile = new Scanner(tokenFile);
			String token = inFile.next();
			
			jda = JDABuilder.createDefault(token).build();
			
			jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
			jda.getPresence().setActivity(Activity.playing("Nothing"));
			
			jda.addEventListener(new Commands());
		} catch (FileNotFoundException e) {
			System.out.println("Token file does not exist.");
			e.printStackTrace();
		}
	}
}
