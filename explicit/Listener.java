package explicit;

import java.io.File;

import explicit.utils.FileUtils;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
    	if (!event.isFromGuild() || event.getAuthor().getId().contains("700622750896750612")) {
    		return;
    	}
    	
    	if (Bot.instance.muteManager != null) {
    		if (Bot.instance.muteManager.onMessageReceived(event)) {
    			return;
    		}
    	}
    	
    	if (Bot.instance.commandManager != null) {
    		Bot.instance.commandManager.onMessageReceived(event);
    	}
    }

    @Override
    public void onGuildJoin(final GuildJoinEvent event) {
    	Bot.instance.guildManager.onGuildJoin(event.getGuild());
    }
    
    @Override
    public void onGuildLeave(final GuildLeaveEvent event) {
    	Bot.instance.guildManager.onGuildLeave(event.getGuild());
    }
    
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
    	if (event.getMember().isFake() || event.getMember().getUser().isBot()) {
    		return;
    	}
    	
    	File f = new File(event.getGuild().getId(), "joinmessage.txt");
    	if (f.exists()) {
        	try {
            	String joinmessage = FileUtils.getLines(f).get(0);
            	if (joinmessage != null && joinmessage != "") {
            		joinmessage = joinmessage.replaceAll("%user%", event.getMember().getUser().getName().split("#")[0]);
            		joinmessage = joinmessage.replaceAll("%server%", event.getGuild().getName());
                	event.getMember().getUser().openPrivateChannel().complete().sendMessage(joinmessage).queue();
            	}
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        	}
    	}
    }
    
    @Override
    public void onGuildReady(final GuildReadyEvent event) {
    	Bot.instance.guildManager.onGuildJoin(event.getGuild());
    }
}

