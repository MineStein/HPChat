package me.tylergrissom.hpchat.utility;

import me.tylergrissom.hpchat.Main;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class FreezeUtility {

    private Main plugin;

    public FreezeUtility(Main plugin) {
        this.plugin = plugin;
    }

    public void freezeEntity(Entity en){
        net.minecraft.server.v1_9_R1.Entity nmsEn = ((CraftEntity) en).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsEn.c(compound);
        compound.setByte("NoAI", (byte) 1);
        nmsEn.f(compound);
    }

    public void unfreezeEntity(Entity en){
        net.minecraft.server.v1_9_R1.Entity nmsEn = ((CraftEntity) en).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsEn.c(compound);
        compound.setByte("NoAI", (byte) 0);
        nmsEn.f(compound);
    }
}
