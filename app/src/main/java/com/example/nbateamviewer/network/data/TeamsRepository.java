package com.example.nbateamviewer.network.data;

import androidx.lifecycle.MutableLiveData;

import com.example.nbateamviewer.network.ApiServiceInterface;
import com.example.nbateamviewer.network.model.Teams;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsRepository {
    private static TeamsRepository newsRepository;

        public static TeamsRepository getInstance(){
            if (newsRepository == null){
                newsRepository = new TeamsRepository();
            }
            return newsRepository;
        }

        private ApiServiceInterface apiServiceInterface;

        public TeamsRepository(){
            apiServiceInterface = ApiServiceInterface.Factory.create();
        }

        public MutableLiveData<List<Teams>> getAllTeams(){
            final MutableLiveData<List<Teams>> teamsList = new MutableLiveData<>();
            apiServiceInterface.getTeamsList().enqueue(new Callback<List<Teams>>() {
                @Override
                public void onResponse(Call<List<Teams>> call,
                                       Response<List<Teams>> response) {
                    if (response.isSuccessful()){
                        teamsList.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Teams>> call, Throwable t) {
                    teamsList.setValue(null);
                }
            });
            return teamsList;
        }
}