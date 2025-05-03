package com.createfuture.takehome.di

import com.createfuture.takehome.data.remote.apiservice.AmazonApiService
import com.createfuture.takehome.data.repository.CharactersRepositoryImpl
import com.createfuture.takehome.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(
        apiService: AmazonApiService
    ): CharactersRepository {
        return CharactersRepositoryImpl(apiService)
    }

}