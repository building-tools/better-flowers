package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.SingleFlower;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;

public final class FlowerGroup extends SingleFlower {

    private final @Getter List<SingleFlower> flowers;

    public FlowerGroup(Material material, Material blockMaterial, String displayName, List<SingleFlower> flowers) {
        super(material, blockMaterial, displayName);
        this.flowers = flowers;
    }

    public FlowerGroup(Material material, String displayName, List<SingleFlower> flowers) {
        super(material, displayName);
        this.flowers = flowers;
    }

    @Override
    public void setFlower(Block block) {}
}