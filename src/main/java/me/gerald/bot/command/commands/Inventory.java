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
        super.onCommand(playerName, args);
        switch (args.length) {
            case 1:
                Player player = Bot.INSTANCE.getConfigManager().loadPlayer(playerName);
                if (player == null) {
                    Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
                    Util.sendMessage(playerName, "Diamonds [0] Blocks [0]", true);
                    return;
                }
                Util.sendMessage(playerName, "Diamonds [" + player.getDiamonds() + "] Blocks [" + player.getBlocks() + "]", true);
                break;
            case 2:
                if (!playerName.equalsIgnoreCase(Minecraft.getMinecraft().player.getDisplayNameString())) {
                    Util.sendMessage(playerName, "Sorry you don't have permission to use that command.", true);
                    return;
                }
                Player targetPlayer = Bot.INSTANCE.getConfigManager().loadPlayer(args[1]);
                if (targetPlayer == null) {
                    Bot.INSTANCE.getConfigManager().savePlayer(new Player(playerName, 0, 0, 0, 0, 0, 0));
                    Util.sendMessage(playerName, targetPlayer.getName() + " Diamonds [0] Blocks [0]", false);
                    return;
                }
                Util.sendMessage(playerName, targetPlayer.getName() + " Diamonds [" + targetPlayer.getDiamonds() + "] Blocks [" + targetPlayer.getBlocks() + "]", false);
        }
    }
}
