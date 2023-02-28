package com.teger.expertise;

import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData {

    private static List<PlayerData> playerDataList = new LinkedList<>();

    private UUID uniqueId;
    private HashMap<ExpertiseType, Long> expValue = new HashMap<>();

    public PlayerData(UUID uniqueId){
        this.uniqueId = uniqueId;
        Arrays.stream(ExpertiseType.values()).forEach(type -> expValue.put(type, 0L));
    }

    public PlayerData(Player player){
        this(player.getUniqueId());
    }

    public void setTotalExp(ExpertiseType type, long exp){
        expValue.put(type, exp);
    }

    public void addTotalExp(ExpertiseType type, long exp){
        expValue.put(type, expValue.getOrDefault(type, 0L)+exp);
    }

    public long getTotalExp(ExpertiseType type){
        return expValue.getOrDefault(type, 0L);
    }

    public long getExp(ExpertiseType type){
        int req = 60;
        int level = 1;
        long nowExp = expValue.getOrDefault(type, 0L);
        while(nowExp >= req){
            nowExp -= req;
            level ++;
            req = (int)Math.round(((double)req)*1.3);
        }
        if(level >= 30) return 0;
        return nowExp;
    }

    public int getLevel(ExpertiseType type){
        int req = 60;
        int level = 1;
        long nowExp = expValue.getOrDefault(type, 0L);
        while(nowExp >= req){
            nowExp -= req;
            level ++;
            req = (int)Math.round(((double)req)*1.3);
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

    public static PlayerData getInstance(Player player){ return getInstance(player.getUniqueId()); }
}
