package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class CommandListener implements Listener {

    private Main plugin;

    public CommandListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreProcess(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();

        if (message.equals("/pl") || message.equals("/plugins") || message.equals("/plugin")) {
            if (!player.isOp() && !player.hasPermission("hpchat.plugins")) {
                event.setCancelled(true);

                player.sendMessage("§fPlugins (2): §aNice§f, §aTry");
            }
        }

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String commandResult = event.isCancelled() ? "CANCELLED" : "SUCCESS";
        String commandResultColor = event.isCancelled() ? "c" : "§a";

        for (String str : plugin.getStorage().getCommandSpy()) {
            if (str.equals(player.getUniqueId().toString())) {
                Player spyPlayer = Bukkit.getServer().getPlayer(str);

                spyPlayer.sendMessage("§7(§" + commandResultColor + commandResult + "§7) §b" + player.getName() + "§7: §7" + event.getMessage());
            }
        }

        plugin.getLogUtility().logToFile("command-log", "(" + commandResult +  ") <timestamp='" + format.format(now) + "',ip='" + player.getAddress().toString() + "',uuid='" + player.getUniqueId().toString() + "'> " + player.getName() + ": " + event.getMessage());
    }
}
