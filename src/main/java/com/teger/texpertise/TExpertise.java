package com.teger.texpertise;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class TExpertise extends JavaPlugin {

    private PlayerDataConfiguration configuration;

    @Override
    public void onEnable(){
        configuration = new PlayerDataConfiguration();
        try {
            configuration.loadConfiguration();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getCommand("숙련도").setExecutor(new CommandManager());
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);
        new PotionTimer().runTaskTimer(this, 20L, 20L);
    }

    @Override
    public void onDisable(){
        try {
            configuration.saveConfiguration();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
