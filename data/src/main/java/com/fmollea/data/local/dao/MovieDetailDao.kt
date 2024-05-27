package com.fmollea.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fmollea.data.local.entities.MovieDetailEntity

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity)

    @Query("SELECT * FROM movies_detail")
    suspend fun getAllMovieDetails(): List<MovieDetailEntity>

    @Delete
    suspend fun deleteMovieDetail(movieDetail: MovieDetailEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM movies_detail WHERE id = :id)")
    suspend fun isMovieDetailSaved(id: Long): Boolean
}
