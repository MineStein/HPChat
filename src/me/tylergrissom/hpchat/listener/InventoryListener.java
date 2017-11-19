package me.tylergrissom.hpchat.listener;

import me.tylergrissom.hpchat.HPChatPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class InventoryListener implements Listener {

    private HPChatPlugin plugin;

    public InventoryListener(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    private boolean isItem(ItemStack itemStack, Material checkMaterial, String checkName) {
        return itemStack.getType().equals(checkMaterial) && ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()).equals(ChatColor.stripColor(checkName));
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals("Player Menu")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null && event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            ItemStack item = event.getCurrentItem();

            if (isItem(item, Material.PAPER, "Staff")) {
                // TODO This code
            }
        } else if (event.getInventory().getName().equals("Moderator Messages")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null && event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            ItemStack item = event.getCurrentItem();

            if (isItem(item, Material.PAPER, "Website")) {
                player.chat("§7§lCheck out our website at http://godricsmc.tyler-g.net");
            }
        }
    }
}
