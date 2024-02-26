package com.example.challengekosmosandroidcompose.network

import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("/api/character/?page=1")
    suspend fun getCharacters(): RickAndMortyAPIResponse
}