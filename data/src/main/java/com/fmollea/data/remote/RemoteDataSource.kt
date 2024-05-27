package com.fmollea.data.remote

import com.fmollea.data.remote.api.MovieApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: MovieApi,
    private val apiKey: String
) {
    suspend fun getMovies(page: Int) = api.getMovies(apiKey = apiKey, page = page)
    suspend fun getGenres() = api.getGenresMovies(apiKey = apiKey)
    suspend fun getMovieDetail(movieId: Int) = api.getMovieDetail(movieId = movieId, apiKey = apiKey)
}
