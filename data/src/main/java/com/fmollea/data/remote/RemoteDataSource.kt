package com.fmollea.data.remote

import com.fmollea.data.remote.api.MovieApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: MovieApi) {
    suspend fun getMovies() = api.getMovies()
}
