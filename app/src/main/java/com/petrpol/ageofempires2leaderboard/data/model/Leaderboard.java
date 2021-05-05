package com.petrpol.ageofempires2leaderboard.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/** Object used as response to server call for top players list */
public class Leaderboard {

    int total;

    @SerializedName("leaderboard")
    List<Player> players;

    //Getters and setters
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
