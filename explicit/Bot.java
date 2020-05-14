package explicit;

import javax.security.auth.login.LoginException;

import explicit.command.CommandManager;
import explicit.features.GuildManager;
import explicit.features.MuteManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {
	
	public static JDABuilder jda;
	public static Bot instance;
	public long startTime;
	
	public CommandManager commandManager;
	public MuteManager muteManager;
	public GuildManager guildManager;
	
	public Bot() {
       	startTime = System.currentTimeMillis();
       	guildManager = new GuildManager();
       	muteManager = new MuteManager();
       	commandManager = new CommandManager();
	}
	
    public static void main(String[] args) throws LoginException {
        (Bot.jda = new JDABuilder()).setToken("");
        Bot.jda.addEventListeners(new Listener());
        Bot.jda.setActivity(Activity.watching("https://explicitclient.weebly.com/"));
        Bot.jda.build();
        
        System.out.println("Built bot");
        
        instance = new Bot();
    }
    
}
