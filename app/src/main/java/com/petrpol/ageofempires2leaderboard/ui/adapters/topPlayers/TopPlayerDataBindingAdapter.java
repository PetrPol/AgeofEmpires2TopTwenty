package com.petrpol.ageofempires2leaderboard.ui.adapters.topPlayers;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.petrpol.ageofempires2leaderboard.R;
import com.petrpol.ageofempires2leaderboard.ui.leaderboardFragment.LeaderboardViewModel;
import com.petrpol.ageofempires2leaderboard.utils.SnackBarController;

/** Data binding adapters for Top player items */
public class TopPlayerDataBindingAdapter {

    /** Data binder for rating text */
    @BindingAdapter("ratingText")
    public static void setRatingText(TextView view, int rating){
       view.setText(view.getContext().getString(R.string.player_item_rating,rating));
    }

    /** Data binder for percentage text */
    @BindingAdapter("percentageText")
    public static void setPercentageText(TextView view, float value){
        view.setText(view.getContext().getString(R.string.text_percentage,(int)(value*100)));
    }

    /** Data binder for rank image tint */
    @BindingAdapter("app:tint")
    public static void setPercentageText(ImageView view, int rank){

        int colorResource;

        switch (rank){
            case 1:
                colorResource = R.color.colorGold;
                break;
            case 2:
                colorResource = R.color.colorSilver;
                break;
            case 3:
                colorResource = R.color.colorBronze;
                break;
            default:
                colorResource = R.color.colorGray;
                break;
        }

        view.setColorFilter(view.getContext().getResources().getColor(colorResource));
    }

    /** Creates on refresh data listener for leaderboard view model */
    @BindingAdapter({"refreshListener"})
    public static void setOnRefreshListener(SwipeRefreshLayout view, LeaderboardViewModel leaderboardViewModel) {
        view.setOnRefreshListener(leaderboardViewModel::refreshData);
    }

    /** Snack bar binder - shows snackbar in CoordinatorLayout
     * @param message - message of snackbar to show */
    @BindingAdapter("snackBar")
    public static void showSnackBar(CoordinatorLayout view, String message){
        if (message!=null) {
            SnackBarController.showDefaultSnackBar(view, message);
        }
    }
}
