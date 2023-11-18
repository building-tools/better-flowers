package com.uroria.betterflowers.data;

import com.uroria.betterflowers.flowers.SingleFlower;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;
import java.util.Random;

public final class FlowerData {

    private final List<SingleFlower> flowers;
    @Getter
    private final String name;
    @Getter
    private final Material display;

    public FlowerData(SingleFlower singleFlower) {
        this.flowers = List.of(singleFlower);
        name = singleFlower.getDisplayName();
        this.display = singleFlower.getDisplay();
    }

    public FlowerData(List<SingleFlower> singleFlowers, String string, Material display) {
        this.flowers = singleFlowers;
        name = string;
        this.display = display;
    }

    public SingleFlower getSingleFlower() {
        return flowers.get(new Random().nextInt(flowers.size()));
    }
}