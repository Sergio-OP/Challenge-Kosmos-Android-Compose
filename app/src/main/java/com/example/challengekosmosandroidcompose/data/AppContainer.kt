package com.example.challengekosmosandroidcompose.data

import com.example.challengekosmosandroidcompose.network.RickAndMortyApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val challengeKosmosRepository: ChallengeKosmosRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://rickandmortyapi.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
    override val challengeKosmosRepository: ChallengeKosmosRepository by lazy {
        NetworkRickAndMortyCharactersRepository(retrofitService)
    }

}