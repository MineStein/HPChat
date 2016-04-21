package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ChannelsCommand extends CommandBase {

    Main plugin;

    public ChannelsCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        String[] help = new String[] { "§a§l*** §7/channels §a§l***" };

        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(help);
            }
        } else {
            sender.sendMessage("§4§lX §cConsole cannot execute this command");
        }
    }
}
