package me.tylergrissom.hpchat.inventory;

import me.tylergrissom.hpchat.HPChatPlugin;
import me.tylergrissom.hpchat.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class MailboxInventory extends CustomInventory {

    public enum Page {

        BASE, SEND, CHECK
    }

    private HPChatPlugin plugin;
    private Player player;
    private Page page;

    public MailboxInventory(HPChatPlugin plugin, Player player, Page page) {
        super(plugin, player);

        this.plugin = plugin;
        this.player = player;
        this.page = page;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory;

        if (page.equals(Page.BASE)) {
            inventory = Bukkit.createInventory(null, 9, "Mailbox");

            ItemBuilder sendMail = new ItemBuilder()
                    .type(Material.DIAMOND)
                    .amount(1)
                    .name("§3§lSend Mail")
                    .lore("§7Send mail to another player.", "", "§6§lClick §7to prepare mail");

            ItemBuilder checkMail = new ItemBuilder()
                    .type(Material.PAPER)
                    .amount(1)
                    .name("§3§lCheck Mail")
                    .lore("§7Check your mail.", "", "§6§lClick §7to view");

            inventory.setItem(3, sendMail.build());
            inventory.setItem(5, checkMail.build());
        } else if (page.equals(Page.SEND)) {
            inventory = Bukkit.createInventory(null, 54, "Mailbox> Send Mail");

            ItemBuilder sendMail = new ItemBuilder()
                    .type(Material.FEATHER)
                    .amount(1)
                    .name("§3§lSend Items")
                    .lore("§7Send the items placed into this mailbox.", "", "§6§lClick §7to send");

            ItemBuilder attachMessage = new ItemBuilder()
                    .type(Material.PAPER)
                    .amount(1)
                    .name("§3§lAttach Message")
                    .lore("§7Attach a message to these items.", "", "§6§lClick §7to set");

            ItemBuilder cancel = new ItemBuilder()
                    .type(Material.BARRIER)
                    .amount(1)
                    .name("§3§lCancel")
                    .lore("§7§lCancel this mail.", "", "§6§lClick §7to cancel");

            inventory.setItem(51, sendMail.build());
            inventory.setItem(52, attachMessage.build());
            inventory.setItem(53, cancel.build());
        } else {
            inventory = Bukkit.createInventory(null, 54, "Mailbox> Check Mail");
        }

        return inventory;
    }
}
