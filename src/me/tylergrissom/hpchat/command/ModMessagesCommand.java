package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.HPChatPlugin;
import me.tylergrissom.hpchat.inventory.ModMessagesInventory;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ModMessagesCommand extends CommandBase {

    private HPChatPlugin plugin;

    public ModMessagesCommand(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            final Player player = ((Player) sender);

            if (player.hasPermission("hpchat.modmessages")) {
                ModMessagesInventory inv = new ModMessagesInventory(plugin);

                player.openInventory(inv.getInventory());
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
            } else {
                player.sendMessage("§4§lX §cYou don't have permission.");
            }
        } else {
            sender.sendMessage("§4§lX §cYou must be a player to perform this command.");
        }
    }
}
