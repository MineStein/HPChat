package me.tylergrissom.hpchat.inventory;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ModMessagesInventory {

    private HPChatPlugin plugin;

    public ModMessagesInventory(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack getItemForMessage(String msg, String text) {
        ItemStack itemStack = new ItemStack(Material.PAPER); {
            ItemMeta meta = itemStack.getItemMeta();

            meta.setDisplayName("§7" + msg);

            List<String> lore = new ArrayList<>();

            lore.add("§6§lText:");
            lore.add("  §7" + text);

            meta.setLore(lore);

            itemStack.setItemMeta(meta);
        }

        return itemStack;
    }

    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Moderator Messages");

        inventory.setItem(0, getItemForMessage("Website", "Check out our website at http://godricsmc.tyler-g.net"));

        return inventory;
    }
}
