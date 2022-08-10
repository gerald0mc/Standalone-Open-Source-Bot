package me.gerald.bot.managers.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.gerald.bot.Bot;
import net.minecraft.client.Minecraft;

import java.io.*;

public class ConfigManager {
    private final Gson gson;
    private final JsonParser parser;
    private final File saveDir;
    public final File statDir;
    private final File miscDir;
    private final File configFile;
    private final File adminFile;

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
        adminFile = new File(miscDir, "Admins.json");
        if (!adminFile.exists()) {
            try {
                adminFile.createNewFile();
                final BufferedWriter writer = new BufferedWriter(new FileWriter(adminFile));
                final JsonObject blankObject = new JsonObject();
                gson.toJson(blankObject, writer);
                writer.flush();
                writer.close();
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
            final JsonObject jsonObject = new JsonObject();
            //Stats
            final JsonObject statObject = new JsonObject();
            statObject.addProperty("Prestige", player.getPrestige());
            statObject.addProperty("Level", player.getLevel());
            statObject.addProperty("XP", player.getXp());
            statObject.addProperty("Balance", player.getBalance());
            jsonObject.add("Stats", statObject);
            //Inventory
            final JsonObject invObject = new JsonObject();
            invObject.addProperty("Diamonds", player.getDiamonds());
            invObject.addProperty("Blocks", player.getBlocks());
            jsonObject.add("Inventory", invObject);
            //Saving the JSON
            final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            gson.toJson(jsonObject, writer);
            System.out.println("Saved new player: " + player.getName());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving stats of player: " + player.getName());
        }
    }

    public Player loadPlayer(String name) {
        try {
            final File playerFile = new File(statDir, name + ".json");
            if (!playerFile.exists()) return null;
            final BufferedReader reader = new BufferedReader(new FileReader(playerFile));
            final JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
            final JsonObject statObject = jsonObject.get("Stats").getAsJsonObject();
            final JsonObject invObject = jsonObject.get("Inventory").getAsJsonObject();
            return new Player(name, statObject.get("Prestige").getAsInt(), statObject.get("Level").getAsInt(), statObject.get("XP").getAsInt(), statObject.get("Balance").getAsInt(), invObject.get("Diamonds").getAsInt(), invObject.get("Blocks").getAsInt());
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
            System.out.println("Saved GeraldBot config.");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
