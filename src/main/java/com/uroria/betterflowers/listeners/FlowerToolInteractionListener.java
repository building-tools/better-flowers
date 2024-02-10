package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.events.FlowerBrushInteractionEvent;
import com.uroria.betterflowers.events.FlowerPlacerInteractionEvent;
import com.uroria.betterflowers.events.types.FlowerInteractionTypes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public record FlowerToolInteractionListener(BetterFlowers betterFlowers) implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent playerInteractEvent) {

        if (!playerInteractEvent.hasItem()) return;

        final var item = playerInteractEvent.getItem();
        if (item == null || item.getType() == Material.AIR) return;
        if (playerInteractEvent.getHand() == EquipmentSlot.OFF_HAND) return;
        callEvent(playerInteractEvent, item);
    }

    private void callEvent(PlayerInteractEvent playerInteractEvent, ItemStack itemStack) {
        final var player = playerInteractEvent.getPlayer();
        final var click = playerInteractEvent.getAction();

        Optional<FlowerInteractionTypes> type = Optional.empty();
        if (click.isRightClick()) type = Optional.of(FlowerInteractionTypes.USE);
        if (click == Action.LEFT_CLICK_AIR && player.isSneaking()) type = Optional.of(FlowerInteractionTypes.OPEN);
        if (type.isEmpty()) return;

        if (itemStack.getType() == Material.BLAZE_ROD) {

            if (!betterFlowers.getFlowerManager().getBrushes().containsKey(itemStack)) return;
            final var brush = betterFlowers.getFlowerManager().getBrushes().get(itemStack);
            var interactionPoint = playerInteractEvent.getInteractionPoint();

            if (type.get() == FlowerInteractionTypes.USE) {
                interactionPoint = playerInteractEvent.getPlayer().getTargetBlock(null, 200).getLocation();
                if (interactionPoint.getBlock().getType() == Material.AIR) return;
                if (interactionPoint.getBlock().getType() != Material.SHORT_GRASS) interactionPoint.add(0, 1, 0);
            }

            final var event = new FlowerBrushInteractionEvent(brush, player, interactionPoint, type.get());
            Bukkit.getPluginManager().callEvent(event);
            playerInteractEvent.setCancelled(true);
            return;
        }

        if (itemStack.getType() == Material.BLAZE_POWDER) {

            if (!betterFlowers.getFlowerManager().getFlowers().containsKey(itemStack)) return;
            final var brush = betterFlowers.getFlowerManager().getFlowers().get(itemStack);
            var interactionPoint = playerInteractEvent.getPlayer().getLocation();

            if (type.get() == FlowerInteractionTypes.USE) {
                if (click != Action.RIGHT_CLICK_BLOCK) return;
                if (!playerInteractEvent.hasBlock()) return;
                interactionPoint = playerInteractEvent.getInteractionPoint();
                if (interactionPoint.getBlock().getType() != Material.AIR) return;
                System.out.println(interactionPoint.getBlock().getType());
            }

            final var event = new FlowerPlacerInteractionEvent(brush, player, interactionPoint, type.get());
            playerInteractEvent.setCancelled(true);
            Bukkit.getPluginManager().callEvent(event);
        }
    }
}