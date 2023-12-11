package com.uroria.betterflowers.data;

import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;

import java.util.HashMap;

public record Operation(HashMap<Vector, BlockData> originalBlocks, World world) {

}