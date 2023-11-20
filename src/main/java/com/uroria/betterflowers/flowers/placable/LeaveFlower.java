package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.SingleFlower;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Leaves;

public final class LeaveFlower extends SingleFlower {

    private final @Getter boolean isPersistent;

    public LeaveFlower(Material display, Material blockMaterial, String displayName, boolean isPersistent) {
        super(display, blockMaterial, displayName);
        this.isPersistent = isPersistent;
    }

    public LeaveFlower(Material blockMaterial, String displayName, boolean isPersistent) {
        super(blockMaterial, displayName);
        this.isPersistent = isPersistent;
    }

    @Override
    public void setFlower(Block block) {
        block.setType(this.getBlockMaterial());

        if (block instanceof Leaves leaves) {

            leaves.setPersistent(isPersistent);
            block.setBlockData(leaves);
        }
    }
}
