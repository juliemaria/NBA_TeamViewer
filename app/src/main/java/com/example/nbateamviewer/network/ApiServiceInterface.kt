package com.example.nbateamviewer.network

import android.content.Context
import com.example.nbateamviewer.network.model.Teams
import com.example.nbateamviewer.utils.NetworkUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("master/input.json")
    fun getTeamsList(): Call<ArrayList<Teams>>


    companion object Factory {
        fun create(baseUrl : String, context: Context): ApiServiceInterface {

            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(context.cacheDir, cacheSize)

            val okHttpClient = OkHttpClient.Builder()
                    .cache(myCache)
                    .addInterceptor { chain ->
                        var request = chain.request()
                        request = if (NetworkUtils.hasNetwork(context)!!)
                            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                        else
                            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                        chain.proceed(request)
                    }
                    .build()

            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}