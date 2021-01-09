package general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.requests.ratelimit.BotRateLimiter;

import java.util.List;

public class Commands extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");

		// Help command
		if (args[0].equalsIgnoreCase(Bot.prefix + "help")) {
			EmbedBuilder cmdList = new EmbedBuilder();
			cmdList.setTitle("List of Commands");
			cmdList.setDescription("Below is a list of all useable commands.");
			cmdList.addField("help", "Displays list of commands", false);
			cmdList.addField("purge [# of messages]", "Deletes given number of messages", false);
			cmdList.setColor(0xbb0000);
			event.getChannel().sendMessage(cmdList.build()).queue();
			cmdList.clear();
		}

		// Purge command
		// Check manage messages permission?
		else if (args[0].equalsIgnoreCase(Bot.prefix + "purge")) {
			if (args.length < 2) {
				EmbedBuilder invalidSyntaxError = new EmbedBuilder();
				invalidSyntaxError.setColor(0xff0000);
				invalidSyntaxError.setTitle("Please specify the number of messages to delete.");
				invalidSyntaxError.setDescription("Syntax: " + Bot.prefix + "purge [# of messages]");
				event.getChannel().sendMessage(invalidSyntaxError.build()).queue();
				invalidSyntaxError.clear();
			} else {
				try {
					int numMessagesToPurge = Integer.parseInt(args[1]);
					if (numMessagesToPurge < 2 || numMessagesToPurge > 100) {
						// Invalid number of messages to delete
						EmbedBuilder invalidMsgNumError = new EmbedBuilder();
						invalidMsgNumError.setColor(0xff0000);
						invalidMsgNumError.setTitle("Invalid number of selected messages to delete.");
						invalidMsgNumError.setDescription("Can only delete between 2 to 100 messages inclusive.");
						event.getChannel().sendMessage(invalidMsgNumError.build()).queue();
						invalidMsgNumError.clear();
					} else {
						try {
							List<Message> messages = event.getChannel().getHistory().retrievePast(numMessagesToPurge).complete();
							event.getChannel().deleteMessages(messages).queue();
							EmbedBuilder success = new EmbedBuilder();
							success.setColor(0x00ff00);
							success.setTitle(numMessagesToPurge + " messages have been purged.");
							event.getChannel().sendMessage(success.build()).queue();
							success.clear();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
							// Message is too old
							EmbedBuilder oldMsgError = new EmbedBuilder();
							oldMsgError.setColor(0xff0000);
							oldMsgError.setTitle("Selected message(s) is too old and cannot be deleted.");
							oldMsgError.setDescription("Messages 2 weeks or older cannot be deleted.");
							event.getChannel().sendMessage(oldMsgError.build()).queue();
							oldMsgError.clear();
						}
					}
				} catch (NumberFormatException e) {
					EmbedBuilder invalidParameterError = new EmbedBuilder();
					invalidParameterError.setColor(0xff0000);
					invalidParameterError.setTitle("Invalid number of selected messages to delete.");
					invalidParameterError.setDescription("No need. Just enter a number man.");
					event.getChannel().sendMessage(invalidParameterError.build()).queue();
					invalidParameterError.clear();
				}
			}
		}
		
		
		// More commands

	}

}
