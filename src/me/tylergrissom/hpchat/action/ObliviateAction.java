package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class ObliviateAction extends BaseSpellAction {

    private int messageAmount = 100;

    @Override
    public void prepare(CastContext context, ConfigurationSection parameters) {
        super.prepare(context, parameters);

        this.messageAmount = parameters.getInt("messageAmount");
    }

    @Override
    public SpellResult perform(CastContext castContext) {
        if (castContext.getTargetEntity() instanceof Player) {
            Player player = (Player) castContext.getTargetEntity();

            for (int i = 0; i < messageAmount; i++) {
                player.sendMessage("");
            }
        }

        return SpellResult.CAST;
    }
}
