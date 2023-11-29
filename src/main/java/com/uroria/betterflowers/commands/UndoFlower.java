package com.uroria.betterflowers.commands;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.data.Operation;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public record UndoFlower(BetterFlowers betterFlowers) implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("betterflowers.undo")) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#EB3349:#F45C43>You dont have enough permission to use BetterFlowers!</gradient>"));
            return true;
        }

        var undoPlayer = player;
        int undoTimes = 1;

        if (args.length > 2) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#EB3349:#F45C43>To many inputs!</gradient>"));
            return true;
        }

        for (var argument : Arrays.stream(args).toList()) {
            if (isPositiveNumber(argument)) undoTimes = Integer.parseInt(argument);
            if (getPlayer(argument).isPresent()) undoPlayer = getPlayer(argument).get();
        }

        var operationHistories =  betterFlowers.getFlowerManager().getOperationHistory();

        if (!operationHistories.containsKey(undoPlayer.getUniqueId())) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#EB3349:#F45C43>The given Player has nothing to undo!</gradient>"));
            return true;
        }

        var history = new ArrayList<>(List.copyOf(operationHistories.get(undoPlayer.getUniqueId())));
        var undo = new ArrayList<Operation>();

        for (int index = 0; index < undoTimes; index++) {
            if (index >= history.size()) break;

            var currentOperation = history.get((history.size() - index) - 1);

            if (currentOperation.world() == null) {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#EB3349:#F45C43>The world to undo isn't loaded!</gradient>"));
                break;
            }

            currentOperation.originalBlocks().forEach((vector, blockData) -> {
                currentOperation.world().setBlockData(vector.toLocation(currentOperation.world()), blockData);
            });

            undo.add(currentOperation);
        }

        history.removeAll(undo);
        operationHistories.put(undoPlayer.getUniqueId(), history);
        player.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#a8ff78:#78ffd6>Undo successful"));
        return false;
    }

    private boolean isPositiveNumber(String string) {

        try  {

            final var value = Integer.parseInt(string);
            if (value <= 0) return false;
            return true;
        }
        catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private Optional<Player> getPlayer(String string) {

        for (var player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(string)) return Optional.of(player);
        }

        return Optional.empty();
    }
}