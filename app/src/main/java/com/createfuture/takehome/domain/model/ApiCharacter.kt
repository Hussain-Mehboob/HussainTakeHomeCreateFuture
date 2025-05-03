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
)