package com.teger;

import com.teger.expertise.ExpertiseType;
import com.teger.expertise.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender cs, Command c, String l, String[] a) {
        if(!(cs instanceof Player)) return true;
        Player player = (Player) cs;
        player.openInventory(JobInventory.getInventory(player));
        return false;
    }
}
