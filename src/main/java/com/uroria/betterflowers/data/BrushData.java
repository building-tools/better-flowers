package com.uroria.betterflowers.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public record BrushData(List<FlowerGroupData> flowerGroupData, int radius, float airRandomizer,
                        List<Material> globalMasks) {}