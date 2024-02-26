package com.example.challengekosmosandroidcompose.network

import kotlinx.serialization.Serializable

@Serializable
data class RickAndMortyAPIResponse (
    val info: Info,
    val results: List<Result>
)

@Serializable
data class Info (
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: String? = null
)

@Serializable
data class Result (
    val id: Long,
    val name: String,
    val status: Status? = null,
    val species: Species? = null,
    val type: String,
    val gender: Gender? = null,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)


enum class Gender {
    Female,
    Male,
    Unknown
}

@Serializable
data class Location (
    val name: String,
    val url: String
)

enum class Species {
    Alien,
    Human
}

enum class Status {
    Alive,
    Dead,
    Unknown
}
