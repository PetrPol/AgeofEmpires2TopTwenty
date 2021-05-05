package com.petrpol.ageofempires2leaderboard.data.model;

import com.google.gson.annotations.SerializedName;

/** Object used as response from server to get last match */
public class LastMatchResponse {

    String name;
    @SerializedName("last_match")
    LastMatch lastMatch;

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LastMatch getLastMatch() {
        return lastMatch;
    }

    public void setLastMatch(LastMatch lastMatch) {
        this.lastMatch = lastMatch;
    }
}
