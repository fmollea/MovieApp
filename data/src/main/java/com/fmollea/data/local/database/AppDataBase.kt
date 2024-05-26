package com.fmollea.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fmollea.data.local.dao.MovieDao
import com.fmollea.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
