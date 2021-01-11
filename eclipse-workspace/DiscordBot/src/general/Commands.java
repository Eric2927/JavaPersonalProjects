package general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.RoleManager;
import net.dv8tion.jda.internal.requests.ratelimit.BotRateLimiter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Commands extends ListenerAdapter {
	
	public void onGuildJoin(GuildJoinEvent event) {
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");

		// Help command
		if (args[0].equalsIgnoreCase(Bot.prefix + "help")) {
			EmbedBuilder cmdList = new EmbedBuilder();
			cmdList.setTitle("List of Commands");
			cmdList.setDescription("Below is a list of all useable commands. [] indicate mandatory parameters. {} indicate optional parameters.");
			cmdList.addField("help", "Displays list of commands", false);
			cmdList.addField("purge [# of messages]", "Deletes given number of messages", false);
			cmdList.setColor(Bot.primaryColor);
			event.getChannel().sendMessage(cmdList.build()).queue((embed) -> cmdList.clear());
		}

		// Purge command
		// Check manage messages permission?
		else if (args[0].equalsIgnoreCase(Bot.prefix + "purge")) {
			if (args.length < 2) {
				EmbedBuilder invalidSyntaxError = new EmbedBuilder();
				invalidSyntaxError.setColor(0xff0000);
				invalidSyntaxError.setTitle("Please specify the number of messages to delete.");
				invalidSyntaxError.setDescription("Command syntax: " + Bot.prefix + "purge [# of messages]");
				event.getChannel().sendMessage(invalidSyntaxError.build()).queue((embed) -> invalidSyntaxError.clear());
				return;
			}
			try {
				int numMessagesToPurge = Integer.parseInt(args[1]);
				if (numMessagesToPurge < 2 || numMessagesToPurge > 100) {
					// Invalid number of messages to delete
					EmbedBuilder invalidMsgNumError = new EmbedBuilder();
					invalidMsgNumError.setColor(Bot.errorColor);
					invalidMsgNumError.setTitle("Invalid number of selected messages to delete.");
					invalidMsgNumError.setDescription("Can only delete between 2 to 100 messages inclusive.");
					event.getChannel().sendMessage(invalidMsgNumError.build()).queue((embed) -> invalidMsgNumError.clear());
					return;
				}
				try {
					List<Message> messages = event.getChannel().getHistory().retrievePast(numMessagesToPurge).complete();
					event.getChannel().deleteMessages(messages).queue();
					EmbedBuilder success = new EmbedBuilder();
					success.setColor(Bot.successColor);
					success.setTitle(numMessagesToPurge + " messages have been purged.");
					event.getChannel().sendMessage(success.build()).queue((embed) -> success.clear());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					// Message is too old
					EmbedBuilder oldMsgError = new EmbedBuilder();
					oldMsgError.setColor(Bot.errorColor);
					oldMsgError.setTitle("Selected message(s) is too old and cannot be deleted.");
					oldMsgError.setDescription("Messages 2 weeks or older cannot be deleted.");
					event.getChannel().sendMessage(oldMsgError.build()).queue((embed) -> oldMsgError.clear());
				}
			} catch (NumberFormatException e) {
				EmbedBuilder invalidParameterError = new EmbedBuilder();
				invalidParameterError.setColor(Bot.errorColor);
				invalidParameterError.setTitle("Invalid number of selected messages to delete.");
				invalidParameterError.setDescription("No need. Just enter a number.");
				event.getChannel().sendMessage(invalidParameterError.build()).queue((embed) -> invalidParameterError.clear());
			}
		}
		
		
		// Mute command
		else if (args[0].equalsIgnoreCase(Bot.prefix + "mute")) {
			final List<Role> mutedRoles = event.getGuild().getRolesByName("Muted", true);
			if (mutedRoles.isEmpty()) {
				event.getGuild().createRole().setName("Muted").setColor(0x000001).complete();
			}
			Role mutedRole = event.getGuild().getRolesByName("Muted", true).get(0);
			Member userToMute;
			switch (args.length) {
			case 2:
				userToMute = event.getGuild().getMemberById(args[1].replace("<@", "").replace(">", ""));
				event.getGuild().addRoleToMember(userToMute, mutedRole).queue();
				event.getGuild().getTextChannels().forEach((channel) -> channel.putPermissionOverride(userToMute).deny(Permission.MESSAGE_WRITE).queue());
				break;
			case 3:
				userToMute = event.getGuild().getMemberById(args[1].replace("<@", "").replace(">", ""));
				event.getGuild().addRoleToMember(userToMute, mutedRole).queue();
				break;
			default:
				EmbedBuilder invalidParamNumError = new EmbedBuilder();
				invalidParamNumError.setColor(Bot.errorColor);
				invalidParamNumError.setTitle("Invalid number of parameters.");
				invalidParamNumError.setDescription("Command syntax: " + Bot.prefix + "mute [user tag] {minutes to mute}");
				event.getChannel().sendMessage(invalidParamNumError.build()).queue((embed) -> invalidParamNumError.clear());
				break;
			}
		}
		
		// Unmute command
		else if (args[0].equalsIgnoreCase(Bot.prefix + "unmute")) {
			Role mutedRole = event.getGuild().getRolesByName("Muted", true).get(0);
			if (args.length == 2) {
				Member userToUnmute = event.getGuild().getMemberById(args[1].replace("<@", "").replace(">", ""));
				event.getGuild().removeRoleFromMember(userToUnmute, mutedRole).queue();
			} else {
				EmbedBuilder invalidParamNumError = new EmbedBuilder();
				invalidParamNumError.setColor(Bot.errorColor);
				invalidParamNumError.setTitle("Invalid number of parameters.");
				invalidParamNumError.setDescription("Command syntax: " + Bot.prefix + "unmute [user tag]");
				event.getChannel().sendMessage(invalidParamNumError.build()).queue((embed) -> invalidParamNumError.clear());
			}
		}
		
		
		
		// More commands

	}

}
