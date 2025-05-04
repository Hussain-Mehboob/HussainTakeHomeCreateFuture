package com.createfuture.takehome.data.mapper

import com.createfuture.takehome.data.model.response.ApiCharacterResponseDto
import com.createfuture.takehome.domain.model.ApiCharacter

class CharactersMapper : Mapper<ApiCharacterResponseDto, ApiCharacter> {
    override fun map(from: ApiCharacterResponseDto): ApiCharacter {
        return ApiCharacter(
            name = from.name,
            gender = from.gender,
            culture = from.culture,
            born = from.born,
            died = from.died,
            aliases = from.aliases,
            tvSeries = from.tvSeries,
            playedBy = from.playedBy,
        )
    }
}

fun <ApiCharacterResponse, ApiCharacter> List<ApiCharacterResponse>.mapList(mapper: Mapper<ApiCharacterResponse, ApiCharacter>): List<ApiCharacter> {
    return this.map { mapper.map(it) }
}
