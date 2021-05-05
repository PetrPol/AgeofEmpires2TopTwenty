package com.petrpol.ageofempires2leaderboard.data.repositories;

/** Callback interface for Item Names repository */
public interface ItemNamesRepositoryInterface {

    /** Called when data successfully got from server */
    void onGetItemNamesDataSuccess();

    /** Called when data get from server failed
     *  @param message - error message */
    void onGetItemNamesDataFail(String message);
}
