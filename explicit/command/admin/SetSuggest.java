package explicit.command.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import explicit.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SetSuggest extends Command {

	public SetSuggest() {
		super("SetSuggest", "Sets the channel where suggestions go into", Permission.MANAGE_SERVER);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
    	File f = new File(event.getGuild().getId(), "channelID.txt");
    	if (!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
			if (!event.getMessage().getMentionedChannels().isEmpty()) {
				try {
    				PrintWriter pw = new PrintWriter(f);
    				pw.write(event.getMessage().getMentionedChannels().get(0).getId());
    				event.getChannel().sendMessage(new StringBuilder("Set suggestion channel to ").append(event.getMessage().getMentionedChannels().get(0).getAsMention())).queue();
    				pw.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
}
