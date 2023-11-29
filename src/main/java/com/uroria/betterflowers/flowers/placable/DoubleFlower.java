package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.SingleFlower;
import lombok.Getter;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Bisected;

@Getter
public final class DoubleFlower extends SingleFlower {

    private final Bisected.Half half;

    public DoubleFlower(Material display, Material blockMaterial, String displayName, Bisected.Half half) {
        super(display, blockMaterial, displayName);
        this.half = half;
    }
    public DoubleFlower(Material blockMaterial, String displayName, Bisected.Half half) {
        super(blockMaterial, displayName);
        this.half = half;
    }

    @Override
    public void setFlower(Block block) {
        block.setType(this.getBlockMaterial());

        if (block.getBlockData() instanceof Bisected bisected) {
            bisected.setHalf(this.half);
            block.setBlockData(bisected);
        }
    }
}