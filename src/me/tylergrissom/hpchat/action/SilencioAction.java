package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class SilencioAction extends BaseSpellAction {

    private int duration = 30000;

    @Override
    public void prepare(CastContext context, ConfigurationSection parameters) {
        super.prepare(context, parameters);

        this.duration = parameters.getInt("duration");
    }

    @Override
    public SpellResult perform(CastContext castContext) {
        Collection<Entity> entities = castContext.getTargetedEntities();
        Set<Player> players = new HashSet<>();

        for (Entity entity : entities) {
            if (entity instanceof Player) {
                players.add(((Player) entity));
            }
        }

        for (Player player : players) {
            player.sendMessage("Targeted");
        }

        Bukkit.getScheduler().runTaskLater(HPChatPlugin.staticPlugin, new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : players) {
                    player.sendMessage("§dThe Silencing Charm casted upon you has worn off.");

                    players.remove(player);

                    if (HPChatPlugin.staticPlugin.getStorage().getSilencio().contains(player.getUniqueId().toString())) {
                        HPChatPlugin.staticPlugin.getStorage().getSilencio().remove(player.getUniqueId().toString());
                    }
                }
            }
        }, ((duration / 1000) * 20));


        return SpellResult.CAST;
    }
}
