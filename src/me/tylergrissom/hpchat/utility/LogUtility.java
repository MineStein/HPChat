package me.tylergrissom.hpchat.utility;

import me.tylergrissom.hpchat.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class LogUtility {

    private Main plugin;

    public LogUtility(Main plugin) {
        this.plugin = plugin;
    }

    public void logToFile(String file, String message)

    {

        try
        {
            File dataFolder = plugin.getDataFolder();
            if(!dataFolder.exists())
            {
                dataFolder.mkdir();
            }

            File saveTo = new File(plugin.getDataFolder(), file + ".txt");
            if (!saveTo.exists())
            {
                saveTo.createNewFile();
            }


            FileWriter fw = new FileWriter(saveTo, true);

            PrintWriter pw = new PrintWriter(fw);

            pw.println(message);

            pw.flush();

            pw.close();

        } catch (IOException e)
        {

            e.printStackTrace();

        }

    }
}
