package me.tylergrissom.hpchat.inventory;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public abstract class CustomInventory {

    private HPChatPlugin plugin;
    private Player player;

    public CustomInventory(HPChatPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void open(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1F, 1F);

        player.openInventory(getInventory());
    }

    abstract Inventory getInventory();
}
