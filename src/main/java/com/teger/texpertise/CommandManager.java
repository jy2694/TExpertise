package com.teger.texpertise;

import com.teger.texpertise.expertise.ExpertiseType;
import com.teger.texpertise.expertise.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender cs, Command c, String l, String[] a) {
        if(!(cs instanceof Player player)) return true;
        if(a.length > 0){
            PlayerData data = PlayerData.getInstance(player);
            data.addTotalExp(ExpertiseType.valueOf(a[0]), Integer.parseInt(a[1]));
        } else {
            player.openInventory(JobInventory.getInventory(player));
        }
        return false;
    }
}
