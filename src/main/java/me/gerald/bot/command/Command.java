package me.gerald.bot.command;

import me.gerald.bot.Bot;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Command {
    private final String name;
    private final String[] usage;

    public static Minecraft mc = Minecraft.getMinecraft();

    public Command(String name, String[] usage) {
        MinecraftForge.EVENT_BUS.register(this);
        this.name = name;
        this.usage = usage;
    }

    public void onCommand(String playerName, String[] args) { }

    public String getName() {
        return name;
    }

    public String[] getUsage() {
        return usage;
    }

    public void sendMessage(String playerName, String message, boolean lowerFirst) {
        if (Bot.dmPlayer && !playerName.equalsIgnoreCase(mc.player.getDisplayNameString())) {
            mc.player.sendChatMessage("/msg " + playerName + " " + (lowerFirst ? playerName + " " + message.replace(message.split(" ")[0], message.split(" ")[0].toLowerCase()) : message));
        } else {
            mc.player.sendChatMessage((Bot.greenText ? "> " : "") + (lowerFirst ? playerName + " " : " ") + message);
        }
    }
}
