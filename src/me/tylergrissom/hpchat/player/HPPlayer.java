package me.tylergrissom.hpchat.player;

import com.elmakers.mine.bukkit.api.wand.Wand;
import com.elmakers.mine.bukkit.magic.MagicPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class HPPlayer {

    private Player player;

    /**
     * Instantiate a new HPPlayer
     * @param player The player to base the HPPlayer off of
     */
    public HPPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the player's display name
     * @param formatted Whether or not the display name included formatting, color, and ranks
     * @return The player's display name
     */
    public String getDisplayName(boolean formatted) {
        if (formatted) {

        } else {

        }

        return null;
    }

    /**
     * Searches the player's inventory for any wands and returns the wand
     * @return The player's primary wand
     */
    public Wand getWand() {
        for (ItemStack item : player.getInventory().getContents()) {
            if (MagicPlugin.getAPI().isWand(item)) return MagicPlugin.getAPI().getWand(item);
        }

        return null;
    }
}
