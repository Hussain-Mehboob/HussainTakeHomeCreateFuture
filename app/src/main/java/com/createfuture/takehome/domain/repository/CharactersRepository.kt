package com.createfuture.takehome.domain.repository

import com.createfuture.takehome.domain.model.ApiCharacter
import com.createfuture.takehome.utils.ApiResult


interface CharactersRepository {
    suspend fun getCharacters(): ApiResult<List<ApiCharacter>>
}