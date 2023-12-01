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
        final var oldBlocks = new HashMap<Vector, BlockData>();

        final var item = playerInteractEvent.getItem();
        if (!flowerManager.getBrushes().containsKey(item)) return;

        final var radius = flowerManager.getBrushes().get(item).radius();
        handleRadiusPlacement(playerInteractEvent, oldBlocks, radius, currentLocation);
        handleFlowerHistory(playerInteractEvent, oldBlocks);

        playerInteractEvent.getPlayer().playSound(currentLocation, Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1, 0);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onCustomFlowerPlaceEvent(BlockPhysicsEvent blockPhysicsEvent) {
        var currentBlock = blockPhysicsEvent.getBlock();
        if (flowerBlocks.contains(currentBlock)) blockPhysicsEvent.setCancelled(true);
    }

    private void handleFlowerPlacement(PlayerInteractEvent playerInteractEvent, HashMap<Vector, BlockData> oldBlocks, Location currentLocation) {

        final var item = playerInteractEvent.getItem();
        final var flowerOptions = flowerManager.getBrushes().get(item).flowerGroupData();
        final var flowerGroup = flowerOptions.get(new Random().nextInt(flowerOptions.size()));
        final var flowers = flowerGroup.flowerData();
        final var values = flowerManager.getFlowerRandomizer().get(flowerGroup);
        final var offset = playerLookUp(playerInteractEvent.getPlayer()) ? -1 : 1;

        for (int i = 0; i < flowers.size(); i++) {
            if (values.get(i) && new Random().nextBoolean()) continue;

            final var currentBlock = currentLocation.getBlock();
            oldBlocks.put(currentBlock.getLocation().toVector(), currentBlock.getBlockData());

            flowerBlocks.add(currentBlock);
            flowers.get(i).getSingleFlower().setFlower(currentBlock);
            currentLocation.add(0, offset, 0);
        }
    }

    private void handleRadiusPlacement(PlayerInteractEvent playerInteractEvent, HashMap<Vector, BlockData> oldBlocks, int radius, Location location) {

        for (int x = -radius; x < radius; x++) for (int z = -radius; z < radius; z++) {
            final var currentLocation = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            handleFlowerPlacement(playerInteractEvent, oldBlocks, currentLocation.add(x, 0, z));
        }
    }

    private void handleFlowerHistory(PlayerInteractEvent playerInteractEvent, HashMap<Vector, BlockData> oldBlocks) {

        final var world = playerInteractEvent.getPlayer().getTargetBlock(Set.of(Material.AIR), 200).getWorld();
        final var history = new Operation(oldBlocks, world);
        final var uuid = playerInteractEvent.getPlayer().getUniqueId();

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