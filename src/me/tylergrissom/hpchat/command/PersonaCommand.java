package me.tylergrissom.hpchat.command;

import com.elmakers.mine.bukkit.api.wand.Wand;
import com.elmakers.mine.bukkit.magic.Mage;
import com.elmakers.mine.bukkit.magic.MagicPlugin;
import me.tylergrissom.hpchat.Main;
import me.tylergrissom.hpchat.scoreboard.SimpleScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class PersonaCommand extends CommandBase {

    private Main plugin;

    public PersonaCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        String[] help = new String[] { "§a§l*** §7/persona §a§l", "§7/persona info [target] - displays player persona info" };

        if (args.length == 0) {
            sender.sendMessage(help);
        } else {
            String arg = args[0];

            if (args.length == 1) {
                if (arg.equalsIgnoreCase("info")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;

                        final char houseColor = plugin.getPlayerUtility().getHouseColor(player);
                        final SimpleScoreboard scoreboard = new SimpleScoreboard("§" + houseColor + "§l" + player.getName());

                        if (MagicPlugin.getAPI().isWand(player.getItemInHand())) {
                            player.sendMessage("§7Fetching information...");

                            final Wand wand = MagicPlugin.getAPI().getWand(player.getItemInHand());

                            scoreboard.blankLine();
                            scoreboard.add("§7House");
                            scoreboard.add("  " + plugin.getPlayerUtility().getHouse(player, true));
                            scoreboard.blankLine();
                            scoreboard.add("§7Year");
                            scoreboard.add("  §" + houseColor + "§l1st year");

                            if (wand != null) {
                                scoreboard.blankLine();
                                scoreboard.add("§7Spells learnt");
                                scoreboard.add("  §" + houseColor + "§l" + wand.getSpells().size());
                            }

                            scoreboard.build();
                            scoreboard.send(player);

                            Bukkit.getScheduler().runTaskLater(plugin, new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                                }
                            }, 100);
                        } else {
                            sender.sendMessage("§4§lX §cYou must have your wand in your hand to view your persona info");
                        }
                    } else {
                        sender.sendMessage("§4§lX §cYou cannot execute this command from console");
                    }
                } else {
                    sender.sendMessage("§4§lX §cUnknown sub-command");
                }
            } else {
                if (arg.equalsIgnoreCase("info")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    
                    if (target != null) {
                        final char houseColor = plugin.getPlayerUtility().getHouseColor(target);
                        final SimpleScoreboard scoreboard = new SimpleScoreboard("§" + houseColor + "§l" + target.getName());

                        target.sendMessage("§7Fetching information...");

                        ItemStack wandItem = plugin.getPlayerUtility().findWand(target);

                        if (wandItem != null) {
                            Wand wand = MagicPlugin.getAPI().getWand(wandItem);

                            scoreboard.blankLine();
                            scoreboard.add("§7House");
                            scoreboard.add("  " + plugin.getPlayerUtility().getHouse(target, true));
                            scoreboard.blankLine();
                            scoreboard.add("§7Year");
                            scoreboard.add("  §" + houseColor + "§l1st year");
                            scoreboard.blankLine();
                            scoreboard.add("§7Spells learnt");
                            scoreboard.add("  §" + houseColor + "§l" + wand.getSpells().size());

                            scoreboard.build();
                            scoreboard.send(target);

                            Bukkit.getScheduler().runTaskLater(plugin, new BukkitRunnable() {
                                @Override
                                public void run() {
                                    target.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                                }
                            }, 100);
                        } else {
                            sender.sendMessage("§4§lX §cThat player doesn't have a wand");
                        }
                    }
                }
            }
        }
    }
}
