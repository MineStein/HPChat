package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class NPCCommand extends CommandBase {

    private Main plugin;

    public NPCCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        String[] help = new String[] { "§a§l*** §7/npc §a§l***", "§7/npc create <command> <args...>" };
    }
}
