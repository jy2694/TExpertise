package com.teger.texpertise;

import com.teger.texpertise.expertise.ExpertiseType;
import com.teger.texpertise.expertise.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PotionTimer extends BukkitRunnable {
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            PlayerData data = PlayerData.getInstance(player);
            int mineLevel = data.getLevel(ExpertiseType.MINE);
            int woodLevel = data.getLevel(ExpertiseType.WOOD);
            int tillageLevel = data.getLevel(ExpertiseType.TILLAGE);
            int fishLevel = data.getLevel(ExpertiseType.FISH);
            int huntLevel = data.getLevel(ExpertiseType.HUNT);

            int nightVisionLevel = mineLevel >= 26 ? 1 : 0;
            int fireResistanceLevel = woodLevel >= 30 ? 1 : 0;
            int jumpBoostLevel = tillageLevel >= 20 ? 1 : 0;
            int speedLevel = tillageLevel >= 30 ? 1 : 0;
            int breathLevel = fishLevel >= 30 ? 1 : 0;
            int forceLevel = huntLevel >= 30 ? 1 : 0;

            PotionEffect nightVision = player.getPotionEffect(PotionEffectType.NIGHT_VISION);
            if(nightVision == null && nightVisionLevel > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
            PotionEffect fireResistance = player.getPotionEffect(PotionEffectType.FIRE_RESISTANCE);
            if(fireResistance == null && fireResistanceLevel > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
            PotionEffect jumpBoost = player.getPotionEffect(PotionEffectType.JUMP);
            if(jumpBoost == null && jumpBoostLevel > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0));
            PotionEffect speed = player.getPotionEffect(PotionEffectType.SPEED);
            if(speed == null && speedLevel > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
            PotionEffect breath = player.getPotionEffect(PotionEffectType.WATER_BREATHING);
            if(breath == null && breathLevel > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
            PotionEffect force = player.getPotionEffect(PotionEffectType.INCREASE_DAMAGE);
            if(force == null && forceLevel > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));


            int increaseHP = huntLevel > 10 ? huntLevel-10 : 0;
            player.setMaxHealth(20 + increaseHP);
        }
    }
}
