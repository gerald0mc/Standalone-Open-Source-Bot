package me.gerald.bot.command.commands;

import me.gerald.bot.Bot;
import me.gerald.bot.command.Command;
import net.minecraft.util.text.TextComponentString;

public class Help extends Command {
    public Help() {
        super("Help", new String[] {"help"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        super.onCommand(playerName, args);
        StringBuilder message = new StringBuilder("Commands: ");
        for (Command command : Bot.INSTANCE.getCommandManager().getCommands()) {
            message.append(Bot.INSTANCE.getEventManager().getChatListener().returnFirstLetter()).append(command.getUsage()[0]).append(" ");
        }
        sendMessage(playerName, message.toString(), false);
        if (playerName.equalsIgnoreCase(mc.player.getDisplayNameString())) {
            StringBuilder adminMessage = new StringBuilder("Admin Commands: ");
            for (Command command : Bot.INSTANCE.getCommandManager().getAdminCommands()) {
                adminMessage.append(Bot.INSTANCE.getEventManager().getChatListener().returnFirstLetter()).append(command.getUsage()[0]).append(" ");
            }
            mc.player.sendMessage(new TextComponentString(adminMessage.toString()));
        }
    }
}
