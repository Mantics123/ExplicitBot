package explicit.command.everyone;

import java.util.concurrent.TimeUnit;

import explicit.Bot;
import explicit.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Uptime extends Command {

	public Uptime() {
		super("Uptime", "Shows you how long the bot has been up since last restart", null);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		long time = System.currentTimeMillis() - Bot.instance.startTime;
		long second = TimeUnit.MILLISECONDS.toSeconds(time) % 60;
		long minute = TimeUnit.MILLISECONDS.toMinutes(time) % 60;
		long hour = TimeUnit.MILLISECONDS.toHours(time) % 24;
		long day = TimeUnit.MILLISECONDS.toDays(time);
		StringBuilder sb = new StringBuilder("Uptime: ```");
		sb.append((day > 0 ? day + " day" + (day > 1 ? "s, " : ", ") : ""));
		sb.append((hour > 0 ? hour + " hour" + (hour > 1 ? "s, " : ", ") : ""));
		sb.append((minute > 0 ? minute + " minute" + (minute > 1 ? "s, " : ", ") : ""));
		sb.append((second > 0 ? second + " second" + (second > 1 ? "s" : "") : ""));
		sb.append("```");
		event.getChannel().sendMessage(sb.toString()).queue();
	}
}
