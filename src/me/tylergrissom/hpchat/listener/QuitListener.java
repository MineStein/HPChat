package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class QuitListener implements Listener {

    private Main plugin;

    public QuitListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (plugin.getStorage().getCommandSpy().contains(player.getUniqueId().toString())) {
            plugin.getStorage().getCommandSpy().remove(player.getUniqueId().toString());
        }

        if (plugin.getStorage().getPmSpy().contains(player.getUniqueId().toString())) {
            plugin.getStorage().getPmSpy().remove(player.getUniqueId().toString());
        }
    }
}
