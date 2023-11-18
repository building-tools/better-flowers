package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.data.Operation;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class CustomFlowerPlaceListener implements Listener {
    public final BetterFlowers betterFlowers;
    public final List<Block> flowerBlocks;

    public CustomFlowerPlaceListener(BetterFlowers betterFlowers) {
        this.betterFlowers = betterFlowers;
        this.flowerBlocks = new ArrayList<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(BlockPlaceEvent blockPlaceEvent) {

        if (blockPlaceEvent.getItemInHand().getType() != Material.FLOWER_POT) return;

        final var flowerManager = betterFlowers.getFlowerManager();
        final var item = blockPlaceEvent.getItemInHand();

        if (!flowerManager.getFlowers().containsKey(item)) return;

        final var flowers = flowerManager.getFlowers().get(item);
        final var values = flowerManager.getFlowerRandomizer().get(item);
        final var currentLocation = blockPlaceEvent.getBlock().getLocation();
        final var oldBlocks = new HashMap<Vector, BlockData>();

        for (int i = 0; i < flowers.size(); i++) {
            if (values.get(i) && new Random().nextBoolean()) continue;

            final Block currentBlock = currentLocation.getBlock();
            if (currentBlock.getBlockData().getMaterial() == Material.FLOWER_POT) currentBlock.setType(Material.AIR);
            oldBlocks.put(currentBlock.getLocation().toVector(), currentBlock.getBlockData());

            flowerBlocks.add(currentBlock);
            flowers.get(i).getSingleFlower().setFlower(currentBlock);
            currentLocation.add(0, 1, 0);
        }

        var history = new Operation(oldBlocks, blockPlaceEvent.getBlockPlaced().getWorld());
        var uuid = blockPlaceEvent.getPlayer().getUniqueId();

        if (flowerManager.getOperationHistory().containsKey(uuid)) {
            var copy = new ArrayList<>(List.copyOf(flowerManager.getOperationHistory().get(uuid)));
            copy.add(history);

            flowerManager.getOperationHistory().put(uuid, copy);
        }
        else flowerManager.getOperationHistory().put(uuid, List.of(history));

        blockPlaceEvent.getPlayer().playSound(currentLocation, Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1, 0);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(BlockPhysicsEvent blockPhysicsEvent) {
        var currentBlock = blockPhysicsEvent.getBlock();
        if (flowerBlocks.contains(currentBlock)) blockPhysicsEvent.setCancelled(true);
    }
}