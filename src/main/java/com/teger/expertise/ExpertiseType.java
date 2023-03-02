package com.teger.expertise;

public enum ExpertiseType {
    CRAFT("CRAFT"),
    MINE("MINE"),
    TILLAGE("TILLAGE"),
    WOOD("WOODCUTTING"),
    FISH("FISHING");


    private String name;

    ExpertiseType(String name){
        this.name = name;
    }
}
