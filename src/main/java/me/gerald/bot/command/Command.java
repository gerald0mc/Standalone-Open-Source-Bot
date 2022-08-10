package me.gerald.bot.command;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public abstract class Command {
    private final String name;
    private final String[] usage;
    private final boolean isAdminCommand;

    protected static Minecraft mc = Minecraft.getMinecraft();

    public Command(String name, String[] usage) {
        this(name, usage, false);
    }

    public Command(String name, String[] usage, boolean isAdminCommand) {
        MinecraftForge.EVENT_BUS.register(this);
        this.name = name;
        this.usage = usage;
        this.isAdminCommand = isAdminCommand;
    }

    public abstract void onCommand(String playerName, String[] args);

    public String getName() {
        return name;
    }

    public String[] getUsage() {
        return usage;
    }

    public boolean isAdminCommand() {
        return isAdminCommand;
    }
}
