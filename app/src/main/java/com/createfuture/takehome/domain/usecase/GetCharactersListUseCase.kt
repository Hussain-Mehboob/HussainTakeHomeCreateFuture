package com.createfuture.takehome.domain.usecase

import com.createfuture.takehome.domain.model.ApiCharacter
import com.createfuture.takehome.domain.repository.CharactersRepository
import com.createfuture.takehome.utils.ApiResult
import javax.inject.Inject


class GetCharactersListUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(): ApiResult<List<ApiCharacter>> {
        return repository.getCharacters()
    }
}