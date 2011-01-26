package com.bukkit.fouram.QuickPort;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Handle events for all Player related events
 * @author 4am
 */
public class QuickPortPlayerListener extends PlayerListener {
    private final QuickPort plugin;

    public QuickPortPlayerListener(QuickPort instance) {
        plugin = instance;
    }

    //Insert Player related code here
	@Override
	public void onPlayerAnimation(PlayerAnimationEvent aniEvent) {
		
		
		if ( aniEvent.getAnimationType() == PlayerAnimationType.ARM_SWING ) {
			Player thePlayer = aniEvent.getPlayer();
			TargetBlock thePlayerBlockTarget = new TargetBlock(thePlayer);
			Block theTargetBlock = thePlayerBlockTarget.getTargetBlock();
			
			if ( theTargetBlock != null && thePlayer.getItemInHand().getType() == Material.COMPASS ) {
				
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
						aniEvent.getPlayer().teleportTo(theLoc);
						break;
					}
				}
				
			}
		}
	}
	
}
