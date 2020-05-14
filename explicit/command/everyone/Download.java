package explicit.command.everyone;

import explicit.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Download extends Command {

	public Download() {
		super("Download", "Sends you to the download page for Explicit", null);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		event.getChannel().sendMessage("You can download Explicit at https://explicitclient.weebly.com/").queue();
	}
}
