package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class PumpkinHeadAction extends BaseSpellAction {

    @Override
    public SpellResult perform(CastContext castContext) {
        Entity entity = castContext.getTargetEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;
            ItemStack currentHelmet = player.getInventory().getHelmet();

            player.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));

            Bukkit.getScheduler().runTaskLater(Main.staticPlugin, new BukkitRunnable() {
                @Override
                public void run() {
                    player.getInventory().setHelmet(currentHelmet);
                }
            }, 60);
        } else {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;

                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 1, false, false));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 100, false, false));

                Block currentBlock = livingEntity.getLocation().getBlock();
                Material oldType = currentBlock.getType();
                byte oldData = currentBlock.getData();

                currentBlock.setType(Material.PUMPKIN);

                Bukkit.getScheduler().runTaskLater(Main.staticPlugin, new BukkitRunnable() {
                    @Override
                    public void run() {
                        livingEntity.teleport(currentBlock.getLocation());
                        currentBlock.setType(oldType);
                        currentBlock.setData(oldData);
                    }
                }, 55);
            }
        }

        return SpellResult.CAST;
    }

    @Override
    public boolean requiresTargetEntity() {
        return true;
    }

    @Override
    public boolean requiresBuildPermission() {
        return true;
    }
}
