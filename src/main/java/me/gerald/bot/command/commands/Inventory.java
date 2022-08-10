package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;

public class Inventory extends Command {
    public Inventory() {
        super("Inventory", new String[] {"inventory"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        super.onCommand(playerName, args);
        Player player = Bot.INSTANCE.getConfigManager().loadPlayer(playerName);
        if (player == null) {
            Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
            sendMessage(playerName, "Diamonds [0] Blocks [0]", false);
            return;
        }
        sendMessage(playerName, "Diamonds [" + player.getDiamonds() + "] Blocks [" + player.getBlocks() + "]", false);
    }
}
