package me.tylergrissom.hpchat.storage;

import me.tylergrissom.hpchat.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class Storage {

    // REMEMBER always update StorageCleanupTask when adding/removing/changing collections and other objects contained in here

    private Main plugin;
    private List<String> commandSpy;
    private List<String> pmSpy;

    public Storage(Main plugin) {
        this.plugin = plugin;
        this.commandSpy = new ArrayList<>();
        this.pmSpy = new ArrayList<>();
    }

    public List<String> getCommandSpy() {
        return commandSpy;
    }

    public List<String> getPmSpy() {
        return pmSpy;
    }
}
