package com.fmollea.data.local

import com.fmollea.data.local.dao.GenreDao
import com.fmollea.data.local.dao.MovieDao
import com.fmollea.data.local.entities.GenreEntity
import com.fmollea.data.local.entities.MovieEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao
) {
    suspend fun getMovies(): List<MovieEntity> = movieDao.getMovies()
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)
    suspend fun getGenres(): List<GenreEntity> = genreDao.getGenres()
    suspend fun insertGenres(genres: List<GenreEntity>) = genreDao.insertGenres(genres)
}
