package explicit.command.admin.mute;

import explicit.Bot;
import explicit.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UnMute extends Command {

	public UnMute() {
		super("Unmute", "Removes a mute from a user so they can talk", Permission.MESSAGE_MANAGE);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		if (event.getMessage().getMentionedMembers().size() > 0) {
			if (Bot.instance.muteManager.unMuteUser(event.getGuild(), event.getMessage().getMentionedMembers().get(0))) {
				event.getChannel().sendMessage("Successfully unmuted " + event.getMessage().getMentionedMembers().get(0).getUser().getName()).queue();
			} else {
				event.getChannel().sendMessage("Something went wrong with unmuting " + event.getMessage().getMentionedMembers().get(0).getUser().getName() + ". Maybe they were already unmuted?").queue();
			}
		}
	}
}
