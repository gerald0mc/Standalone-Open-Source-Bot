package me.gerald.bot.managers;

import me.gerald.bot.Bot;
import me.gerald.bot.events.ChatListener;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventManager {
    private static final String[] joinMessages = new String[] {
            " is alive!",
            " initialized!",
            " is online!",
            " is now cumming!",
            " is now ballin!",
            " is deep in a milf!",
            " is hitting the nae nae!",
            " is fucking bitches now!"
    };
    private static final String[] adMessages = new String[] {
            "Reminder to add the creator of this bot on discord :D gerald0mc#5743",
            "Reminder to star this bots repo on github :D https://github.com/gerald0mc/Standalone-Open-Source-Bot",
            "Reminder to use " + Util.returnFirstLetter() + "help to see all available commands for use :D"
    };
    private static final Minecraft mc = Minecraft.getMinecraft();

    private final Random random = new Random();
    private ChatListener chatListener = null;
    private ScheduledExecutorService messageExecutor = null;
    private ScheduledExecutorService reloadExecutor = null;

    public EventManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() == mc.player) {
            if (chatListener == null) {
                chatListener = new ChatListener();
            }
            mc.player.sendChatMessage(Bot.botName + "Bot" + joinMessages[random.nextInt(joinMessages.length)] + " You can use " + Util.returnFirstLetter() + "help to see all commands you can use!");

            if (messageExecutor == null) {
                messageExecutor = Executors.newSingleThreadScheduledExecutor();
                messageExecutor.scheduleAtFixedRate(() -> {
                    try {
                        if (mc.player == null || mc.world == null) {
                            messageExecutor.shutdown();
                            messageExecutor = null;
                            return;
                        }

                        Util.sendMessage(mc.player.getDisplayNameString(), adMessages[random.nextInt(adMessages.length)]);
                    } catch (Exception e) {
                        messageExecutor.shutdown();
                    }
                }, 20, 1200, TimeUnit.SECONDS);
            }
            if (reloadExecutor == null) {
                reloadExecutor = Executors.newSingleThreadScheduledExecutor();
                reloadExecutor.scheduleAtFixedRate(() -> {
                    try {
                        if (mc.player == null || mc.world == null) {
                            reloadExecutor.shutdown();
                            reloadExecutor = null;
                            return;
                        }

                        try (Stream<Path> pathStream = Files.walk(Paths.get(Bot.getConfigManager().statDir.getPath()), 1)) {
                            for (Path path : pathStream
                                    .filter(Files::isRegularFile)
                                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                                    .collect(Collectors.toList())) {
                                String playerName = path.getFileName().toString().replace(".json", "");
                                Player player = Bot.getConfigManager().loadPlayer(playerName);
                                if (player == null) {
                                    System.out.println(playerName + " is a null Player.");
                                    continue;
                                }
                                if (player.getXp() >= 1000) {
                                    Bot.getConfigManager().savePlayer(new Player(playerName, player.getPrestige(), player.getLevel() + 1, player.getXp() - 1000, player.getBalance(), player.getDiamonds(), player.getBlocks()));
                                    for (EntityPlayer p : mc.world.playerEntities) {
                                        if (p.getDisplayNameString().equalsIgnoreCase(playerName)) {
                                            Util.sendMessage(p.getDisplayNameString(), "Congrats you are now level " + (player.getLevel() + 1) + "!", true);
                                        }
                                    }
                                }

                                System.out.println(path.getFileName());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        reloadExecutor.shutdown();
                    }
                }, 10, 60, TimeUnit.SECONDS);
            }
        }
    }

    public ChatListener getChatListener() {
        return chatListener;
    }
}