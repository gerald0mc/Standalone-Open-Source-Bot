package me.gerald.bot.command.commands;

import me.gerald.bot.command.Command;
import me.gerald.bot.utils.Util;

public class Pos extends Command {
    public Pos() {
        super("Pos", new String[] {"pos"});
    }

    @Override
    public void onCommand(String playerName, String[] args) {
        if (args.length > 1) {
            Util.sendMessage(playerName, "Please perform the " + Util.returnFirstLetter() + "help command to properly perform this command.", true);
            return;
        }
        String message;
        if (mc.player.getDistance(0, mc.player.posY, 0) <= 10000) {
            message = "In the spawn region. In the " + (isNegative(mc.player.posX) ? "[-] " : "[+] ") + (isNegative(mc.player.posZ) ? "[-] " : "[+]") + " portion of the map. Dimension [" + getDimension() + "]";
        } else {
            message = "Farther then 10000 out of spawn. In the " + (isNegative(mc.player.posX) ? "[-] " : "[+] ") + (isNegative(mc.player.posZ) ? "[-] " : "[+]") + " portion of the map. Dimension [" + getDimension() + "]";
        }
        Util.sendMessage(playerName, message);
    }

    private boolean isNegative(double number) {
        if (number < 0) return true;
        return false;
    }

    private String getDimension() {
        switch (mc.player.dimension) {
            case -1:
                return "Nether";
            case 0:
                return "Overworld";
            case 1:
                return "The End";
            default:
                return "?";
        }
    }
}
