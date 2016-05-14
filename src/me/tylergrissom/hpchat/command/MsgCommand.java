package me.tylergrissom.hpchat.command;

import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class MsgCommand extends CommandBase {

    private HPChatPlugin plugin;

    public MsgCommand(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        String usage = "§7Usage: §b/msg <player> <message...>";

        if (args.length <= 1) {
            sender.sendMessage(usage);
        } else {
            if (Bukkit.getServer().getPlayer(args[0]) == null) {
                sender.sendMessage("§4§lX §cThat player is offline.");
            } else if (sender instanceof Player && Bukkit.getServer().getPlayer(args[0]) == sender) {
                sender.sendMessage("§4§lX §cYou can't message yourself.");
            } else {
                StringBuilder builder = new StringBuilder();

                for (int i = 1; i < args.length; i++) {
                    builder.append(args[i]).append(" ");
                }

                String message = builder.toString().trim();
                String senderName = (sender instanceof Player) ? sender.getName() : "Console";
                Player target = Bukkit.getServer().getPlayer(args[0]);

                target.sendMessage("§b§l/msg: §7" + senderName + " §3§l§m--§r§3§l> §7You§3§l: §e" + message);
                sender.sendMessage("§b§l/msg: §7You §3§l§m--§r§3§l> §7" + target.getName() + "§3§l: §e" + message);

                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                if (sender instanceof Player) {
                    final Player player = (Player) sender;

                    for (String str : plugin.getStorage().getPmSpy()) {
                        if (str.equals(player.getUniqueId().toString())) {
                            Player spyPlayer = Bukkit.getServer().getPlayer(str);

                            spyPlayer.sendMessage("§b§l/msg: §7" + senderName + " §3§l§m--§r§3§l> §7" + target.getName() + "§3§l: §e" + message);
                        }
                    }

                    plugin.getLogUtility().logToFile("logs/pm/" + new SimpleDateFormat("dd-MM-yyyy").format(now), "<timestamp='" + format.format(now) + "',ip='" + player.getAddress().toString() + "',uuid='" + player.getUniqueId().toString() + "'> " + player.getName() + " --> " + target.getName() + ": " + message);
                } else {
                    plugin.getLogUtility().logToFile("logs/pm/" + new SimpleDateFormat("dd-MM-yyyy").format(now), "<timestamp='" + format.format(now) + "'> Console --> " + target.getName() + ": " + message);
                }
            }
        }
    }
}
