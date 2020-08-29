package com.example.nbateamviewer.network.viewmodels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nbateamviewer.NbaApplication;
import com.example.nbateamviewer.network.data.TeamsRepository;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.ui.team.TeamDetailNavigator;

import java.util.List;

public class TeamsViewModel extends ViewModel{
    private MutableLiveData<List<Teams>> mutableLiveData;
    private TeamsRepository teamsRepository;
    private TeamDetailNavigator teamDetailNavigator;

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

    public void setTeamDetailNavigator(TeamDetailNavigator teamDetailNavigator) {
        this.teamDetailNavigator = teamDetailNavigator;
    }

    public void teamItemClicked(Teams teams){
        teamDetailNavigator.onTeamItemClicked(teams);
    }

    public void onClick(View view) {
        Toast.makeText(NbaApplication.getAppContext(),"Selected!!!",Toast.LENGTH_LONG).show();
    }

}
