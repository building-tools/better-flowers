package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.AgebleFlower;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bamboo;

@Getter
public final class BambooFlower extends AgebleFlower {

    private final Bamboo.Leaves leaves;

    public BambooFlower(Material display, Material blockMaterial, String displayName, int age, Bamboo.Leaves leaves) {
        super(display, blockMaterial, displayName, age);
        this.leaves = leaves;
    }

    public BambooFlower(Material blockMaterial, String displayName, int age, Bamboo.Leaves leaves) {
        super(blockMaterial, displayName, age);
        this.leaves = leaves;
    }

    @Override
    public void setFlower(Block block) {
        block.setType(this.getBlockMaterial());

        if (block.getBlockData() instanceof Bamboo bamboo) {
            bamboo.setLeaves(this.leaves);
            bamboo.setAge(this.getAge());
            block.setBlockData(bamboo);
        }
    }
}