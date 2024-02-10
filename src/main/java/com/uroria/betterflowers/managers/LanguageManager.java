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

    private static final Map<String, String> SINGLE_MESSAGE = new HashMap<>();
    private static final Map<String, List<String>> MULTI_MESSAGE = new HashMap<>();

    public static void sendPlayerMessage(Player player, String key) {
        player.sendMessage(getComponent(key));
    }

    public static void sendPlayersMessage(Collection<Player> players, String key) {
        players.forEach(player -> sendPlayerMessage(player, key));
    }

    public static Component getComponent(String key, String regex, String value) {
        final var string = getStringFromConfig(key).replace(regex, value);
        return MiniMessage.miniMessage().deserialize(string);
    }

    public static Component getComponent(String key) {
        final var string = getStringFromConfig(key);
        return MiniMessage.miniMessage().deserialize(string);
    }

    public static List<Component> getComponents(String key) {
        return getStringsFromConfig(key).stream().map(string -> MiniMessage.miniMessage().deserialize(string)).toList();
    }

    private static void readLangaugeFromResources(String path) {

        final var inputStream = LanguageManager.class.getClassLoader().getResourceAsStream("language.json");
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

    private static void writeFile(String path, String data) {

        try (var fileWriter = new FileWriter(path)) {

            fileWriter.write(data);
            fileWriter.flush();

        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private static void readLanguageFileFromJson(File file) {

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

    private static void readConfigData(String key, JsonElement element) {

        if (element.isJsonArray()) {

            final var list = new ArrayList<String>();

            for (var jsonElement : element.getAsJsonArray().asList()) {
                list.add(jsonElement.getAsString());
            }

            MULTI_MESSAGE.put(key, list);
            return;
        }

        SINGLE_MESSAGE.put(key, element.getAsString());
    }

    public  static void readConfig() {

        final var path = Bukkit.getPluginsFolder() + "/BetterFlowers";
        final var file = new File(path + "/language.json");

        try {

            if (new File(path).mkdir()) throw new FileNotFoundException();

            if (file.exists()) {
                readLanguageFileFromJson(file);
                return;
            }

            readLangaugeFromResources(path);

        } catch (FileNotFoundException exception) {
            readLangaugeFromResources(path);
        }
    }

    private static String getStringFromConfig(String key) {
        return SINGLE_MESSAGE.getOrDefault(key, key);
    }

    private static String getStringFromConfig(String key, String optional) {
        return SINGLE_MESSAGE.getOrDefault(key, optional);
    }

    private static List<String> getStringsFromConfig(String key) {
        return MULTI_MESSAGE.getOrDefault(key, List.of(key));
    }

    private static List<String> getStringsFromConfig(String key, List<String> optional) {
        return MULTI_MESSAGE.getOrDefault(key, optional);
    }
}