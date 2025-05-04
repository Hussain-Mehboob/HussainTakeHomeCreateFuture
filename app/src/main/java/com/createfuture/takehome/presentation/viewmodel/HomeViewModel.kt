package com.createfuture.takehome.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.createfuture.takehome.domain.model.ApiCharacter
import com.createfuture.takehome.domain.usecase.GetCharactersListUseCase
import com.createfuture.takehome.presentation.common.intent.CharactersIntent
import com.createfuture.takehome.presentation.common.state.CharactersState
import com.createfuture.takehome.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
) : ViewModel() {

    private val _charactersState =
        MutableStateFlow<CharactersState<List<ApiCharacter>>>(CharactersState.Loading)
    val characters = _charactersState.asStateFlow()

    fun handleIntent(intent: CharactersIntent) {
        when (intent) {
            is CharactersIntent.GetCharactersList -> getCharactersList()
        }
    }

    private fun getCharactersList() {
        _charactersState.value = CharactersState.Loading
        viewModelScope.launch {
            when (val result = getCharactersListUseCase()) {
                is ApiResult.Success -> {
                    //Deliberate Delay Added to show the state change
                    delay(3000)
                    _charactersState.value =
                        CharactersState.GetCharactersListSuccess(data = result.data)
                }

                is ApiResult.Error -> {
                    _charactersState.value =
                        CharactersState.Error(result.exception.message ?: "Unknown error")
                }

                ApiResult.Loading -> _charactersState.value = CharactersState.Loading
            }
        }
    }
}