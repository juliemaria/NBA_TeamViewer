package com.example.nbateamviewer.network.viewmodels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.nbateamviewer.network.model.Players;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.ui.interfaces.TeamListModifications;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TeamsViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    TeamsViewModel teamsViewModel;

    @Mock
    TeamListModifications teamListModifications;

    @Before
    public void setUpViewModel()
    {

        teamsViewModel = new TeamsViewModel();
        teamListModifications = mock(TeamListModifications.class);
        teamsViewModel.setTeamListModifications(teamListModifications);

        MutableLiveData<ArrayList<Teams>> mockInput = new MutableLiveData<>();
        mockInput.setValue(fetchTeams());

        teamsViewModel.setMutableLiveData(mockInput);
    }

    @Test
    public void testTeamsResponse() {

        teamsViewModel.sortTeamList("TeamNameDesc");

        assertEquals("New Team", teamsViewModel.getMutableLiveData().getValue().get(0).getFull_name());

    }

    @NotNull
    private ArrayList<Teams> fetchTeams() {
        ArrayList<Teams> mockTeams = new ArrayList<Teams>();
        ArrayList<Players> mockPlayers = new ArrayList<Players>();
        Players mockPlayer1 = new Players(1,"Andrew", "Johnson", "G", 7);
        mockPlayers.add(mockPlayer1);
        Teams mockTeam1 = new Teams(10,20,"Team X", 1, mockPlayers);
        Teams mockTeam2 = new Teams(20,30,"New Team", 2, mockPlayers);
        mockTeams.add(mockTeam1);
        mockTeams.add(mockTeam2);
        return mockTeams;
    }

    class MockTeamsViewModel extends TeamsViewModel {
        MutableLiveData<ArrayList<Teams>> teamData = new MutableLiveData<>();

        public MockTeamsViewModel(MutableLiveData<ArrayList<Teams>> mockData){
            this.teamData = mockData;
        }
        @Override
        public LiveData<ArrayList<Teams>> getTeamsRepository() {
            return this.teamData;
        }
    }
}
