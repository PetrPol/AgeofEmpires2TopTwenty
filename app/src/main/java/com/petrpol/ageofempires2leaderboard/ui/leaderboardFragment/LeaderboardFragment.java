package com.petrpol.ageofempires2leaderboard.ui.leaderboardFragment;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petrpol.ageofempires2leaderboard.R;
import com.petrpol.ageofempires2leaderboard.databinding.LeaderboardFragmentBinding;
import com.petrpol.ageofempires2leaderboard.ui.adapters.topPlayers.TopPlayersAdapter;

public class LeaderboardFragment extends Fragment {

    private LeaderboardViewModel mViewModel;
    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Data binding create view
        LeaderboardFragmentBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.leaderboard_fragment, container, false);
        root = mBinding.getRoot();

        //Create new model only if is null
        if (mViewModel==null)
            mViewModel = new LeaderboardViewModel();

        //Set binding
        mViewModel.clean();
        mBinding.setLeaderboardViewModel(mViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());

        setupRecyclerView();

        return root;
    }

    /** Setups recycler view and adapter */
    private void setupRecyclerView(){
        RecyclerView cardBackRecyclerView = root.findViewById(R.id.leaderboard_recycler);
        TopPlayersAdapter cardBacksAdapter = new TopPlayersAdapter(getContext(),mViewModel.getPlayers(), this::showPlayerLastGame);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cardBackRecyclerView.setLayoutManager(layoutManager);
        cardBackRecyclerView.setAdapter(cardBacksAdapter);
    }

    /** Navigate to last match fragment */
    public void showPlayerLastGame(int playerId){
        LeaderboardFragmentDirections.ActionNavigationHomeToLastMatchFragment action =  LeaderboardFragmentDirections.actionNavigationHomeToLastMatchFragment(playerId);
        Navigation.findNavController(root).navigate(action);
    }

}