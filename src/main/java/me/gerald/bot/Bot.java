package me.gerald.bot;

import me.gerald.bot.managers.CommandManager;
import me.gerald.bot.managers.EventManager;
import me.gerald.bot.managers.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = Bot.MOD_ID,
        name = Bot.MOD_NAME,
        version = Bot.VERSION
)
public class Bot {

    public static final String MOD_ID = "gerald-bot";
    public static final String MOD_NAME = "GeraldBot";
    public static final String VERSION = "0.1";

    @Mod.Instance(MOD_ID)
    public static Bot INSTANCE;

    private CommandManager commandManager;
    private ConfigManager configManager;
    private EventManager eventManager;

    public static String botName = "Gerald";
    public static boolean greenText;
    public static boolean dmPlayer;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        commandManager = new CommandManager();
        configManager = new ConfigManager();
        eventManager = new EventManager();
        configManager.loadConfig();
    }

    public static CommandManager getCommandManager() {
        return INSTANCE.commandManager;
    }

    public static ConfigManager getConfigManager() {
        return INSTANCE.configManager;
    }

    public static EventManager getEventManager() {
        return INSTANCE.eventManager;
    }
}
