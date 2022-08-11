package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

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
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        mc.player.sendChatMessage(Bot.botName + "Bot wants " + builder + "!");
    }
}
