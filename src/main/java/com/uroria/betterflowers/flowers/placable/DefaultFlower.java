package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.SingleFlower;
import org.bukkit.Material;
import org.bukkit.block.Block;

public final class DefaultFlower extends SingleFlower {
    public DefaultFlower(Material display, Material blockMaterial, String displayName) {
        super(display, blockMaterial, displayName);
    }

    public DefaultFlower(Material blockMaterial, String displayName) {
        super(blockMaterial, displayName);
    }

    @Override
    public void setFlower(Block block) {
        block.setType(this.getBlockMaterial());
    }
}