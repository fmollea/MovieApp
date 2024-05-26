package com.fmollea.data.remote

import com.fmollea.data.remote.api.MovieApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: MovieApi,
    private val apiKey: String
) {
    suspend fun getMovies() = api.getMovies(apiKey)
}
