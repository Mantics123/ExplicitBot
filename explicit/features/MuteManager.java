package explicit.features;

import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MuteManager {
	
	public boolean muteUser(Guild guild, Member u) {
		if (this.isMuted(u)) {
			return false;
		}
		System.out.println("Muted " + u.getUser().getName() + " in " + guild.getName());
		List<Role> roles = guild.getRolesByName("Muted", true);
		if (roles != null && !roles.isEmpty()) {
			guild.addRoleToMember(u, roles.get(0)).queue();
			return true;
		} else if (roles == null || roles.isEmpty()) {
			Role r = guild.createRole().setName("Muted").complete();
			guild.addRoleToMember(u, r).queue();
			return true;
		}
		return false;
	}
	
	public boolean unMuteUser(Guild guild, Member u) {
		if (!this.isMuted(u)) {
			return false;
		}
		System.out.println("Unmuted " + u.getUser().getName() + " in " + guild.getName());
		List<Role> roles = guild.getRolesByName("Muted", true);
		if (roles != null && !roles.isEmpty()) {
			guild.removeRoleFromMember(u, roles.get(0)).queue();
			return true;
		} else if (roles == null || roles.isEmpty()) {
			Role r = guild.createRole().setName("Muted").complete();
			guild.removeRoleFromMember(u, r).queue();
			return true;
		}
		return false;
	}
	
	public boolean isMuted(Member u) {
		for (int k = 0; k < u.getRoles().size(); k++) {
			if (u.getRoles().get(k).getName().equalsIgnoreCase("Muted")) {
				return true;
			}
		}
		
		return false;
	}
	
    public boolean onMessageReceived(MessageReceivedEvent event) {	
    	if (!event.isFromGuild() || event.getAuthor().getId().contains("700622750896750612")) {
    		return false;
    	}
    	
    	if (this.isMuted(event.getMember())) {
    		event.getMessage().delete().complete();
    		
    		Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					Message message = event.getChannel().sendMessage(event.getMember().getAsMention() + " sorry you are muted.").complete();
					
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					message.delete().complete();
				}
    		});
    		t1.start();
			return true;
    	}
    	return false;
    }
}
