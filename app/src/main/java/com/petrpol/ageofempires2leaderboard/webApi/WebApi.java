package com.petrpol.ageofempires2leaderboard.webApi;

import com.petrpol.ageofempires2leaderboard.data.model.ItemNames;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatchResponse;
import com.petrpol.ageofempires2leaderboard.data.model.Leaderboard;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/** Interface for web calls */
public interface WebApi {

    /** Gets top 20 players in 1v1 random map */
    @GET("leaderboard?game=aoe2de&leaderboard_id=3&start=1&count=20")
    Call<Leaderboard> getTopPlayers();

    /** Gets last match for given player id */
    @GET("player/lastmatch?game=aoe2de&")
    Call<LastMatchResponse> getLastMatch(@Query("profile_id") int playerId);

    /** Gets maps and civilisations names */
    @GET("https://aoe2.net/api/strings?game=aoe2de&language=en")
    Call<ItemNames> getItemNames();
}
