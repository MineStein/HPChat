package me.tylergrissom.hpchat.utility;

import me.tylergrissom.hpchat.Main;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ActionBarUtility {

    private Main plugin;

    public ActionBarUtility(Main plugin) {
        this.plugin = plugin;
    }

    public void sendActionBar(Player p, String message) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);

        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
    }
}
