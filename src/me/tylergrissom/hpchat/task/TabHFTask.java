package me.tylergrissom.hpchat.task;

import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class TabHFTask extends BukkitRunnable {

    private Main plugin;
    private int word = 0;

    public TabHFTask(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (word == 0) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §" + plugin.getPlayerUtility().getHouseColor(player) + "§lThe §f§lWizarding §f§lWorld §f§lof §f§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word++;
            } else if (word == 1) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §f§lThe §"  + plugin.getPlayerUtility().getHouseColor(player) +  "§lWizarding §f§lWorld §f§lof §f§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word++;
            } else if (word == 2) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §f§lThe §f§lWizarding §"  + plugin.getPlayerUtility().getHouseColor(player) +  "§lWorld §f§lof §f§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word++;
            } else if (word == 3) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §f§lThe §f§lWizarding §f§lWorld §"  + plugin.getPlayerUtility().getHouseColor(player) +  "§lof §f§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word++;
            } else if (word == 4) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §f§lThe §f§lWizarding §f§lWorld §f§lof §"  + plugin.getPlayerUtility().getHouseColor(player) +  "§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word++;
            } else if (word == 5) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §f§lThe §f§lWizarding §f§lWorld §"  + plugin.getPlayerUtility().getHouseColor(player) +  "§lof §f§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word++;
            } else if (word == 6) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §f§lThe §f§lWizarding §"  + plugin.getPlayerUtility().getHouseColor(player) +  "§lWorld §f§lof §f§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word++;
            } else if (word == 7) {
                plugin.getTitleUtility().sendTabHF(player, "§d§l§k::§r §f§lThe §"  + plugin.getPlayerUtility().getHouseColor(player) +  "§lWizarding §f§lWorld §f§lof §f§lMinecraft §d§l§k::", "§7There are §" + plugin.getPlayerUtility().getHouseColor(player) + "§l" + Bukkit.getServer().getOnlinePlayers().size() + " §7witches and wizards online\n§7Ping: §" + plugin.getPlayerUtility().getHouseColor(player) + ((CraftPlayer) player).getHandle().ping);

                word = 0;
            } else {
                word = 0;
            }

            player.setPlayerListName("§" + plugin.getPlayerUtility().getHouseColor(player) + player.getName());
        }
    }
}
