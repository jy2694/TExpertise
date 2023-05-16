package com.teger;

import com.teger.expertise.ExpertiseType;
import com.teger.expertise.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataConfiguration {

    private static final File folder = new File("plugins/TExpertise");
    private static final File file = new File("plugins/TExpertise/players.yml");

    public FileConfiguration dataConfig;

    public void loadConfiguration() throws IOException {
        if(!folder.exists()) folder.mkdirs();
        if(!file.exists()) file.createNewFile();
        dataConfig = YamlConfiguration.loadConfiguration(file);
        for(String rawUniqueId : dataConfig.getKeys(false)){
            UUID uuid = UUID.fromString(rawUniqueId);
            PlayerData data = PlayerData.getInstance(uuid);
            for(ExpertiseType type : ExpertiseType.values()){
                long exp = dataConfig.getLong(rawUniqueId + "." + type.toString());
                data.addTotalExp(type, exp);
            }
        }
    }

    public void saveConfiguration() throws IOException {
        if(!folder.exists()) folder.mkdirs();
        for(PlayerData data : PlayerData.getPlayerDataList()){
            for(ExpertiseType type : ExpertiseType.values()){
                dataConfig.set(data.getUniqueId() + "." + type.toString(), data.getTotalExp(type));
            }
        }
        dataConfig.save(file);
    }
}
