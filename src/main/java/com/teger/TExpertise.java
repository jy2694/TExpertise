package com.teger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TExpertise extends JavaPlugin {

    @Override
    public void onEnable(){
        getCommand("job").setExecutor(new CommandManager());
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);
        new PotionTimer().runTaskTimer(this, 20L, 20L);
    }
}
