package com.example.challengekosmosandroidcompose

import android.app.Application
import com.example.challengekosmosandroidcompose.data.AppContainer
import com.example.challengekosmosandroidcompose.data.DefaultAppContainer

class ChallengeKosmosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}