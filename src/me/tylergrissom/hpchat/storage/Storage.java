package me.tylergrissom.hpchat.storage;

import me.tylergrissom.hpchat.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class Storage {

    // REMEMBER always update StorageCleanupTask when adding/removing/changing collections and other objects contained in here

    private Main plugin;
    private List<String> commandSpy;
    private List<String> pmSpy;
    private List<String> silencio;

    public Storage(Main plugin) {
        this.plugin = plugin;
        this.commandSpy = new ArrayList<>();
        this.pmSpy = new ArrayList<>();
        this.silencio = new ArrayList<>();
    }

    public List<String> getCommandSpy() {
        return commandSpy;
    }

    public List<String> getPmSpy() {
        return pmSpy;
    }

    public List<String> getSilencio() {
        return silencio;
    }
}
