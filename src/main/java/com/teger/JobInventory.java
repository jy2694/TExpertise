package com.teger;

import com.teger.expertise.ExpertiseType;
import com.teger.expertise.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobInventory {

    private static HashMap<Player, Inventory> invs = new HashMap<>();

    public static Inventory getOpenedInventory(Player player){
        return invs.get(player);
    }

    public static Inventory getInventory(Player player){

        PlayerData data = PlayerData.getInstance(player);

        int mineLevel = data.getLevel(ExpertiseType.MINE),
                craftLevel = data.getLevel(ExpertiseType.CRAFT),
                tillageLevel = data.getLevel(ExpertiseType.TILLAGE),
                woodLevel = data.getLevel(ExpertiseType.WOODCUTTING),
                fishLevel = data.getLevel(ExpertiseType.FISHERMAN);

        double mineExp = data.getExp(ExpertiseType.MINE),
                craftExp = data.getExp(ExpertiseType.CRAFT),
                tillageExp = data.getExp(ExpertiseType.TILLAGE),
                woodExp = data.getExp(ExpertiseType.WOODCUTTING),
                fishExp = data.getExp(ExpertiseType.FISHERMAN);

        double mineReqExp = 60 * Math.pow(1.3, mineLevel-1),
                craftReqExp = 60 * Math.pow(1.3, craftLevel-1),
                tillageReqExp = 60 * Math.pow(1.3, tillageLevel-1),
                woodReqExp = 60 * Math.pow(1.3, woodLevel-1),
                fishReqExp = 60 * Math.pow(1.3, fishLevel-1);

        double mineExpPercent = mineLevel >= 30 ? 0 : ((double)((int)((mineExp / mineReqExp) * 1000.0))) / 10.0,
                craftExpPercent = craftLevel >= 30 ? 0 : ((double)((int)((craftExp / craftReqExp) * 1000.0))) / 10.0,
                tillageExpPercent = tillageLevel >= 30 ? 0 : ((double)((int)((tillageExp / tillageReqExp) * 1000.0))) / 10.0,
                woodExpPercent = woodLevel >= 30 ? 0 : ((double)((int)((woodExp / woodReqExp) * 1000.0))) / 10.0,
                fishExpPercent = fishLevel >= 30 ? 0 : ((double)((int)((fishExp / fishReqExp) * 1000.0))) / 10.0;

        ItemStack mine = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack craft = new ItemStack(Material.CRAFTING_TABLE);
        ItemStack tillage = new ItemStack(Material.WHEAT);
        ItemStack wood = new ItemStack(Material.IRON_AXE);
        ItemStack fish = new ItemStack(Material.TROPICAL_FISH);

        ItemMeta mine_meta = mine.getItemMeta();
        ItemMeta craft_meta = craft.getItemMeta();
        ItemMeta tillage_meta = tillage.getItemMeta();
        ItemMeta wood_meta = wood.getItemMeta();
        ItemMeta fish_meta = fish.getItemMeta();

        mine_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "MINE") + " Lv." + mineLevel);
        craft_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "CRAFT") + " Lv." + craftLevel);
        tillage_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "TILLAGE") + " Lv." + tillageLevel);
        wood_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "WOODCUTTING") + " Lv." + woodLevel);
        fish_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "FISHING") + " Lv." + fishLevel);

        List<String> mineLore = new ArrayList<>();
        List<String> craftLore = new ArrayList<>();
        List<String> tillageLore = new ArrayList<>();
        List<String> woodLore = new ArrayList<>();
        List<String> fishLore = new ArrayList<>();

        mineLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", mineExpPercent) + "%");
        mineLore.add("");
        mineLore.add(ChatColor.WHITE + "Effects :");
        if(mineLevel <= 3){
            mineLore.add(ChatColor.WHITE + "None");
        } else if(mineLevel > 3 && mineLevel <= 6){
            mineLore.add(ChatColor.WHITE + "Efficiency Lv.1");
        } else if(mineLevel > 6 && mineLevel <= 9){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.YELLOW+"Lv.2");
        } else if(mineLevel > 9 && mineLevel <= 14){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
        } else if(mineLevel > 14 && mineLevel <= 22){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune Lv.1");
        } else if(mineLevel > 22 && mineLevel <= 25){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune "+ChatColor.YELLOW+"Lv.2");
        } else if(mineLevel > 25 && mineLevel <= 29){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune "+ChatColor.YELLOW+"Lv.2");
            mineLore.add(ChatColor.WHITE + "Night Vision Effect");
        } else if(mineLevel >= 30){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Night Vision Effect");
        }

        craftLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", craftExpPercent) + "%");
        craftLore.add("");
        craftLore.add(ChatColor.WHITE + "Effects :");
        if(craftLevel <= 4){
            craftLore.add(ChatColor.WHITE + "None");
        } else if(craftLevel > 4 && craftLevel <= 10){
            craftLore.add(ChatColor.WHITE + "Random Enchantment Lv.1");
        } else if(craftLevel > 10 && craftLevel <= 15){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.YELLOW+"Lv.2");
        } else if(craftLevel > 15 && craftLevel <= 20){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
        } else if(craftLevel > 20 && craftLevel <= 25){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
            craftLore.add(ChatColor.WHITE + "Add Unbreaking Enchantment Lv.1");
        } else if(craftLevel > 25 && craftLevel <= 29){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
            craftLore.add(ChatColor.WHITE + "Add Unbreaking Enchantment "+ChatColor.YELLOW+"Lv.2");
        }else if(craftLevel >= 30){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
            craftLore.add(ChatColor.WHITE + "Add Unbreaking Enchantment "+ChatColor.RED+"Lv.3");
        }

        tillageLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", tillageExpPercent) + "%");
        tillageLore.add("");
        tillageLore.add(ChatColor.WHITE + "Effects :");
        if(tillageLevel <= 3){
            tillageLore.add(ChatColor.WHITE + "None");
        } else if(tillageLevel > 3 && tillageLevel <= 6){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate Lv.1");
        } else if(tillageLevel > 6 && tillageLevel <= 10){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
        } else if(tillageLevel > 10 && tillageLevel <= 14){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate Lv.1");
        } else if(tillageLevel > 14 && tillageLevel <= 19){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate "+ChatColor.YELLOW+"Lv.2");
        } else if(tillageLevel > 19 && tillageLevel <= 29){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "Jump Boost Effect");
        } else if(tillageLevel >= 30){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "Jump Boost Effect");
            tillageLore.add(ChatColor.WHITE + "Speed Effect");
        }

        woodLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", woodExpPercent) + "%");
        woodLore.add("");
        woodLore.add(ChatColor.WHITE + "Effects :");
        if(woodLevel <= 3){
            woodLore.add(ChatColor.WHITE + "None");
        } else if(woodLevel > 3 && woodLevel <= 6){
            woodLore.add(ChatColor.WHITE + "Efficiency Lv.1");
        } else if(woodLevel > 6 && woodLevel <= 9){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.YELLOW+"Lv.2");
        } else if(woodLevel > 9 && woodLevel <= 14){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
        } else if(woodLevel > 14 && woodLevel <= 19){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.GREEN+"Lv.4");
        } else if(woodLevel > 19 && woodLevel <= 24){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.AQUA+"Lv.5");
        } else if(woodLevel > 24 && woodLevel <= 29){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.AQUA+"Lv.5");
            woodLore.add(ChatColor.WHITE + "Can get exp by woodcutting");
        } else if(woodLevel >= 30){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.AQUA+"Lv.5");
            woodLore.add(ChatColor.WHITE + "Can get exp by woodcutting");
            woodLore.add(ChatColor.WHITE + "ChopTree Function");
            woodLore.add(ChatColor.WHITE + "Fire Resistance Effect");
        }

        fishLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", fishExpPercent) + "%");
        fishLore.add("");
        fishLore.add(ChatColor.WHITE + "Effects :");
        if(fishLevel <= 4){
            fishLore.add(ChatColor.WHITE + "None");
        } else if(fishLevel > 4 && fishLevel <= 8){
            fishLore.add(ChatColor.WHITE + "Lure Lv.1");
        } else if(fishLevel > 8 && fishLevel <= 12){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.YELLOW+"Lv.2");
        } else if(fishLevel > 12 && fishLevel <= 15){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.RED+"Lv.3");
        } else if(fishLevel > 15 && fishLevel <= 21){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.RED+"Lv.3");
            fishLore.add(ChatColor.WHITE + "Luck of the sea Lv.1");
        } else if(fishLevel > 21 && fishLevel <= 28){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.RED+"Lv.3");
            fishLore.add(ChatColor.WHITE + "Luck of the sea "+ChatColor.YELLOW+"Lv.2");
        } else if(fishLevel > 28 && fishLevel <= 29){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.RED+"Lv.3");
            fishLore.add(ChatColor.WHITE + "Luck of the sea "+ChatColor.RED+"Lv.3");
        } else if(fishLevel >= 30){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.RED+"Lv.3");
            fishLore.add(ChatColor.WHITE + "Luck of the sea "+ChatColor.RED+"Lv.3");
            fishLore.add(ChatColor.WHITE + "Water Breathing Effect");
        }

        mine_meta.setLore(mineLore);
        craft_meta.setLore(craftLore);
        wood_meta.setLore(woodLore);
        tillage_meta.setLore(tillageLore);
        fish_meta.setLore(fishLore);

        mine.setItemMeta(mine_meta);
        craft.setItemMeta(craft_meta);
        wood.setItemMeta(wood_meta);
        tillage.setItemMeta(tillage_meta);
        fish.setItemMeta(fish_meta);

        Inventory inv = Bukkit.createInventory(null, 9*6, "EXPERTISE");
        inv.setItem(10, mine);
        inv.setItem(13, craft);
        inv.setItem(16, tillage);
        inv.setItem(38, wood);
        inv.setItem(42, fish);
        invs.put(player, inv);
        return inv;
    }
}
