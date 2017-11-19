package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class PingListener implements Listener {

    private HPChatPlugin plugin;

    public PingListener(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(final ServerListPingEvent event) {
        String motd = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ping-message"));

        event.setMotd(motd);
    }
}
