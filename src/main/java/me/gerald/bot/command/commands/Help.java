package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

import java.util.stream.Collectors;

public class Help extends Command {
    public Help() {
        super("Help", new String[] {"help"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        String message = Bot.getCommandManager().getCommands().stream()
                .map(command -> String.join(" ", command.getUsage()) + (command.isAdminCommand() ? " ADMIN" : ""))
                .map(string -> Util.returnFirstLetter() + string)
                .collect(Collectors.joining(", "));
        Util.sendMessage(playerName, message);
    }
}
