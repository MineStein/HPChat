package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class RepariforsAction extends BaseSpellAction {

    @Override
    public SpellResult perform(CastContext castContext) {
        Entity entity = castContext.getTargetEntity();

        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;

            if (livingEntity instanceof Player) {
                ((Player) livingEntity).setFoodLevel(20);
            }

            livingEntity.setFireTicks(0);

            livingEntity.getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getType().getId() == 2 ||
                    potionEffect.getType().getId() == 4 ||
                    potionEffect.getType().getId() == 7 ||
                    potionEffect.getType().getId() == 9 ||
                    potionEffect.getType().getId() == 15 ||
                    potionEffect.getType().getId() == 17 ||
                    potionEffect.getType().getId() == 18 ||
                    potionEffect.getType().getId() == 19 ||
                    potionEffect.getType().getId() == 20 ||
                    potionEffect.getType().getId() == 27).forEach(potionEffect -> {
                livingEntity.removePotionEffect(potionEffect.getType());
            });
        }

        return SpellResult.CAST;
    }
}
