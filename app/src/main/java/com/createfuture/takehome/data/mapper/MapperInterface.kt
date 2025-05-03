package com.createfuture.takehome.data.mapper

interface Mapper<F, T> {
    fun map(from: F): T
}
