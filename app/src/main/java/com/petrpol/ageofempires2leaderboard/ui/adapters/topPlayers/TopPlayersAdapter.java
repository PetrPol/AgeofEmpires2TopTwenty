package com.petrpol.ageofempires2leaderboard.ui.adapters.topPlayers;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.petrpol.ageofempires2leaderboard.R;
import com.petrpol.ageofempires2leaderboard.data.model.Player;
import com.petrpol.ageofempires2leaderboard.ui.adapters.ARecyclerViewAdapter;

import java.util.List;

/** Recycler view adapter for list of players
 *  extends ARecyclerViewAdapter*/
public class TopPlayersAdapter extends ARecyclerViewAdapter {

    private LiveData<List<Player>> players;
    private TopPlayerItemCallback callback;

    public TopPlayersAdapter(Context context, LiveData<List<Player>> players, TopPlayerItemCallback callback) {
        this.players = players;
        this.callback = callback;

        players.observe((LifecycleOwner) context, players1 -> notifyDataSetChanged());
    }

    @Override
    protected Object getItemForPosition(int position) {
        if (players.getValue()!=null)
            return players.getValue().get(position);
        else
            return null;
    }

    @Override
    protected Object getListenerForPosition(int position) {
        return callback;
    }

    @Override
    protected int getLayoutForPosition(int position) {
        return R.layout.top_player_list_item;
    }

    @Override
    public int getItemCount() {
        if (players==null || players.getValue()==null)
            return 0;

        return players.getValue().size();
    }
}
