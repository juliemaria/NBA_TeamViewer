package com.example.nbateamviewer.network.data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.nbateamviewer.network.ApiServiceInterface;
import com.example.nbateamviewer.network.model.Teams;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsRepository {
    private static TeamsRepository teamsRepository;
    final MutableLiveData<ArrayList<Teams>> teamsList = new MutableLiveData<>();
    private ApiServiceInterface apiServiceInterface;

    public TeamsRepository() {
    }
        public static TeamsRepository getInstance(String baseUrl, Context context){
            if (teamsRepository == null){
                teamsRepository = new TeamsRepository(baseUrl, context);
            }
            return teamsRepository;
        }

    public TeamsRepository(String baseUrl, Context context){
        apiServiceInterface = ApiServiceInterface.Factory.create(baseUrl, context);
    }

        public MutableLiveData<ArrayList<Teams>> getAllTeams(){

            apiServiceInterface.getTeamsList().enqueue(new Callback<ArrayList<Teams>>() {
                @Override
                public void onResponse(Call<ArrayList<Teams>> call,
                                       Response<ArrayList<Teams>> response) {
                    if (response.isSuccessful()){
                        teamsList.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Teams>> call, Throwable t) {
                    teamsList.setValue(null);
                }
            });

            return teamsList;
        }
}