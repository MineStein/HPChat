package me.tylergrissom.hpchat.task;

import me.tylergrissom.hpchat.HPChatPlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.Random;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class BossBarTask extends BukkitRunnable {

    private HPChatPlugin plugin;

    public BossBarTask(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            BossBarAPI.Color color = BossBarAPI.Color.WHITE;
            String house = plugin.getPlayerUtility().getHouse(player, false);

            if (house.equalsIgnoreCase("gryffindor")) {
                color = BossBarAPI.Color.RED;
            } else if (house.equalsIgnoreCase("slytherin")) {
                color = BossBarAPI.Color.GREEN;
            }  else if (house.equalsIgnoreCase("hufflepuff")) {
                color = BossBarAPI.Color.YELLOW;
            }  else if (house.equalsIgnoreCase("ravenclaw")) {
                color = BossBarAPI.Color.BLUE;
            }

            ConfigurationSection section = plugin.getConfig().getConfigurationSection("houseSettings." + house);
            char primaryColor;
            char secondaryColor;

            if (section == null) {
                primaryColor = '7';
                secondaryColor = 'b';
            } else {
                primaryColor = section.getString("primaryColor").charAt(0);
                secondaryColor = section.getString("secondaryColor").charAt(0);
            }

            String str = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getStringList("boss-bar-messages").get(new Random().nextInt(plugin.getConfig().getStringList("boss-bar-messages").size()))).replace("%pColor", "§" + primaryColor).replace("%sColor", "§" + secondaryColor);

            BossBarAPI.removeAllBars(player);
            BossBarAPI.addBar(player,
                    new TextComponent(str),
                    color, // Color of the bar
                    BossBarAPI.Style.NOTCHED_20, // Bar style
                    1.0f, // Progress (0.0 - 1.0)
                    20, 20); // Timeout-interval
        }
    }
}
