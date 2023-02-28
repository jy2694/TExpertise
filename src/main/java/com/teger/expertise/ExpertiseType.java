package com.teger.expertise;

public enum ExpertiseType {
    CRAFT("CRAFT"),
    MINE("MINE"),
    TILLAGE("TILLAGE"),
    WOODCUTTING("WOODCUTTING"),
    FISHERMAN("FISHING");


    private String name;

    ExpertiseType(String name){
        this.name = name;
    }
}
