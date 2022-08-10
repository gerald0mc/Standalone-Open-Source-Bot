package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;

import java.util.Random;

public class Mine extends Command {
    private static final Random random = new Random();

    public Mine() {
        super("Mine", new String[] {"mine"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        int diamonds = random.nextInt(9);
        int xpGain = random.nextInt(200);
        Player player = Bot.getConfigManager().loadPlayer(playerName);
        if (player == null) {
            Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, xpGain, 0, random.nextInt(9), 0));
            Util.sendMessage(playerName, "Mined " + diamonds + " Diamonds and got " + xpGain + " XP!", true);
        } else {
            Bot.getConfigManager().savePlayer(new Player(playerName, player.getPrestige(), player.getLevel(), player.getXp() + xpGain, player.getBalance(), player.getDiamonds() + random.nextInt(9), player.getBlocks()));
            Util.sendMessage(playerName, "Mined " + diamonds + " Diamonds and got " + xpGain + " XP!", true);
        }
    }
}