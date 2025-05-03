package com.createfuture.takehome.data.remote.apiservice

import com.createfuture.takehome.data.model.response.ApiCharacterResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface AmazonApiService {
    @GET("/characters")
    suspend fun getCharacters(): Response<List<ApiCharacterResponseDto>>
}
