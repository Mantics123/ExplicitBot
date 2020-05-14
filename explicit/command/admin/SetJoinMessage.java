package explicit.command.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import explicit.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SetJoinMessage extends Command {

	public SetJoinMessage() {
		super("SetJoinMessage", "Sets the join messages for new users. Replaces %user% with the users name, and %server% with the servers name", Permission.MANAGE_SERVER);
	}
	
	@Override
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
    	File f = new File(event.getGuild().getId(), "joinmessage.txt");
    	if (!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
    	
    	if (args.length > 0) {
    		try {
				PrintWriter pw = new PrintWriter(f);
				String toPrint = "";
				for (int k = 0; k < args.length; k++) {
					if (k > 0) {
						toPrint = args[k] + " ";
					}
				}
				pw.print(toPrint);
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    	}
	}
}
