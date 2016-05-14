package me.tylergrissom.hpchat.inventory;

import me.tylergrissom.hpchat.HPChatPlugin;
import me.tylergrissom.hpchat.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class PlayerMenuInventory extends CustomInventory {

    public PlayerMenuInventory(HPChatPlugin plugin, Player player) {
        super(plugin, player);
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 18, "Player Menu");

        // TODO Fix isModerator
        // Make barrier/other item depending on isModerator

        boolean isModerator = true;

        ItemBuilder onlineStaff = new ItemBuilder()
                .type(Material.PAPER)
                .amount(1)
                .name("§3§lStaff")
                .lore("§7View online staff members.", "", "§6§lClick §7to view");

        ItemBuilder moderatorSubTools = new ItemBuilder()
                .type(Material.BARRIER)
                .amount(1)
                .name("§3§lModerator Tools")
                .lore("§7Utilize the tool set for moderators.", "", "§6§lClick §7to view");

        inventory.setItem(9, onlineStaff.build());
        inventory.setItem(1, moderatorSubTools.build());

        return inventory;
    }
}
