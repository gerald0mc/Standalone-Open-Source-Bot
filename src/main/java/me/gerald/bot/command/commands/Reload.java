package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

public class Reload extends Command {
    public Reload() {
        super("Reload", new String[] {"reload"}, true);
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        if (playerName.equalsIgnoreCase(mc.player.getDisplayNameString())) {
            Bot.getConfigManager().loadConfig();
            Util.sendMessage(playerName, "Reloaded the bots config.", true);
        }
    }
}
