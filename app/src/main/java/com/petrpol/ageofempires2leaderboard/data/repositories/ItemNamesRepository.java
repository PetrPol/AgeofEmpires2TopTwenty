package com.petrpol.ageofempires2leaderboard.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.petrpol.ageofempires2leaderboard.data.model.ItemNames;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatch;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatchResponse;
import com.petrpol.ageofempires2leaderboard.webApi.WebApiController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/** Repository for item names
 *  Singleton pattern */
public class ItemNamesRepository {

    public static ItemNamesRepository instance;

    private WebApiController webApiController;

    MutableLiveData<ItemNames> itemNames = new MutableLiveData<>();


    /** Default constructor */
    public ItemNamesRepository() {
        webApiController = WebApiController.getInstance();
    }

    /** Gets instance (create if not exist) */
    public static synchronized ItemNamesRepository getInstance() {

        if (instance == null)
            instance = new ItemNamesRepository();

        return instance;
    }

    /** Update Item names object with data from server */
    public void refreshItemNames(final ItemNamesRepositoryInterface callback){

        webApiController.getItemNames(new Callback<ItemNames>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ItemNames> call, Response<ItemNames> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    callback.onGetItemNamesDataFail(response.message());
                    return;
                }

                itemNames.postValue(response.body());
                callback.onGetItemNamesDataSuccess();
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ItemNames> call, Throwable t) {
                if (t.getMessage() != null)
                    callback.onGetItemNamesDataFail(t.getMessage());
                else
                    callback.onGetItemNamesDataFail("Unknown server error");

            }
        });
    }

    //Getters
    public MutableLiveData<ItemNames> getItemNames() {
        return itemNames;
    }
}
