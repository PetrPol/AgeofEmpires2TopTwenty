package com.petrpol.ageofempires2leaderboard.data.model;

import android.os.Build;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/** Object which represents Last match of player */
public class LastMatch {


    String name;
    @SerializedName("map_type")
    int mapType;
    List<MatchPlayer> players;
    int started;
    int finished;


    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMapType() {
        return mapType;
    }

    public void setMapType(int mapType) {
        this.mapType = mapType;
    }

    public List<MatchPlayer> getPlayers() {
        return players;
    }

    public void sortPlayers(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            players.sort((o1, o2) -> {
                if (!o1.won && o2.won) return 1;
                else if (o1.won && !o2.won) return -1;
                else return 0;
            });
        }
    }

    public void setPlayers(List<MatchPlayer> players) {
        this.players = players;
    }

    public int getStarted() {
        return started;
    }

    public void setStarted(int started) {
        this.started = started;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getMatchTime(){
        if (finished-started<0)
            return "00:00";

        int minutes = (finished-started)/60;
        int seconds = (finished-started)%60;
        return minutes + ":" + seconds;
    }
}
