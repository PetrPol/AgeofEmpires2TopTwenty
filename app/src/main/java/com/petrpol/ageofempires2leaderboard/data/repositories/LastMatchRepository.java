package com.petrpol.ageofempires2leaderboard.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.petrpol.ageofempires2leaderboard.data.model.LastMatch;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatchResponse;
import com.petrpol.ageofempires2leaderboard.data.model.Leaderboard;
import com.petrpol.ageofempires2leaderboard.data.model.Player;
import com.petrpol.ageofempires2leaderboard.webApi.WebApiController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/** Repository for last match
 *  Singleton pattern */
public class LastMatchRepository {

    public static LastMatchRepository instance;

    private WebApiController webApiController;

    /** Default constructor */
    public LastMatchRepository() {
        webApiController = WebApiController.getInstance();
    }

    /** Gets instance (create if not exist) */
    public static synchronized LastMatchRepository getInstance() {

        if (instance == null)
            instance = new LastMatchRepository();

        return instance;
    }

    /** Updates Last match info of Live data object based on data from server
     * @param playerId - id of player to get his last match */
    public void getLastMatch(int playerId, MutableLiveData<LastMatch> data, final RepositoryInterface callback){

        webApiController.getLastMatch(playerId,new Callback<LastMatchResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<LastMatchResponse> call, Response<LastMatchResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    callback.onGetDataFail(response.message());
                    return;
                }

                response.body().getLastMatch().setName(response.body().getName());
                data.postValue(response.body().getLastMatch());
                callback.onGetDataSuccess();
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<LastMatchResponse> call, Throwable t) {
                if (t.getMessage() != null)
                    callback.onGetDataFail(t.getMessage());
                else
                    callback.onGetDataFail("Unknown server error");

            }
        });
    }
}
