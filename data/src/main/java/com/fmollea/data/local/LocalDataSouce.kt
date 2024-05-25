package com.fmollea.data.local

import com.fmollea.data.local.dao.MovieDao
import com.fmollea.data.local.entities.MovieEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    suspend fun getMovies(): List<MovieEntity> = movieDao.getMovies()
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)
}
