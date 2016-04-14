package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class RulesCommand extends CommandBase {

    private Main plugin;

    public RulesCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        List<String> newRules = plugin.getConfig().getStringList("rules").stream().map(str -> "§7" + ChatColor.translateAlternateColorCodes('&', str)).collect(Collectors.toList());

        sender.sendMessage("§3§l*** §7Rules §3§l***");

        for (String str : newRules) {
            sender.sendMessage("  §7- " + str);
        }
    }
}
