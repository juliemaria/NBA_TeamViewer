package com.example.nbateamviewer.network

import com.example.nbateamviewer.model.Teams
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("master/input.json")
    fun getTeamsList(): Call<ArrayList<Teams>>
}