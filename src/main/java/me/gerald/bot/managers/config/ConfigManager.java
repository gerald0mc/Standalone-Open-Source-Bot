package me.gerald.bot.managers.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.gerald.bot.Bot;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.stream.Collectors;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ConfigManager {
    public final Gson gson;
    public final JsonParser parser;
    public final File saveDir;
    public final File statDir;
    public final File miscDir;
    public final File configFile;

    public ConfigManager() {
        this.saveDir = new File(Minecraft.getMinecraft().gameDir, "Bot");
        this.statDir = new File(saveDir, "Stats");
        this.miscDir = new File(saveDir, "Misc");
        this.parser = new JsonParser();
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        if (!saveDir.exists()) {
            statDir.mkdirs();
            miscDir.mkdir();
        }
        if (!statDir.exists()) statDir.mkdir();
        if (!miscDir.exists()) miscDir.mkdir();
        configFile = new File(miscDir, "Config.json");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                saveConfig(true, true, Bot.botName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void savePlayer(Player player) {
        try {
            final File file = new File(statDir, player.getName() + ".json");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            // Saving the JSON
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                gson.toJson(player, writer);
                System.out.println("Saved new player: " + player.getName());
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Error saving stats of player: " + player.getName());
        }
    }

    public Player loadPlayer(String name) {
        try {
            final File playerFile = new File(statDir, name + ".json");
            if (!playerFile.exists()) return null;
            try (final BufferedReader reader = new BufferedReader(new FileReader(playerFile))) {
                return gson.fromJson(reader.lines().collect(Collectors.joining("\n")), Player.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveConfig(boolean greenText, boolean dmPlayer, String botName) {
        try {
            if (configFile.exists()) {
                configFile.delete();
                configFile.createNewFile();
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("GreenText", greenText);
            jsonObject.addProperty("DMPlayer", dmPlayer);
            jsonObject.addProperty("BotName", botName);
            final BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
            gson.toJson(jsonObject, writer);
            System.out.println("Saved " + Bot.botName + "Bot config.");
            writer.flush();
            writer.close();
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(configFile));
            JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
            Bot.greenText = jsonObject.get("GreenText").getAsBoolean();
            Bot.dmPlayer = jsonObject.get("DMPlayer").getAsBoolean();
            Bot.botName = jsonObject.get("BotName").getAsString();
            System.out.println("Loaded " + Bot.botName + "Bot config.");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
