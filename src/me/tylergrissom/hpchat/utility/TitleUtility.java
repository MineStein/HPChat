package me.tylergrissom.hpchat.utility;

import me.tylergrissom.hpchat.HPChatPlugin;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R1.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class TitleUtility {

    private HPChatPlugin plugin;

    /**
     * Instantiate a new TitleUtility
     * @param plugin The JavaPlugin instance of HPChat
     */
    public TitleUtility(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Send the player a title
     * @param player The player to send the title to
     * @param title The larger message on the top
     * @param subtitle The smaller message on the bottom
     * @param fadeIn How long it takes for the title to fade in (in ticks)
     * @param stay How long the title will linger for (in ticks)
     * @param fadeOut How long it takes for the title to fade out (in ticks)
     */
    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        CraftPlayer craftplayer = (CraftPlayer) player;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
        IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + subtitle + "'}");
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, fadeIn, stay, fadeOut);
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);

        connection.sendPacket(titlePacket);
        connection.sendPacket(subtitlePacket);
    }

    /**
     * Send the player a tab header and footer
     * @param player The player to send the tab update to
     * @param header The header of the tab
     * @param footer The footer of the tab
     */
    public void sendTabHF(Player player, String header, String footer) {
        CraftPlayer craftplayer = (CraftPlayer) player;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent headerJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', header) + "\"}");
        IChatBaseComponent footerJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', footer) + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field headerField = packet.getClass().getDeclaredField("a");

            headerField.setAccessible(true);
            headerField.set(packet, headerJSON);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");

            footerField.setAccessible(true);
            footerField.set(packet, footerJSON);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception e) {
            e.printStackTrace();
        }

        connection.sendPacket(packet);
    }
}
