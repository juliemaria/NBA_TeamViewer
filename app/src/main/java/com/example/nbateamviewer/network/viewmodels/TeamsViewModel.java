package com.example.nbateamviewer.network.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nbateamviewer.Constants;
import com.example.nbateamviewer.NbaApplication;
import com.example.nbateamviewer.network.data.TeamsRepository;
import com.example.nbateamviewer.network.model.TeamRepositoryModel;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.ui.interfaces.TeamDetailNavigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TeamsViewModel extends ViewModel {

    private MutableLiveData<TeamRepositoryModel> teamsMutableLiveData;
    private TeamsRepository teamsRepository;
    private TeamDetailNavigator teamDetailNavigator;

    public LiveData<TeamRepositoryModel> getTeamsMutableLiveData() {
        return teamsMutableLiveData;
    }

    public void setTeamsMutableLiveData(MutableLiveData<TeamRepositoryModel> teamsMutableLiveData) {
        this.teamsMutableLiveData = teamsMutableLiveData;
    }

   public void init(){
        if (teamsMutableLiveData != null){
            return;
        }
        teamsRepository = TeamsRepository.getInstance(Constants.BASE_URL, NbaApplication.getAppContext());
        teamsMutableLiveData = teamsRepository.getAllTeams();
    }

    public void setTeamDetailNavigator(TeamDetailNavigator teamDetailNavigator) {
        this.teamDetailNavigator = teamDetailNavigator;
    }

    public void teamItemClicked(Teams teams){
        teamDetailNavigator.onTeamItemClicked(teams);
    }

    public void sortTeamList(String sortType) {
        Collections.sort(teamsMutableLiveData.getValue().getTeamsArrayList(), new Comparator<Teams>() {
            public int compare(Teams obj1, Teams obj2) {
                if (sortType.equalsIgnoreCase("TeamNameAsc")) {
                    return obj1.getFull_name().compareToIgnoreCase(obj2.getFull_name());
                } else if (sortType.equalsIgnoreCase("WinsDesc")) {
                    return obj2.getWins().compareTo(obj1.getWins());
                } else if (sortType.equalsIgnoreCase("LossDesc")) {
                    return obj2.getLosses().compareTo(obj1.getLosses());
                } else if (sortType.equalsIgnoreCase("TeamNameDesc")) {
                    return obj2.getFull_name().compareToIgnoreCase(obj1.getFull_name());
                } else if (sortType.equalsIgnoreCase("WinsAsc")) {
                    return obj1.getWins().compareTo(obj2.getWins());
                } else {
                    return obj1.getLosses().compareTo(obj2.getLosses());
                }
            }
        });
    }
}
