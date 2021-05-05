package com.petrpol.ageofempires2leaderboard.data.model;

import com.google.gson.annotations.SerializedName;

/** Object which represents name of object with id */
public class StringName {

    int id;
    @SerializedName("string")
    String name;

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
