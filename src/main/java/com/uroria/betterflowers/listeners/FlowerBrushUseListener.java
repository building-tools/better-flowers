package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.data.FlowerGroupData;
import com.uroria.betterflowers.data.Operation;
import com.uroria.betterflowers.events.FlowerBrushInteractionEvent;
import com.uroria.betterflowers.events.types.FlowerInteractionTypes;
import com.uroria.betterflowers.managers.FlowerManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.util.Vector;

import java.util.*;

public final class FlowerBrushUseListener implements Listener {

    private final FlowerManager flowerManager;
    private final List<Block> flowerBlocks;

    public FlowerBrushUseListener(BetterFlowers betterFlowers) {
        this.flowerManager = betterFlowers.getFlowerManager();
        this.flowerBlocks = new ArrayList<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onFlowerBrushInteractionEvent(FlowerBrushInteractionEvent flowerBrushInteractionEvent) {
        if (flowerBrushInteractionEvent.getFlowerTool() != FlowerInteractionTypes.USE) return;

        final var oldBlocks = new HashMap<Vector, BlockData>();
        final var radius = flowerBrushInteractionEvent.getBrushData().radius();
        final var airRandomizer = flowerBrushInteractionEvent.getBrushData().airRandomizer();
        final var interactionPoint = flowerBrushInteractionEvent.getInteractionPoint();

        handleRadiusPlacement(flowerBrushInteractionEvent, oldBlocks, radius, interactionPoint, airRandomizer);
        handleFlowerHistory(flowerBrushInteractionEvent, oldBlocks);

        flowerBrushInteractionEvent.getPlayer().playSound(interactionPoint, Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1, 0);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(BlockPhysicsEvent blockPhysicsEvent) {
        var currentBlock = blockPhysicsEvent.getBlock();
        if (flowerBlocks.contains(currentBlock)) blockPhysicsEvent.setCancelled(true);
    }

    private void handleRadiusPlacement(FlowerBrushInteractionEvent flowerBrushInteractionEvent, HashMap<Vector, BlockData> oldBlocks, int radius, Location location, float air) {

        for (var innerLocation : getPlayerCircle(location, radius)) {
            if (air >= new Random().nextFloat()) continue;
            if (!adjustHeight(innerLocation)) continue;
            handleFlowerPlacement(flowerBrushInteractionEvent, oldBlocks, innerLocation);
        }
    }

    private void handleFlowerPlacement(FlowerBrushInteractionEvent flowerBrushInteractionEvent, HashMap<Vector, BlockData> oldBlocks, Location currentLocation) {

        final var flowerOptions = flowerBrushInteractionEvent.getBrushData().flowerGroupData();
        final var flowerGroup = flowerOptions.get(new Random().nextInt(flowerOptions.size()));
        final var flowers = flowerGroup.flowerData();
        final var values = flowerGroup.randomizer();

        if (!onCorrectGround(currentLocation, flowerGroup, flowerBrushInteractionEvent.getBrushData().globalMasks())) return;

        for (int i = 0; i < flowers.size(); i++) {
            if (values.get(i) && new Random().nextBoolean()) continue;

            final var currentBlock = currentLocation.getBlock();
            oldBlocks.put(currentBlock.getLocation().toVector(), currentBlock.getBlockData());

            flowerBlocks.add(currentBlock);
            flowers.get(i).getSingleFlower().setFlower(currentBlock);
            currentLocation.add(0, 1, 0);
        }
    }

    private Collection<Location> getPlayerCircle(Location location, int radius) {
        final var locations = new ArrayList<Location>();

        for (int x = -radius; x < radius; x++)
            for (int z = -radius; z < radius; z++) {
                final var newLocation = new Location(location.getWorld(), location.getX() + x, location.getY(), location.getZ() + z);
                if (location.distance(newLocation) < radius) locations.add(newLocation);
            }

        return locations;
    }

    private void handleFlowerHistory(FlowerBrushInteractionEvent flowerBrushInteractionEvent, HashMap<Vector, BlockData> oldBlocks) {

        final var player = flowerBrushInteractionEvent.getPlayer();
        final var uuid = player.getUniqueId();
        final var history = new Operation(oldBlocks, player.getWorld());

        if (flowerManager.getOperationHistory().containsKey(uuid)) {
            var copy = new ArrayList<>(List.copyOf(flowerManager.getOperationHistory().get(uuid)));
            copy.add(history);

            flowerManager.getOperationHistory().put(uuid, copy);
        } else flowerManager.getOperationHistory().put(uuid, List.of(history));
    }

    private boolean adjustHeight(Location location) {
        final var block = location.getBlock();

        for (var index = 0; index < 10; index++) {

            final var y = block.isSolid() ? location.getBlockY() + index : location.getBlockY() - index;
            final var currentBlock = location.getWorld().getBlockAt(location.getBlockX(), y, location.getBlockZ());

            if (currentBlock.isSolid()) {
                location.setY(currentBlock.getY() + 1);
                return true;
            }

            final var nextBlock = block.isSolid() ? currentBlock.getRelative(BlockFace.UP) : currentBlock.getRelative(BlockFace.DOWN);
            if (!nextBlock.isSolid()) continue;
            location.setY(nextBlock.getY() + 1);
            return true;
        }

        return false;
    }

    private boolean onCorrectGround(Location location, FlowerGroupData data, List<Material> globalMasks) {
        final var floorType = location.getBlock().getRelative(BlockFace.DOWN).getType();

        if (globalMasks.isEmpty()) return true;
        return (globalMasks.contains(floorType));
    }
}