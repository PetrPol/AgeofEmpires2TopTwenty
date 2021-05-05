package com.petrpol.ageofempires2leaderboard.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/** Object to store sting names of civilisations and maps */
public class ItemNames {

    @SerializedName("civ")
    List<StringName> civilisations;
    @SerializedName("map_type")
    List<StringName> maps;


    //Getters and setter
    public List<StringName> getCivilisations() {
        return civilisations;
    }

    public void setCivilisations(List<StringName> civilisations) {
        this.civilisations = civilisations;
    }

    public List<StringName> getMaps() {
        return maps;
    }

    public void setMaps(List<StringName> maps) {
        this.maps = maps;
    }

    public String getCivilisationNameByID(int id){
        for (StringName s : civilisations) {
            if (s.id==id)
                return s.getName();
        }
        return null;
    }

    public String getMapNameByID(int id){
        for (StringName s : maps) {
            if (s.id==id)
                return s.getName();
        }
        return null;
    }
}
