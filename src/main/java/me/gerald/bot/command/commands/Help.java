package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

public class Help extends Command {
    public Help() {
        super("Help", new String[] {"help"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        StringBuilder message = new StringBuilder("Commands: ");
        for (Command command : Bot.getCommandManager().getCommands()) {
            message.append(Util.returnFirstLetter())
                    .append(command.getUsage()[0])
                    .append(" ")
                    .append(command.isAdminCommand() ? "ADMIN " : "");
        }
        Util.sendMessage(playerName, message.toString(), false);
    }
}
