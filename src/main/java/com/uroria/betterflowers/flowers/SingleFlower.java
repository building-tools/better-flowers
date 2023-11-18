package com.uroria.betterflowers.flowers;

import lombok.Getter;

import org.bukkit.Material;
import org.bukkit.block.Block;

public abstract class SingleFlower {

    private final @Getter Material display;
    private final @Getter Material blockMaterial;
    private final @Getter String displayName;

    public SingleFlower(Material display, Material blockMaterial, String displayName) {
        this.display = display;
        this.blockMaterial = blockMaterial;
        this.displayName = displayName;
    }

    public SingleFlower(Material blockMaterial, String displayName) {
        this.display = blockMaterial;
        this.blockMaterial = blockMaterial;
        this.displayName = displayName;
    }

    public abstract void setFlower(Block block);
}