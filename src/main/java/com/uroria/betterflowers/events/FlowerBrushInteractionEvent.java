package com.uroria.betterflowers.events;

import com.uroria.betterflowers.data.BrushData;
import com.uroria.betterflowers.events.types.FlowerInteractionTypes;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public final class FlowerBrushInteractionEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final BrushData brushData;
    @Getter
    private final Player player;
    @Getter
    private final Location interactionPoint;
    @Getter
    private final FlowerInteractionTypes flowerTool;
    private boolean isCancelled;

    public FlowerBrushInteractionEvent(BrushData brushData, Player player, Location interactionPoint, FlowerInteractionTypes flowerTool) {
        this.brushData = brushData;
        this.player = player;
        this.interactionPoint = interactionPoint;
        this.flowerTool = flowerTool;

        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}