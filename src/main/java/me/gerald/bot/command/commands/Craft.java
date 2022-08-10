package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;

import java.util.Random;

public class Craft extends Command {
    private static final Random random = new Random();

    public Craft() {
        super("Craft", new String[] {"craft"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        Player player = Bot.getConfigManager().loadPlayer(playerName);
        int xpGain = random.nextInt(300);
        if (player == null) {
            Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
            Util.sendMessage(playerName, "Diamond count to low to craft a block.", true);
            return;
        }
        if (player.getDiamonds() >= 9) {
            Bot.getConfigManager().savePlayer(new Player(playerName, player.getPrestige(), player.getLevel(), player.getXp() + xpGain, player.getBalance(), player.getDiamonds() - 9, player.getBlocks() + 1));
            Util.sendMessage(playerName, "Crafted a block and got " + xpGain + " XP!", true);
        }
    }
}
