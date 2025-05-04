package com.createfuture.takehome.data.repository

import com.createfuture.takehome.data.mapper.CharactersMapper
import com.createfuture.takehome.data.mapper.mapList
import com.createfuture.takehome.data.model.response.ApiCharacterResponseDto
import com.createfuture.takehome.data.remote.apiservice.AmazonApiService
import com.createfuture.takehome.utils.ApiResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CharactersRepositoryImplTest {

    private lateinit var repository: CharactersRepositoryImpl

    @RelaxedMockK
    private lateinit var amazonApiService: AmazonApiService

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        repository = CharactersRepositoryImpl(amazonApiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCharacters() returns successful response`() = runTest {
        val mockResponse = mockk<Response<List<ApiCharacterResponseDto>>>()
        val mockCharacterList = listOf(ApiCharacterResponseDto("Eddark", "Male", "Nothmen", "In 264 AC", "In 364 AC"))
        val mockApiCharacterList = mockCharacterList.mapList(CharactersMapper())

        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns mockCharacterList
        coEvery { amazonApiService.getCharacters() } returns mockResponse

        val result = repository.getCharacters()

        assertEquals(ApiResult.Success(mockApiCharacterList), result)
    }

    @Test
    fun `getCharacters() returns response body as null`() = runTest {
        val mockResponse = mockk<Response<List<ApiCharacterResponseDto>>>()

        coEvery { mockResponse.code() } returns 200
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns null
        coEvery { amazonApiService.getCharacters() } returns mockResponse

        val result = repository.getCharacters()

        assertTrue(result is ApiResult.Error)
        assertEquals("Response was not successful - ${mockResponse.code()}", (result as ApiResult.Error).exception.message)
    }

    @Test
    fun `getCharacters() returns unsuccessful response`() = runTest {
        val mockResponse = mockk<Response<List<ApiCharacterResponseDto>>>()

        coEvery { mockResponse.isSuccessful } returns false
        coEvery { mockResponse.code() } returns 404
        coEvery { amazonApiService.getCharacters() } returns mockResponse

        val result = repository.getCharacters()

        assertTrue(result is ApiResult.Error)
        assertEquals("Response was not successful - 404", (result as ApiResult.Error).exception.message)
    }

    @Test
    fun `getCharacters() returns exception`() = runTest {
        coEvery { amazonApiService.getCharacters() } throws Exception("Network error")

        val result = repository.getCharacters()

        assertTrue(result is ApiResult.Error)
        assertEquals("Network error", (result as ApiResult.Error).exception.message)
    }
}