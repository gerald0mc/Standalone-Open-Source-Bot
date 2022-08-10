package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;
import net.minecraft.util.text.TextComponentString;

public class Help extends Command {
    public Help() {
        super("Help", new String[] {"help"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        super.onCommand(playerName, args);
        StringBuilder message = new StringBuilder("Commands: ");
        for (Command command : Bot.INSTANCE.getCommandManager().getCommands()) {
            message.append(Util.returnFirstLetter()).append(command.getUsage()[0]).append(" " + (command.isAdminCommand() ? "ADMIN " : ""));
        }
        Util.sendMessage(playerName, message.toString(), false);
    }
}
