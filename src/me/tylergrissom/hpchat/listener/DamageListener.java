package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class DamageListener implements Listener {

    private HPChatPlugin plugin;

    public DamageListener(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPvp(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) {
                event.setDamage(0.0);
            }
        }
    }
}
