package com.example.nbateamviewer.network.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nbateamviewer.network.data.TeamsRepository;
import com.example.nbateamviewer.network.model.Teams;

public class TeamDetailViewModel extends ViewModel{
    private MutableLiveData<Teams> mutableLiveData;
    private TeamsRepository teamsRepository;

    public void init(int teamId){
        if (mutableLiveData != null){
            return;
        }
        teamsRepository = TeamsRepository.getInstance();
        mutableLiveData = teamsRepository.getTeamDetail(teamId);
    }

    public LiveData<Teams> getTeamsRepository() {
        return mutableLiveData;
    }



}
