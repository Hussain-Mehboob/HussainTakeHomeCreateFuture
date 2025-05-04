package com.createfuture.takehome.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.createfuture.takehome.domain.model.ApiCharacter
import com.createfuture.takehome.domain.usecase.GetCharactersListUseCase
import com.createfuture.takehome.presentation.common.intent.CharactersIntent
import com.createfuture.takehome.presentation.common.state.CharactersState
import com.createfuture.takehome.utils.ApiResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    @RelaxedMockK
    private lateinit var getCharactersListUseCase: GetCharactersListUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(getCharactersListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `handleIntent should emit Loading and then Success for GetCharactersList`() = runTest {
        val mockCharacters = listOf(ApiCharacter("Eddark", "Male", "Nothmen", "In 264 AC", "In 364 AC"))
        coEvery { getCharactersListUseCase() } returns ApiResult.Success(mockCharacters)

        viewModel.handleIntent(CharactersIntent.GetCharactersList)

        assertEquals(CharactersState.Loading, viewModel.characters.value)
        advanceUntilIdle()
        assertEquals(CharactersState.GetCharactersListSuccess(mockCharacters), viewModel.characters.value)
    }

    @Test
    fun `handleIntent should emit Error when use case fails for GetCharactersList`() = runTest {
        val errorMessage = "Error fetching characters"
        coEvery { getCharactersListUseCase() } returns ApiResult.Error(Exception(errorMessage))

        viewModel.handleIntent(CharactersIntent.GetCharactersList)

        assertEquals(CharactersState.Loading, viewModel.characters.value)
        advanceUntilIdle()
        assertEquals(CharactersState.Error(errorMessage), viewModel.characters.value)
    }

    @Test
    fun `test loading state for GetCharactersList`() = runTest {
        coEvery { getCharactersListUseCase() } returns ApiResult.Loading

        viewModel.handleIntent(CharactersIntent.GetCharactersList)

        assertEquals(CharactersState.Loading, viewModel.characters.value)
    }
}


