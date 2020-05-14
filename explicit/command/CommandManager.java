package explicit.command;

import java.util.ArrayList;

import explicit.command.admin.Nuke;
import explicit.command.admin.SetJoinMessage;
import explicit.command.admin.SetSuggest;
import explicit.command.admin.mute.Mute;
import explicit.command.admin.mute.TempMute;
import explicit.command.admin.mute.UnMute;
import explicit.command.everyone.Download;
import explicit.command.everyone.Help;
import explicit.command.everyone.Ping;
import explicit.command.everyone.Suggest;
import explicit.command.everyone.Uptime;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandManager {

	public ArrayList<Command> commands = new ArrayList<Command>();
	private String prefix = "e$";
	
	public CommandManager() {
		this.commands.add(new Ping());
		this.commands.add(new Download());
		this.commands.add(new Help());
		this.commands.add(new Nuke());
		this.commands.add(new SetJoinMessage());
		this.commands.add(new SetSuggest());
		this.commands.add(new Suggest());
		this.commands.add(new Uptime());
		this.commands.add(new Mute());
		this.commands.add(new UnMute());
		this.commands.add(new TempMute());
	}
	
    public void onMessageReceived(MessageReceivedEvent event) {
    	
    	String message = event.getMessage().getContentRaw();
    	if (!message.startsWith("e!") && !message.startsWith("E!") && !message.startsWith("e$") && !message.startsWith("E$")) {
    		return;
    	}
    	
    	message = message.subSequence(2, message.length()).toString();
    	String args[] = message.split(" ");
    	for (int k = 0; k < commands.size(); k++) {
    		if (args[0].equalsIgnoreCase(commands.get(k).getName()) && event.getMember().getPermissions(event.getTextChannel()).contains(commands.get(k).getNeededPermission())) {
    			commands.get(k).onCommand(message, args, event);
    		}
    	}
    }
    
	public String getPrefix() {
		return this.prefix;
	}
}
