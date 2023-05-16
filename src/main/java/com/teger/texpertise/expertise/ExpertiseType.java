package com.teger.texpertise.expertise;

public enum ExpertiseType {
    CRAFT("CRAFT"),
    MINE("MINE"),
    TILLAGE("TILLAGE"),
    WOOD("WOODCUTTING"),
    FISH("FISHING"),
    HUNT("HUNTING");


    private final String name;

    ExpertiseType(String name){
        this.name = name;
    }
}
