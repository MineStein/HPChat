package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class BubbleHeadAction extends BaseSpellAction {

    private int duration;

    @Override
    public void prepare(CastContext context, ConfigurationSection parameters) {
        super.prepare(context, parameters);

        this.duration = parameters.getInt("duration");
    }

    @Override
    public SpellResult perform(CastContext castContext) {
        Entity entity = castContext.getTargetEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;
            ItemStack oldHelmet = player.getInventory().getHelmet();

            player.getInventory().setHelmet(new ItemStack(Material.GLASS));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, (((duration / 1000) * 20) - 5), 1, false, false));

            Bukkit.getScheduler().runTaskLater(Main.staticPlugin, new BukkitRunnable() {
                @Override
                public void run() {
                    player.getInventory().setHelmet(oldHelmet);

                    for (PotionEffect potionEffect : player.getActivePotionEffects()) {
                        if (potionEffect.getType().equals(PotionEffectType.WATER_BREATHING)) player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    }
                }
            }, (((duration / 1000) * 20) - 5));
        }

        return SpellResult.CAST;
    }
}
