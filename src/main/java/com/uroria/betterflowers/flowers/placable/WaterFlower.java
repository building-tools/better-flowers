package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.WaterloggableFlower;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;

public final class WaterFlower extends WaterloggableFlower {
    public WaterFlower(Material display, Material blockMaterial, String displayName, boolean isWaterlogged) {
        super(display, blockMaterial, displayName, isWaterlogged);
    }

    public WaterFlower(Material blockMaterial, String displayName, boolean isWaterlogged) {
        super(blockMaterial, displayName, isWaterlogged);
    }

    @Override
    public void setFlower(Block block) {
        block.setType(this.getBlockMaterial());

        if (block.getBlockData() instanceof Waterlogged waterlogged) {
            waterlogged.setWaterlogged(isWaterlogged());
            block.setBlockData(waterlogged);
        }
    }
}