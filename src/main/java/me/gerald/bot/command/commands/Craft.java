package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;

import java.util.Random;

public class Craft extends Command {
    public Craft() {
        super("Craft", new String[] {"craft"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        super.onCommand(playerName, args);
        Player player = Bot.INSTANCE.getConfigManager().loadPlayer(playerName);
        Random r = new Random();
        int xpGain = r.nextInt(300);
        if (player == null) {
            Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
            sendMessage(playerName, "Diamond count to low to craft a block.", true);
            return;
        }
        if (player.getDiamonds() >= 9) {
            Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, player.getPrestige(), player.getLevel(), player.getXp() + xpGain, player.getBalance(), player.getDiamonds() - 9, player.getBlocks() + 1));
            sendMessage(playerName, "Crafted a block and got " + xpGain + " XP!", true);
        }
    }
}
