package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public record CustomFlowerInteractListener(BetterFlowers betterFlowers) implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onCustomFlowerInteractEvent(PlayerInteractEvent playerInteractEvent) {

        if (playerInteractEvent.getItem() == null || playerInteractEvent.getItem().getType() != Material.FLOWER_POT) return;
        if (betterFlowers.getFlowerManager().getFlowers().containsKey(playerInteractEvent.getItem())) playerInteractEvent.setCancelled(true);
    }
}