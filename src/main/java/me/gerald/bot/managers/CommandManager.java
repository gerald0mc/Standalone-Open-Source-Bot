package me.gerald.bot.managers;

import me.gerald.bot.command.Command;
import me.gerald.bot.command.commands.*;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<Command> commands;

    private final List<Command> adminCommands;

    public CommandManager() {
        commands = new ArrayList<>();
        commands.add(new Stats());
        commands.add(new Inventory());
        commands.add(new Mine());
        commands.add(new Craft());
        commands.add(new Say());
        commands.add(new Help());
        adminCommands = new ArrayList<>();
        adminCommands.add(new Reload());
    }

    public List<Command> getCommands() {
        return commands;
    }

    public List<Command> getAdminCommands() {
        return adminCommands;
    }
}
