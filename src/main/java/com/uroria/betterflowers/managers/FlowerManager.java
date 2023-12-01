package com.uroria.betterflowers.managers;

import com.uroria.betterflowers.data.BrushData;
import com.uroria.betterflowers.data.FlowerGroupData;
import com.uroria.betterflowers.data.Operation;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public final class FlowerManager {

    private final Map<ItemStack, FlowerGroupData> flowers;
    private final Map<ItemStack, BrushData> brushes;
    private final Map<FlowerGroupData, List<Boolean>> flowerRandomizer;
    private final Map<UUID, List<Operation>> operationHistory;

    public FlowerManager() {
        this.flowers = new HashMap<>();
        this.brushes = new HashMap<>();
        this.flowerRandomizer = new HashMap<>();
        this.operationHistory = new HashMap<>();
    }
}