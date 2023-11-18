package com.uroria.betterflowers.commands;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.menus.FlowerCreationMenu;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record Flowers(BetterFlowers betterFlowers) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player player)) return true;
        if (!player.hasPermission("betterflowers.use")) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#EB3349:#F45C43>You dont have enough permission to use BetterFlowers!</gradient>"));
            return true;
        }

        new FlowerCreationMenu(player, betterFlowers).open();
        return false;
    }
}