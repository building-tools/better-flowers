package com.uroria.betterflowers.managers;

import com.google.gson.JsonParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public final class LanguageManager {

    private final Map<String, String> singleMessage;
    private final Map<String, List<String>> multiMessage;

    public LanguageManager() {
        this.singleMessage = new HashMap<>();
        this.multiMessage = new HashMap<>();

        readLangaugeConfig();
    }

    public void sendPlayerMessage(Player player, String key) {
        player.sendMessage(getComponent(key));
    }

    public void sendPlayersMessage(Collection<Player> players, String key) {
        players.forEach(player -> sendPlayerMessage(player, key));
    }

    public Component getComponent(String key, String regex, String value) {
        final var string = getStringFromConfig(key).replace(regex, value);
        return MiniMessage.miniMessage().deserialize(string);
    }

    public Component getComponent(String key) {
        final var string = getStringFromConfig(key);
        return MiniMessage.miniMessage().deserialize(string);
    }

    /*
    public List<Component> getComponents(String key) {
        return getStringsFromConfig(key).stream().map(string -> MiniMessage.miniMessage().deserialize(string)).toList();
    }
     */

    private String getStringFromConfig(String key) {
        return this.singleMessage.getOrDefault(key, key);
    }

    private String getStringFromConfig(String key, String optional) {
        return this.singleMessage.getOrDefault(key, optional);
    }

    /*
    private List<String> getStringsFromConfig(String key) {
        return List.of(key);
    }

    private List<String> getStringsFromConfig(String key, List<String> optional) {
        return optional;
    }
     */

    private void readLangaugeConfig() {
        final var path =  Bukkit.getPluginsFolder() + "\\BetterFlowers\\langauge.json";
        final var languageFile = new File(path);

        if (!languageFile.exists()){
            return;
        }

        try {
            final var jsonFile = JsonParser.parseReader(new FileReader(path)).getAsJsonObject();

            jsonFile.keySet().forEach(key -> {

                final var element = jsonFile.get(key);

                /*
                if (element.isJsonArray()) {

                    final var list = new ArrayList<String>();
                    element.getAsJsonArray().forEach(jsonElement -> list.add(jsonElement.getAsString()));

                    this.multiMessage.put(key, list);
                    return;
                }
                 */

                this.singleMessage.put(key, element.getAsString());

            });

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}