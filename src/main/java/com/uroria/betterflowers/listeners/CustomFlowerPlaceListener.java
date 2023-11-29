package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.data.Operation;
import com.uroria.betterflowers.managers.FlowerManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class CustomFlowerPlaceListener implements Listener {
    private final FlowerManager flowerManager;
    private final List<Block> flowerBlocks;

    public CustomFlowerPlaceListener(BetterFlowers betterFlowers) {
        this.flowerManager = betterFlowers.getFlowerManager();
        this.flowerBlocks = new ArrayList<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(BlockPlaceEvent blockPlaceEvent) {

        if (blockPlaceEvent.getItemInHand().getType() != Material.FLOWER_POT) return;

        final var currentLocation = blockPlaceEvent.getBlock().getLocation();
        final var oldBlocks = new HashMap<Vector, BlockData>();

        handleFlowerPlacement(blockPlaceEvent, oldBlocks, blockPlaceEvent.getBlock().getLocation());
        handleFlowerHistory(blockPlaceEvent, oldBlocks);

        blockPlaceEvent.getPlayer().playSound(currentLocation, Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1, 0);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(BlockPhysicsEvent blockPhysicsEvent) {
        var currentBlock = blockPhysicsEvent.getBlock();
        if (flowerBlocks.contains(currentBlock)) blockPhysicsEvent.setCancelled(true);
    }

    private void handleFlowerPlacement(BlockPlaceEvent blockPlaceEvent, HashMap<Vector, BlockData> oldBlocks, Location currentLocation) {

        final var item = blockPlaceEvent.getItemInHand();
        if (!flowerManager.getFlowers().containsKey(item)) return;

        final var flowers = flowerManager.getFlowers().get(item);
        final var values = flowerManager.getFlowerRandomizer().get(item);
        final var offset = playerLookUp(blockPlaceEvent.getPlayer()) ? -1 : 1;

        for (int i = 0; i < flowers.size(); i++) {
            if (values.get(i) && new Random().nextBoolean()) continue;

            final var currentBlock = currentLocation.getBlock();
            if (currentBlock.getBlockData().getMaterial() == Material.FLOWER_POT) currentBlock.setType(Material.AIR);
            oldBlocks.put(currentBlock.getLocation().toVector(), currentBlock.getBlockData());

            flowerBlocks.add(currentBlock);
            flowers.get(i).getSingleFlower().setFlower(currentBlock);
            currentLocation.add(0, offset, 0);
        }
    }

    private void handleFlowerHistory(BlockPlaceEvent blockPlaceEvent, HashMap<Vector, BlockData> oldBlocks) {
        var history = new Operation(oldBlocks, blockPlaceEvent.getBlockPlaced().getWorld());
        var uuid = blockPlaceEvent.getPlayer().getUniqueId();

        if (flowerManager.getOperationHistory().containsKey(uuid)) {
            var copy = new ArrayList<>(List.copyOf(flowerManager.getOperationHistory().get(uuid)));
            copy.add(history);

            flowerManager.getOperationHistory().put(uuid, copy);
        } else flowerManager.getOperationHistory().put(uuid, List.of(history));
    }

    private boolean playerLookUp(Player player) {
        return player.getPitch() < 0;
    }
}