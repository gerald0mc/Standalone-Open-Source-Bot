package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;
import net.minecraft.util.text.TextComponentString;

public class Reload extends Command {
    public Reload() {
        super("Reload", new String[] {"reload"});
        setIsAdminCommand(true);
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        super.onCommand(playerName, args);
        if (playerName.equalsIgnoreCase(mc.player.getDisplayNameString())) {
            Bot.INSTANCE.getConfigManager().loadConfig();
            Util.sendMessage(playerName, "Reloaded the bots config.", true);
        }
    }
}
