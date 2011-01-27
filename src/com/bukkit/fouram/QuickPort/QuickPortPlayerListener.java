package com.bukkit.fouram.QuickPort;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerItemEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Handle events for all Player related events
 * @author 4am
 */
public class QuickPortPlayerListener extends PlayerListener {
    private final QuickPort plugin;
    public static final Logger log = Logger.getLogger("Minecraft");

    public QuickPortPlayerListener(QuickPort instance) {
        plugin = instance;
    }

    //Insert Player related code here
    @Override
    public void onPlayerItem(PlayerItemEvent evt) {
    	if (evt.getItem().getType() == Material.COMPASS) {
    		
    	}
    }
    
	@Override
	public void onPlayerAnimation(PlayerAnimationEvent aniEvent) {
		Player thePlayer = aniEvent.getPlayer();
		
		if ( aniEvent.getAnimationType() == PlayerAnimationType.ARM_SWING && thePlayer.getItemInHand().getType() == Material.COMPASS ) {
			
			float pitch = thePlayer.getLocation().getPitch();
			float yaw = thePlayer.getLocation().getYaw();
			
			if (!plugin.Permissions.has(thePlayer, "fouram.quickport.normal")) {
				log.info("Attempted to teleport, no permission!");
				return;
			}
			
			TargetBlock thePlayerBlockTarget = new TargetBlock(thePlayer);
			Block theTargetBlock = thePlayerBlockTarget.getTargetBlock();
			
			if ( theTargetBlock != null ) {
				
				for (int i=0; i<100; i++) {
					int landingType = this.plugin.getServer().getWorlds()[0].getBlockAt(	theTargetBlock.getX(), 
																							theTargetBlock.getY() + i,
																							theTargetBlock.getZ()).getTypeId();
					int landingAbove = this.plugin.getServer().getWorlds()[0].getBlockAt(	theTargetBlock.getX(), 
																							theTargetBlock.getY() + i + 1,
																							theTargetBlock.getZ()).getTypeId();
					if (landingType == 0 && landingAbove == 0){
						Location theLoc = theTargetBlock.getLocation();
						
						theLoc.setX(theLoc.getX() + .5);
						theLoc.setZ(theLoc.getZ() + .5);
						theLoc.setY(theLoc.getY() + i);
						theLoc.setPitch(pitch);
						theLoc.setYaw(yaw);
						aniEvent.getPlayer().teleportTo(theLoc);
						break;
					}
				}
				
			}
		}
	}
	
}
