package com.createfuture.takehome.data.mapper

import com.createfuture.takehome.data.model.response.ApiCharacterResponseDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharactersMapperTest {

    private lateinit var charactersMapper: CharactersMapper

    @Before
    fun setUp() {
        charactersMapper = CharactersMapper()
    }

    @Test
    fun `test map() ApiCharacterResponseDto to ApiCharacter`() {
        
        val responseDto = ApiCharacterResponseDto(
            name = "Character 1",
            gender = "Male",
            culture = "Culture",
            born = "Born",
            died = "Died",
            aliases = listOf("Alias 1"),
            tvSeries = listOf("Season 1"),
            playedBy = listOf("Actor 1")
        )

        val result = charactersMapper.map(responseDto)

        assertEquals("Character 1", result.name)
        assertEquals("Male", result.gender)
        assertEquals("Culture", result.culture)
        assertEquals("Born", result.born)
        assertEquals("Died", result.died)
        assertEquals(listOf("Alias 1"), result.aliases)
        assertEquals(listOf("Season 1"), result.tvSeries)
        assertEquals(listOf("Actor 1"), result.playedBy)
    }

    @Test
    fun `test mapList() with valid input`() {
        
        val responseDtoList = listOf(
            ApiCharacterResponseDto("Character 1", "Male", "Culture", "Born", "Died"),
            ApiCharacterResponseDto("Character 2", "Female", "Culture", "Born", "Died")
        )
        
        val result = responseDtoList.mapList(charactersMapper)

        assertEquals(2, result.size)
        assertEquals("Character 1", result[0].name)
        assertEquals("Character 2", result[1].name)
    }

    @Test
    fun `test mapList() with empty list`() {
        
        val responseDtoList = emptyList<ApiCharacterResponseDto>()

        val result = responseDtoList.mapList(charactersMapper)
        
        assertEquals(0, result.size)
    }
}
