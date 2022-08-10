package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;
import net.minecraft.client.Minecraft;

public class Inventory extends Command {
    public Inventory() {
        super("Inventory", new String[] {"inventory", "[player]"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        Player player = Bot.getConfigManager().loadPlayer(playerName);
        if (player == null) {
            Bot.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
            Util.sendMessage(playerName, "Diamonds [0] Blocks [0]", true);
            return;
        }
        Util.sendMessage(playerName, "Diamonds [" + player.getDiamonds() + "] Blocks [" + player.getBlocks() + "]", true);
    }
}
