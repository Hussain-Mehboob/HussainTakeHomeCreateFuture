package com.createfuture.takehome.domain.model

data class ApiCharacter(
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val aliases: List<String> = mutableListOf(),
    val tvSeries: List<String> = mutableListOf(),
    val playedBy: List<String> = mutableListOf(),
) {
    val seasons: String = getSeasons(this)
}

fun getSeasons(character: ApiCharacter): String {
    return character.tvSeries.joinToString {
        when (it) {
            "Season 1" -> "I"
            "Season 2" -> "II"
            "Season 3" -> "III"
            "Season 4" -> "IV"
            "Season 5" -> "V"
            "Season 6" -> "VI"
            "Season 7" -> "VII"
            "Season 8" -> "VIII"
            else -> ""
        }
    }
}