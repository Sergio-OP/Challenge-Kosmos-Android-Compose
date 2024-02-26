package com.example.challengekosmosandroidcompose.domain.entities

data class Character(
    val name: String,
    val imageSource: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val details: Boolean = false,
)