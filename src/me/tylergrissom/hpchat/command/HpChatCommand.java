package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.ReloadCommand;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class HpChatCommand extends CommandBase {

    private Main plugin;

    public HpChatCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        String[] help = new String[] { "§a§l*** §7/hpchat §a§l***", "§7/hpchat reload - reload HPChat configuration files" };

        if (args.length == 0) {
            sender.sendMessage(help);
        } else {
            String arg = args[0];

            if (arg.equalsIgnoreCase("reload")) {
                sender.sendMessage("§7Reloading the HPChat config...");

                try {
                    plugin.reloadConfig();

                    sender.sendMessage("§7Successfully reloaded the HPChat config");
                } catch (Exception exception) {
                    sender.sendMessage("§4§lX §cFailed to reload the HPChat config");
                }
            } else {
                sender.sendMessage("§4§lX §cUnknown sub-command");
            }
        }
    }
}
