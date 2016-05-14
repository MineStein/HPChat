package me.tylergrissom.hpchat.task;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class TimeExtensionTask extends BukkitRunnable {

    private int tick = 1;
    private HPChatPlugin plugin;

    public TimeExtensionTask(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        tick++;

        if (tick >= 10 || tick <= 0) {
            tick = 1;
        } else {
            for (World world : Bukkit.getWorlds()) {
                world.setFullTime(world.getFullTime());
            }
        }
    }
}
