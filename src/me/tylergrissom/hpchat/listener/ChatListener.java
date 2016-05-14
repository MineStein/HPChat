package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.HPChatPlugin;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ChatListener implements Listener {

    private HPChatPlugin plugin;

    public ChatListener(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    private void notify(String string, Player sender) {
        String revision = string.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (revision.contains(player.getName().replaceAll("[^a-zA-Z]", "").toLowerCase().trim())) {
                if (!player.equals(sender)) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);

                    plugin.getActionBarUtility().sendActionBar(player, "§b" + sender.getName() + " §7mentioned you in chat");
                }
            }
        }
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        Chat chat = plugin.getChat();
        StringBuilder builder = new StringBuilder();
        String[] groupNames = PermissionsEx.getUser(player).getGroupNames();

        if (plugin.getStorage().getSilencio().contains(player.getUniqueId().toString())) {
            player.sendMessage("§dYou are under the effects of a Silencing Charm which should wear off soon.");

            event.setCancelled(true);

            return;
        }

        for (String groupName : groupNames) {
            if (groupName.toLowerCase().contains("gryffindor") ||
                    groupName.toLowerCase().contains("slytherin") ||
                    groupName.toLowerCase().contains("ravenclaw") ||
                    groupName.toLowerCase().contains("hufflepuff")) {
                continue;
            }

            if (groupName.toLowerCase().contains("headofhouse")) {
                builder.append("§").append(plugin.getPlayerUtility().getHouseColor(player)).append("§lHead of House ");

                continue;
            }

            builder.append(chat.getGroupPrefix("", groupName)).append(" ");
        }

        char houseColor = plugin.getPlayerUtility().getHouseColor(player);

        String str = builder.toString().trim();

        event.setFormat(ChatColor.translateAlternateColorCodes('&', str + " &" + houseColor + player.getName() + "&7> " + event.getMessage()));

        notify(event.getMessage(), event.getPlayer());

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        plugin.getLogUtility().logToFile("logs/chat/" + new SimpleDateFormat("dd-MM-yyyy").format(now), "<timestamp='" + format.format(now) + "',ip='" + player.getAddress().toString() + "',uuid='" + player.getUniqueId().toString() + "'> " + player.getName() + ": " + event.getMessage());
    }
}
