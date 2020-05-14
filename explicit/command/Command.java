package explicit.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Command {

	private String usage;
	private String description;
	private Permission neededPerm;
	
	public Command(String usage, String description, Permission neededPerm) {
		super();
		this.usage = usage;
		this.description = description;
		if (neededPerm == null) {
			this.neededPerm = Permission.MESSAGE_WRITE;
		} else {
			this.neededPerm = neededPerm;
		}
	}
	
	public String getName() {
		return usage;
	}
	 
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Permission getNeededPermission() {
		return this.neededPerm;
	}
	
	public void onCommand(String message, String[] args, MessageReceivedEvent event) {
		
	}
	
}
