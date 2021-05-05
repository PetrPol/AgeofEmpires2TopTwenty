package com.petrpol.ageofempires2leaderboard.ui.lastMatchFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petrpol.ageofempires2leaderboard.data.model.ItemNames;
import com.petrpol.ageofempires2leaderboard.data.model.LastMatch;
import com.petrpol.ageofempires2leaderboard.data.model.Player;
import com.petrpol.ageofempires2leaderboard.data.repositories.ItemNamesRepository;
import com.petrpol.ageofempires2leaderboard.data.repositories.ItemNamesRepositoryInterface;
import com.petrpol.ageofempires2leaderboard.data.repositories.LastMatchRepository;
import com.petrpol.ageofempires2leaderboard.data.repositories.PlayersRepository;
import com.petrpol.ageofempires2leaderboard.data.repositories.RepositoryInterface;

import java.util.List;

/** View model for last match fragment */
public class LastMatchViewModel extends ViewModel implements RepositoryInterface, ItemNamesRepositoryInterface {

    MutableLiveData<LastMatch> lastMatch = new MutableLiveData<>();
    MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    LiveData<ItemNames> itemNames;

    int playerId=-1;

    LastMatchRepository lastMatchRepository;
    ItemNamesRepository itemNamesRepository;

    /** Default constructor */
    public LastMatchViewModel() {
        //Default values
        errorMessage.setValue(null);
        isDataLoading.setValue(true);

        //Create repositories
        lastMatchRepository = LastMatchRepository.getInstance();
        itemNamesRepository = ItemNamesRepository.getInstance();

        //Get Item names
        itemNames = itemNamesRepository.getItemNames();
        itemNamesRepository.refreshItemNames(this);
    }

    /** Sets player id to view model */
    public void setPlayerId(int playerId){
        this.playerId = playerId;
    }

    /** Refresh last match and item names data from server */
    public void refreshData(){
        if (playerId!=-1) {
            isDataLoading.postValue(true);
            itemNamesRepository.refreshItemNames(this);
            lastMatchRepository.getLastMatch(playerId, lastMatch, this);
        }
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
    public LiveData<LastMatch> getLastMatch() {
        return lastMatch;
    }

    public LiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<ItemNames> getItemNames() {
        return itemNames;
    }
}