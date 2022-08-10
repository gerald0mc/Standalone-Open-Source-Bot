package me.gerald.bot.command.commands;

import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

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
        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            String s = args[i];
            message.append(s).append(" ");
        }
        if (message.toString().trim().startsWith("/") || message.toString().trim().startsWith("!")) {
            Util.sendMessage(playerName, "Nice try!", true);
            return;
        }
        mc.player.sendChatMessage(message.toString());
    }
}
