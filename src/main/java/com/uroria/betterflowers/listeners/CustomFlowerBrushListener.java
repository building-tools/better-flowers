package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.data.FlowerGroupData;
import com.uroria.betterflowers.data.Operation;
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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;

import java.util.*;

public final class CustomFlowerBrushListener implements Listener {
    private final FlowerManager flowerManager;
    private final List<Block> flowerBlocks;

    public CustomFlowerBrushListener(BetterFlowers betterFlowers) {
        this.flowerManager = betterFlowers.getFlowerManager();
        this.flowerBlocks = new ArrayList<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(PlayerInteractEvent playerInteractEvent) {

        if (playerInteractEvent.getAction().isLeftClick()) return;
        if (playerInteractEvent.getHand() != EquipmentSlot.HAND) return;
        if (!playerInteractEvent.hasItem() || playerInteractEvent.getItem() == null) return;
        if (playerInteractEvent.getItem().getType() != Material.BLAZE_ROD) return;

        final var currentLocation = playerInteractEvent.getPlayer().getTargetBlock(null, 200).getLocation();
        if (currentLocation.getBlock().getType() == Material.AIR) return;
        if (currentLocation.getBlock().getType() != Material.GRASS) currentLocation.add(0, 1,0);

        final var oldBlocks = new HashMap<Vector, BlockData>();
        final var item = playerInteractEvent.getItem();

        if (!flowerManager.getBrushes().containsKey(item)) return;
        final var radius = flowerManager.getBrushes().get(item).radius();
        final var airRandomizer = flowerManager.getBrushes().get(item).airRandomizer();
        handleRadiusPlacement(playerInteractEvent, oldBlocks, radius, currentLocation, airRandomizer);
        handleFlowerHistory(playerInteractEvent, oldBlocks);

        playerInteractEvent.getPlayer().playSound(currentLocation, Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1, 0);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(BlockPhysicsEvent blockPhysicsEvent) {
        var currentBlock = blockPhysicsEvent.getBlock();
        if (flowerBlocks.contains(currentBlock)) blockPhysicsEvent.setCancelled(true);
    }

    private void handleRadiusPlacement(PlayerInteractEvent playerInteractEvent, HashMap<Vector, BlockData> oldBlocks, int radius, Location location, float air) {

        for (var innerLocation : getPlayerCircle(location, radius)) {
            if (air >= new Random().nextFloat()) continue;
            if (adjustHeight(innerLocation)) continue;
            handleFlowerPlacement(playerInteractEvent, oldBlocks, innerLocation);
        }
    }

    private void handleFlowerPlacement(PlayerInteractEvent playerInteractEvent, HashMap<Vector, BlockData> oldBlocks, Location currentLocation) {

        final var item = playerInteractEvent.getItem();
        final var flowerBrush = flowerManager.getBrushes().get(item);
        final var flowerOptions = flowerBrush.flowerGroupData();
        final var flowerGroup = flowerOptions.get(new Random().nextInt(flowerOptions.size()));
        final var flowers = flowerGroup.flowerData();
        final var values = flowerManager.getFlowerRandomizer().get(flowerGroup);

        if (!onCorrectGround(currentLocation, flowerGroup, flowerBrush.maskData())) return;

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

        for (int x = -radius; x < radius; x++) for (int z = -radius; z < radius; z++) {
            final var newLocation = new Location(location.getWorld(), location.getX() + x, location.getY(),location.getZ() + z);
            if (location.distance(newLocation) < radius) locations.add(newLocation);
        }

        return locations;
    }

    private void handleFlowerHistory(PlayerInteractEvent playerInteractEvent, HashMap<Vector, BlockData> oldBlocks) {

        final var player = playerInteractEvent.getPlayer();
        final var uuid = player.getUniqueId();
        final var history = new Operation(oldBlocks, player.getWorld());

        if (flowerManager.getOperationHistory().containsKey(uuid)) {
            var copy = new ArrayList<>(List.copyOf(flowerManager.getOperationHistory().get(uuid)));
            copy.add(history);

            flowerManager.getOperationHistory().put(uuid, copy);
        } else flowerManager.getOperationHistory().put(uuid, List.of(history));
    }

    private boolean adjustHeight(Location location) {
        final var offset = (location.getBlock().getType() == Material.AIR) ? -1 : 1;

        for (int index = 0; index < 10; index++) {

            final var offsetLocation = new Location(location.getWorld(), location.getX(), location.getY() + (offset * index), location.getZ());
            if (offsetLocation.getBlock().getType() == Material.AIR || offsetLocation.getBlock().getType() == Material.GRASS) continue;
            if (offsetLocation.getBlock().getRelative(BlockFace.UP).getType() != Material.AIR) continue;
            location.setY(offsetLocation.getY() + 1);
            return false;
        }

        return true;
    }

    private boolean onCorrectGround(Location location, FlowerGroupData flowerGroupData, Map<FlowerGroupData, Material> maskData) {
        final var floorType = location.getBlock().getRelative(BlockFace.DOWN).getType();

        if (maskData.isEmpty()) return true;
        if (!maskData.containsKey(flowerGroupData)) return true;
        if (maskData.get(flowerGroupData) == floorType) return true;

        return false;
    }
}