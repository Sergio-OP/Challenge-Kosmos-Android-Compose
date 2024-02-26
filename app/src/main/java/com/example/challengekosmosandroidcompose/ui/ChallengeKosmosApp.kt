@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.challengekosmosandroidcompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.challengekosmosandroidcompose.R
import com.example.challengekosmosandroidcompose.ui.screens.ChallengeKosmosViewModel
import com.example.challengekosmosandroidcompose.ui.screens.MainScreen

@Composable
fun ChallengeKosmosApp() {
    val viewModel: ChallengeKosmosViewModel = viewModel(factory = ChallengeKosmosViewModel.Factory)
    val uiState = viewModel.uiState.collectAsState()
    val onDetailsClicked = viewModel::onDetailsClicked
    Scaffold(
        topBar = { KosmosTopBar() },
    ) { paddingValues ->
        MainScreen(
            uiState = uiState.value,
            onDetailsClicked = onDetailsClicked,
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}

@Composable
fun KosmosTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier
    )
}
