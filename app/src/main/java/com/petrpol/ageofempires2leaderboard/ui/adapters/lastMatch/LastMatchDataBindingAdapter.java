package com.petrpol.ageofempires2leaderboard.ui.adapters.lastMatch;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.petrpol.ageofempires2leaderboard.R;
import com.petrpol.ageofempires2leaderboard.data.model.ItemNames;
import com.petrpol.ageofempires2leaderboard.data.repositories.ItemNamesRepository;
import com.petrpol.ageofempires2leaderboard.ui.lastMatchFragment.LastMatchViewModel;

/** Data binding adapters for Last match items */
public class LastMatchDataBindingAdapter {

    /** Data binder for map type text */
    @BindingAdapter({"mapTypeText","itemNames"})
    public static void setMapTypeText(TextView view, int mapType, ItemNames itemNames){
        if(itemNames!=null){
            String name = itemNames.getMapNameByID(mapType);
            if (name!=null)
                view.setText(view.getContext().getString(R.string.last_match_map_type,name));
            else
                view.setText("");
        }
    }

    /** Data binder civilisation name */
    @BindingAdapter("civilisationName")
    public static void setCivilisationName(TextView view, int civilisationId){
        LiveData<ItemNames> itemNames = ItemNamesRepository.getInstance().getItemNames();
        itemNames.observe((LifecycleOwner) view.getContext(), itemNames1 -> {
            if(itemNames1 !=null){
                String name = itemNames1.getCivilisationNameByID(civilisationId);
                if (name!=null)
                    view.setText(name);
                else
                    view.setText("");
            }
        });

    }

    /** Creates on refresh data listener for last match view model */
    @BindingAdapter("refreshListener")
    public static void setOnRefreshListener(SwipeRefreshLayout view, LastMatchViewModel lastMatchViewModel) {
        view.setOnRefreshListener(lastMatchViewModel::refreshData);
    }

    /** Sets background to view based on boolean value of parameter won */
    @BindingAdapter("PlayerItemBackground")
    public static void setPlayerBackground(ConstraintLayout view, boolean won) {
        if (won)
            view.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.match_player_list_item_background_won));
        else
            view.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.match_player_list_item_background_loose));
    }

    /** Sets visibility based on boolean value */
    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        if (view !=null && value != null){
            view.setVisibility(value ? View.VISIBLE : View.GONE);
        }
    }

}
