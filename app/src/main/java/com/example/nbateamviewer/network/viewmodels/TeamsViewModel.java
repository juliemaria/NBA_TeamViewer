package com.example.nbateamviewer.network.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nbateamviewer.Constants;
import com.example.nbateamviewer.NbaApplication;
import com.example.nbateamviewer.network.data.TeamsRepository;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.ui.interfaces.TeamDetailNavigator;
import com.example.nbateamviewer.ui.interfaces.TeamListModifications;

import java.util.ArrayList;
import java.util.List;

public class TeamsViewModel extends ViewModel{


    private MutableLiveData<ArrayList<Teams>> mutableLiveData;
    private TeamsRepository teamsRepository;
    private TeamDetailNavigator teamDetailNavigator;
    private TeamListModifications teamListModifications;

    public MutableLiveData<ArrayList<Teams>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<ArrayList<Teams>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

   public void init(){
        if (mutableLiveData != null){
            return;
        }
        teamsRepository = TeamsRepository.getInstance(Constants.BASE_URL, NbaApplication.getAppContext());
        mutableLiveData = teamsRepository.getAllTeams();
    }

    public LiveData<ArrayList<Teams>> getTeamsRepository() {
        return mutableLiveData;
    }

    public void setTeamDetailNavigator(TeamDetailNavigator teamDetailNavigator) {
        this.teamDetailNavigator = teamDetailNavigator;
    }

    public void setTeamListModifications(TeamListModifications teamListModifications) {
        this.teamListModifications = teamListModifications;
    }

    public void teamItemClicked(Teams teams){
        teamDetailNavigator.onTeamItemClicked(teams);
    }

    public void sortTeamList(String sortType) {
       teamListModifications.sortTeamList(sortType);
    }
}
