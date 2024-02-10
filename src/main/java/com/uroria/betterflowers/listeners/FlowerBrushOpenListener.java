package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.events.FlowerBrushInteractionEvent;
import com.uroria.betterflowers.events.types.FlowerInteractionTypes;
import com.uroria.betterflowers.menus.FlowerBrushMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public record FlowerBrushOpenListener(BetterFlowers betterFlowers) implements Listener {

    @EventHandler
    public void onFlowerBrushInteractionEvent(FlowerBrushInteractionEvent flowerBrushInteractionEvent) {
        if (flowerBrushInteractionEvent.getFlowerTool() != FlowerInteractionTypes.OPEN) return;
        new FlowerBrushMenu(betterFlowers, flowerBrushInteractionEvent.getPlayer(), flowerBrushInteractionEvent.getBrushData(), true).open();
    }
}