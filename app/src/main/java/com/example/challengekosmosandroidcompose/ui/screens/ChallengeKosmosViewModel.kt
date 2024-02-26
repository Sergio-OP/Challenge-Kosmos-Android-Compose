package com.example.challengekosmosandroidcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.challengekosmosandroidcompose.ChallengeKosmosApplication
import com.example.challengekosmosandroidcompose.data.ChallengeKosmosRepository
import com.example.challengekosmosandroidcompose.domain.entities.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChallengeKosmosUiState(
    val characters: List<Character> = emptyList()
)

class ChallengeKosmosViewModel(
    private val challengeKosmosRepository: ChallengeKosmosRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ChallengeKosmosUiState())
    val uiState: StateFlow<ChallengeKosmosUiState> = _uiState.asStateFlow()

    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter.asStateFlow()

    init {
        getCharacters()
    }

    fun onDetailsClicked(character: Character) {
        _uiState.update { currentState ->
            val updatedCharacters = currentState.characters.map { existingCharacter ->
                if (existingCharacter == character) {
                    existingCharacter.copy(details = !existingCharacter.details)
                } else {
                    existingCharacter
                }
            }
            currentState.copy(updatedCharacters)
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val characters = challengeKosmosRepository.getCharacters()
            _uiState.update {
                it.copy(
                    characters = characters
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ChallengeKosmosApplication)
                val challengeKosmosRepository = application.container.challengeKosmosRepository
                ChallengeKosmosViewModel(challengeKosmosRepository = challengeKosmosRepository)
            }
        }
    }
}