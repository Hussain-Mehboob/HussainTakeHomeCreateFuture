package com.createfuture.takehome.data.model.response

data class ApiCharacterResponseDto(
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val aliases: List<String>,
    val tvSeries: List<String>,
    val playedBy: List<String>,
)