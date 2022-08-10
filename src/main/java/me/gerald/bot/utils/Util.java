package me.gerald.bot.utils;

import me.gerald.bot.Bot;
import net.minecraft.client.Minecraft;

public class Util {
    public static String returnFirstLetter() {
        String[] arg = Bot.botName.split("");
        String nameChar = arg[0] + "_";
        return nameChar;
    }

    public static void sendMessage(String playerName, String message, boolean lowerFirst) {
        if (Bot.dmPlayer && !playerName.equalsIgnoreCase(Minecraft.getMinecraft().player.getDisplayNameString())) {
            Minecraft.getMinecraft().player.sendChatMessage("/msg " + playerName + " " + (lowerFirst ? playerName + " " + message.replace(message.split(" ")[0], message.split(" ")[0].toLowerCase()) : message));
        } else {
            Minecraft.getMinecraft().player.sendChatMessage((Bot.greenText ? "> " : "") + (lowerFirst ? playerName + " " : " ") + message);
        }
    }
}
