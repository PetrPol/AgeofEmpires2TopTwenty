package com.petrpol.ageofempires2leaderboard.data.model;

import com.google.gson.annotations.SerializedName;

/** Object which represents Player as part of Last match object */
public class MatchPlayer {

    String name;
    @SerializedName("civ")
    int civilisation;
    boolean won;


    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCivilisation() {
        return civilisation;
    }

    public void setCivilisation(int civilisation) {
        this.civilisation = civilisation;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
