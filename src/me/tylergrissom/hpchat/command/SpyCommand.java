package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class SpyCommand extends CommandBase {

    private HPChatPlugin plugin;

    public SpyCommand(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        String[] help = new String[] { "§a§l*** §7/spy §a§l***", "§7/spy pm - enable/disable private message spying", "§7/spy command - enable/disable command spying" };

        if (args.length == 0) {
            sender.sendMessage(help);
        } else {
            String arg = args[0];

            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (arg.equalsIgnoreCase("pm")) {
                    if (player.hasPermission("hpchat.spy.pm")) {
                        if (plugin.getStorage().getPmSpy().contains(player.getUniqueId().toString())) {
                            plugin.getStorage().getPmSpy().remove(player.getUniqueId().toString());

                            player.sendMessage("§7Private message spying §c§ldisabled§7.");
                        } else {
                            plugin.getStorage().getPmSpy().add(player.getUniqueId().toString());

                            player.sendMessage("§7Private message spying §aenabled§7.");
                        }
                    } else {
                        sender.sendMessage("§4§lX §cYou don't have permission");
                    }
                } else if (arg.equalsIgnoreCase("command")) {
                    if (player.hasPermission("hpchat.spy.command")) {
                        if (plugin.getStorage().getCommandSpy().contains(player.getUniqueId().toString())) {
                            plugin.getStorage().getCommandSpy().remove(player.getUniqueId().toString());

                            player.sendMessage("§7Command spying §c§ldisabled§7.");
                        } else {
                            plugin.getStorage().getCommandSpy().add(player.getUniqueId().toString());

                            player.sendMessage("§7Command spying §aenabled§7.");
                        }
                    } else {
                        sender.sendMessage("§4§lX §cYou don't have permission");
                    }
                }
            } else {
                sender.sendMessage("§4§lX §cYou cannot execute this command from console");
            }
        }
    }
}
