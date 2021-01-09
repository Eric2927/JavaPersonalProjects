package general;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {
	public static JDA jda;
	public static String prefix = "::";
	
	public static void main(String args[]) throws LoginException {
		jda = JDABuilder.createDefault("Nzk2MDY3NTc3NTg3NDMzNDcz.X_ShtA.7ywhfvaJ6ZXtxiUh9Xf4YIL5DHI").build();
		
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		jda.getPresence().setActivity(Activity.playing("Nothing"));
		
		jda.addEventListener(new Commands());
	}
}
