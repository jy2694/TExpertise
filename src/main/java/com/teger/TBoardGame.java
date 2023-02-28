package com.teger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TBoardGame extends JavaPlugin {

    @Override
    public void onEnable(){
        getCommand("job").setExecutor(new CommandManager());
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);
    }
}
