package me.tylergrissom.hpchat.utility;

import me.tylergrissom.hpchat.HPChatPlugin;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ActionBarUtility {

    private HPChatPlugin plugin;

    /**
     * Instantiate a new ActionBarUtility
     * @param plugin The JavaPlugin instance of HPChat
     */
    public ActionBarUtility(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Sends the message as an action bar to the specified player
     * @param p The player to send the action bar to
     * @param message The message to attach to the action bar
     */
    public void sendActionBar(Player p, String message) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);

        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
    }
}
