package explicit.command.admin;

import explicit.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Nuke extends Command {

	public Nuke() {
		super("Nuke", "Nukes the channel typed in, removing all messages", Permission.MANAGE_CHANNEL);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		if (event.getTextChannel() != null) {
			System.out.println("Nuking Channel: #" + event.getChannel().getName());
			TextChannel channel = event.getTextChannel().createCopy().complete();
			channel.getManager().setPosition(event.getTextChannel().getPosition()).queue();
			event.getGuild().getTextChannelById(event.getChannel().getId()).delete().queue();
			channel.sendMessage("This channel has been nuked!").queue();
		}
	}
}
