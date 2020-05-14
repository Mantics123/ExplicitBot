package explicit.command.everyone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import explicit.Bot;
import explicit.command.Command;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Suggest extends Command {

	public Suggest() {
		super("Suggest", "Makes a suggestion to the server", null);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		String channelID = "";
    	File f = new File(event.getGuild().getId(), "channelID.txt");
    	if (!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			while (line != null && line != "") {
				channelID = line;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (channelID != null && channelID != "" && args.length > 1) {
			
			StringBuilder sb = new StringBuilder("");
			for (String str : args) {
				if (str != args[0]) {
					sb.append(str);
					sb.append(" ");
				}
			}
			TextChannel textChannel = event.getGuild().getTextChannelById(channelID);
			Message m = textChannel.sendMessage("Suggestion From " + event.getAuthor().getAsMention() + ": " + sb.toString()).complete();
			m.addReaction("👍").queue();
			m.addReaction("👎").queue();
			event.getChannel().sendMessage("Successfully added your suggestion in " + textChannel.getAsMention()).queue();
		} else if (args.length > 1) {
			event.getChannel().sendMessage("The channel is not setup").queue();
		} else {
			event.getChannel().sendMessage("ERROR: No Suggestion. USAGE: " + Bot.instance.commandManager.getPrefix() + "suggest <Suggestion>").queue();
		}
	}
}
