package com.example.nbateamviewer.network.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nbateamviewer.network.data.TeamsRepository;
import com.example.nbateamviewer.network.model.Teams;

import java.util.List;

public class TeamsViewModel extends ViewModel {
    private MutableLiveData<List<Teams>> mutableLiveData;
    private TeamsRepository teamsRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        teamsRepository = TeamsRepository.getInstance();
        mutableLiveData = teamsRepository.getAllTeams();

    }

    public LiveData<List<Teams>> getTeamsRepository() {
        return mutableLiveData;
    }
}
