package com.example.nbateamviewer.network.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.nbateamviewer.model.Players;
import com.example.nbateamviewer.model.TeamRepositoryModel;
import com.example.nbateamviewer.model.Teams;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TeamRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @NotNull
    private ArrayList<Teams> fetchTeams() {
        ArrayList<Teams> mockTeams = new ArrayList<Teams>();
        Teams mockTeam1 = new Teams(10,20,"Mock Team 1", 1, new ArrayList<Players>());
        Teams mockTeam2 = new Teams(20,30,"Mock Team 2", 2, new ArrayList<Players>());
        mockTeams.add(mockTeam1);
        mockTeams.add(mockTeam2);
        return mockTeams;
    }


    @Test
    public void testSuccessfulTeamsResponse() {
        MutableLiveData<TeamRepositoryModel> mockInput = new MutableLiveData<>();
        TeamRepositoryModel teamRepositoryModel = new TeamRepositoryModel(fetchTeams());
        mockInput.setValue(teamRepositoryModel);

        TeamsRepository repo = new MockTeamRepository();
        MutableLiveData<TeamRepositoryModel> mockResult = repo.getAllTeams();
        assertEquals(mockInput.getValue().getTeamsArrayList(), mockResult.getValue().getTeamsArrayList());
    }

    class MockTeamRepository extends TeamsRepository {
        MutableLiveData<TeamRepositoryModel> teamData;

        public MockTeamRepository() {
            super();
        }

        @Override
        public MutableLiveData<TeamRepositoryModel> getAllTeams() {
            teamData = new MutableLiveData<>();
            TeamRepositoryModel teamRepositoryModel = new TeamRepositoryModel(fetchTeams());
            teamData.setValue(teamRepositoryModel);
            return teamData;
        }
    }


   @Test
    public void testFailedTeamsResponse() {
        ArrayList<Teams> mockTeams = fetchTeams();

        MutableLiveData<ArrayList<Teams>> mockInput = new MutableLiveData<>();
        mockInput.setValue(mockTeams);

        TeamsRepository repo = new FailedMockTeamRepository();
        MutableLiveData<TeamRepositoryModel> mockResult = repo.getAllTeams();
        assertNotEquals(mockInput, mockResult);
        assertEquals(null, mockResult);
    }

    class FailedMockTeamRepository extends TeamsRepository {

        public FailedMockTeamRepository() {
            super();
        }

        @Override
        public MutableLiveData<TeamRepositoryModel> getAllTeams() {
            return null;
        }
    }


}
