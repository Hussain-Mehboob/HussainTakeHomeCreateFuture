package com.createfuture.takehome.data.repository

import com.createfuture.takehome.data.mapper.CharactersMapper
import com.createfuture.takehome.data.mapper.mapList
import com.createfuture.takehome.data.remote.apiservice.AmazonApiService
import com.createfuture.takehome.domain.model.ApiCharacter
import com.createfuture.takehome.domain.repository.CharactersRepository
import com.createfuture.takehome.utils.ApiResult
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val amazonApiService: AmazonApiService) : CharactersRepository {

    override suspend fun getCharacters(): ApiResult<List<ApiCharacter>> {
        return try {
            val response = amazonApiService.getCharacters()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val characterList = body.mapList(CharactersMapper())
                    return ApiResult.Success(characterList)
                }
            }
            ApiResult.Error(Exception("Response was not successful - ${response.code()}"))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}