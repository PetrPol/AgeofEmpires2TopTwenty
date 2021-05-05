package com.petrpol.ageofempires2leaderboard.ui.adapters.lastMatch;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.petrpol.ageofempires2leaderboard.R;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatch;
import com.petrpol.ageofempires2leaderboard.data.model.MatchPlayer;
import com.petrpol.ageofempires2leaderboard.data.model.Player;
import com.petrpol.ageofempires2leaderboard.ui.adapters.ARecyclerViewAdapter;
import com.petrpol.ageofempires2leaderboard.ui.adapters.topPlayers.TopPlayerItemCallback;

import java.util.List;

/** Recycler view adapter for list of last match players
 *  Extends ARecyclerViewAdapter */
public class LastMatchPlayersAdapter extends ARecyclerViewAdapter {

    private LiveData<LastMatch> lastMatch;

    public LastMatchPlayersAdapter(Context context, LiveData<LastMatch> lastMatch) {
        this.lastMatch = lastMatch;

        lastMatch.observe((LifecycleOwner) context, players1 -> {
            notifyDataSetChanged();
            if (lastMatch.getValue()!=null)
                lastMatch.getValue().sortPlayers();});
    }

    @Override
    protected Object getItemForPosition(int position) {
        if (lastMatch.getValue()!=null && lastMatch.getValue().getPlayers()!=null)
            return lastMatch.getValue().getPlayers().get(position);
        else
            return null;
    }

    @Override
    protected Object getListenerForPosition(int position) {
        return null;
    }

    @Override
    protected int getLayoutForPosition(int position) {
        return R.layout.last_match_player_list_item;
    }

    @Override
    public int getItemCount() {
        if (lastMatch.getValue()==null || lastMatch.getValue().getPlayers()==null)
            return 0;

        return lastMatch.getValue().getPlayers().size();
    }
}
