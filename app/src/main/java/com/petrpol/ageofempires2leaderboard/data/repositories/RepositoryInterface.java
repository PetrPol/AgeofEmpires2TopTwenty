package com.petrpol.ageofempires2leaderboard.data.repositories;

/** Callback interface for repositories */
public interface RepositoryInterface {

    /** Called when data successfully got from server */
    void onGetDataSuccess();

    /** Called when data get from server failed
     *  @param message - error message */
    void onGetDataFail(String message);
}
