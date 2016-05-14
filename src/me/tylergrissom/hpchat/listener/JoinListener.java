package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class JoinListener implements Listener {

    private HPChatPlugin plugin;

    public JoinListener(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("players");

        if (section.getConfigurationSection(player.getUniqueId().toString()) == null) {
            ConfigurationSection newSection = section.createSection(player.getUniqueId().toString());

            newSection.set("lastKnownName", player.getName());
            newSection.set("sessionsPlayed", 1);
        } else {
            section = section.getConfigurationSection(player.getUniqueId().toString());

            section.set("sessionsPlayed", section.getInt("sessionsPlayed") + 1);

            if (!Objects.equals(section.getString("lastKnownName"), player.getName())) {
                section.set("lastKnownName", player.getName());
            }
        }

        plugin.saveConfig();
    }
}
