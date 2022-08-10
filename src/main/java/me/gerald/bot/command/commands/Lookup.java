package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;

public class Lookup extends Command {
    public Lookup() {
        super("Lookup", new String[] {"lookup", "[stats/inventory]", "[player]"}, true);
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        if (args.length != 3) {
            Util.sendMessage(playerName, "Please perform the " + Util.returnFirstLetter() + "_help command to properly perform this command.", true);
            return;
        }
        switch (args[1]) {
            case "stats":
                Player targetPlayer = Bot.getConfigManager().loadPlayer(args[2]);
                if (targetPlayer == null) {
                    Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
                    Util.sendMessage(playerName, args[2] + " Prestige [0] Level [0] XP [0] Balance [0]", false);
                    return;
                }
                Util.sendMessage(playerName, targetPlayer.getName() + " Prestige [" + targetPlayer.getPrestige() + "] Level [" + targetPlayer.getLevel() + "] XP [" + targetPlayer.getXp() + "] Balance [" + targetPlayer.getBalance() + "]", false);
                break;
            case "inventory":
                Player tPlayer = Bot.getConfigManager().loadPlayer(args[2]);
                if (tPlayer == null) {
                    Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
                    Util.sendMessage(playerName, args[2] + " Diamonds [0] Blocks [0]", false);
                    return;
                }
                Util.sendMessage(playerName, tPlayer.getName() + " Diamonds [" + tPlayer.getDiamonds() + "] Blocks [" + tPlayer.getBlocks() + "]", false);
        }
    }
}
