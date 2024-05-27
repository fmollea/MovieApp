package com.fmollea.domain.repository

import androidx.paging.PagingData
import com.fmollea.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getGenres(): HashMap<Int, String>
}
