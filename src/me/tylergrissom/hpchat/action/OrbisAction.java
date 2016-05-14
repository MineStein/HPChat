package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import com.elmakers.mine.bukkit.magic.MagicPlugin;
import me.tylergrissom.hpchat.HPChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class OrbisAction extends BaseSpellAction {

    private int duration = 500;
    private int fallingBlocksAmount = 3;

    @Override
    public void prepare(CastContext context, ConfigurationSection parameters) {
        super.prepare(context, parameters);

        this.duration = parameters.getInt("duration");
        this.fallingBlocksAmount = parameters.getInt("fallingBlocksAmount");
    }

    @Override
    public SpellResult perform(CastContext castContext) {
        LivingEntity entity = (LivingEntity) castContext.getTargetEntity();
        Location location = entity.getLocation().clone();

        entity.teleport(location.subtract(0, 3, 0));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 10, false, false));

        Set<Material> transparentMaterials = MagicPlugin.getAPI().getController().getMaterialSet("transparent");
        Block blockBelow = location.subtract(0, 1, 0).getBlock();

        if (transparentMaterials.contains(blockBelow.getType())) {
            return SpellResult.FAIL;
        }

        Bukkit.getScheduler().runTaskLater(HPChatPlugin.staticPlugin, new BukkitRunnable() {
            @Override
            public void run() {
                entity.teleport(location.add(0, 4, 0));
            }
        }, (duration / 1000) * 20);

        return SpellResult.CAST;
    }

    private BlockFace getEntityFacing(LivingEntity entity)
    {

        float y = entity.getLocation().getYaw();
        if( y < 0 ) y += 360;
        y %= 360;
        int i = (int)((y+8) / 22.5);

        if(i == 0) return BlockFace.WEST;
        else if(i == 1) return BlockFace.NORTH_WEST;
        else if(i == 2) return BlockFace.NORTH_WEST;
        else if(i == 3) return BlockFace.NORTH_WEST;
        else if(i == 4) return BlockFace.NORTH;
        else if(i == 5) return BlockFace.NORTH_EAST;
        else if(i == 6) return BlockFace.NORTH_EAST;
        else if(i == 7) return BlockFace.NORTH_EAST;
        else if(i == 8) return BlockFace.EAST;
        else if(i == 9) return BlockFace.SOUTH_EAST;
        else if(i == 10) return BlockFace.SOUTH_EAST;
        else if(i == 11) return BlockFace.SOUTH_EAST;
        else if(i == 12) return BlockFace.SOUTH;
        else if(i == 13) return BlockFace.SOUTH_WEST;
        else if(i == 14) return BlockFace.SOUTH_WEST;
        else if(i == 15) return BlockFace.SOUTH_WEST;

        return BlockFace.WEST;

    }
}
