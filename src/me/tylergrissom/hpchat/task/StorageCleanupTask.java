package me.tylergrissom.hpchat.task;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class StorageCleanupTask extends BukkitRunnable {

    private HPChatPlugin plugin;

    public StorageCleanupTask(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (int i = 0; i < plugin.getStorage().getCommandSpy().size(); i++) {
            Player player = Bukkit.getPlayer(plugin.getStorage().getCommandSpy().get(i));
            
            if (player == null) {
                plugin.getStorage().getCommandSpy().remove(i);
            }
        }

        for (int i = 0; i < plugin.getStorage().getPmSpy().size(); i++) {
            Player player = Bukkit.getPlayer(plugin.getStorage().getPmSpy().get(i));

            if (player == null) {
                plugin.getStorage().getPmSpy().remove(i);
            }
        }

        for (int i = 0; i < plugin.getStorage().getSilencio().size(); i++) {
            Player player = Bukkit.getPlayer(plugin.getStorage().getSilencio().get(i));

            if (player == null) {
                plugin.getStorage().getSilencio().remove(i);
            }
        }
    }
}
