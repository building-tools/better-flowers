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
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
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
    private void onCustomFlowerPlaceEvent(PlayerInteractEvent playerInteractEvent) {

        if (playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (playerInteractEvent.getHand() != EquipmentSlot.HAND) return;
        if (!playerInteractEvent.hasItem() || playerInteractEvent.getItem() == null) return;

        if (playerInteractEvent.getItem().getType() != Material.BLAZE_POWDER) return;
        if (playerInteractEvent.getClickedBlock() == null) return;
        if (playerInteractEvent.getInteractionPoint() == null) return;

        final var currentLocation = playerInteractEvent.getInteractionPoint();
        final var oldBlocks = new HashMap<Vector, BlockData>();

        handleFlowerPlacement(playerInteractEvent, oldBlocks, currentLocation);
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
        if (!flowerManager.getFlowers().containsKey(item)) return;

        final var flowerGroupData = flowerManager.getFlowers().get(item);
        final var flowers = flowerGroupData.flowerData();
        final var values = flowerManager.getFlowerRandomizer().get(flowerGroupData);
        final var offset = playerLookUp(playerInteractEvent.getPlayer()) ? -1 : 1;
        if (playerLookUp(playerInteractEvent.getPlayer())) currentLocation.add(0, -1, 0);

        for (int i = 0; i < flowers.size(); i++) {
            if (values.get(i) && new Random().nextBoolean()) continue;

            final var currentBlock = currentLocation.getBlock();
            oldBlocks.put(currentBlock.getLocation().toVector(), currentBlock.getBlockData());

            flowerBlocks.add(currentBlock);
            flowers.get(i).getSingleFlower().setFlower(currentBlock);
            currentLocation.add(0, offset, 0);
        }
    }

    private void handleFlowerHistory(PlayerInteractEvent playerInteractEvent, HashMap<Vector, BlockData> oldBlocks) {

        final var location = playerInteractEvent.getClickedBlock();
        if (location == null) return;

        final var history = new Operation(oldBlocks, playerInteractEvent.getClickedBlock().getWorld());
        final var uuid = playerInteractEvent.getPlayer().getUniqueId();

        if (flowerManager.getOperationHistory().containsKey(uuid)) {
            var copy = new ArrayList<>(List.copyOf(flowerManager.getOperationHistory().get(uuid)));
            copy.add(history);

            flowerManager.getOperationHistory().put(uuid, copy);
        } else flowerManager.getOperationHistory().put(uuid, List.of(history));
    }

    private boolean playerLookUp(Player player) {
        return player.getPitch() < 0.0F;
    }
}