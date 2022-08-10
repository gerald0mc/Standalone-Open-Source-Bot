package me.gerald.bot.utils;

import me.gerald.bot.Bot;
import net.minecraft.client.Minecraft;

public class Util {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static String returnFirstLetter() {
        return Bot.botName.split("")[0] + "_";
    }

    public static void sendMessage(String playerName, String message, boolean lowerFirst) {
        if (Bot.dmPlayer && !playerName.equalsIgnoreCase(mc.player.getDisplayNameString())) {
            mc.player.sendChatMessage("/msg " + playerName + " " + (lowerFirst ? playerName + " " + message.replace(message.split(" ")[0], message.split(" ")[0].toLowerCase()) : message));
        } else {
            mc.player.sendChatMessage((Bot.greenText ? "> " : "") + (lowerFirst ? playerName + " " : " ") + message);
        }
    }
}
