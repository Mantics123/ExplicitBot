package explicit.command.admin.mute;

import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import explicit.Bot;
import explicit.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TempMute extends Command {

	public TempMute() {
		super("TempMute", "Temporarily mutes a user (Usage: e!tempmute @User 1h", Permission.MESSAGE_MANAGE);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		if (event.getMessage().getMentionedMembers().size() > 0 && args.length > 2 && (message.toLowerCase().endsWith("s") || message.toLowerCase().endsWith("h")
				|| message.toLowerCase().endsWith("d") || message.toLowerCase().endsWith("m"))) {
			
			int length = Integer.parseInt(args[2].substring(0, args[2].length() - 1));
			this.tempMute(this.parseTime(length, message.charAt(message.length() - 1) + ""), event.getMessage().getMentionedMembers().get(0), event.getGuild());
			event.getChannel().sendMessage("Successfully muted " + event.getMessage().getMentionedMembers().get(0).getUser().getName() + " for " + (double) TimeUnit.MILLISECONDS.toHours(this.parseTime(length, message.charAt(message.length() - 1) + "")) + " hours").queue();
		} else {
			event.getChannel().sendMessage("Something went wrong with muting " + event.getMessage().getMentionedMembers().get(0).getUser().getName() + ". Maybe they were already muted or the time was invalid?").queue();
		}
	}
	
	private long parseTime(int number, String c) {
		if (c.equalsIgnoreCase("m")) {
			return TimeUnit.MINUTES.toMillis(number);
		}
		if (c.equalsIgnoreCase("h")) {
			return TimeUnit.HOURS.toMillis(number);
		}
		if (c.equalsIgnoreCase("d")) {
			return TimeUnit.DAYS.toMillis(number);
		}
		if (c.equalsIgnoreCase("s")) {
			return TimeUnit.SECONDS.toMillis(number);
		}
		return -1;
	}
	
	private void tempMute(long time, Member u, Guild g) {
		Bot.instance.muteManager.muteUser(g, u);
		
		Timer timer = new Timer((int) time, e -> Bot.instance.muteManager.unMuteUser(g, u));

		timer.setRepeats(false);
		timer.start();
	}
}
