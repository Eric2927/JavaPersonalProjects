package general;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;

public class Bot {
	public static JDA jda;
	public static String prefix = "::";
	public static int errorColor = 0xff0000;
	public static int successColor = 0x00ff00;
	public static int primaryColor = 0xbb0000;
	
	public static void main(String args[]) throws LoginException {
		File tokenFile = new File("Token.txt");
		try {
			Scanner inFile = new Scanner(tokenFile);
			String token = inFile.next();
			
			jda = JDABuilder.createDefault(token).setChunkingFilter(ChunkingFilter.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS).build();
			
			jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
			jda.getPresence().setActivity(Activity.playing("Nothing"));
			
			jda.addEventListener(new Commands());
			
		} catch (FileNotFoundException e) {
			System.out.println("Token file does not exist.");
			e.printStackTrace();
		}
	}
}
