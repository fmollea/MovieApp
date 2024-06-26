package com.fmollea.domain.model

data class Movie(
    val id: Long,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val genreId: Int
)
