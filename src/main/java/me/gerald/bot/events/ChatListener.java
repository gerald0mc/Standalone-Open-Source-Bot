package me.gerald.bot.events;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class ChatListener {
    public ChatListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onMessage(ClientChatReceivedEvent event) {
        String[] message = event.getMessage().getUnformattedText().split(" ");
        String playerName = "";
        try {
            playerName = event.getMessage().getUnformattedText().substring(1, event.getMessage().getUnformattedText().indexOf(">"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (playerName.equals("")) return;
        if (message[1].trim().startsWith(Util.returnFirstLetter())) {
            for (Command command : Bot.getCommandManager().getCommands()) {
                if (message[1].equalsIgnoreCase(Util.returnFirstLetter() + command.getUsage()[0])) {
                    if (command.isAdminCommand() && !playerName.equalsIgnoreCase(Minecraft.getMinecraft().player.getDisplayNameString())) {
                        Util.sendMessage(playerName, "Sorry you don't have permission to use this command.", true);
                        break;
                    }
                    String[] args = Arrays.copyOfRange(message, 1, message.length);
                    command.onCommand(playerName, args);
                }
            }
        }
    }
}
