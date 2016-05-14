package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ResourcePackListener implements Listener {

    private HPChatPlugin plugin;

    public ResourcePackListener(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onResourcepackStatusEvent(final PlayerResourcePackStatusEvent event){
        final Player player = event.getPlayer();

        if (event.getStatus().equals(PlayerResourcePackStatusEvent.Status.DECLINED)) {
            player.sendMessage("You must accept the resource pack.");
        }
    }
}
