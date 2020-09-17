package com.example.nbateamviewer.viewmodels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.nbateamviewer.model.Players;
import com.example.nbateamviewer.model.TeamRepositoryModel;
import com.example.nbateamviewer.model.Teams;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TeamsViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    TeamsViewModel teamsViewModel;

    @Before
    public void setUpViewModel()
    {
        teamsViewModel = new TeamsViewModel();
        MutableLiveData<TeamRepositoryModel> mockInput = new MutableLiveData<>();
        TeamRepositoryModel mockTeamRepositoryModel = new TeamRepositoryModel(fetchTeams());
        mockInput.setValue(mockTeamRepositoryModel);
        teamsViewModel.setTeamsMutableLiveData(mockInput);
    }

    @Test
    public void testTeamsSorting() {

        teamsViewModel.sortTeamList("TeamNameDesc");
        assertEquals("Team X", teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList().get(0).getFull_name());

        teamsViewModel.sortTeamList("TeamNameAsc");
        assertEquals("Another Team", teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList().get(0).getFull_name());

        teamsViewModel.sortTeamList("LossDesc");
        assertEquals("New Team", teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList().get(0).getFull_name());

        teamsViewModel.sortTeamList("LossAsc");
        assertEquals("Another Team", teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList().get(0).getFull_name());

        teamsViewModel.sortTeamList("WinsAsc");
        assertEquals("New Team", teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList().get(0).getFull_name());

        teamsViewModel.sortTeamList("WinsDesc");
        assertEquals("Team X", teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList().get(0).getFull_name());

    }

    @NotNull
    private ArrayList<Teams> fetchTeams() {
        ArrayList<Teams> mockTeams = new ArrayList<Teams>();
        ArrayList<Players> mockPlayers = new ArrayList<Players>();
        Players mockPlayer1 = new Players(1,"Andrew", "Johnson", "G", 7);
        mockPlayers.add(mockPlayer1);
        Teams mockTeam1 = new Teams(35,20,"Team X", 1, mockPlayers);
        Teams mockTeam2 = new Teams(20,30,"New Team", 2, mockPlayers);
        Teams mockTeam3 = new Teams(30,10,"Another Team", 3, mockPlayers);
        mockTeams.add(mockTeam1);
        mockTeams.add(mockTeam2);
        mockTeams.add(mockTeam3);
        return mockTeams;
    }

}
