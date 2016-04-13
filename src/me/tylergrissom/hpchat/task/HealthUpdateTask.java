package me.tylergrissom.hpchat.task;

import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class HealthUpdateTask extends BukkitRunnable {

    private Main plugin;

    public HealthUpdateTask(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("showhealth", "health");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName(" / 20");

        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
            online.setHealth(online.getHealth()); //Update their health
        }
    }
}
