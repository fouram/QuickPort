package com.bukkit.fouram.QuickPort;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * QuickPort block listener
 * @author 4am
 */
public class QuickPortBlockListener extends BlockListener {
    private final QuickPort plugin;

    public QuickPortBlockListener(final QuickPort plugin) {
        this.plugin = plugin;
    }

    //put all Block related code here
}
