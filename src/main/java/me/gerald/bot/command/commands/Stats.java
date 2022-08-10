package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;

public class Stats extends Command {
    public Stats() {
        super("Stats", new String[] {"stats"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        super.onCommand(playerName, args);
        Player player = Bot.INSTANCE.getConfigManager().loadPlayer(playerName);
        if (player == null) {
            Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
            sendMessage(playerName, "Prestige [0] Level [0] XP [0] Balance [0]", true);
            return;
        }
        sendMessage(playerName, "Prestige [" + player.getPrestige() + "] Level [" + player.getLevel() + "] XP [" + player.getXp() + "] Balance [" + player.getBalance() + "]", true);
    }
}
