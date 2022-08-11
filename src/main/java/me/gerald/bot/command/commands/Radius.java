package me.gerald.bot.command.commands;

import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;
import net.minecraft.entity.player.EntityPlayer;

public class Radius extends Command {
    public Radius() {
        super("Radius", new String[] {"radius"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        StringBuilder message = new StringBuilder("Players in Radius: ");
        for (EntityPlayer player : mc.world.playerEntities) {
            if (player == mc.player) continue;
            message
                    .append(player.getDisplayNameString())
                    .append(" ");
        }
        if (message.toString().equals("Players in Radius: "))
            message.append("I am lonely :(");
        Util.sendMessage(playerName, message.toString(), false);
    }
}
