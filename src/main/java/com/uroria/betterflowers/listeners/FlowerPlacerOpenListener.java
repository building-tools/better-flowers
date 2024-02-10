package com.uroria.betterflowers.listeners;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.events.FlowerPlacerInteractionEvent;
import com.uroria.betterflowers.events.types.FlowerInteractionTypes;
import com.uroria.betterflowers.menus.FlowerCreationMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public record FlowerPlacerOpenListener(BetterFlowers betterFlowers) implements Listener {

    @EventHandler
    public void onFlowerPlacerOpenListener(FlowerPlacerInteractionEvent flowerPlacerInteractionEvent) {
        if (flowerPlacerInteractionEvent.getFlowerTool() != FlowerInteractionTypes.OPEN) return;
        new FlowerCreationMenu(flowerPlacerInteractionEvent.getPlayer(), betterFlowers, flowerPlacerInteractionEvent.getFlowerGroupData(), true).open();
    }
}
