package com.fmollea.data.remote.api

import com.fmollea.data.remote.model.GenresListResponse
import com.fmollea.data.remote.model.MovieListResponse
import com.fmollea.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface MovieApi{
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): MovieListResponse
    @GET("genre/movie/list")
    suspend fun getGenresMovies(@Query("api_key") apiKey: String): GenresListResponse
}
