package me.gerald.bot.managers;

import me.gerald.bot.Bot;
import me.gerald.bot.command.commands.Mine;
import me.gerald.bot.events.ChatListener;
import me.gerald.bot.managers.config.Player;
import me.gerald.bot.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.Sys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EventManager {
    public EventManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private final String[] joinMessages = new String[] {" is alive!", " initialized!", " is online!", " is now cumming!", " is now ballin!", " is deep in a milf!", " is hitting the nae nae!", " is fucking bitches now!"};
    private final Random random = new Random();
    private long reloadMS = -1;
    private long remindMS = -1;

    private ChatListener chatListener = null;

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() == Minecraft.getMinecraft().player) {
            if (chatListener == null) {
                chatListener = new ChatListener();
                reloadMS = System.currentTimeMillis();
                remindMS = System.currentTimeMillis();
            }
            String message = joinMessages[random.nextInt(joinMessages.length) - 1];
            Minecraft.getMinecraft().player.sendChatMessage(Bot.botName + "Bot" + message + " You can use " + Util.returnFirstLetter() + "help to see all commands you can use!");
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().world == null) return;
        if (remindMS != -1 && System.currentTimeMillis() - remindMS >= 60000 * 10) {
            Minecraft.getMinecraft().player.sendChatMessage("Reminder to add the creator of this bot on discord :D gerald0mc#5743");
            remindMS = System.currentTimeMillis();
        }
        if (reloadMS != -1 && System.currentTimeMillis() - reloadMS < 60000) return;
        List<String> playerNames = new LinkedList<>();
        try {
            List<Path> paths = Files.walk(Paths.get(Bot.INSTANCE.getConfigManager().statDir.getPath()),1)
                    .filter(Files::isRegularFile)
                    .filter(path-> path.getFileName().toString().endsWith(".json"))
                    .collect(Collectors.toList());
            for (Path path : paths) {
                playerNames.add(path.getFileName().toString());
                System.out.println(path.getFileName().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : playerNames) {
            Player player = Bot.INSTANCE.getConfigManager().loadPlayer(s.replace(".json", ""));
            if (player == null) {
                System.out.println(s + " is a null Player.");
                continue;
            }
            if (player.getXp() >= 1000) {
                Bot.INSTANCE.getConfigManager().savePlayer(new Player(s.replace(".json", ""), player.getPrestige(), player.getLevel() + 1, player.getXp() - 1000, player.getBalance(), player.getDiamonds(), player.getBlocks()));
                for (EntityPlayer p : Minecraft.getMinecraft().player.world.playerEntities) {
                    if (p.getDisplayNameString().equalsIgnoreCase(s.replace(".json", ""))) {
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