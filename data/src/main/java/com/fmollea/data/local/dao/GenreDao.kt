package com.fmollea.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fmollea.data.local.entities.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres ORDER BY id ASC")
    fun getGenres(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(movie: List<GenreEntity>)
}
