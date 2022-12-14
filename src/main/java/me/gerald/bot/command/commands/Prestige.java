package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;

public class Prestige extends Command {
    public Prestige() {
        super("Prestige", new String[] {"prestige"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        Player player = Bot.getConfigManager().loadPlayer(playerName);
        if (player == null) {
            Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
            Util.sendMessage(playerName, "Not enough levels to prestige. (5 min)", true);
            return;
        }
        if (player.getLevel() < 5) {
            Util.sendMessage(playerName, "Not enough levels to prestige. (5 min)", true);
            return;
        }
        Bot.getConfigManager().savePlayer(new Player(playerName, player.getPrestige() + 1, player.getLevel() - 5, player.getXp(), player.getBalance(), player.getDiamonds(), player.getBlocks()));
        Util.sendMessage(playerName, "Congrats you prestiged!", true);
    }
}
