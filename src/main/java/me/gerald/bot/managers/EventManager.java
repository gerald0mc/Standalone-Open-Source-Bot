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
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventManager {
    private static final String[] joinMessages = new String[]{
            " is alive!",
            " initialized!",
            " is online!",
            " is now cumming!",
            " is now ballin!",
            " is deep in a milf!",
            " is hitting the nae nae!",
            " is fucking bitches now!"
    };
    private static final Minecraft mc = Minecraft.getMinecraft();

    private final Random random = new Random();
    private long reloadMS = -1;
    private long remindMS = -1;
    private ChatListener chatListener = null;

    public EventManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() == mc.player) {
            if (chatListener == null) {
                chatListener = new ChatListener();
                reloadMS = System.currentTimeMillis();
                remindMS = System.currentTimeMillis();
            }
            String message = joinMessages[random.nextInt(joinMessages.length)];
            mc.player.sendChatMessage(Bot.botName + "Bot" + message + " You can use " + Util.returnFirstLetter() + "help to see all commands you can use!");
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player == null || mc.world == null) return;
        if (remindMS != -1 && System.currentTimeMillis() - remindMS >= 60000 * 10) {
            mc.player.sendChatMessage("Reminder to add the creator of this bot on discord :D gerald0mc#5743");
            remindMS = System.currentTimeMillis();
        }
        if (reloadMS != -1 && System.currentTimeMillis() - reloadMS < 60000) return;
        List<String> playerNames = new LinkedList<>();
        try (Stream<Path> pathStream = Files.walk(Paths.get(Bot.getConfigManager().statDir.getPath()), 1)) {
            for (Path path : pathStream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                    .collect(Collectors.toList())) {
                playerNames.add(path.getFileName().toString());
                System.out.println(path.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String playerName : playerNames) {
            Player player = Bot.getConfigManager().loadPlayer(playerName.replace(".json", ""));
            if (player == null) {
                System.out.println(playerName + " is a null Player.");
                continue;
            }
            if (player.getXp() >= 1000) {
                Bot.getConfigManager().savePlayer(new Player(playerName.replace(".json", ""), player.getPrestige(), player.getLevel() + 1, player.getXp() - 1000, player.getBalance(), player.getDiamonds(), player.getBlocks()));
                for (EntityPlayer p : mc.player.world.playerEntities) {
                    if (p.getDisplayNameString().equalsIgnoreCase(playerName.replace(".json", ""))) {
                        Util.sendMessage(p.getDisplayNameString(), "Congrats you are now level " + (player.getLevel() + 1) + "!", true);
                    }
                }
            }
        }
        reloadMS = System.currentTimeMillis();
    }

    public ChatListener getChatListener() {
        return chatListener;
    }
}