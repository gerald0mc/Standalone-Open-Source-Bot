package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;

import java.util.Random;

public class Mine extends Command {
    public Mine() {
        super("Mine", new String[] {"mine"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        super.onCommand(playerName, args);
        Random random = new Random();
        int diamonds = random.nextInt(9);
        int xpGain = random.nextInt(200);
        Player player = Bot.INSTANCE.getConfigManager().loadPlayer(playerName);
        if (player == null) {
            Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, 0, 0, xpGain, 0, random.nextInt(9), 0));
            sendMessage(playerName, "Mined " + diamonds + " Diamonds and got " + xpGain + " XP!", true);
        } else {
            Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, player.getPrestige(), player.getLevel(), player.getXp() + xpGain, player.getBalance(), player.getDiamonds() + random.nextInt(9), player.getBlocks()));
            sendMessage(playerName, "Mined " + diamonds + " Diamonds and got " + xpGain + " XP!", true);
        }
    }
}
