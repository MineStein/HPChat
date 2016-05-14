package me.tylergrissom.hpchat.utility;

import me.tylergrissom.hpchat.HPChatPlugin;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class FreezeUtility {

    private HPChatPlugin plugin;

    /**
     * Instantiate a new FreezeUtility
     * @param plugin The JavaPlugin instance of HPChat
     */
    public FreezeUtility(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Freezes an entity
     * @param en The entity to freeze
     */
    public void freezeEntity(Entity en){
        if (en instanceof LivingEntity) {
            ((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 20, false, false));
            ((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, -20, false, false));
        }
    }

    /**
     * Un-freezes an entity
     * @param en The entity to un-freeze
     */
    public void unfreezeEntity(Entity en){
        if (en instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) en;

            for (PotionEffect effect : entity.getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.SLOW) || effect.getType().equals(PotionEffectType.JUMP)) entity.removePotionEffect(effect.getType());
            }
        }
    }
}
