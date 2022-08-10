package me.gerald.bot.managers;

import com.google.common.reflect.ClassPath;
import me.gerald.bot.command.Command;
import net.minecraft.launchwrapper.Launch;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

public class CommandManager {
    private final List<Command> commands;

    public CommandManager() {
        commands = new LinkedList<>();
        loadCommands("me.gerald.bot.command.commands");
    }

    public List<Command> getCommands() {
        return commands;
    }

    @SuppressWarnings({"UnstableApiUsage", "SameParameterValue"})
    private void loadCommands(String pkg) {
        try {
            for (ClassPath.ClassInfo classInfo : ClassPath.from(Launch.classLoader).getAllClasses()) {
                if (classInfo.getName().startsWith(pkg)) {
                    final Class<?> clazz = classInfo.load();

                    if (!Modifier.isAbstract(clazz.getModifiers()) && Command.class.isAssignableFrom(clazz)) {
                        for (Constructor<?> constructor : clazz.getConstructors()) {
                            if (!constructor.isAccessible()) {
                                constructor.setAccessible(true);
                            }

                            if (constructor.getParameterCount() == 0) {
                                final Command command = (Command) constructor.newInstance();
                                for (Field field : command.getClass().getDeclaredFields()) {
                                    if (!field.isAccessible())
                                        field.setAccessible(true);
                                }
                                commands.add(command);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
