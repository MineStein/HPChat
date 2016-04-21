package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import me.tylergrissom.hpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class SpawnBunnyAction extends BaseSpellAction {

    List<Rabbit> rabbitList = new ArrayList<>();

    @Override
    public SpellResult perform(CastContext castContext) {
        Entity target = castContext.getTargetEntity();
        Random random = new Random();

        if (target instanceof Player) {
            for (int i = 0; i < 3; i++) {
                Rabbit rabbit = target.getWorld().spawn(target.getLocation(), Rabbit.class);

                rabbitList.add(rabbit);

                rabbit.setVelocity(new Vector(random.nextDouble() - 0.5, random.nextDouble() / 2.0, random.nextDouble() - 0.5));
            }
        }

        Bukkit.getScheduler().runTaskLater(Main.staticPlugin, new BukkitRunnable() {
            @Override
            public void run() {
                for (Rabbit rabbit : rabbitList) {
                    if (rabbit.isDead()) continue;

                    try {
                        Firework f = rabbit.getWorld().spawn(rabbit.getLocation(), Firework.class);

                        FireworkMeta fm = f.getFireworkMeta();
                        fm.addEffect(FireworkEffect.builder()
                                .flicker(false)
                                .trail(false)
                                .with(FireworkEffect.Type.BALL_LARGE)
                                .withColor(Color.FUCHSIA)
                                .withFade(Color.WHITE)
                                .build());
                        fm.setPower(3);
                        f.setFireworkMeta(fm);

                        Bukkit.getScheduler().runTaskLater(Main.staticPlugin, f::detonate, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    rabbit.remove();
                }

                rabbitList.clear();
            }
        }, 100);

        return SpellResult.CAST;
    }
}
