package com.teger.expertise;

import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData {

    private static final List<PlayerData> playerDataList = new LinkedList<>();

    private final UUID uniqueId;
    private final HashMap<ExpertiseType, Long> expValue = new HashMap<>();

    public PlayerData(UUID uniqueId){
        this.uniqueId = uniqueId;
        Arrays.stream(ExpertiseType.values()).forEach(type -> expValue.put(type, 0L));
    }

    public UUID getUniqueId(){
        return uniqueId;
    }

    public void addTotalExp(ExpertiseType type, long exp){
        expValue.put(type, expValue.getOrDefault(type, 0L)+exp);
    }

    public long getTotalExp(ExpertiseType type){
        return expValue.getOrDefault(type, 0L);
    }

    public long getExp(ExpertiseType type){
        int req = type.equals(ExpertiseType.HUNT) ? 100 : 60;
        int level = 1;
        long nowExp = expValue.getOrDefault(type, 0L);
        while(nowExp >= req){
            nowExp -= req;
            level ++;
            if(type.equals(ExpertiseType.HUNT)){
                if(level <= 10) req = (int)Math.round(((double)req)*1.8);
            } else req = (int)Math.round(((double)req)*1.3);
        }
        if(level >= 30) return 0;
        return nowExp;
    }

    public int getLevel(ExpertiseType type){
        int req = type.equals(ExpertiseType.HUNT) ? 100 : 60;
        int level = 1;
        long nowExp = expValue.getOrDefault(type, 0L);
        while(nowExp >= req){
            nowExp -= req;
            level ++;
            if(type.equals(ExpertiseType.HUNT)){
                if(level <= 10) req = (int)Math.round(((double)req)*1.8);
            } else req = (int)Math.round(((double)req)*1.3);
        }
        return level;
    }


    public static PlayerData getInstance(UUID uniqueId){
        for(PlayerData data : playerDataList)
            if(data.uniqueId.equals(uniqueId))
                return data;
        PlayerData data = new PlayerData(uniqueId);
        playerDataList.add(data);
        return data;
    }

    public static List<PlayerData> getPlayerDataList(){
        return playerDataList;
    }

    public static PlayerData getInstance(Player player){ return getInstance(player.getUniqueId()); }
}
