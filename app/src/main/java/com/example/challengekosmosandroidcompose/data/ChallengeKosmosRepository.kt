package com.example.challengekosmosandroidcompose.data

import com.example.challengekosmosandroidcompose.domain.entities.Character
import com.example.challengekosmosandroidcompose.network.RickAndMortyApiService

interface ChallengeKosmosRepository {
    suspend fun getCharacters(): List<Character>
}

class NetworkRickAndMortyCharactersRepository(
    private val rickAndMortyApiService: RickAndMortyApiService
): ChallengeKosmosRepository {
    override suspend fun getCharacters(): List<Character> {
        return rickAndMortyApiService.getCharacters().results.map {
            Character(
                id = it.id,
                name = it.name,
                gender = it.gender?.name,
                imageSource = it.image,
                status = it.status?.name,
                species = it.species?.name,
                type = it.type,
                origin = it.origin.name,
                location = it.location.name,
            )
        }
    }

}