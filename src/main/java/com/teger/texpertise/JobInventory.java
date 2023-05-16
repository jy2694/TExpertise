package com.teger.texpertise;

import com.teger.texpertise.expertise.ExpertiseType;
import com.teger.texpertise.expertise.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobInventory {

    private static final HashMap<Player, Inventory> invs = new HashMap<>();

    public static Inventory getOpenedInventory(Player player){
        return invs.get(player);
    }

    public static Inventory getInventory(Player player){

        PlayerData data = PlayerData.getInstance(player);

        int mineLevel = data.getLevel(ExpertiseType.MINE),
                craftLevel = data.getLevel(ExpertiseType.CRAFT),
                tillageLevel = data.getLevel(ExpertiseType.TILLAGE),
                woodLevel = data.getLevel(ExpertiseType.WOOD),
                fishLevel = data.getLevel(ExpertiseType.FISH),
                huntLevel = data.getLevel(ExpertiseType.HUNT);

        double mineExp = data.getExp(ExpertiseType.MINE),
                craftExp = data.getExp(ExpertiseType.CRAFT),
                tillageExp = data.getExp(ExpertiseType.TILLAGE),
                woodExp = data.getExp(ExpertiseType.WOOD),
                fishExp = data.getExp(ExpertiseType.FISH),
                huntExp = data.getExp(ExpertiseType.HUNT);

        double mineReqExp = 60 * Math.pow(1.3, mineLevel-1),
                craftReqExp = 60 * Math.pow(1.3, craftLevel-1),
                tillageReqExp = 60 * Math.pow(1.3, tillageLevel-1),
                woodReqExp = 60 * Math.pow(1.3, woodLevel-1),
                fishReqExp = 60 * Math.pow(1.3, fishLevel-1),
                huntReqExp = huntLevel <= 10 ? (100 * Math.pow(1.8, huntLevel-1)) : (100 * Math.pow(1.8, 9));

        double mineExpPercent = mineLevel >= 30 ? 0 : ((double)((int)((mineExp / mineReqExp) * 1000.0))) / 10.0,
                craftExpPercent = craftLevel >= 30 ? 0 : ((double)((int)((craftExp / craftReqExp) * 1000.0))) / 10.0,
                tillageExpPercent = tillageLevel >= 30 ? 0 : ((double)((int)((tillageExp / tillageReqExp) * 1000.0))) / 10.0,
                woodExpPercent = woodLevel >= 30 ? 0 : ((double)((int)((woodExp / woodReqExp) * 1000.0))) / 10.0,
                fishExpPercent = fishLevel >= 30 ? 0 : ((double)((int)((fishExp / fishReqExp) * 1000.0))) / 10.0,
                huntExpPercent = huntLevel >= 30 ? 0 : ((double)((int)((huntExp / huntReqExp) * 1000.0))) / 10.0;

        ItemStack mine = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack craft = new ItemStack(Material.CRAFTING_TABLE);
        ItemStack tillage = new ItemStack(Material.WHEAT);
        ItemStack wood = new ItemStack(Material.IRON_AXE);
        ItemStack fish = new ItemStack(Material.TROPICAL_FISH);
        ItemStack hunt = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta mine_meta = mine.getItemMeta();
        ItemMeta craft_meta = craft.getItemMeta();
        ItemMeta tillage_meta = tillage.getItemMeta();
        ItemMeta wood_meta = wood.getItemMeta();
        ItemMeta fish_meta = fish.getItemMeta();
        ItemMeta hunt_meta = hunt.getItemMeta();

        mine_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        craft_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        tillage_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        wood_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        fish_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        hunt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

        mine_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "MINE") + " Lv." + mineLevel);
        craft_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "CRAFT") + " Lv." + craftLevel);
        tillage_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "TILLAGE") + " Lv." + tillageLevel);
        wood_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "WOODCUTTING") + " Lv." + woodLevel);
        fish_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "FISHING") + " Lv." + fishLevel);
        hunt_meta.setDisplayName(ChatColor.WHITE + (ChatColor.BOLD + "HUNTING") + " Lv." + huntLevel);

        List<String> mineLore = new ArrayList<>();
        List<String> craftLore = new ArrayList<>();
        List<String> tillageLore = new ArrayList<>();
        List<String> woodLore = new ArrayList<>();
        List<String> fishLore = new ArrayList<>();
        List<String> huntLore = new ArrayList<>();

        huntLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", huntExpPercent) + "% ("+(int)huntExp+"/"+(int)huntReqExp+")");
        huntLore.add("");
        huntLore.add(ChatColor.WHITE + "Effects :");
        if(huntLevel <= 2){
            huntLore.add(ChatColor.WHITE + "None");
        } else if(huntLevel == 3){
            huntLore.add(ChatColor.WHITE + "Smite Lv.1");
        } else if(huntLevel == 4){
            huntLore.add(ChatColor.WHITE + "Smite Lv.1");
            huntLore.add(ChatColor.WHITE + "Sharpness Lv.1");
        } else if(huntLevel == 5){
            huntLore.add(ChatColor.WHITE + "Smite "+ChatColor.YELLOW+"Lv.2");
            huntLore.add(ChatColor.WHITE + "Sharpness Lv.1");
        } else if(huntLevel == 6){
            huntLore.add(ChatColor.WHITE + "Smite "+ChatColor.YELLOW+"Lv.2");
            huntLore.add(ChatColor.WHITE + "Sharpness "+ChatColor.YELLOW+"Lv.2");
        } else if(huntLevel == 7){
            huntLore.add(ChatColor.WHITE + "Smite "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Sharpness "+ChatColor.YELLOW+"Lv.2");
        } else if(huntLevel <= 9){
            huntLore.add(ChatColor.WHITE + "Smite "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Sharpness "+ChatColor.RED+"Lv.3");
        } else if(huntLevel == 10){
            huntLore.add(ChatColor.WHITE + "Smite "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Sharpness "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Infinite Lv.1");
        } else if(huntLevel <= 29){
            huntLore.add(ChatColor.WHITE + "Smite "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Sharpness "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Infinite Lv.1");
            huntLore.add(ChatColor.WHITE + "Increase Max Health " + (huntLevel-10) + " Points");
        } else {
            huntLore.add(ChatColor.WHITE + "Smite "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Sharpness "+ChatColor.RED+"Lv.3");
            huntLore.add(ChatColor.WHITE + "Infinite Lv.1");
            huntLore.add(ChatColor.WHITE + "Increase Max Health 20 Points");
            huntLore.add(ChatColor.WHITE + "Increase Damage Effect");
        }

        mineLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", mineExpPercent) + "% ("+(int)mineExp+"/"+(int)mineReqExp+")");
        mineLore.add("");
        mineLore.add(ChatColor.WHITE + "Effects :");
        if(mineLevel <= 3){
            mineLore.add(ChatColor.WHITE + "None");
        } else if(mineLevel <= 6){
            mineLore.add(ChatColor.WHITE + "Efficiency Lv.1");
        } else if(mineLevel <= 9){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.YELLOW+"Lv.2");
        } else if(mineLevel <= 14){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
        } else if(mineLevel <= 22){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune Lv.1");
        } else if(mineLevel <= 25){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune "+ChatColor.YELLOW+"Lv.2");
        } else if(mineLevel <= 29){
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune "+ChatColor.YELLOW+"Lv.2");
            mineLore.add(ChatColor.WHITE + "Night Vision Effect");
        } else {
            mineLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Fortune "+ChatColor.RED+"Lv.3");
            mineLore.add(ChatColor.WHITE + "Night Vision Effect");
        }

        craftLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", craftExpPercent) + "% ("+(int)craftExp+"/"+(int)craftReqExp+")");
        craftLore.add("");
        craftLore.add(ChatColor.WHITE + "Effects :");
        if(craftLevel <= 4){
            craftLore.add(ChatColor.WHITE + "None");
        } else if(craftLevel <= 10){
            craftLore.add(ChatColor.WHITE + "Random Enchantment Lv.1");
        } else if(craftLevel <= 15){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.YELLOW+"Lv.2");
        } else if(craftLevel <= 20){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
        } else if(craftLevel <= 25){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
            craftLore.add(ChatColor.WHITE + "Add Unbreaking Enchantment Lv.1");
        } else if(craftLevel <= 29){
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
            craftLore.add(ChatColor.WHITE + "Add Unbreaking Enchantment "+ChatColor.YELLOW+"Lv.2");
        }else {
            craftLore.add(ChatColor.WHITE + "Random Enchantment "+ChatColor.RED+"Lv.3");
            craftLore.add(ChatColor.WHITE + "Add Unbreaking Enchantment "+ChatColor.RED+"Lv.3");
        }

        tillageLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", tillageExpPercent) + "% ("+(int)tillageExp+"/"+(int)tillageReqExp+")");
        tillageLore.add("");
        tillageLore.add(ChatColor.WHITE + "Effects :");
        if(tillageLevel <= 3){
            tillageLore.add(ChatColor.WHITE + "None");
        } else if(tillageLevel <= 6){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate Lv.1");
        } else if(tillageLevel <= 10){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
        } else if(tillageLevel <= 14){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate Lv.1");
        } else if(tillageLevel <= 19){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate "+ChatColor.YELLOW+"Lv.2");
        } else if(tillageLevel <= 29){
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "Jump Boost Effect");
        } else {
            tillageLore.add(ChatColor.WHITE + "Increased seed drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "increased crops drop rate "+ChatColor.YELLOW+"Lv.2");
            tillageLore.add(ChatColor.WHITE + "Jump Boost Effect");
            tillageLore.add(ChatColor.WHITE + "Speed Effect");
        }

        woodLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", woodExpPercent) + "% ("+(int)woodExp+"/"+(int)woodReqExp+")");
        woodLore.add("");
        woodLore.add(ChatColor.WHITE + "Effects :");
        if(woodLevel <= 3){
            woodLore.add(ChatColor.WHITE + "None");
        } else if(woodLevel <= 6){
            woodLore.add(ChatColor.WHITE + "Efficiency Lv.1");
        } else if(woodLevel <= 9){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.YELLOW+"Lv.2");
        } else if(woodLevel <= 14){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.RED+"Lv.3");
        } else if(woodLevel <= 19){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.GREEN+"Lv.4");
        } else if(woodLevel <= 24){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.AQUA+"Lv.5");
        } else if(woodLevel <= 29){
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.AQUA+"Lv.5");
            woodLore.add(ChatColor.WHITE + "Can get exp by woodcutting");
        } else {
            woodLore.add(ChatColor.WHITE + "Efficiency "+ChatColor.AQUA+"Lv.5");
            woodLore.add(ChatColor.WHITE + "Can get exp by woodcutting");
            woodLore.add(ChatColor.WHITE + "ChopTree Function");
            woodLore.add(ChatColor.WHITE + "Fire Resistance Effect");
        }

        fishLore.add(ChatColor.GOLD + "EXP : " + String.format("%.2f", fishExpPercent) + "% ("+(int)fishExp+"/"+(int)fishReqExp+")");
        fishLore.add("");
        fishLore.add(ChatColor.WHITE + "Effects :");
        if(fishLevel <= 4){
            fishLore.add(ChatColor.WHITE + "None");
        } else if(fishLevel <= 8){
            fishLore.add(ChatColor.WHITE + "Lure Lv.1");
        } else if(fishLevel <= 12){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.YELLOW+"Lv.2");
        } else if(fishLevel <= 15){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.YELLOW+"Lv.2");
            fishLore.add(ChatColor.WHITE + "Luck of the sea Lv.1");
        } else if(fishLevel <= 21){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.YELLOW+"Lv.2");
            fishLore.add(ChatColor.WHITE + "Luck of the sea "+ChatColor.YELLOW+"Lv.2");
        } else if(fishLevel <= 28){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.YELLOW+"Lv.2");
            fishLore.add(ChatColor.WHITE + "Luck of the sea "+ChatColor.RED+"Lv.3");
        } else if(fishLevel == 29){
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.YELLOW+"Lv.2");
            fishLore.add(ChatColor.WHITE + "Luck of the sea "+ChatColor.GREEN+"Lv.4");
        } else {
            fishLore.add(ChatColor.WHITE + "Lure "+ChatColor.YELLOW+"Lv.2");
            fishLore.add(ChatColor.WHITE + "Luck of the sea "+ChatColor.GREEN+"Lv.4");
            fishLore.add(ChatColor.WHITE + "Water Breathing Effect");
        }

        mine_meta.setLore(mineLore);
        craft_meta.setLore(craftLore);
        wood_meta.setLore(woodLore);
        tillage_meta.setLore(tillageLore);
        fish_meta.setLore(fishLore);
        hunt_meta.setLore(huntLore);

        mine.setItemMeta(mine_meta);
        craft.setItemMeta(craft_meta);
        wood.setItemMeta(wood_meta);
        tillage.setItemMeta(tillage_meta);
        fish.setItemMeta(fish_meta);
        hunt.setItemMeta(hunt_meta);

        Inventory inv = Bukkit.createInventory(null, 9*6, "EXPERTISE");
        inv.setItem(10, mine);
        inv.setItem(13, craft);
        inv.setItem(16, tillage);
        inv.setItem(10 + 27, wood);
        inv.setItem(13 + 27, fish);
        inv.setItem(16 + 27, hunt);
        invs.put(player, inv);
        return inv;
    }
}
