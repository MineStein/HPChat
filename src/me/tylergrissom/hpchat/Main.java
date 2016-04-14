package me.tylergrissom.hpchat;

import com.elmakers.mine.bukkit.action.ActionContext;
import com.elmakers.mine.bukkit.action.ActionHandler;
import com.elmakers.mine.bukkit.action.ActionHandlerContext;
import com.elmakers.mine.bukkit.action.CastContext;
import com.elmakers.mine.bukkit.batch.ActionBatch;
import com.elmakers.mine.bukkit.magic.MagicPlugin;
import me.tylergrissom.hpchat.action.PumpkinHeadAction;
import me.tylergrissom.hpchat.command.*;
import me.tylergrissom.hpchat.listener.*;
import me.tylergrissom.hpchat.storage.Storage;
import me.tylergrissom.hpchat.task.BossBarTask;
import me.tylergrissom.hpchat.task.HealthUpdateTask;
import me.tylergrissom.hpchat.task.StorageCleanupTask;
import me.tylergrissom.hpchat.task.TabHFTask;
import me.tylergrissom.hpchat.utility.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.material.Pumpkin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class Main extends JavaPlugin {

    /**
     * TODO
     * - Prefix display limit
     * - Anti-spam
     * - Finish scoreboard
     * - Optional login required (blindness and everything until password on join)
     * - First join quiz (get gender to specify witch or wizard later, etc.)
     * - Player settings (login, etc.)
     * - Channels
     * - Year system
     * - Make tab animation go each individual letter instead of word
     * - Make all instances of player naming show their house color
     * - Nick name support
     * - Start saving player data to config and write API tools for accessing it
     * - Make every day have a new chat log txt file
     * - Modify username overhead to include house color
     * - Mod pre-made phrases
     * - Persona info keep
     * - Persona info shows ranks
     * - Change boss bar color (when 1.9) and boss bar text color based on house
     * - /persona info [target] separate permission
     */

    // WARNING: DON'T USE UNLESS YOU HAVE TO (I.E. spell actions)
    public static Main staticPlugin;

    private Main plugin;
    private Permission permission;
    private Chat chat;
    private Storage storage;
    private LogUtility logUtility;
    private ActionBarUtility actionBarUtility;
    private TitleUtility titleUtility;
    private PlayerUtility playerUtility;
    private FireworkEffectUtility fireworkEffectUtility;
    private FreezeUtility freezeUtility;

    public FreezeUtility getFreezeUtility() {
        return freezeUtility;
    }

    public FireworkEffectUtility getFireworkEffectUtility() {
        return fireworkEffectUtility;
    }

    public PlayerUtility getPlayerUtility() {
        return playerUtility;
    }

    public Storage getStorage() {
        return storage;
    }

    public LogUtility getLogUtility() {
        return logUtility;
    }

    public ActionBarUtility getActionBarUtility() {
        return actionBarUtility;
    }

    public TitleUtility getTitleUtility() {
        return titleUtility;
    }

    public Main getPlugin() {
        return plugin;
    }

    public Permission getPermission() {
        return permission;
    }

    public Chat getChat() {
        return chat;
    }

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    @Override
    public void onEnable() {
        plugin = this;
        staticPlugin = this;
        storage = new Storage(this);
        logUtility = new LogUtility(this);
        actionBarUtility = new ActionBarUtility(this);
        titleUtility = new TitleUtility(this);
        playerUtility = new PlayerUtility(this);
        fireworkEffectUtility = new FireworkEffectUtility();

        getConfig().options().copyDefaults(true);
        saveConfig();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TabHFTask(this), 20, 20);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new StorageCleanupTask(this), 20, 60 * 20);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new HealthUpdateTask(this), 20, 60 * 20);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BossBarTask(this), 20, 5 * 20);

        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ResourcePackListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(this), this);

        getCommand("msg").setExecutor(new MsgCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand(this));
        getCommand("spy").setExecutor(new SpyCommand(this));
        getCommand("persona").setExecutor(new PersonaCommand(this));
        getCommand("hpchat").setExecutor(new HpChatCommand(this));
        getCommand("rules").setExecutor(new RulesCommand(this));

        setupPermissions();
        setupChat();

        MagicPlugin magicPlugin = (MagicPlugin) Bukkit.getServer().getPluginManager().getPlugin("Magic");

        if (magicPlugin == null) {
            Bukkit.getServer().getLogger().severe("Could not hook into Magic. Stopping server...");
            Bukkit.getServer().shutdown();
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
    }
}
