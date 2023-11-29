package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.AgebleFlower;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.CaveVines;

@Getter
public final class CaveVinePlant extends AgebleFlower {
    private final boolean hasBarries;

    public CaveVinePlant(Material display, Material blockMaterial, String displayName, int age, boolean hasBerries) {
        super(display, blockMaterial, displayName, age);
        this.hasBarries = hasBerries;
    }

    public CaveVinePlant(Material blockMaterial, String displayName, int age, boolean hasBerries) {
        super(blockMaterial, displayName, age);
        this.hasBarries = hasBerries;
    }

    @Override
    public void setFlower(Block block) {
        block.setType(this.getBlockMaterial());

        if (block.getBlockData() instanceof CaveVines caveVines) {
            caveVines.setBerries(hasBarries);
            caveVines.setAge(getAge());
            block.setBlockData(caveVines);
        }
    }
}