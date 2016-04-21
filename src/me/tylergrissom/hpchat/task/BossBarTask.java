package me.tylergrissom.hpchat.task;

import me.tylergrissom.hpchat.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.Random;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class BossBarTask extends BukkitRunnable {

    private Main plugin;

    public BossBarTask(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            BossBar bossBar = BossBarAPI.addBar(player,
                    new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getStringList("boss-bar-messages").get(new Random().nextInt(plugin.getConfig().getStringList("boss-bar-messages").size())))), // Displayed message
                    BossBarAPI.Color.WHITE, // Color of the bar
                    BossBarAPI.Style.NOTCHED_20, // Bar style
                    1.0f, // Progress (0.0 - 1.0)
                    20, 20); // Timeout-interval
        }
    }
}
