package me.gerald.bot.events;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.command.commands.Mine;
import me.gerald.bot.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class ChatListener {
    public ChatListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onMessage(ClientChatReceivedEvent event) {
        if (Minecraft.getMinecraft().getCurrentServerData() == null) return;
        String[] message = event.getMessage().getUnformattedText().split(" ");
        String playerName = null;
        try {
            playerName = event.getMessage().getUnformattedText().substring(1, event.getMessage().getUnformattedText().indexOf(">"));
        } catch (StringIndexOutOfBoundsException e) {
            //#TODO ADD WHISPER SUPPORT
            /* if (event.getMessage().getUnformattedText().contains(Util.returnFirstLetter())) {
                for (Command command : Bot.getCommandManager().getCommands()) {
                    for (String s : message) {
                        if (Minecraft.getMinecraft().getCurrentServerData().playerList.contains(s) && !s.equalsIgnoreCase(Minecraft.getMinecraft().player.getDisplayNameString())) {
                            playerName = s;
                        }
                    }
                    if (playerName == null) return;
                    if (command.isAdminCommand()) {
                        Util.sendMessage(playerName, "Sorry you don't have permission to use this command.", true);
                        return;
                    }
                    int startString = -1;
                    for (int i = 0; i < message.length; i++)
                        if (message[i].trim().startsWith(Util.returnFirstLetter())) startString = i;
                    if (startString == -1) return;
                    String[] args = Arrays.copyOfRange(message, startString, message.length);
                    command.onCommand(playerName, args);
                    return;
                }
            } */
            return;
        }
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
