package com.petrpol.ageofempires2leaderboard.ui.lastMatchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.ageofempires2leaderboard.R;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatch;
import com.petrpol.ageofempires2leaderboard.databinding.LastMatchFragmentBinding;
import com.petrpol.ageofempires2leaderboard.databinding.LeaderboardFragmentBinding;
import com.petrpol.ageofempires2leaderboard.ui.adapters.lastMatch.LastMatchPlayersAdapter;
import com.petrpol.ageofempires2leaderboard.ui.adapters.topPlayers.TopPlayersAdapter;
import com.petrpol.ageofempires2leaderboard.ui.leaderboardFragment.LeaderboardViewModel;

/** Fragment to show last match of selected player */
public class LastMatchFragment extends Fragment {

    private LastMatchViewModel mViewModel;
    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Data binding create view
        LastMatchFragmentBinding mBinding = DataBindingUtil.inflate(inflater,R.layout.last_match_fragment, container, false);
        root = mBinding.getRoot();
        setHasOptionsMenu(true);

        //Create new model only if is null
        if (mViewModel==null)
            mViewModel = new LastMatchViewModel();

        //Get playerId from bundle
        int playerId = -1;
        if (getArguments() != null)
            playerId = LastMatchFragmentArgs.fromBundle(getArguments()).getPlayerId();

        //Set binding
        mViewModel.clean();
        mBinding.setLastMatchViewModel(mViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());

        setupRecyclerView();

        mViewModel.setPlayerId(playerId);
        mViewModel.refreshData();

        return root;
    }

    /** Setups recycler view and adapter */
    private void setupRecyclerView(){
        RecyclerView cardBackRecyclerView = root.findViewById(R.id.last_match_recycler);
        LastMatchPlayersAdapter cardBacksAdapter = new LastMatchPlayersAdapter(getContext(),mViewModel.getLastMatch());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cardBackRecyclerView.setLayoutManager(layoutManager);
        cardBackRecyclerView.setAdapter(cardBacksAdapter);
    }

    /** Handle back arrow press */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Navigation.findNavController(root).popBackStack();
        return false;
    }
}