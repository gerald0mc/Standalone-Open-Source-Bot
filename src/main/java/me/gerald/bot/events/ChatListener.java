package me.gerald.bot.events;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
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
        event.setCanceled(false);
        if (Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().world == null) return;
        String[] message = event.getMessage().getUnformattedText().split(" ");
        String playerName = event.getMessage().getUnformattedText().substring(1, event.getMessage().getUnformattedText().indexOf(">"));
        if (message[1].contains(returnFirstLetter())) {
            boolean stop = false;
            if (playerName.equalsIgnoreCase(Minecraft.getMinecraft().player.getDisplayNameString())) {
                for (Command command : Bot.INSTANCE.getCommandManager().getAdminCommands()) {
                    if (message[1].equalsIgnoreCase(returnFirstLetter() + command.getUsage()[0])) {
                        String[] args = Arrays.copyOfRange(message, 1, message.length);
                        command.onCommand(playerName, args);
                        stop = true;
                        break;
                    }
                }
            }
            if (stop) return;
            for (Command command : Bot.INSTANCE.getCommandManager().getCommands()) {
                if (message[1].equalsIgnoreCase(returnFirstLetter() + command.getUsage()[0])) {
                    String[] args = Arrays.copyOfRange(message, 1, message.length);
                    command.onCommand(playerName, args);
                }
            }
        }
    }

    public String returnFirstLetter() {
        String[] arg = Bot.botName.split("");
        String nameChar = arg[0] + "_";
        return nameChar;
    }
}
