package com.petrpol.ageofempires2leaderboard.ui.adapters.topPlayers;

/** Interface for top player list item  */
public interface TopPlayerItemCallback {

    /** Called when clicked on item
     *  @param playerId Selected player id */
    void onClick(int playerId);
}
