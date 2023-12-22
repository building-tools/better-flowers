package com.uroria.betterflowers.managers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;

public final class LanguageManager {

    private final Map<String, String> singleMessage;
    private final Map<String, List<String>> multiMessage;

    public LanguageManager() {
        this.singleMessage = new HashMap<>();
        this.multiMessage = new HashMap<>();

        readConfig();
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

    public List<Component> getComponents(String key) {
        return getStringsFromConfig(key).stream().map(string -> MiniMessage.miniMessage().deserialize(string)).toList();
    }

    private void readLangaugeFromResources(String path) {

        final var inputStream = getClass().getClassLoader().getResourceAsStream("language.json");
        if (inputStream == null) return;

        try {
            final var reader = new BufferedReader(new InputStreamReader(inputStream));
            final var stringBuilder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            final var jsonString = stringBuilder.toString();
            final var jsonFile = new Gson().fromJson(jsonString, JsonObject.class);

            jsonFile.keySet().forEach(key -> {

                final var element = jsonFile.get(key);
                readConfigData(key, element);

            });

            writeFile(path + "/language.json", jsonString);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void writeFile(String path, String data) {

        try (var fileWriter = new FileWriter(path)) {

            fileWriter.write(data);
            fileWriter.flush();

        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private void readLanguageFileFromJson(File file)  {

        try {

            final var jsonFile = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();

            jsonFile.keySet().forEach(key -> {

                final var element = jsonFile.get(key);
                readConfigData(key, element);

            });

        } catch (FileNotFoundException fileNotFoundException) {
            throw new RuntimeException(fileNotFoundException);
        }
    }

    private void readConfigData(String key, JsonElement element) {

        if (element.isJsonArray()) {

            final var list = new ArrayList<String>();

            for(var jsonElement : element.getAsJsonArray().asList()) {
                list.add(jsonElement.getAsString());
            }

            this.multiMessage.put(key, list);
            return;
        }

        this.singleMessage.put(key, element.getAsString());
    }

    private void readConfig() {

        final var path = Bukkit.getPluginsFolder() + "/BetterFlowers";
        final var file = new File(path + "/language.json");

        if (new File(path).mkdir()) return;
        if (file.exists()) {
            readLanguageFileFromJson(file);
            return;
        }

        readLangaugeFromResources(path);
    }

    private String getStringFromConfig(String key) {
        return this.singleMessage.getOrDefault(key, key);
    }

    private String getStringFromConfig(String key, String optional) {
        return this.singleMessage.getOrDefault(key, optional);
    }

    private List<String> getStringsFromConfig(String key) {
        return this.multiMessage.getOrDefault(key, List.of(key));
    }

    private List<String> getStringsFromConfig(String key, List<String> optional) {
        return this.multiMessage.getOrDefault(key, optional);
    }
}