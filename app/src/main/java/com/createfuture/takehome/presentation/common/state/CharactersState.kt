package com.createfuture.takehome.presentation.common.state

sealed class CharactersState<out R> {
    data class GetCharactersListSuccess<out T>(val data: T) : CharactersState<T>()
    data class Error(val error: String) : CharactersState<Nothing>()
    object Loading : CharactersState<Nothing>()
}