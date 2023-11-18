package com.uroria.betterflowers.flowers.placable;

import com.uroria.betterflowers.flowers.SingleFlower;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.PinkPetals;
import org.bukkit.block.data.type.SeaPickle;
import org.bukkit.block.data.type.TurtleEgg;

public final class ClusterFlower extends SingleFlower {

    private final @Getter int amount;

    public ClusterFlower(Material display, Material blockMaterial, String displayName, int amount) {
        super(display, blockMaterial, displayName);
        this.amount = amount;
    }

    public ClusterFlower(Material blockMaterial, String displayName, int amount) {
        super(blockMaterial, displayName);
        this.amount = amount;
    }

    @Override
    public void setFlower(Block block) {
        block.setType(this.getBlockMaterial());

        if (block.getBlockData() instanceof SeaPickle seaPickle) {

            seaPickle.setPickles(this.amount);
            seaPickle.setWaterlogged(false);
            block.setBlockData(seaPickle);
            return;
        }

        if (block.getBlockData() instanceof PinkPetals pinkPetals) {

            pinkPetals.setFlowerAmount(this.amount);
            block.setBlockData(pinkPetals);
            return;
        }

        if (block.getBlockData() instanceof TurtleEgg turtleEgg) {

            turtleEgg.setEggs(this.amount);
            block.setBlockData(turtleEgg);
        }
    }
}