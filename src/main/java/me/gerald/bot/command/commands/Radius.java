package me.gerald.bot.command.commands;

import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;
import net.minecraft.entity.player.EntityPlayer;

import java.util.stream.Collectors;

public class Radius extends Command {
    public Radius() {
        super("Radius", new String[] {"radius"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        String radius = mc.world.playerEntities.stream()
                .filter(player -> player != mc.player)
                .map(EntityPlayer::getDisplayNameString)
                .collect(Collectors.joining(", "));
        Util.sendMessage(playerName, "Players in Radius: " + (radius.isEmpty() ? "I am lonely :(" : radius));
    }
}
