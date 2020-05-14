package explicit.command.everyone;

import explicit.Bot;
import explicit.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command {

	public Help() {
		super("Help", "Lists all commands and what they do", null);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		String toSend = "";
		for (int k = 0; k < Bot.instance.commandManager.commands.size(); k++) {
			Command command = Bot.instance.commandManager.commands.get(k);
			if (event.getMember().getPermissions(event.getTextChannel()).contains(command.getNeededPermission())) {
				toSend = toSend + command.getName() + " - " + command.getDescription() + "\n";
			}
		}
		event.getChannel().sendMessage(toSend).queue();
	}
}
