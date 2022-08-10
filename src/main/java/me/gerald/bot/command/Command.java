package me.gerald.bot.command;

import me.gerald.bot.Bot;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Command {
    private final String name;
    private final String[] usage;
    private boolean isAdminCommand = false;

    public static Minecraft mc = Minecraft.getMinecraft();

    public Command(String name, String[] usage) {
        MinecraftForge.EVENT_BUS.register(this);
        this.name = name;
        this.usage = usage;
    }

    public void onCommand(String playerName, String[] args) { }

    public String getName() {
        return name;
    }

    public String[] getUsage() {
        return usage;
    }

    public void setIsAdminCommand(boolean isAdminCommand) {
        this.isAdminCommand = isAdminCommand;
    }

    public boolean isAdminCommand() {
        return isAdminCommand;
    }
}
