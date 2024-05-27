package com.fmollea.domain.model

data class MovieDetail(
    val backdropPath: String,
    val id: Long,
    val posterPath: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
)
