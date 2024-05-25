package com.fmollea.domain.repository

import com.fmollea.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
}
