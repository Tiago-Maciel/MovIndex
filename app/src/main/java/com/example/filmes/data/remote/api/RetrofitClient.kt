package com.example.filmes.data.remote.api

import com.example.filmes.core.constants.ApiConstants
import com.example.filmes.core.network.NetworkConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(NetworkConfig.okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApiService: MovieApiService = retrofit.create(MovieApiService::class.java)
}
