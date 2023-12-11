package com.uroria.betterflowers.commands;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.menus.FlowerBrushMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record FlowerBrush(BetterFlowers betterFlowers) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("betterflowers.use")) {
            betterFlowers.getLanguageManager().sendPlayerMessage(player, "permission.use.error");
            return true;
        }

        new FlowerBrushMenu(betterFlowers, player).open();
        return false;
    }
}