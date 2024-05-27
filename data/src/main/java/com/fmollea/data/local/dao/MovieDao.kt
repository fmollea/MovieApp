package com.fmollea.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fmollea.data.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}
