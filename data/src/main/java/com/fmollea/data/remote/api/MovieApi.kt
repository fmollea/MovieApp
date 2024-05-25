package com.fmollea.data.remote.api

import com.fmollea.data.remote.model.MovieListResponse
import com.fmollea.data.remote.model.MovieResponse
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getMovies(): MovieListResponse
}
