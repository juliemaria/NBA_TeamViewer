package com.example.nbateamviewer.network.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.nbateamviewer.network.model.Players;
import com.example.nbateamviewer.network.model.Teams;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TeamRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void testSuccessfulTeamsResponse() {
        ArrayList<Teams> mockTeams = fetchTeams();

        MutableLiveData<ArrayList<Teams>> mockInput = new MutableLiveData<>();
        mockInput.setValue(mockTeams);

        TeamsRepository repo = new MockTeamRepository(mockInput);
        MutableLiveData<ArrayList<Teams>> mockResult = repo.getAllTeams();
        assertEquals(mockInput, mockResult);
    }

    @Test
    public void testFailedTeamsResponse() {
        ArrayList<Teams> mockTeams = fetchTeams();

        MutableLiveData<ArrayList<Teams>> mockInput = new MutableLiveData<>();
        mockInput.setValue(mockTeams);

        TeamsRepository repo = new FailedMockTeamRepository();
        MutableLiveData<ArrayList<Teams>> mockResult = repo.getAllTeams();
        assertNotEquals(mockInput, mockResult);
        assertEquals(null, mockResult);
    }

    @NotNull
    private ArrayList<Teams> fetchTeams() {
        ArrayList<Teams> mockTeams = new ArrayList<Teams>();
        ArrayList<Players> mockPlayers = new ArrayList<Players>();
        Players mockPlayer1 = new Players(1,"Andrew", "Johnson", "G", 7);
        mockPlayers.add(mockPlayer1);
        Teams mockTeam1 = new Teams(10,20,"Mock Team 1", 1, mockPlayers);
        Teams mockTeam2 = new Teams(20,30,"Mock Team 2", 2, mockPlayers);
        mockTeams.add(mockTeam1);
        mockTeams.add(mockTeam2);
        return mockTeams;
    }

    class MockTeamRepository extends TeamsRepository {
        MutableLiveData<ArrayList<Teams>> teamData;

        public MockTeamRepository(MutableLiveData<ArrayList<Teams>> mockTeams) {
            this.teamData = mockTeams;
        }

        @Override
        public MutableLiveData<ArrayList<Teams>> getAllTeams() {
            return teamData;
        }
    }

    class FailedMockTeamRepository extends TeamsRepository {

        public FailedMockTeamRepository() {

        }

        @Override
        public MutableLiveData<ArrayList<Teams>> getAllTeams() {
            return null;
        }
    }
}
