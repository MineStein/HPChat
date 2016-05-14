package me.tylergrissom.hpchat.utility;

import me.tylergrissom.hpchat.HPChatPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright (c) 2013-2016 Tyler Grissom
 */
public class LogUtility {

    private HPChatPlugin plugin;

    /**
     * Instantiate a new LogUtility
     * @param plugin The JavaPlugin instance of HPChat
     */
    public LogUtility(HPChatPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Logs the message to the specified file path
     * @param file The file path to log the message to
     * @param message The message to log
     */
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
