package me.tylergrissom.hpchat.action;

import com.elmakers.mine.bukkit.action.BaseSpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class BabblingCurseAction extends BaseSpellAction {

    private List<String> messages = new ArrayList<>();

    @Override
    public void prepare(CastContext context, ConfigurationSection parameters) {
        super.prepare(context, parameters);

        this.messages = parameters.getStringList("messages");
    }

    @Override
    public SpellResult perform(CastContext castContext) {
        Player player = (Player) castContext.getTargetEntity();

        player.chat(messages.get(new Random().nextInt(messages.size())));

        return SpellResult.CAST;
    }

    @Override
    public boolean requiresTargetEntity() {
        return true;
    }
}
