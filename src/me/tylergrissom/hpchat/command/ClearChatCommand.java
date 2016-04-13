package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ClearChatCommand extends CommandBase {

    private Main plugin;

    public ClearChatCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        String[] help = new String[] { "§a§l*** §7/clearchat §a§l***", "§7/clearchat self - clears your chat", "§7/clearchat all - clears everyone's chat" };

        if (args.length == 0) {
            sender.sendMessage(help);
        } else {
            String arg = args[0];

            if (arg.equalsIgnoreCase("self")) {
                if (sender.hasPermission("hpchat.clearchat.self")) {
                    for (int i = 0; i < 100; i++) {
                        sender.sendMessage("");
                    }
                } else {
                    sender.sendMessage("§4§lX §cYou don't have permission");
                }
            } else if (arg.equalsIgnoreCase("all")) {
                if (sender.hasPermission("hpchat.clearchat.all")) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        for (int i = 0; i < 100; i++) {
                            player.sendMessage("");
                        }
                    }
                } else {
                    sender.sendMessage("§4§lX §cYou don't have permission");
                }
            } else {
                sender.sendMessage("§4§lX §cUnknown sub-command");
            }
        }
    }
}
