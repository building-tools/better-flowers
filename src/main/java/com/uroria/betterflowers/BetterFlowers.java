package com.uroria.betterflowers;

import com.uroria.betterflowers.commands.Flower;
import com.uroria.betterflowers.commands.FlowerBrush;
import com.uroria.betterflowers.commands.UndoFlower;
import com.uroria.betterflowers.listeners.CustomFlowerBrushListener;
import com.uroria.betterflowers.listeners.CustomFlowerPlaceListener;
import com.uroria.betterflowers.managers.FlowerManager;
import com.uroria.betterflowers.managers.LanguageManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
public final class BetterFlowers extends JavaPlugin {

    private final FlowerManager flowerManager;
    private final LanguageManager languageManager;

    public BetterFlowers() {
        this.flowerManager = new FlowerManager();
        this.languageManager = new LanguageManager();
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerListener();
    }

    private void registerCommands() {
        final var flowerCommand = getCommand("flower");
        if (flowerCommand != null) {
            flowerCommand.setAliases(List.of("f"));
            flowerCommand.setExecutor(new Flower(this));
        }

        final var flowerBrushCommand = getCommand("flowerbrush");
        if (flowerBrushCommand != null) {
            flowerBrushCommand.setAliases(List.of("fb"));
            flowerBrushCommand.setExecutor(new FlowerBrush(this));
        }

        final var undoFlowerCommand = getCommand("undoflower");
        if (undoFlowerCommand != null) {
            undoFlowerCommand.setAliases(List.of("uf"));
            undoFlowerCommand.setExecutor(new UndoFlower(this));
        }
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new CustomFlowerPlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomFlowerBrushListener(this), this);
    }
}