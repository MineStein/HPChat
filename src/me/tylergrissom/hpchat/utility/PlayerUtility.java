package me.tylergrissom.hpchat.utility;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.magic.MagicPlugin;
import me.tylergrissom.hpchat.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class PlayerUtility {

    private Main plugin;

    public PlayerUtility(Main plugin) {
        this.plugin = plugin;
    }

    public String getHouse(Player player, boolean formatted) {
        char houseColor = getHouseColor(player);

        if (houseColor == '4') {
            if (formatted) {
                return "§4§lGryffindor";
            } else {
                return "gryffindor";
            }
        } else if (houseColor == '2') {
            if (formatted) {
                return "§2§lSlytherin";
            } else {
                return "slytherin";
            }
        } else if (houseColor == '9') {
            if (formatted) {
                return "§9§lRavenclaw";
            } else {
                return "ravenclaw";
            }
        } else if (houseColor == 'e') {
            if (formatted) {
                return "§e§lHufflepuff";
            } else {
                return "hufflepuff";
            }
        }

        if (formatted) {
            return "§f§lMuggle";
        } else {
            return "muggle";
        }
    }

    public char getHouseColor(Player player) {
        String[] groupNames = PermissionsEx.getUser(player).getGroupNames();
        char houseColor = 'f';

        for (String groupName : groupNames) {
            if (groupName.toLowerCase().contains("gryffindor")) {
                houseColor = '4';
            } else if (groupName.toLowerCase().contains("slytherin")) {
                houseColor = '2';
            } else if (groupName.toLowerCase().contains("ravenclaw")) {
                houseColor = '9';
            } else if (groupName.toLowerCase().contains("hufflepuff")) {
                houseColor = 'e';
            }
        }

        if ((houseColor != '4') && (houseColor != '2') && (houseColor != '9') && (houseColor != 'e')) {
            houseColor = 'f';
        }

        return houseColor;
    }

    public ItemStack findWand(Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (MagicPlugin.getAPI().isWand(itemStack)) {
                return itemStack;
            }
        }

        MagicPlugin.getAPI().getWandKeys();

        return null;
    }
}
