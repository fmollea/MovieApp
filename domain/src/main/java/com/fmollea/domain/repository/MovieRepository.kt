package com.fmollea.domain.repository

import android.adservices.adid.AdId
import androidx.paging.PagingData
import com.fmollea.domain.model.Movie
import com.fmollea.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getGenres(): HashMap<Int, String>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
}
