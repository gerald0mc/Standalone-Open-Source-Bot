package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;

public class Stats extends Command {
    public Stats() {
        super("Stats", new String[] {"stats", "[player]"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        switch (args.length) {
            case 1:
                Player player = Bot.getConfigManager().loadPlayer(playerName);
                if (player == null) {
                    Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
                    Util.sendMessage(playerName, "Prestige [0] Level [0] XP [0] Balance [0]", true);
                    return;
                }
                Util.sendMessage(playerName, "Prestige [" + player.getPrestige() + "] Level [" + player.getLevel() + "] XP [" + player.getXp() + "] Balance [" + player.getBalance() + "]", true);
                break;
            case 2:
                if (!playerName.equalsIgnoreCase(mc.player.getDisplayNameString())) {
                    Util.sendMessage(playerName, "Sorry you don't have permission to use that command.", true);
                    return;
                }
                Player targetPlayer = Bot.getConfigManager().loadPlayer(args[1]);
                if (targetPlayer == null) {
                    Bot.getConfigManager().savePlayer(new Player(args[1], 0, 0, 0, 0, 0, 0));
                    Util.sendMessage(playerName, args[1] + " Prestige [0] Level [0] XP [0] Balance [0]", false);
                    return;
                }
                Util.sendMessage(playerName, targetPlayer.getName() + " Prestige [" + targetPlayer.getPrestige() + "] Level [" + targetPlayer.getLevel() + "] XP [" + targetPlayer.getXp() + "] Balance [" + targetPlayer.getBalance() + "]", false);
                break;
        }
    }
}
