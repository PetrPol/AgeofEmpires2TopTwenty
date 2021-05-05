package com.petrpol.ageofempires2leaderboard.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.petrpol.ageofempires2leaderboard.data.model.Leaderboard;
import com.petrpol.ageofempires2leaderboard.data.model.Player;
import com.petrpol.ageofempires2leaderboard.webApi.WebApiController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/** Repository for leaderboard
 *  Singleton pattern */
public class PlayersRepository {

    public static PlayersRepository instance;

    private WebApiController webApiController;

    /** Default constructor */
    public PlayersRepository() {
        webApiController = WebApiController.getInstance();
    }

    /** Gets instance (create if not exist) */
    public static synchronized PlayersRepository getInstance() {

        if (instance == null)
            instance = new PlayersRepository();

        return instance;
    }

    /** Updates List of top players from server */
    public void getTopPlayers(MutableLiveData<List<Player>> data, final RepositoryInterface callback){

        webApiController.getTopPlayers(new Callback<Leaderboard>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    callback.onGetDataFail(response.message());
                    return;
                }

                data.postValue(response.body().getPlayers());
                callback.onGetDataSuccess();
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<Leaderboard> call, Throwable t) {
                if (t.getMessage() != null)
                    callback.onGetDataFail(t.getMessage());
                else
                    callback.onGetDataFail("Unknown server error");

            }
        });
    }
}
