package com.fmollea.data.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.fmollea.data.local.dao.GenreDao
import com.fmollea.data.local.dao.MovieDao
import com.fmollea.data.local.dao.MovieDetailDao
import com.fmollea.data.local.database.AppDataBase
import com.fmollea.data.local.entities.GenreEntity
import com.fmollea.data.local.entities.MovieDetailEntity
import com.fmollea.data.local.entities.MovieEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dataBase: AppDataBase,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val movieDetailDao: MovieDetailDao
) {
    fun getMovies(): PagingSource<Int, MovieEntity> = movieDao.getMovies()
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun clearAndInsertMovies(movies: List<MovieEntity>) {
        dataBase.withTransaction {
            movieDao.clearMovies()
            movieDao.insertMovies(movies)
        }
    }
    fun getGenres(): List<GenreEntity> = genreDao.getGenres()
    suspend fun insertGenres(genres: List<GenreEntity>) = genreDao.insertGenres(genres)

    suspend fun insertMovieDetail(movieDetailEntity: MovieDetailEntity) =
        movieDetailDao.insertMovieDetail(movieDetailEntity)

    suspend fun deleteMovieDetail(movieDetailEntity: MovieDetailEntity) =
        movieDetailDao.deleteMovieDetail(movieDetailEntity)

    suspend fun getAllMovieDetail() = movieDetailDao.getAllMovieDetails()

    suspend fun isMovieDetailSaved(movieDetailId: Long) =
        movieDetailDao.isMovieDetailSaved(movieDetailId)
}
