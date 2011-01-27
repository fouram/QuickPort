package com.bukkit.fouram.QuickPort;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;
import org.bukkit.plugin.Plugin;

/**
 * QuickPort for Bukkit
 *
 * @author 4am
 */
public class QuickPort extends JavaPlugin {
    private final QuickPortPlayerListener playerListener = new QuickPortPlayerListener(this);
    private final QuickPortBlockListener blockListener = new QuickPortBlockListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public static final Logger log = Logger.getLogger("Minecraft");
    public static PermissionHandler Permissions = null;
    public PluginDescriptionFile pdfFile = this.getDescription();
    
    public static class QuickPortPlayerModeManager {
    	private enum Mode { SELF, TUNNEL, SELECT, PLAYER };
        private HashMap<String, Mode> playerModeTable = new HashMap();
        // TODO: Implement list of player modes to kep track of who is doing what
    }
    
    
    public QuickPort(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) {
        super(pluginLoader, instance, desc, folder, plugin, cLoader);
        // TODO: Place any custom initialisation code here

        // NOTE: Event registration should be done in onEnable not here as all events are unregistered when a plugin is disabled
    }

    

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        
        pm.registerEvent(Event.Type.PLAYER_ANIMATION, playerListener, Event.Priority.Lowest, this);
        
        // TODO: Configuration file code
        
        // Load Permissions
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

        if(this.Permissions == null) {
        	if(test != null) {
        		Permissions = ((Permissions)test).getHandler();
        		log.info("["+pdfFile.getName()+"] Permission system detected and applied.");
        	} else {
        		log.severe("["+pdfFile.getName()+"] DISABLED: Permission system not detected!");
        		this.getServer().getPluginManager().disablePlugin(this);
        	}
        }

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        
       log.info( "[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is enabled!" );
       log.warning("[" + pdfFile.getName() + "] " +"THIS IS ALPHA-VERSION SOFTWARE. There is a known conflict");
       log.warning("[" + pdfFile.getName() + "] " +"with WorldEdit, which has implemented compass right-clicks.");
       log.warning("[" + pdfFile.getName() + "] " +"YOU HAVE BEEN WARNED! No complaining in the forums ;-)");
    }
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
    	log.info( "[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is disabled!" );
    }
    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
