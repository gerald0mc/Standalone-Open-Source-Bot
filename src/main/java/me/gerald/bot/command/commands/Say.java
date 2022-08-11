package me.gerald.bot.command.commands;

import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Say extends Command {
    public Say() {
        super("Say", new String[] {"say", "[message]"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        if (args.length == 1) {
            mc.player.sendChatMessage("Please input a message you wish to send.");
            return;
        }
        String message = Arrays.stream(args)
                .skip(1).collect(Collectors.joining(" "));
        if (message.trim().startsWith("/") || message.trim().startsWith("!")) {
            Util.sendMessage(playerName, "Nice try!", true);
            return;
        }
        mc.player.sendChatMessage(message);
    }
}
