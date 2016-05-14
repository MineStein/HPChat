package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.HPChatPlugin;
import me.tylergrissom.hpchat.inventory.MailboxInventory;
import me.tylergrissom.hpchat.inventory.PlayerMenuInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class InteractListener implements Listener {

    private HPChatPlugin plugin;

    public InteractListener(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && player.isSneaking()) {
            PlayerMenuInventory playerMenuInventory = new PlayerMenuInventory(plugin, player);

            playerMenuInventory.open(player);
        }

        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.JUKEBOX)) {
            MailboxInventory mailboxInventory = new MailboxInventory(plugin, player, MailboxInventory.Page.BASE);

            mailboxInventory.open(player);
        }
    }
}
