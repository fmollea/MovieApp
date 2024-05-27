package com.fmollea.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fmollea.data.local.dao.GenreDao
import com.fmollea.data.local.dao.MovieDao
import com.fmollea.data.local.dao.MovieDetailDao
import com.fmollea.data.local.entities.GenreEntity
import com.fmollea.data.local.entities.MovieDetailEntity
import com.fmollea.data.local.entities.MovieEntity

@Database(entities = [
        MovieEntity::class,
        GenreEntity::class,
        MovieDetailEntity::class
    ],
    version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
    abstract fun movieDetailDao(): MovieDetailDao
}
