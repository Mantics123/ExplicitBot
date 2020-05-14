package explicit.command.everyone;

import explicit.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping extends Command {

	public Ping() {
		super("Ping", "Returns the bots ping to the server", null);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		event.getChannel().sendMessage("I have got " + event.getJDA().getRestPing().complete() + " ping").queue();
	}
}
