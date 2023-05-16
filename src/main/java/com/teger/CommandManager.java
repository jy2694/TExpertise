package com.teger;

import com.teger.expertise.ExpertiseType;
import com.teger.expertise.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender cs, @NotNull Command c, @NotNull String l, @NotNull String[] a) {
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
