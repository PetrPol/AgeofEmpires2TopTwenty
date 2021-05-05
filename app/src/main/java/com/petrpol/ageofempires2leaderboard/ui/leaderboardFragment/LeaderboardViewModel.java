package com.petrpol.ageofempires2leaderboard.ui.leaderboardFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.ageofempires2leaderboard.data.model.Player;
import com.petrpol.ageofempires2leaderboard.data.repositories.ItemNamesRepository;
import com.petrpol.ageofempires2leaderboard.data.repositories.ItemNamesRepositoryInterface;
import com.petrpol.ageofempires2leaderboard.data.repositories.PlayersRepository;
import com.petrpol.ageofempires2leaderboard.data.repositories.RepositoryInterface;

import java.util.List;

public class LeaderboardViewModel extends ViewModel implements RepositoryInterface, ItemNamesRepositoryInterface {

    MutableLiveData<List<Player>> players = new MutableLiveData<>();
    MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    PlayersRepository playersRepository;
    ItemNamesRepository itemNamesRepository;


    /** Default constructor */
    public LeaderboardViewModel() {
        //Default values
        errorMessage.setValue(null);
        isDataLoading.setValue(true);

        //Create repositories
        playersRepository = PlayersRepository.getInstance();
        itemNamesRepository = ItemNamesRepository.getInstance();

        //Get players and item names
        itemNamesRepository.refreshItemNames(this);
        playersRepository.getTopPlayers(players,this);
    }

    /** Refresh top players list from server */
    public void refreshData(){
        isDataLoading.postValue(true);
        playersRepository.getTopPlayers(players,this);
    }

    /** Cleans View model before reuse to new view */
    public void clean(){
        errorMessage.postValue(null);
    }

    //Repository interface
    @Override
    public void onGetDataSuccess() {
        isDataLoading.postValue(false);
    }

    @Override
    public void onGetDataFail(String message) {
        isDataLoading.postValue(false);
        errorMessage.postValue(message);
    }

    @Override
    public void onGetItemNamesDataSuccess() {

    }

    @Override
    public void onGetItemNamesDataFail(String message) {
        errorMessage.postValue(message);
    }


    //Getters
    public LiveData<List<Player>> getPlayers() {
        return players;
    }

    public LiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}