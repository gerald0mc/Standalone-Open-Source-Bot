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
        Util.sendMessage(playerName, "Command List: https://github.com/gerald0mc/Standalone-Open-Source-Bot/blob/main/Bot-Commands.md");
    }
}
