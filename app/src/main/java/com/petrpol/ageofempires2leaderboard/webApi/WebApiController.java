package com.petrpol.ageofempires2leaderboard.webApi;

import android.os.Debug;
import android.util.Log;

import com.petrpol.ageofempires2leaderboard.data.model.ItemNames;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatchResponse;
import com.petrpol.ageofempires2leaderboard.data.model.Leaderboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Controller for web api calls */
public class WebApiController {

    private static WebApiController instance;
    private WebApi webApi;

    /** Default constructor
     *  Crates retrofit instance */
    public WebApiController() {
        String BASE_URL = "https://aoe2.net/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webApi = retrofit.create(WebApi.class);
    }

    /** Returns instance (creates if null) */
    public static synchronized WebApiController getInstance(){
        if (instance==null)
            instance = new WebApiController();

        return instance;
    }

    /** Gets list of top 20 players */
    public void getTopPlayers(Callback<Leaderboard> callback){
        Call<Leaderboard> call = webApi.getTopPlayers();
        call.enqueue(callback);
    }

    /** Gets Last match for given player id */
    public void getLastMatch(int playerID, Callback<LastMatchResponse> callback){
        Call<LastMatchResponse> call = webApi.getLastMatch(playerID);
        call.enqueue(callback);
    }

    /** Gets Last match for given player id */
    public void getItemNames(Callback<ItemNames> callback){
        Call<ItemNames> call = webApi.getItemNames();
        call.enqueue(callback);
    }

}
