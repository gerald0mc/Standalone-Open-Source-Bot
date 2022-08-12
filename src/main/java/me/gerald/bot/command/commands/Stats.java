package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;

public class Stats extends Command {
    public Stats() {
        super("Stats", new String[] {"stats"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        Player player = Bot.getConfigManager().loadPlayer(playerName);
        if (player == null) {
            Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
            Util.sendMessage(playerName, "Prestige [0] Level [0] XP [0] Balance [0]", true);
            return;
        }
        Util.sendMessage(playerName, "Prestige [" + player.getPrestige() + "] Level [" + player.getLevel() + "] XP [" + player.getXp() + "] Balance [" + player.getBalance() + "]", true);
    }
}
