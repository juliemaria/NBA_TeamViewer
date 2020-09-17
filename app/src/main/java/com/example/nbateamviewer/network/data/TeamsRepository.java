package com.example.nbateamviewer.network.data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.nbateamviewer.utils.Constants;
import com.example.nbateamviewer.NbaApplication;
import com.example.nbateamviewer.network.ApiServiceInterface;
import com.example.nbateamviewer.model.TeamRepositoryModel;
import com.example.nbateamviewer.model.Teams;
import com.example.nbateamviewer.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamsRepository {
    private static TeamsRepository teamsRepository;
    final MutableLiveData<TeamRepositoryModel> teamsList = new MutableLiveData<>();
    private static ApiServiceInterface apiServiceInterface;


    public static TeamsRepository getInstance() {
        if (teamsRepository == null) {
            teamsRepository = new TeamsRepository();
        }
        apiServiceInterface = getApiServiceInterface();
        return teamsRepository;
    }

    public MutableLiveData<TeamRepositoryModel> getAllTeams() {

        apiServiceInterface.getTeamsList().enqueue(new Callback<ArrayList<Teams>>() {
            @Override
            public void onResponse(Call<ArrayList<Teams>> call,
                                   Response<ArrayList<Teams>> response) {
                if (response.isSuccessful()) {
                    teamsList.setValue(new TeamRepositoryModel(response.body()));
                } else {
                    teamsList.setValue(new TeamRepositoryModel(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Teams>> call, Throwable t) {
                teamsList.setValue(new TeamRepositoryModel(t));
            }
        });

        return teamsList;
    }

    private static ApiServiceInterface getApiServiceInterface() {
        Context context = NbaApplication.getAppContext();
        Long cacheSize = Long.valueOf((5 * 1024 * 1024));
        Cache myCache = new Cache(context.getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        request = NetworkUtils.Companion.hasNetwork(context)
                                ? request.newBuilder().header("Cache-Control", "public, max-age=5").build()
                                : request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=604800").build();
                        return chain.proceed(request);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .build();

        return retrofit.create(ApiServiceInterface.class);
    }

}