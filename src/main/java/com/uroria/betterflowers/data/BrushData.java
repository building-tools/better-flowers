package com.uroria.betterflowers.data;

import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public record BrushData(List<FlowerGroupData> flowerGroupData, int radius, float airRandomizer,
                        Map<FlowerGroupData, Material> maskData) {

}