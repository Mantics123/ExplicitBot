package explicit.features;

import java.io.File;

import net.dv8tion.jda.api.entities.Guild;

public class GuildManager {
	
	public void onGuildJoin(Guild g) {
		System.out.println("Joined Guild " + g.getName());
        final File directory = new File(g.getId());
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Created Directory: " + directory.getAbsolutePath());
        }
	}
	
	public void onGuildLeave(Guild g) {
    	System.out.println("Removing traces of " + g.getName());
    	File directory = new File(g.getId());
    	if (directory.exists() && directory.isDirectory()) {
    		File[] files = directory.listFiles();
    		for (File f : files) {
    			f.delete();
    		}
    		directory.delete();
    	}
	}
}
