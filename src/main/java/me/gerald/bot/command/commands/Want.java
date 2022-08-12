package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Want extends Command {
    public Want() {
        super("Want", new String[] {"want", "[want]"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        if (args.length == 1) {
            Util.sendMessage(playerName, "Please perform the " + Util.returnFirstLetter() + "_help command to properly perform this command.", true);
            return;
        }
        String message = Arrays.stream(args)
                .skip(1).collect(Collectors.joining(" "));
        mc.player.sendChatMessage(Bot.botName + "Bot wants " + message + "!");
    }
}
