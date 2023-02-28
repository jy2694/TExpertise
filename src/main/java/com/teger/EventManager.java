package com.teger;

import com.teger.expertise.ExpertiseType;
import com.teger.expertise.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EventManager implements Listener {

    public static List<int[]> nonePoint = new ArrayList<>();

    private SecureRandom random = new SecureRandom();

    @EventHandler
    public void onWoodBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        if(block.getType().toString().endsWith("_LOG")){
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.WOODCUTTING);
            if(previousLevel == 30) return;
            data.addTotalExp(ExpertiseType.WOODCUTTING, 10);
            int newLevel = data.getLevel(ExpertiseType.WOODCUTTING);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[WOOD] ") + ChatColor.GREEN + " The WoodCutting level has increased. (" + previousLevel + " -> " + newLevel + ")");
        }
    }

    @EventHandler
    public void onStoneBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        if(block.getType().toString().endsWith("_ORE")){
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.MINE);
            if(previousLevel == 30) return;
            data.addTotalExp(ExpertiseType.MINE, 7);
            int newLevel = data.getLevel(ExpertiseType.MINE);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[MINE] ") + ChatColor.GREEN + " The mining level has increased. (" + previousLevel + " -> " + newLevel + ")");
        } else if(block.getType().equals(Material.STONE)
                || block.getType().equals(Material.COBBLESTONE) || block.getType().equals(Material.MOSSY_COBBLESTONE)
                || block.getType().equals(Material.SMOOTH_STONE) || block.getType().equals(Material.STONE_BRICKS)
                || block.getType().equals(Material.MOSSY_STONE_BRICKS) || block.getType().equals(Material.CHISELED_STONE_BRICKS)
                || block.getType().equals(Material.CRACKED_STONE_BRICKS) || block.getType().equals(Material.GRANITE)
                || block.getType().equals(Material.DIORITE) || block.getType().equals(Material.ANDESITE)
                || block.getType().equals(Material.DEEPSLATE) || block.getType().equals(Material.CHISELED_DEEPSLATE)
                || block.getType().equals(Material.COBBLED_DEEPSLATE) || block.getType().equals(Material.SANDSTONE)
                || block.getType().equals(Material.RED_SANDSTONE) || block.getType().equals(Material.BASALT)
                || block.getType().equals(Material.BLACKSTONE) || block.getType().equals(Material.END_STONE)
                || block.getType().equals(Material.NETHERRACK)){
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.MINE);
            if(previousLevel == 30) return;
            data.addTotalExp(ExpertiseType.MINE, 3);
            int newLevel = data.getLevel(ExpertiseType.MINE);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[MINE] ") + ChatColor.GREEN + " The mining level has increased. (" + previousLevel + " -> " + newLevel + ")");
        }
    }

    @EventHandler
    public void onCraftEvent(CraftItemEvent e){
        ItemStack item = e.getCurrentItem();
        if(item.getType().toString().endsWith("_HELMET")
            || item.getType().toString().endsWith("_CHESTPLATE")
            || item.getType().toString().endsWith("_LEGGINGS")
            || item.getType().toString().endsWith("_BOOTS")
            || item.getType().toString().endsWith("_SWORD")
            || item.getType().toString().endsWith("_HOE")
            || item.getType().toString().endsWith("_PICKAXE")
            || item.getType().toString().endsWith("_AXE")
            || item.getType().toString().endsWith("_SHOVEL")
            || item.getType().equals(Material.BUCKET)
            || item.getType().equals(Material.FISHING_ROD)
            || item.getType().equals(Material.FLINT_AND_STEEL)
            || item.getType().equals(Material.SHEARS)
            || item.getType().equals(Material.NAME_TAG)
            || item.getType().equals(Material.LEAD)){
            Player player = (Player) e.getWhoClicked();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.CRAFT);
            if(previousLevel == 30) return;
            data.addTotalExp(ExpertiseType.CRAFT, 15 * item.getAmount());
            int newLevel = data.getLevel(ExpertiseType.CRAFT);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[CRAFT] ") + ChatColor.GREEN + " The crafting level has increased. (" + previousLevel + " -> " + newLevel + ")");
        } else {
            Player player = (Player) e.getWhoClicked();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.CRAFT);
            if(previousLevel == 30) return;
            data.addTotalExp(ExpertiseType.CRAFT, 5 * item.getAmount());
            int newLevel = data.getLevel(ExpertiseType.CRAFT);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[CRAFT] ") + ChatColor.GREEN + " The crafting level has increased. (" + previousLevel + " -> " + newLevel + ")");
        }

    }

    @EventHandler
    public void onFishing(PlayerFishEvent e){
        Entity entity = e.getCaught();
        if(e.getState() == PlayerFishEvent.State.CAUGHT_FISH && entity instanceof Item){
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.FISHERMAN);
            if(previousLevel == 30) return;
            Item item = (Item) entity;
            ItemStack itemStack = item.getItemStack();
            if(itemStack.getType().equals(Material.COD) || itemStack.getType().equals(Material.SALMON)
                || itemStack.getType().equals(Material.TROPICAL_FISH) || itemStack.getType().equals(Material.PUFFERFISH)){
                data.addTotalExp(ExpertiseType.FISHERMAN, 5);
            } else if(itemStack.getType().equals(Material.NAUTILUS_SHELL)){
                data.addTotalExp(ExpertiseType.FISHERMAN, 50);
            } else if(itemStack.getType().equals(Material.ENCHANTED_BOOK)){
                data.addTotalExp(ExpertiseType.FISHERMAN, 10);
            } else {
                data.addTotalExp(ExpertiseType.FISHERMAN, 8);
            }
            int newLevel = data.getLevel(ExpertiseType.FISHERMAN);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[FISHING] ") + ChatColor.GREEN + " The Fishing level has increased. (" + previousLevel + " -> " + newLevel + ")");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Block block = e.getBlock();
        if(block.getType().equals(Material.SUGAR_CANE)){
            Location underLoc = block.getLocation().clone();
            underLoc.setY(block.getLocation().getBlockY()-1);
            Block under = block.getWorld().getBlockAt(underLoc);
            if(under.getType().equals(Material.SUGAR_CANE)) {
                nonePoint.add(new int[]{block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ()});
                return;
            }
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.TILLAGE);
            data.addTotalExp(ExpertiseType.TILLAGE, 1);
            int newLevel = data.getLevel(ExpertiseType.TILLAGE);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[CULTIVATE] ") + ChatColor.GREEN + " The cultivation level has increased. (" + previousLevel + " -> " + newLevel + ")");
        } else if(block.getType().equals(Material.WHEAT)
            || block.getType().equals(Material.CARROTS)
            || block.getType().equals(Material.POTATOES)
            || block.getType().equals(Material.BEETROOTS)
            || block.getType().equals(Material.PUMPKIN_STEM)
            || block.getType().equals(Material.MELON_STEM)){
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.TILLAGE);
            data.addTotalExp(ExpertiseType.TILLAGE, 1);
            int newLevel = data.getLevel(ExpertiseType.TILLAGE);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[CULTIVATE] ") + ChatColor.GREEN + " The cultivation level has increased. (" + previousLevel + " -> " + newLevel + ")");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        if(block.getType().equals(Material.SUGAR_CANE)){
            Location underLoc = block.getLocation().clone();
            underLoc.setY(block.getLocation().getBlockY()-1);
            Block under = block.getWorld().getBlockAt(underLoc);
            if(!under.getType().equals(Material.SUGAR_CANE)) return;
            if(isNonePoint(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ())) return;
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.TILLAGE);
            data.addTotalExp(ExpertiseType.TILLAGE, 20);
            int newLevel = data.getLevel(ExpertiseType.TILLAGE);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[CULTIVATE] ") + ChatColor.GREEN + " The cultivation level has increased. (" + previousLevel + " -> " + newLevel + ")");
        } else if(block.getType().equals(Material.WHEAT)
                || block.getType().equals(Material.CARROTS)
                || block.getType().equals(Material.POTATOES)
                || block.getType().equals(Material.BEETROOTS)
                || block.getType().equals(Material.PUMPKIN)
                || block.getType().equals(Material.MELON)){
            Player player = e.getPlayer();
            PlayerData data = PlayerData.getInstance(player);
            int previousLevel = data.getLevel(ExpertiseType.TILLAGE);
            Ageable ageable = (Ageable) block.getBlockData();
            switch(block.getType()){
                case WHEAT:
                case CARROTS:
                case POTATOES:
                    if(ageable.getAge() == 7){
                        data.addTotalExp(ExpertiseType.TILLAGE, 20);
                    }
                    break;
                case BEETROOTS:
                    if(ageable.getAge() == 3){
                        data.addTotalExp(ExpertiseType.TILLAGE, 20);
                    }
                    break;
                case PUMPKIN:
                case MELON:
                    data.addTotalExp(ExpertiseType.TILLAGE, 20);
                    break;
            }
            int newLevel = data.getLevel(ExpertiseType.TILLAGE);
            if(previousLevel < newLevel)
                player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "[CULTIVATE] ") + ChatColor.GREEN + " The cultivation level has increased. (" + previousLevel + " -> " + newLevel + ")");
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player)e.getWhoClicked();
        Inventory inv = JobInventory.getOpenedInventory(player);
        if(e.getInventory().equals(inv)) {
            e.setCancelled(true);
            return;
        }
        if(e.getClickedInventory() instanceof PlayerInventory){
            int slot = e.getSlot();
            if(slotnumber.get(player.getUniqueId()) == slot){
                ItemStack item = player.getInventory().getItem(slot);
                item = item == null ? new ItemStack(Material.AIR) : item;
                ItemStack cursor = e.getCursor();
                PlayerData data = PlayerData.getInstance(player);
                int level = data.getLevel(ExpertiseType.MINE);
                int efficiency_level = (level >= 4 && level <= 6) ? 1 :
                        (level >= 7 && level <= 9) ? 2 :
                                (level >= 10) ? 3 : 0;
                int fortune_level = (level >= 15 && level <= 22) ? 1 :
                        (level >= 23 && level <= 29) ? 2 :
                                (level >= 30) ? 3 : 0;
                if(cursor.getType().toString().endsWith("_PICKAXE")){
                    int efficiency = cursor.getEnchantmentLevel(Enchantment.DIG_SPEED)+efficiency_level;
                    int fortune = cursor.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)+fortune_level;
                    cursor.removeEnchantment(Enchantment.DIG_SPEED);
                    cursor.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
                    if(efficiency > 0)cursor.addEnchantment(Enchantment.DIG_SPEED, efficiency);
                    if(fortune > 0)cursor.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
                }
                if(item.getType().toString().endsWith("_PICKAXE")){
                    int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
                    int fortune = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)-fortune_level;
                    item.removeEnchantment(Enchantment.DIG_SPEED);
                    item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
                    if(efficiency > 0)item.addEnchantment(Enchantment.DIG_SPEED, efficiency);
                    if(fortune > 0)item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
                }
                level = data.getLevel(ExpertiseType.WOODCUTTING);
                efficiency_level = (level >= 4 && level <= 6) ? 1 :
                        (level >= 7 && level <= 9) ? 2 :
                                (level >= 10 && level <= 14) ? 3 :
                                        (level >= 15 && level <= 19) ? 4 :
                                                (level >= 20) ? 5 : 0;
                if(cursor.getType().toString().endsWith("_AXE")){
                    int efficiency = cursor.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
                    cursor.removeEnchantment(Enchantment.DIG_SPEED);
                    if(efficiency > 0) cursor.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
                }
                if(item.getType().toString().endsWith("_AXE")){
                    int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
                    item.removeEnchantment(Enchantment.DIG_SPEED);
                    if(efficiency > 0) item.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
                }
            }
        }
    }

    public boolean isNonePoint(int x, int y, int z){
        for(int[] loc : nonePoint)
            if(loc[0] == x && loc[1] == y && loc[2] == z)
                return true;
        return false;
    }

    @EventHandler
    public void onPickaxeChanged(PlayerItemHeldEvent e){
        Player player = e.getPlayer();
        PlayerData data = PlayerData.getInstance(player);
        PlayerInventory inv = e.getPlayer().getInventory();
        int level = data.getLevel(ExpertiseType.MINE);
        int efficiency_level = (level >= 4 && level <= 6) ? 1 :
                        (level >= 7 && level <= 9) ? 2 :
                        (level >= 10) ? 3 : 0;
        int fortune_level = (level >= 15 && level <= 22) ? 1 :
                        (level >= 23 && level <= 29) ? 2 :
                        (level >= 30) ? 3 : 0;
        ItemStack item = inv.getItem(e.getNewSlot());
        item = item == null ? new ItemStack(Material.AIR) : item;
        ItemStack previous = inv.getItem(e.getPreviousSlot());
        previous = previous == null ? new ItemStack(Material.AIR) : previous;
        //Add
        if(item.getType().toString().endsWith("_PICKAXE")){
            int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)+efficiency_level;
            int fortune = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)+fortune_level;
            item.removeEnchantment(Enchantment.DIG_SPEED);
            item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
            if(efficiency > 0)item.addEnchantment(Enchantment.DIG_SPEED, efficiency);
            if(fortune > 0)item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
        }
        //Remove
        if(previous.getType().toString().endsWith("_PICKAXE")){
            int efficiency = previous.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
            int fortune = previous.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)-fortune_level;
            previous.removeEnchantment(Enchantment.DIG_SPEED);
            previous.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
            if(efficiency > 0)previous.addEnchantment(Enchantment.DIG_SPEED, efficiency);
            if(fortune > 0)previous.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
        }
    }

    @EventHandler
    public void onAxeChanged(PlayerItemHeldEvent e){
        Player player = e.getPlayer();
        PlayerData data = PlayerData.getInstance(player);
        PlayerInventory inv = e.getPlayer().getInventory();
        int level = data.getLevel(ExpertiseType.WOODCUTTING);
        int efficiency_level = (level >= 4 && level <= 6) ? 1 :
                (level >= 7 && level <= 9) ? 2 :
                (level >= 10 && level <= 14) ? 3 :
                (level >= 15 && level <= 19) ? 4 :
                (level >= 20) ? 5 : 0;
        ItemStack item = inv.getItem(e.getNewSlot());
        item = item == null ? new ItemStack(Material.AIR) : item;
        ItemStack previous = inv.getItem(e.getPreviousSlot());
        previous = previous == null ? new ItemStack(Material.AIR) : previous;
        //Add
        if(item.getType().toString().endsWith("_AXE")){
            int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)+efficiency_level;
            item.removeEnchantment(Enchantment.DIG_SPEED);
            if(efficiency > 0)
                item.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
        }
        //Remove
        if(previous.getType().toString().endsWith("_AXE")){
            int efficiency = previous.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
            previous.removeEnchantment(Enchantment.DIG_SPEED);
            if(efficiency > 0) previous.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
        }
    }

    private static HashMap<UUID, ItemStack> previousItemHand = new HashMap<>();
    private static HashMap<UUID, Integer> slotnumber = new HashMap<>();

    @EventHandler
    public void saveItemStackForDetectItemDropByHand(PlayerItemHeldEvent e){
        ItemStack item = e.getPlayer().getInventory().getItem(e.getNewSlot());
        item = item == null ? new ItemStack(Material.AIR) : item;
        previousItemHand.put(e.getPlayer().getUniqueId(), item.clone());
        slotnumber.put(e.getPlayer().getUniqueId(), e.getNewSlot());
    }

    @EventHandler
    public void saveItemStackForDetectItemDropByHandSwap(PlayerSwapHandItemsEvent e){
        ItemStack item = e.getMainHandItem();
        item = item == null ? new ItemStack(Material.AIR) : item;
        previousItemHand.put(e.getPlayer().getUniqueId(), item.clone());
    }

    @EventHandler
    public void onChangePickAxeOtherHand(PlayerSwapHandItemsEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getMainHandItem();
        ItemStack previous = e.getOffHandItem();
        PlayerData data = PlayerData.getInstance(player);
        int level = data.getLevel(ExpertiseType.MINE);
        int efficiency_level = (level >= 4 && level <= 6) ? 1 :
                (level >= 7 && level <= 9) ? 2 :
                        (level >= 10) ? 3 : 0;
        int fortune_level = (level >= 15 && level <= 22) ? 1 :
                (level >= 23 && level <= 29) ? 2 :
                        (level >= 30) ? 3 : 0;
        //Add
        if(item.getType().toString().endsWith("_PICKAXE")){
            int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)+efficiency_level;
            int fortune = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)+fortune_level;
            item.removeEnchantment(Enchantment.DIG_SPEED);
            item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
            if(efficiency > 0)item.addEnchantment(Enchantment.DIG_SPEED, efficiency);
            if(fortune > 0)item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
        }
        //Remove
        if(previous.getType().toString().endsWith("_PICKAXE")){
            int efficiency = previous.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
            int fortune = previous.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)-fortune_level;
            previous.removeEnchantment(Enchantment.DIG_SPEED);
            previous.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
            if(efficiency > 0)previous.addEnchantment(Enchantment.DIG_SPEED, efficiency);
            if(fortune > 0)previous.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
        }
    }

    @EventHandler
    public void onChangeAxeOtherHand(PlayerSwapHandItemsEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getMainHandItem();
        ItemStack previous = e.getOffHandItem();
        PlayerData data = PlayerData.getInstance(player);
        int level = data.getLevel(ExpertiseType.WOODCUTTING);
        int efficiency_level = (level >= 4 && level <= 6) ? 1 :
                (level >= 7 && level <= 9) ? 2 :
                        (level >= 10 && level <= 14) ? 3 :
                                (level >= 15 && level <= 19) ? 4 :
                                        (level >= 20) ? 5 : 0;
        //Add
        if(item.getType().toString().endsWith("_AXE")){
            int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)+efficiency_level;
            item.removeEnchantment(Enchantment.DIG_SPEED);
            if(efficiency > 0)
                item.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
        }
        //Remove
        if(previous.getType().toString().endsWith("_AXE")){
            int efficiency = previous.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
            previous.removeEnchantment(Enchantment.DIG_SPEED);
            if(efficiency > 0) previous.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
        }
    }

    @EventHandler
    public void onChangePickAxeDropItem(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        if(previousItemHand.get(e.getPlayer().getUniqueId()).getType().equals(Material.AIR)) return;
        if(!previousItemHand.get(e.getPlayer().getUniqueId()).equals(player.getInventory().getItemInMainHand())){
            if(item.getType().toString().endsWith("_PICKAXE")){
                PlayerData data = PlayerData.getInstance(player);
                int level = data.getLevel(ExpertiseType.MINE);
                int efficiency_level = (level >= 4 && level <= 6) ? 1 :
                        (level >= 7 && level <= 9) ? 2 :
                                (level >= 10) ? 3 : 0;
                int fortune_level = (level >= 15 && level <= 22) ? 1 :
                        (level >= 23 && level <= 29) ? 2 :
                                (level >= 30) ? 3 : 0;
                int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
                int fortune = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)-fortune_level;
                item.removeEnchantment(Enchantment.DIG_SPEED);
                item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
                if(efficiency > 0)item.addEnchantment(Enchantment.DIG_SPEED, efficiency);
                if(fortune > 0)item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
                e.getItemDrop().setItemStack(item);
            }
        }
    }

    @EventHandler
    public void onChangeAxeDropItem(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        if(previousItemHand.get(e.getPlayer().getUniqueId()).getType().equals(Material.AIR)) return;
        if(!previousItemHand.get(e.getPlayer().getUniqueId()).equals(player.getInventory().getItemInMainHand())){
            if(item.getType().toString().endsWith("_AXE")){
                PlayerData data = PlayerData.getInstance(player);
                int level = data.getLevel(ExpertiseType.WOODCUTTING);
                int efficiency_level = (level >= 4 && level <= 6) ? 1 :
                        (level >= 7 && level <= 9) ? 2 :
                                (level >= 10 && level <= 14) ? 3 :
                                        (level >= 15 && level <= 19) ? 4 :
                                                (level >= 20) ? 5 : 0;
                int efficiency = item.getEnchantmentLevel(Enchantment.DIG_SPEED)-efficiency_level;
                item.removeEnchantment(Enchantment.DIG_SPEED);
                if(efficiency > 0) item.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
                e.getItemDrop().setItemStack(item);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand().clone();
        previousItemHand.put(e.getPlayer().getUniqueId(), item);
        int slot;
        for(slot = 0; slot < 9; slot ++)
            if(e.getPlayer().getInventory().getItem(slot).equals(item)) break;
        slotnumber.put(e.getPlayer().getUniqueId(), slot);
    }

    private static Enchantment[] tools_enchantment = {Enchantment.DIG_SPEED, Enchantment.SILK_TOUCH, Enchantment.LOOT_BONUS_BLOCKS};
    private static Enchantment[] fishing_enchantment = {Enchantment.LURE, Enchantment.LUCK};
    private static Enchantment[] sword_enchantment = {Enchantment.DAMAGE_ALL,
            Enchantment.DAMAGE_UNDEAD,
            Enchantment.DAMAGE_ARTHROPODS,
            Enchantment.KNOCKBACK,
            Enchantment.FIRE_ASPECT,
            Enchantment.SWEEPING_EDGE,
            Enchantment.LOOT_BONUS_MOBS };
    private static Enchantment[] axe_enchantment = {Enchantment.LOOT_BONUS_BLOCKS,Enchantment.DAMAGE_ALL,
            Enchantment.DAMAGE_UNDEAD,
            Enchantment.DAMAGE_ARTHROPODS,
            Enchantment.KNOCKBACK,
            Enchantment.FIRE_ASPECT,
            Enchantment.SWEEPING_EDGE,
            Enchantment.LOOT_BONUS_MOBS};
    private static Enchantment[] bow_enchantment = {Enchantment.ARROW_DAMAGE, Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE};
    private static Enchantment[] crossbow_enchantment = {Enchantment.QUICK_CHARGE, Enchantment.MULTISHOT, Enchantment.PIERCING};
    private static Enchantment[] helmet_enchantment = {Enchantment.PROTECTION_ENVIRONMENTAL,
            Enchantment.PROTECTION_FIRE,
            Enchantment.PROTECTION_PROJECTILE,
            Enchantment.PROTECTION_EXPLOSIONS,
            Enchantment.THORNS,
            Enchantment.OXYGEN,
            Enchantment.WATER_WORKER};
    private static Enchantment[] chestplate_leggings_enchantment = {Enchantment.PROTECTION_ENVIRONMENTAL,
            Enchantment.PROTECTION_FIRE,
            Enchantment.PROTECTION_PROJECTILE,
            Enchantment.PROTECTION_EXPLOSIONS,
            Enchantment.THORNS};
    private static Enchantment[] boots_enchantment = {Enchantment.PROTECTION_ENVIRONMENTAL,
            Enchantment.PROTECTION_FIRE,
            Enchantment.PROTECTION_PROJECTILE,
            Enchantment.PROTECTION_EXPLOSIONS,
            Enchantment.THORNS,
            Enchantment.PROTECTION_FALL,
            Enchantment.FROST_WALKER,
            Enchantment.DEPTH_STRIDER };

    @EventHandler
    public void onCraftEffectEvent(CraftItemEvent e){
        Player player = (Player)e.getWhoClicked();
        PlayerData data = PlayerData.getInstance(player);
        int level = data.getLevel(ExpertiseType.CRAFT);
        ItemStack item = e.getCurrentItem();
        int max_level = (level >= 5 && level <= 10) ? 1 : (level >= 11 && level <= 15) ? 2 : (level >= 16) ? 3 : 0;
        if(max_level > 0){
            int enchantLevel = random.nextInt(max_level+1);
            Enchantment type = null;
            if(enchantLevel > 0){
                if(item.getType().toString().endsWith("_SHOVEL")
                        || item.getType().toString().endsWith("_PICKAXE")
                        || item.getType().toString().endsWith("_HOE")){
                    type = tools_enchantment[random.nextInt(tools_enchantment.length)];
                } else if(item.getType().toString().endsWith("_AXE")){
                    type = axe_enchantment[random.nextInt(axe_enchantment.length)];
                } else if(item.getType().toString().endsWith("_SWORD")){
                    type = sword_enchantment[random.nextInt(sword_enchantment.length)];
                } else if(item.getType().equals(Material.FISHING_ROD)){
                    type = fishing_enchantment[random.nextInt(fishing_enchantment.length)];
                } else if(item.getType().equals(Material.BOW)){
                    type = bow_enchantment[random.nextInt(bow_enchantment.length)];
                } else if(item.getType().equals(Material.CROSSBOW)){
                    type = crossbow_enchantment[random.nextInt(crossbow_enchantment.length)];
                } else if(item.getType().toString().endsWith("_HELMET")){
                    type = helmet_enchantment[random.nextInt(helmet_enchantment.length)];
                } else if(item.getType().toString().endsWith("_CHESTPLATE")
                    || item.getType().toString().endsWith("_LEGGINGS")){
                    type = chestplate_leggings_enchantment[random.nextInt(chestplate_leggings_enchantment.length)];
                } else if(item.getType().toString().endsWith("_BOOTS")){
                    type = boots_enchantment[random.nextInt(boots_enchantment.length)];
                }
                if(type != null) item.addUnsafeEnchantment(type, enchantLevel);
            }
        }
        int unbreaking_level = (level >= 21) ? ((level >= 26) ? ((level >= 30) ? 3 : 2) : 1) : 0;
        if(unbreaking_level > 0){
            item.addUnsafeEnchantment(Enchantment.DURABILITY, unbreaking_level);
        }
    }
}
