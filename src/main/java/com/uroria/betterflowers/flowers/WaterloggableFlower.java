package com.uroria.betterflowers.flowers;

import lombok.Getter;

import org.bukkit.Material;

@Getter
public abstract class WaterloggableFlower extends SingleFlower {

    private final boolean isWaterlogged;

    public WaterloggableFlower(Material display, Material blockMaterial, String displayName, boolean isWaterlogged) {
        super(display, blockMaterial, displayName);
        this.isWaterlogged = isWaterlogged;
    }
    public WaterloggableFlower(Material blockMaterial, String displayName, boolean isWaterlogged) {
        super(blockMaterial, displayName);
        this.isWaterlogged = isWaterlogged;
    }
}